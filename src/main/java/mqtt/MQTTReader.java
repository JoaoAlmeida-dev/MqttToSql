package mqtt;

import config.ConfigData;
import org.eclipse.paho.client.mqttv3.*;
import sql.CulturaDB;
import util.Average;
import util.Pair;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MQTTReader implements MqttCallback{

    private MqttClient sampleClient;
    private MqttConnectOptions connOpts;
    private Connection connection;
    ConfigData data;

    private ArrayList<Pair<Double,Average>> lastMedicoes;
    private ArrayList<Pair<Integer,ArrayList<String>>> lastMedicao;

    public MQTTReader(ConfigData data, String clientID, MqttClientPersistence persistence,Connection connection) throws MqttException {
        this.data = data;
        sampleClient = new MqttClient(this.data.getMqttbroker(), clientID, persistence);
        connOpts = new MqttConnectOptions();
        connOpts.setAutomaticReconnect(true);
        connOpts.setCleanSession(true);
        connOpts.setConnectionTimeout(10);
        this.connection = connection;
        lastMedicoes = new ArrayList<>();
        lastMedicao = new ArrayList<>();
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
        System.out.println("Connecting to broker: " + data.getMqttbroker());
        sampleClient.connect(connOpts);
        System.out.println("Connected");
    }


    private void subscribe() throws MqttException {
        System.out.println("Subscribing to broker: " + data.getMqttbroker());
        sampleClient.setCallback(this);
        sampleClient.subscribe(data.getMqtttopic());

        System.out.println("Subscribed");
    }

    private void unsubscribe() throws MqttException {
        sampleClient.unsubscribe(data.getMqtttopic());
    }


    private void disconnect() throws MqttException {
        sampleClient.disconnect();
        System.out.println("Disconnected");

    }

    public void stop() throws MqttException {
        unsubscribe();
        disconnect();
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
        ArrayList<String> medicaoValues = CulturaDB.insertMedicao(receivedData, this.connection);
        int sensorId = CulturaDB.getSensorId(this.connection,receivedData);
        addCollection(sensorId,medicaoValues);
        handlePredictedValue(sensorId);
    }

    private void addCollectionForAverage(int collection) {
        for (Pair<Double,Average> col : lastMedicoes) {
            if(col.getB().getId() == collection)
                return;
        }
        lastMedicoes.add(new Pair<>(null,new Average(collection)));
    }

    private int indexOfCollectionForAverage(int collection) {
        for (Pair<Double,Average> col : lastMedicoes) {
            if(col.getB().getId() == collection)
                return lastMedicoes.indexOf(col);
        }
        return 0;
    }

    private void addCollection(int collection,ArrayList<String> medicao) {
        for (Pair<Integer, ArrayList<String>> col : lastMedicao) {
            if(col.getA() == collection)
                lastMedicao.set(indexOfCollection(collection),new Pair<>(collection,medicao));
        }
        lastMedicao.add(new Pair<>(collection,medicao));
    }

    private int indexOfCollection (int collection) {
        for (Pair<Integer, ArrayList<String>> col : lastMedicao) {
            if(col.getA() == collection)
                return lastMedicao.indexOf(col);
        }
        return 0;
    }

    public ArrayList<String> getLastMedicaoWithId(Connection connection, int id) throws SQLException {
        if(CulturaDB.didItGoThrough(connection,lastMedicao.get(indexOfCollection(id)).getB()))
            return lastMedicao.get(indexOfCollectionForAverage(id)).getB();
        return new ArrayList<>();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete\n"+iMqttDeliveryToken);
    }

    private void handlePredictedValue(int sensorID) throws SQLException {
        if(getLastMedicaoWithId(this.connection,sensorID).isEmpty()) { return;}

        addCollectionForAverage(sensorID);

        int indexOfCollection = indexOfCollectionForAverage(sensorID);
        //Calculate change percentage of each medicao and adding value to list

        double newLeitura = Double.parseDouble(getLastMedicaoWithId(this.connection,sensorID).get(3));
        if((newLeitura > 0 && newLeitura < 0.01) || (newLeitura == 0)) { newLeitura=0.01; }
        if(newLeitura < 0 && newLeitura > -0.01) { newLeitura=-0.01; }

        if(lastMedicoes.get(indexOfCollection).getB().getSize() > 1){
            lastMedicoes.get(indexOfCollection).getB().putValue((newLeitura - lastMedicoes.get(indexOfCollection).getA()) * 100 / newLeitura);
            if(lastMedicoes.get(indexOfCollection).getB().getSize() >= 10) {
                double predictedValue = (newLeitura + ((lastMedicoes.get(indexOfCollection).getB().getAverage()/100) * newLeitura));

                //Predicted Value is sent back to backend to be decided if an alerta is sent or not
                ArrayList<String> medicaoForPredicted = getLastMedicaoWithId(this.connection,sensorID);
                medicaoForPredicted.add(String.valueOf(predictedValue));
                CulturaDB.checkForAlerta(this.connection,medicaoForPredicted,true);
                System.out.println("Added Predicted: " + predictedValue + "; percentage applied: " + (lastMedicoes.get(indexOfCollection).getB().getAverage()/100) );
            }
        } else if(lastMedicoes.get(indexOfCollection).getA() != null ){
            lastMedicoes.get(indexOfCollection).getB().putValue((lastMedicoes.get(indexOfCollection).getA()-newLeitura) * 100 / newLeitura);
        }
        lastMedicoes.get(indexOfCollection).setA(newLeitura);
    }
}
