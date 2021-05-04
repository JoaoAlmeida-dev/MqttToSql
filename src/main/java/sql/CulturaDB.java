package sql;

import util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static sql.SqlController.*;
import static sql.SqlVariables.*;

public class CulturaDB {

    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

        dropAllTablesDbCultura();
        createAllTablesDbCultura();
         String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.552906794871795}}";
         insertMedicao(document);
        /*
        String Sp = "CREATE PROCEDURE GetAllMedicoes()\n" +
                "BEGIN\n" +
                "SELECT * FROM medicao;\n" +
                "END";
        executeSQL(connection,Sp);
        */

      //  createSPCriar_Zona(connection);

    }


    private static final String ZONA = "Zona";
    private static final String SENSOR = "Sensor";
    private static final String DATA = "Data";
    private static final String MEDICAO = "Medicao";
    private static Connection connection;
    static {
        try {
            connection = connectDb(PATH_DB_USER);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void insertZona(int[] zona) throws SQLException {
        for(int zonaNumber: zona) {
            ArrayList<Pair> values = new ArrayList<>();
            values.add(new Pair<>(TABLE_ZONA_COLLUMS[0],zonaNumber));
            values.add(new Pair<>(TABLE_ZONA_COLLUMS[1],20));
            values.add(new Pair<>(TABLE_ZONA_COLLUMS[2],20));
            values.add(new Pair<>(TABLE_ZONA_COLLUMS[3],20));
            insertInDbTable(connection,TABLE_ZONA_NAME,values);
        }
    }

    private static void insertSensores(String[] sensor) throws SQLException {
        int id=1;
        for(String sensorName: sensor) {
            ArrayList<Pair> values = new ArrayList<>();
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[0],id));
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[1],sensorName.charAt(0)));
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[2],sensorName.charAt(1)));
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[3],20));
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[4],200));
            values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[5],sensorName.charAt(1)));
            insertInDbTable(connection,TABLE_SENSOR_NAME,values);
            id++;
        }
    }

    public static void dropAllTablesDbCultura() throws SQLException {
        dropTableDb(connection,TABLE_MEDICAO_NAME);
        dropTableDb(connection,TABLE_ALERTA_NAME);
        dropTableDb(connection,TABLE_SENSOR_NAME);
        dropTableDb(connection,TABLE_ZONA_NAME);
        dropTableDb(connection,TABLE_PARAMETROCULTURA_NAME);
        dropTableDb(connection,TABLE_CULTURA_NAME);
        dropTableDb(connection,TABLE_UTILIZADOR_NAME);
    }

    public static void createAllTablesDbCultura() throws SQLException {
        createTableDb(connection, TABLE_UTILIZADOR_NAME, TABLE_UTILIZADOR);
        createTableDb(connection, TABLE_CULTURA_NAME, TABLE_CULTURA);
        createTableDb(connection, TABLE_PARAMETROCULTURA_NAME, TABLE_PARAMETROCULTURA);
        createTableDb(connection, TABLE_ZONA_NAME, TABLE_ZONA);
        createTableDb(connection, TABLE_SENSOR_NAME, TABLE_SENSOR);
        createTableDb(connection, TABLE_ALERTA_NAME, TABLE_ALERTA);
        createTableDb(connection, TABLE_MEDICAO_NAME, TABLE_MEDICAO);

        //Add Sensores and Zonas
        int[] zonas = {1,2};
        insertZona(zonas);

        String[] sensores = {"H1","H2","T1","T2","L1","L2"};
        insertSensores(sensores);
    }
