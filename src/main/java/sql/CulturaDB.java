package sql;

import util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static sql.SqlController.*;
import static sql.SqlVariables.*;

public class CulturaDB {

    /*
    TODO Melhorar Sp de inserir medicao(Mudar de funçao pa SP)
         Verificar se o investigador está associado à cultura
    */

    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

        prepareCulturaDB();

        Connection localConnection = connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);

        /*
        ResultSet rs = CulturaSP.callSPSelect_Alerta(localConnection,1);
        while(rs.next()){
            for(String collum : TABLE_ALERTA_COLLUMS){
                System.out.println(collum +"="+ rs.getString(collum));

            }
            System.out.println();
        }
         */


        String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.552906794871795}}";
        insertMedicao(document,localConnection);
        String document2 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z2, Sensor=T2, Data=2021-02-25 at 21:42:53 GMT, Medicao=53.552906794871795}}";
        insertMedicao(document2,localConnection);
        String document3 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=L1, Data=2021-02-25 at 21:42:53 GMT, Medicao=-17.552906794871795}}";
        insertMedicao(document3,localConnection);

        localConnection.close();


    }

    public static void prepareCulturaDB() throws SQLException {
        createDb(LOCAL_PATH_MYSQL, ROOTUSERNAME, ROOTPASSWORD, DB_NAME);

        Connection localConnection = connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);
        Connection cloudConnection = connectDb(CLOUD_PATH_DB, CLOUD_USERNAME, CLOUD_PASSWORD);

        dropAllTablesDbCultura(localConnection);

        createAllTablesDbCultura(localConnection,cloudConnection);

        CulturaSP.createAllSP(localConnection);

        createAllRoles(localConnection);

        localConnection.close();
        cloudConnection.close();
    }

    public static Connection getLocalConnection() throws SQLException {
        return connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);
    }

    public static Connection getCloudConnection() throws SQLException {
        return connectDb(CLOUD_PATH_DB,  CLOUD_USERNAME, CLOUD_PASSWORD);
    }

    private static Connection changeLocalOrCloud(boolean isItCloud) throws SQLException {
        if (isItCloud) {
            return connectDb(CLOUD_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);
        } else {
            return connectDb(DB_NAME,  CLOUD_USERNAME, CLOUD_PASSWORD);

        }
    }


    private static void insertZona(Connection localConnection,Connection cloudConnection) throws SQLException {
        ArrayList<ArrayList<Pair>> zonaCloudValues = getAllFromDbTable(cloudConnection, TABLE_ZONA_NAME, new ArrayList<>(Arrays.asList(TABLE_ZONA_COLLUMS)));

        ArrayList<Pair> zonaLocalValues = new ArrayList<>();
        for (ArrayList<Pair> zonaValues : zonaCloudValues) {
            for (Pair zonaValue : zonaValues) {
                if (zonaValue.getA().toString().equals("IdZona"))
                    zonaLocalValues.add(new Pair<>(zonaValue.getA(), Integer.parseInt(zonaValue.getB().toString())));
                else
                    zonaLocalValues.add(new Pair<>(zonaValue.getA(), Double.parseDouble(zonaValue.getB().toString())));
            }
            insertInDbTable(localConnection, TABLE_ZONA_NAME, zonaLocalValues);
            zonaLocalValues = new ArrayList<>();
        }
    }

    private static void insertSensores(Connection localConnection,Connection cloudConnection) throws SQLException {
        ArrayList<ArrayList<Pair>> sensorCloudValues = getAllFromDbTable(cloudConnection, TABLE_SENSOR_NAME, new ArrayList<>(Arrays.asList(sensorCloudColumns)));

        ArrayList<Pair> sensorLocalValues = new ArrayList<>();
        for (ArrayList<Pair> sensorValues : sensorCloudValues) {
            for (Pair sensorValue : sensorValues) {
                switch (sensorValue.getA().toString()) {
                    case "idsensor":
                        sensorLocalValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[2], Integer.parseInt(sensorValue.getB().toString())));
                        break;
                    case "tipo":
                        sensorLocalValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[1], sensorValue.getB()));
                        break;
                    case "limiteinferior":
                        sensorLocalValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[3], Double.parseDouble(sensorValue.getB().toString())));
                        break;
                    case "limitesuperior":
                        sensorLocalValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[4], Double.parseDouble(sensorValue.getB().toString())));
                        break;
                    case "idzona":
                        sensorLocalValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[5], Integer.parseInt(sensorValue.getB().toString())));
                        break;
                    default:
                        break;
                }
            }
            insertInDbTable(localConnection, TABLE_SENSOR_NAME, sensorLocalValues);
            sensorLocalValues = new ArrayList<>();
        }
    }

    public static void dropAllTablesDbCultura(Connection connection) throws SQLException {
        dropTableDb(connection, TABLE_MEDICAO_NAME);
        dropTableDb(connection, TABLE_ALERTA_NAME);
        dropTableDb(connection, TABLE_SENSOR_NAME);
        dropTableDb(connection, TABLE_ZONA_NAME);
        dropTableDb(connection, TABLE_PARAMETROCULTURA_NAME);
        dropTableDb(connection, TABLE_CULTURA_NAME);
        dropTableDb(connection, TABLE_UTILIZADOR_NAME);
    }

    public static void createAllTablesDbCultura(Connection localConnection,Connection cloudConnection) throws SQLException {
        createTableDb(localConnection, TABLE_UTILIZADOR_NAME, TABLE_UTILIZADOR);
        createTableDb(localConnection, TABLE_CULTURA_NAME, TABLE_CULTURA);
        createTableDb(localConnection, TABLE_PARAMETROCULTURA_NAME, TABLE_PARAMETROCULTURA);
        createTableDb(localConnection, TABLE_ZONA_NAME, TABLE_ZONA);
        createTableDb(localConnection, TABLE_SENSOR_NAME, TABLE_SENSOR);
        createTableDb(localConnection, TABLE_ALERTA_NAME, TABLE_ALERTA);
        createTableDb(localConnection, TABLE_MEDICAO_NAME, TABLE_MEDICAO);

        //Add Sensores and Zonas
        insertZona(localConnection,cloudConnection);
        insertSensores(localConnection,cloudConnection);
    }

    private static void createInvestigadorRole (Connection connection) throws SQLException {
        createRole(connection, ROLE_INVESTIGADOR);
        //Select
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"SELECT",TABLE_ZONA_NAME,false);
        //Stored Procedures
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"EXECUTE",SP_INSERIR_PARAMETRO_CULTURA_NAME,true);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"EXECUTE",SP_ALTERAR_PARAMETRO_CULTURA_NAME,true);
        grantPermissionRole(connection, ROLE_INVESTIGADOR,"EXECUTE",SP_ELIMINAR_PARAMETRO_CULTURA_NAME,true);
    }

    private static void createTecnicoRole(Connection connection) throws SQLException {
        createRole(connection, ROLE_TECNICO);
        //Select
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, ROLE_TECNICO,"SELECT",TABLE_ZONA_NAME,false);
    }

    private static void createAdminRole(Connection connection) throws SQLException {
        createRole(connection, ROLE_ADMIN);
        //Select
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, ROLE_ADMIN,"SELECT",TABLE_ZONA_NAME,false);
        //Stored Procedures
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_INSERIR_USER_INVESTIGADOR_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_INSERIR_USER_TECNICO_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_INSERIR_USER_ADMIN_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_INSERIR_USER_MQTTREADER_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_ALTERAR_USER_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_ELIMINAR_USER_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_INSERIR_CULTURA_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_ALTERAR_CULTURA_NAME,true);
        grantPermissionRole(connection, ROLE_ADMIN,"EXECUTE",SP_ELIMINAR_CULTURA_NAME,true);
    }

    private static void createMqttReaderRole(Connection connection) throws SQLException {
        createRole(connection, ROLE_MQTTREADER);
        grantPermissionRole(connection, ROLE_MQTTREADER,"EXECUTE",SP_INSERIR_MEDICAO_NAME,true);
    }

    public static void createAllRoles(Connection connection) throws SQLException {
        createInvestigadorRole(connection);
        createTecnicoRole(connection);
        createAdminRole(connection);
        createMqttReaderRole(connection);
    }
    
    public static void insertMedicao(String medicao, Connection connection) throws SQLException {
        ArrayList<String> values = new ArrayList<>();
        String[] splitData = medicao.split(",");
        String idSensor = "";
        for (String data : splitData) {
            String[] datavalues = data.trim().split("=");
            switch (datavalues[0]) {
                case ZONA: {
                    values.add(String.valueOf(datavalues[1].charAt(1)));
                    break;
                }
                case SENSOR: {
                    ArrayList<Pair> paramValues = new ArrayList<>();
                    paramValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[1], datavalues[1].charAt(0)));
                    paramValues.add(new Pair<>(TABLE_SENSOR_COLLUMS[2], datavalues[1].charAt(1)));
                    idSensor = (String) SqlController.getElementsFromDbTable(connection, TABLE_SENSOR_NAME, TABLE_SENSOR_COLLUMS[0],
                            paramValues);
                    values.add((idSensor));
                    break;
                }
                case DATA: {
                    String dateTime = datavalues[1].replace("T", " ");
                    dateTime = dateTime.replace("Z","");
                    dateTime = dateTime.replace("at ","");
                    dateTime = dateTime.replace(" GM ","");
                    values.add(dateTime);
                    break;
                }
                case MEDICAO: {
                    values.add(datavalues[1].replace("}", ""));
                    break;
                }
                default: {

                }
            }
            /*TODO inserir alerta tbm quando necessário*/
        }
        String[] valuesToArray = new String[values.size()];
        valuesToArray = values.toArray(valuesToArray);
        callStoredProcedure(connection,SP_INSERIR_MEDICAO_NAME, valuesToArray);
    }

    public static String typeOfUser(Connection connection, int userID) throws SQLException {
        String[] column = {"TipoUtilizador"};
        ArrayList<String> result = getElementFromDbTable(connection, TABLE_UTILIZADOR_NAME, column, "IdUtilizador", Integer.toString(userID));
        return result.get(0);
    }

}
