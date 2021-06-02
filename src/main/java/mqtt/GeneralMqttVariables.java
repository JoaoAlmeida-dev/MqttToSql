package mqtt;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class GeneralMqttVariables {
    public static final String TOPIC = "Culturas-PISID-G12";
    public static final String CONTENT = "Message from MqttPublishSample";
    public static final int QOS = 2;

    public static final String BROKER = "tcp://broker.mqtt-dashboard.com:1883"; //this is the one
    //public static final String CLIENT_ID = MqttClient.generateClientId();
    public static final String CLIENT_ID = UUID.randomUUID().toString();
    public static final MemoryPersistence PERSISTENCE = new MemoryPersistence();

}
