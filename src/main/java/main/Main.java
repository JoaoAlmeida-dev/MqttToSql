package main;

import mqtt.MQTTReader;
import org.eclipse.paho.client.mqttv3.MqttException;
import sql.CulturaDB;

import java.sql.Connection;
import java.sql.SQLException;

import static mqtt.GeneralMqttVariables.*;
import static sql.CulturaDB.insertMedicao;
import static sql.SqlController.connectDb;
import static sql.SqlController.getLatestEntryFromTable;
import static sql.variables.GeneralVariables.*;

public class Main {
    public static void main(String[] args) {
        // MQTTREADER TESTING

        try {
            Connection connection = CulturaDB.getLocalConnection();
            CulturaDB.prepareCulturaDB();
            MQTTReader reader= new MQTTReader(BROKER, CLIENT_ID, PERSISTENCE,connection);
            reader.initialize();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }



        /*
        BACKEND MYSQL TESTING
         */

        /*
        try {
            CulturaDB.prepareCulturaDB();



            Connection localConnection = connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);

            String document3 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:55 GMT, Medicao=33.552906794871795}}";
            insertMedicao(document3,localConnection);

            String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.558906794871795}}";
            insertMedicao(document,localConnection);

            String document5 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=15.558906794871795}}";
            insertMedicao(document5,localConnection);

            String document6 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=12.558906794871795}}";
            insertMedicao(document6,localConnection);

            String document2 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:54 GMT, Medicao=7.552906794871795}}";
            insertMedicao(document2,localConnection);

            String document7 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:54 GMT, Medicao=3.552906794871795}}";
            insertMedicao(document7,localConnection);

            String document4 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=107.552906794871795}}";
            insertMedicao(document4,localConnection);

            localConnection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


         */
    }
}
