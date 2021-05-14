package sql;

import sql.variables.*;
import util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static sql.SqlController.*;
import static sql.variables.GeneralVariables.*;

public class CulturaDB {

    /*
    TODO Verificar se o investigador está associado à cultura
    */

    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

      //  prepareCulturaDB();

        /*
        ResultSet rs = TableAlerta.callSPSelect_Alerta(localConnection,1);
        while(rs.next()){
            for(String collum : TableAlerta.TABLE_ALERTA_COLLUMS){
                System.out.println(collum +"="+ rs.getString(collum));

            }
            System.out.println();
        }
         */

        Connection localConnection = connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD);
        /*
        String document2 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:54 GMT, Medicao=7.552906794871795}}";
        insertMedicao(document2,localConnection);

        String document3 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:55 GMT, Medicao=23.552906794871795}}";
        insertMedicao(document3,localConnection);

        String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.558906794871795}}";
        insertMedicao(document,localConnection);

        String document4 ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=107.552906794871795}}";
        insertMedicao(document4,localConnection);

         */
        localConnection.close();
    }

    public static void prepareCulturaDB() throws SQLException {
        createDb(LOCAL_PATH_MYSQL, ROOTUSERNAME, ROOTPASSWORD, DB_NAME);

        try(Connection localConnection = connectDb(LOCAL_PATH_DB, ROOTUSERNAME, ROOTPASSWORD)){
            dropAllTablesDbCultura(localConnection);
            try{
                Connection cloudConnection = connectDb(CLOUD_PATH_DB, CLOUD_USERNAME, CLOUD_PASSWORD);
                createAllTablesDbCultura(localConnection, cloudConnection);
            }catch (Exception e){
                System.out.println("Cannot connect to cloud, ignoring cloud DB");
                createAllTablesDbCultura(localConnection);
            }
                CulturaSP.createAllSP(localConnection);
                createAllRoles(localConnection);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( " Problems-exiting");
        }
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
        ArrayList<ArrayList<Pair>> zonaCloudValues = getAllFromDbTable(cloudConnection, TableZona.TABLE_ZONA_NAME, new ArrayList<>(Arrays.asList(TableZona.TABLE_ZONA_COLLUMS)));

        ArrayList<Pair> zonaLocalValues = new ArrayList<>();
        for (ArrayList<Pair> zonaValues : zonaCloudValues) {
            for (Pair zonaValue : zonaValues) {
                if (zonaValue.getA().toString().equals("IdZona"))
                    zonaLocalValues.add(new Pair<>(zonaValue.getA(), Integer.parseInt(zonaValue.getB().toString())));
                else
                    zonaLocalValues.add(new Pair<>(zonaValue.getA(), Double.parseDouble(zonaValue.getB().toString())));
            }
            insertInDbTable(localConnection, TableZona.TABLE_ZONA_NAME, zonaLocalValues);
            zonaLocalValues = new ArrayList<>();
        }
    }

    private static void insertSensores(Connection localConnection,Connection cloudConnection) throws SQLException {
        ArrayList<ArrayList<Pair>> sensorCloudValues = getAllFromDbTable(cloudConnection, TableSensor.TABLE_SENSOR_NAME, new ArrayList<>(Arrays.asList(sensorCloudColumns)));

        ArrayList<Pair> sensorLocalValues = new ArrayList<>();
        for (ArrayList<Pair> sensorValues : sensorCloudValues) {
            for (Pair sensorValue : sensorValues) {
                switch (sensorValue.getA().toString()) {
                    case "idsensor":
                        sensorLocalValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[2], Integer.parseInt(sensorValue.getB().toString())));
                        break;
                    case "tipo":
                        sensorLocalValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[1], sensorValue.getB()));
                        break;
                    case "limiteinferior":
                        sensorLocalValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[3], Double.parseDouble(sensorValue.getB().toString())));
                        break;
                    case "limitesuperior":
                        sensorLocalValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[4], Double.parseDouble(sensorValue.getB().toString())));
                        break;
                    case "idzona":
                        sensorLocalValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[5], Integer.parseInt(sensorValue.getB().toString())));
                        break;
                    default:
                        break;
                }
            }
            insertInDbTable(localConnection, TableSensor.TABLE_SENSOR_NAME, sensorLocalValues);
            sensorLocalValues = new ArrayList<>();
        }
    }

    public static void dropAllTablesDbCultura(Connection connection) throws SQLException {
        dropTableDb(connection, TableMedicao.TABLE_MEDICAO_NAME);
        dropTableDb(connection, TableAlerta.TABLE_ALERTA_NAME);
        dropTableDb(connection, TableSensor.TABLE_SENSOR_NAME);
        dropTableDb(connection, TableZona.TABLE_ZONA_NAME);
        dropTableDb(connection, TableParametroCultura.TABLE_PARAMETROCULTURA_NAME);
        dropTableDb(connection, TableCultura.TABLE_CULTURA_NAME);
        dropTableDb(connection, TableUtilizador.TABLE_UTILIZADOR_NAME);
    }

    public static void createAllTablesDbCultura(Connection localConnection,Connection cloudConnection) throws SQLException {
        createAllTablesDbCultura(localConnection);

        //Add Sensores and Zonas
        insertZona(localConnection,cloudConnection);
        insertSensores(localConnection,cloudConnection);
    }
    public static void createAllTablesDbCultura(Connection localConnection) throws SQLException {
        createTableDb(localConnection, TableUtilizador.TABLE_UTILIZADOR_NAME, TableUtilizador.TABLE_UTILIZADOR);
        createTableDb(localConnection, TableZona.TABLE_ZONA_NAME, TableZona.TABLE_ZONA);
        createTableDb(localConnection, TableCultura.TABLE_CULTURA_NAME, TableCultura.TABLE_CULTURA);
        createTableDb(localConnection, TableParametroCultura.TABLE_PARAMETROCULTURA_NAME, TableParametroCultura.TABLE_PARAMETROCULTURA);
        createTableDb(localConnection, TableSensor.TABLE_SENSOR_NAME, TableSensor.TABLE_SENSOR);
        createTableDb(localConnection, TableAlerta.TABLE_ALERTA_NAME, TableAlerta.TABLE_ALERTA);
        createTableDb(localConnection, TableMedicao.TABLE_MEDICAO_NAME, TableMedicao.TABLE_MEDICAO);

        /*Add Sensores and Zonas
        insertZona(localConnection,cloudConnection);
        insertSensores(localConnection,cloudConnection);
        */
    }

    private static void createInvestigadorRole (Connection connection) throws SQLException {
        createRole(connection, TableUtilizador.ROLE_INVESTIGADOR);
        //Select
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableAlerta.TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableCultura.TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableMedicao.TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableParametroCultura.TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableSensor.TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableUtilizador.TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"SELECT", TableZona.TABLE_ZONA_NAME,false);
        //Stored Procedures
        grantPermissionRole(connection, TableUtilizador.ROLE_INVESTIGADOR,"EXECUTE", TableParametroCultura.SP_ALTERAR_PARAMETRO_CULTURA_NAME,true);
    }

    private static void createTecnicoRole(Connection connection) throws SQLException {
        createRole(connection, TableUtilizador.ROLE_TECNICO);
        //Select
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableAlerta.TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableCultura.TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableMedicao.TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableParametroCultura.TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableSensor.TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableUtilizador.TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_TECNICO,"SELECT", TableZona.TABLE_ZONA_NAME,false);
    }

    private static void createAdminRole(Connection connection) throws SQLException {
        createRole(connection, TableUtilizador.ROLE_ADMIN);
        //Select
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableAlerta.TABLE_ALERTA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableCultura.TABLE_CULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableMedicao.TABLE_MEDICAO_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableParametroCultura.TABLE_PARAMETROCULTURA_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableSensor.TABLE_SENSOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableUtilizador.TABLE_UTILIZADOR_NAME,false);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"SELECT", TableZona.TABLE_ZONA_NAME,false);
        //Stored Procedures
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_INSERIR_USER_INVESTIGADOR_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_INSERIR_USER_TECNICO_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_INSERIR_USER_ADMIN_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_INSERIR_USER_MQTTREADER_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_ALTERAR_USER_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableUtilizador.SP_ELIMINAR_USER_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableCultura.SP_INSERIR_CULTURA_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableCultura.SP_ALTERAR_CULTURA_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableCultura.SP_ELIMINAR_CULTURA_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableParametroCultura.SP_INSERIR_PARAMETRO_CULTURA_NAME,true);
        grantPermissionRole(connection, TableUtilizador.ROLE_ADMIN,"EXECUTE", TableParametroCultura.SP_ELIMINAR_PARAMETRO_CULTURA_NAME,true);
    }

    private static void createMqttReaderRole(Connection connection) throws SQLException {
        createRole(connection, TableUtilizador.ROLE_MQTTREADER);
        grantPermissionRole(connection, TableUtilizador.ROLE_MQTTREADER,"EXECUTE", TableMedicao.SP_INSERIR_MEDICAO_NAME,true);
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
                    paramValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[1], datavalues[1].charAt(0)));
                    paramValues.add(new Pair<>(TableSensor.TABLE_SENSOR_COLLUMS[2], datavalues[1].charAt(1)));
                    idSensor = (String) SqlController.getElementsFromDbTable(connection, TableSensor.TABLE_SENSOR_NAME, TableSensor.TABLE_SENSOR_COLLUMS[0],
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
        }
        String[] valuesToArray = new String[values.size()];
        valuesToArray = values.toArray(valuesToArray);
        callStoredProcedure(connection, TableMedicao.SP_INSERIR_MEDICAO_NAME, valuesToArray);
        checkForAlerta(connection, values);
    }

    private static boolean didItGoThrough(Connection connection, ArrayList<String> medicaoValues) throws SQLException {
        ArrayList<Pair> medicaoPairs = new ArrayList<>();
        for(int x=0;x!=medicaoValues.size();x++) {
            if(TableMedicao.TABLE_MEDICAO_COLLUMS[x+1].equals("Leitura"))
                medicaoPairs.add(new Pair(TableMedicao.TABLE_MEDICAO_COLLUMS[x+1],Math.round(Double.parseDouble(medicaoValues.get(x)) * 100.0)/100.0));
            else
                medicaoPairs.add(new Pair(TableMedicao.TABLE_MEDICAO_COLLUMS[x+1],medicaoValues.get(x)));
        }
        String id = (String) getElementsFromDbTable(connection,TableMedicao.TABLE_MEDICAO_NAME,TableMedicao.TABLE_MEDICAO_COLLUMS[0],medicaoPairs);

        if (id.equals(""))
            return false;
        else
           return true;
    }

    private static void checkForAlerta(Connection connection, ArrayList<String> values) throws SQLException {
        if(!didItGoThrough(connection,values)) return;
        ArrayList<String> culturasAffected =
                getElementFromDbTable(connection,TableCultura.TABLE_CULTURA_NAME,new String[]{TableCultura.TABLE_CULTURA_COLLUMS[0]},TableCultura.TABLE_CULTURA_COLLUMS[4],values.get(0));

        for(String cultura:culturasAffected) {
            System.out.println("Cultura: " + cultura);
            String idParametroCultura =
                    (String) getElementFromDbTable(connection,TableParametroCultura.TABLE_PARAMETROCULTURA_NAME
                            ,TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0]
                            ,TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[1],cultura);
            String sensorType =
                    (String) getElementFromDbTable(connection,TableSensor.TABLE_SENSOR_NAME
                            ,TableSensor.TABLE_SENSOR_COLLUMS[1]
                            ,TableSensor.TABLE_SENSOR_COLLUMS[0],values.get(1));

            ArrayList<String> culturaInfo = getElementFromDbTable(connection,TableCultura.TABLE_CULTURA_NAME
                    ,new String[] {TableCultura.TABLE_CULTURA_COLLUMS[1],TableCultura.TABLE_CULTURA_COLLUMS[2]}
                    ,TableCultura.TABLE_CULTURA_COLLUMS[0],cultura);

            String culturaName = culturaInfo.get(0);

            String idUtilizador = culturaInfo.get(1);

            ArrayList<String> valuesForAlerta = new ArrayList<>();
            valuesForAlerta.add(values.get(0));                     //IdZona
            valuesForAlerta.add(values.get(1));                     //IdSensor
            valuesForAlerta.add(values.get(2));                     //Hora
            valuesForAlerta.add(values.get(3));                     //Leitura
            valuesForAlerta.add(sensorType);                        //TipoAlerta
            valuesForAlerta.add(culturaName);                       //Cultura
            valuesForAlerta.add(idUtilizador);                      //IdUtilizador
            valuesForAlerta.add(getCurrentTimeStamp().toString());  //HoraEscrita
            valuesForAlerta.add(idParametroCultura);                //IdParametroCultura

            String[] valuesToArray = new String[values.size()];
            valuesToArray = valuesForAlerta.toArray(valuesToArray);
            callStoredProcedure(connection,TableAlerta.SP_INSERIR_ALERTA_NAME,valuesToArray);
            valuesForAlerta = new ArrayList<>();
        }


    }

    public static String typeOfUser(Connection connection, int userID) throws SQLException {
        String[] column = {"TipoUtilizador"};
        ArrayList<String> result = getElementFromDbTable(connection, TableUtilizador.TABLE_UTILIZADOR_NAME, column, "IdUtilizador", Integer.toString(userID));
        return result.get(0);
    }

}