/*TODO verificar que medicao esta entre os valores do sensor
    sacar os valores dos sensores da cloud(RAFAEL)
    roles de users pa cena dos privilegios
    SP temos de muda pa mysql(JOAO)
    */
    public static void insertMedicao(String medicao) throws SQLException {
        ArrayList<Pair> values = new ArrayList<>();
        String[] splitData = medicao.split(",");
        for (String data : splitData) {
            String[] datavalues = data.trim().split("=");
            switch (datavalues[0]) {
                case ZONA: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[1],datavalues[1].charAt(1)));
                    break;
                }case SENSOR: {
                    ArrayList<Pair> paramValues = new ArrayList<>();
                    paramValues.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[1],datavalues[1].charAt(0)));
                    paramValues.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[2],datavalues[1].charAt(1)));
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[2],
                            (String)SqlController.getElementsFromDbTable(connection,TABLE_SENSOR_NAME,TABLE_SENSOR_NAME_COLLUMS[0],
                                    paramValues)));
                    break;
                }case DATA: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[3],datavalues[1]));
                    break;
                }case MEDICAO: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[4],datavalues[1].replace("}","")));
                    break;
                }
                default:{

                }
            }

        }
            SqlController.insertInDbTable(connection,TABLE_MEDICAO_NAME,values);

    }

    public static String typeOfUser(Connection connection, int userID) throws SQLException {
        String[] column = {"TipoUtilizador"};
        ArrayList<String> result = getElementFromDbTable(connection, TABLE_UTILIZADOR_NAME, column, "IdUtilizador", Integer.toString(userID));
        return result.get(0);
    }

    //---------------------------------- SPs ----------------------------------

    //---------------------------------- Zona ----------------------------------

    public static void createSP(Connection connection) throws SQLException {
        createSPCriar_Zona(connection);

    }


    public static void createSPCriar_Zona(Connection connection) throws SQLException {
        /*if(typeOfUser(connection,userID).equals(USER_ADMIN)){
            insertInDbTable(connection, TABLE_ZONA_NAME, values);

        }
        */

        String procedureName = "Criar_Zona";

        String[] args = {"IN sp_Name varchar(100)","IN sp_Temperatura decimal(10,0)","IN sp_Humidade decimal(10,0)","IN sp_Luz decimal(10,0)"} ;
        String statements  ="INSERT INTO " + TABLE_ZONA_NAME + " (Name, Temperatura, Humidade ,Luz) VALUES ( sp_Name, sp_Temperatura, sp_Humidade, sp_Luz)";

        createStoredProcedure(connection, procedureName, statements,args);

    }

    public static void SPAlterar_Zona(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_ZONA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_ZONA_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_ZONA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Zona(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_ZONA_NAME, param, paramValue);
        }
    }

    //---------------------------------- Medição ----------------------------------

    public static void SPCriar_Medicao(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            insertInDbTable(connection, TABLE_MEDICAO_NAME, values);
        }
    }

    public static void SPAlterar_Medicao(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_MEDICAO_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_MEDICAO_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_MEDICAO_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Medicao(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_MEDICAO_NAME, param, paramValue);
        }
    }

    //---------------------------------- Sensor ----------------------------------

    public static void SPCriar_Sensor(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            insertInDbTable(connection, TABLE_SENSOR_NAME, values);
        }
    }

    public static void SPAlterar_Sensor(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_SENSOR_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_SENSOR_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_SENSOR_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Sensor(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_SENSOR_NAME, param, paramValue);
        }
    }

    //---------------------------------- User ----------------------------------

    public static void SPCriar_User(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        //if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
        insertInDbTable(connection,TABLE_UTILIZADOR_NAME,values);
        //}
    }

    public static void SPAlterar_User(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection,TABLE_UTILIZADOR_NAME,columns,param,paramValue);
            result = getElementFromDbTable(connection,TABLE_UTILIZADOR_NAME,columns,param,paramValue);
            updateFromDbTable(connection,TABLE_UTILIZADOR_NAME,values,param,paramValue);
        }
    }

    public static void SPEliminar_User(Connection connection,int userID) throws SQLException {
        //if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
        deleteFromDbTable(connection, TABLE_UTILIZADOR_NAME, "IdUtilizador", String.valueOf(userID));
        //}
    }

    //---------------------------------- Cultura ----------------------------------

    public static void SPCriar_Cultura(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            insertInDbTable(connection, TABLE_CULTURA_NAME, values);
        }
    }

    public static void SPAlterar_Cultura(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_CULTURA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_CULTURA_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_CULTURA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Cultura(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_CULTURA_NAME, param, paramValue);
        }
    }

    //---------------------------------- ParametroCultura ----------------------------------
    //TODO Verificar se o investigador está associado à cultura
    public static void SPCriar_ParametroCultura(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_INVESTIGATOR)) {
            insertInDbTable(connection, TABLE_PARAMETROCULTURA_NAME, values);
        }
    }

    public static void SPAlterar_ParametroCultura(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_INVESTIGATOR)) {
            //selectElementFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_PARAMETROCULTURA_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_ParametroCultura(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_INVESTIGATOR)) {
            deleteFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, param, paramValue);
        }
    }

    //---------------------------------- Alerta ----------------------------------

    public static void SPCriar_Alerta(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            insertInDbTable(connection,TABLE_ALERTA_NAME,values);
        }
    }


    public static void SPAlterar_Alerta(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_ALERTA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_ALERTA_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_ALERTA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Alerta(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection,TABLE_ALERTA_NAME,param,paramValue);
        }
    }



}
