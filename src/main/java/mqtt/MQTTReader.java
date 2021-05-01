package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import sql.CulturaDB;
import sql.SqlController;
import sql.SqlVariables;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;

import static mqtt.GeneralMqttVariables.*;

public class MQTTReader implements MqttCallback{

    MqttClient sampleClient;
    MqttConnectOptions connOpts;

    public MQTTReader(String broker, String clientID, MqttClientPersistence persistence) throws MqttException {
        sampleClient = new MqttClient(broker, clientID, persistence);
        connOpts = new MqttConnectOptions();
        connOpts.setAutomaticReconnect(true);
        connOpts.setCleanSession(true);
        connOpts.setConnectionTimeout(10);
    }

    public static void main(String[] args) {
        try {
            MQTTReader reader = new MQTTReader(BROKER, CLIENT_ID, PERSISTENCE);
            reader.connect();
            reader.subscribe();

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

    public void connect() throws MqttException {
        System.out.println("Connecting to broker: " + BROKER);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
    }


    public void subscribe() throws MqttException {
        System.out.println("Subscribing to broker: " + BROKER);
        sampleClient.setCallback(this);
        sampleClient.subscribe(TOPIC);

        System.out.println("Subscribed");
    }

    private void unsubscribe() throws MqttException {
        sampleClient.unsubscribe(TOPIC);
    }


    public void disconnect() throws MqttException {
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
        CulturaDB.insertMedicao(receivedData);
        //System.out.println("Received:"+mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete\n"+iMqttDeliveryToken);

    }
}
