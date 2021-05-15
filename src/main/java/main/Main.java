package main;

import mqtt.MQTTReader;
import org.eclipse.paho.client.mqttv3.MqttException;
import sql.CulturaDB;

import java.sql.Connection;
import java.sql.SQLException;

import static mqtt.GeneralMqttVariables.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = CulturaDB.getLocalConnection();
            MQTTReader reader= new MQTTReader(BROKER, CLIENT_ID, PERSISTENCE,connection);
            reader.initialize();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
