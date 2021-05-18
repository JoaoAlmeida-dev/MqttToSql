package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import sql.CulturaDB;
import util.Pair;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static mqtt.GeneralMqttVariables.*;

public class MQTTReader implements MqttCallback{

    private MqttClient sampleClient;
    private MqttConnectOptions connOpts;
    private Connection connection;

    private ArrayList<Pair<Integer,LinkedList<Double>>> lastMedicoes;

    public MQTTReader(String broker, String clientID, MqttClientPersistence persistence,Connection connection) throws MqttException {
        sampleClient = new MqttClient(broker, clientID, persistence);
        connOpts = new MqttConnectOptions();
        connOpts.setAutomaticReconnect(true);
        connOpts.setCleanSession(true);
        connOpts.setConnectionTimeout(10);
        this.connection = connection;
        lastMedicoes = new ArrayList<>();
    }

    public void initialize() {
        try {
            connect();
            subscribe();
            //reader.unsubscribe();
            //reader.disconnect();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    private void connect() throws MqttException {
        System.out.println("Connecting to broker: " + BROKER);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
    }


    private void subscribe() throws MqttException {
        System.out.println("Subscribing to broker: " + BROKER);
        sampleClient.setCallback(this);
        sampleClient.subscribe(TOPIC);

        System.out.println("Subscribed");
    }

    private void unsubscribe() throws MqttException {
        sampleClient.unsubscribe(TOPIC);
    }


    private void disconnect() throws MqttException {
        sampleClient.disconnect();
        System.out.println("Disconnected");

    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("ConectionLost\n"+throwable);

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        byte[] payload = mqttMessage.getPayload();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        String receivedData = (String) objectInputStream.readObject();
        System.out.println("Received:"+ receivedData);
        CulturaDB.insertMedicao(receivedData, this.connection);

        if(CulturaDB.getLastMedicao(this.connection).isEmpty()) { return;}

        int collectionId = Integer.parseInt(CulturaDB.getLastMedicao(this.connection).get(1));
        addCollection(collectionId);

        int indexOfCollection = indexOfCollection(collectionId);
        //Adding value to list
        if(lastMedicoes.size() == 100) { lastMedicoes.get(indexOfCollection).getB().removeFirst(); }
        //TODO MUDAR LAST LEITURA
        double lastMedicao = CulturaDB.getLastLeitura(this.connection);

        //TODO LAST MEDICAO NAO PODE SER 0
        if(lastMedicao==0) { return;}

        if(lastMedicoes.get(indexOfCollection).getB().isEmpty()){ lastMedicoes.get(indexOfCollection).getB().add(lastMedicao); }
        else if(lastMedicoes.get(indexOfCollection).getB().getLast() != lastMedicao && lastMedicao!=0) { lastMedicoes.get(indexOfCollection).getB().add(lastMedicao); }

        if(lastMedicoes.get(indexOfCollection).getB().size()<2) { return; }

        //Calculate change percentage of each medicao
        ArrayList<Double> medicoesPercentages = new ArrayList<>();
        for(int i=0; i<lastMedicoes.get(indexOfCollection).getB().size()-1; i++) {
            double percentageToAdd = (lastMedicoes.get(indexOfCollection).getB().get(i)-lastMedicoes.get(indexOfCollection).getB().get(i+1)) * 100
                    / lastMedicoes.get(indexOfCollection).getB().get(i+1);
            medicoesPercentages.add(percentageToAdd);
        }

        double predictedValue = lastMedicoes.get(indexOfCollection).getB().getLast() + (lastMedicoes.get(indexOfCollection).getB().getLast() * calculateAverage(medicoesPercentages)/100);

        //Predicted Value is sent back to backend to be decided if an alerta is sent or not
        ArrayList<String> medicaoForPredicted = CulturaDB.getLastMedicao(this.connection);
        if(!medicaoForPredicted.isEmpty()) {
            medicaoForPredicted.add(String.valueOf(predictedValue));
            CulturaDB.checkForAlerta(this.connection,medicaoForPredicted,true);
        }

        //System.out.println("Received:"+mqttMessage);
    }

    private void addCollection(int collection) {
        for (Pair col : lastMedicoes) {
            if((int)col.getA() == collection)
                return;
        }
        lastMedicoes.add(new Pair<>(collection,new LinkedList<>()));
    }

    private int indexOfCollection (int collection) {
        for (Pair col : lastMedicoes) {
            if((int)col.getA() == collection)
                return lastMedicoes.indexOf(col);
        }
        return 0;
    }

    private double calculateAverage(List<Double> marks) {
        return marks.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete\n"+iMqttDeliveryToken);
    }
}
