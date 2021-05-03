package sql;

import util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static sql.SqlController.*;
import static sql.SqlVariables.*;

public class CulturaDB {

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

    private static void insertZona(int zona) throws SQLException {
        ArrayList<Pair> values = new ArrayList<>();
        values.add(new Pair<>(TABLE_ZONA_COLLUMS[0],zona));
        values.add(new Pair<>(TABLE_ZONA_COLLUMS[1],20));
        values.add(new Pair<>(TABLE_ZONA_COLLUMS[2],"Z"+zona));
        values.add(new Pair<>(TABLE_ZONA_COLLUMS[3],20));
        values.add(new Pair<>(TABLE_ZONA_COLLUMS[4],20));
        insertInDbTable(connection,TABLE_ZONA_NAME,values);
    }

    private static void insertSensores(String sensor) throws SQLException {
        int id = 0;
        int zona = 0;
        switch (sensor) {
            case "H1":
                id=1;
                zona=1;
                break;
            case "H2":
                id=2;
                zona=2;
                break;
            case "T1":
                id=3;
                zona=1;
                break;
            case "T2":
                id=4;
                zona=2;
                break;
            case "L1":
                id=5;
                zona=1;
                break;
            case "L2":
                id=6;
                zona=2;
                break;
        }
        ArrayList<Pair> values = new ArrayList<>();
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[0],id));
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[1],sensor));
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[2],"sensorType"));
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[3],20));
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[4],200));
        values.add(new Pair<>(TABLE_SENSOR_NAME_COLLUMS[5],zona));
        insertInDbTable(connection,TABLE_SENSOR_NAME,values);
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
        insertZona(1);
        insertZona(2);

        insertSensores("H1");
        insertSensores("H2");
        insertSensores("T1");
        insertSensores("T2");
        insertSensores("L1");
        insertSensores("L2");

    }

    public static void insertMedicao(String medicao) throws SQLException {
        ArrayList<Pair> values = new ArrayList<>();
        String[] splitData = medicao.split(",");
        for (String data : splitData) {
            String[] datavalues = data.trim().split("=");
            switch (datavalues[0]) {
                case ZONA: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[1],
                            (String)SqlController.getElementFromDbTable(connection,TABLE_ZONA_NAME,TABLE_ZONA_COLLUMS[0],
                                    "Name",datavalues[1])));
                    break;
                }case SENSOR: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[2],
                            (String)SqlController.getElementFromDbTable(connection,TABLE_SENSOR_NAME,TABLE_SENSOR_NAME_COLLUMS[0],
                                    "Name",datavalues[1])));
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

    public static void SPCriar_Zona(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)){
            insertInDbTable(connection, TABLE_ZONA_NAME, values);
        }
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



    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

        //Connection connection = connectDb(PATH_DB_USER);
        ArrayList<Pair> values = new ArrayList<>();
        values.add(new Pair<>("IdUtilizador", "INT"));
        values.add(new Pair<>("email", "teste@hotmail.com"));
        values.add(new Pair<>("password", "1234"));
        // insertInDbTable(connection,"user",values);
        ArrayList<String> columns = new ArrayList<>();

        //columns.add("id");
        //columns.add("nickname");
        //columns.add("email");
        //columns.add("password");

        // Zona
        /*values.add(new Pair<>("Temperatura","12"));
        values.add(new Pair<>("Humidade","60"));
        values.add(new Pair<>("Luz","67"));
*/
        // Medicao
       /* values.add(new Pair<>("IdZona","12"));
        values.add(new Pair<>("IdSensor","60"));
        values.add(new Pair<>("Hora","current_time"));
        values.add(new Pair<>("Leitura","67.5"));
*/
        // Utilizador
        values.add(new Pair<>("NomeInvestigador","Joaquim"));
        values.add(new Pair<>("EmailUtilizador","j@hotmail.com"));
        values.add(new Pair<>("TipoUtilizador","Adm"));
        values.add(new Pair<>("Password","aapl"));

        // Sensor
       /* values.add(new Pair<>("Tipo","T"));
        values.add(new Pair<>("LimiteInferior","27.5"));
        values.add(new Pair<>("LimiteSuperior","73.5"));
        values.add(new Pair<>("IdZona","1"));
*/
        // Cultura
        /*values.add(new Pair<>("NomeCultura","C1"));
        values.add(new Pair<>("IdUtilizador","1"));
        values.add(new Pair<>("Estado","0"));
         */

        // ParametroCultura
       /* values.add(new Pair<>("IdCultura","1"));
        values.add(new Pair<>("MinHumidade","27.5"));
        values.add(new Pair<>("MaxHumidade","73.5"));
        values.add(new Pair<>("MinTemperatura","27.5"));
        values.add(new Pair<>("MaxTemperatura","73.5"));
        values.add(new Pair<>("MinLuz","27.5"));
        values.add(new Pair<>("MaxLuz","73.5"));
        values.add(new Pair<>("DangerZoneMinHumidade","27.5"));
        values.add(new Pair<>("DangerZoneMaxHumidade","73.5"));
        values.add(new Pair<>("DangerZoneMinTemeperatura","27.5"));
        values.add(new Pair<>("DangerZoneMaxTemperatura","73.5"));
        values.add(new Pair<>("DangerZoneMinLuz","27.5"));
        values.add(new Pair<>("DangerZoneMaxLuz","73.5"));
*/
        // Alerta
       /* values.add(new Pair<>("IdZona","1"));
        values.add(new Pair<>("IdSensor","1"));
        values.add(new Pair<>("Hora","current_time"));
        values.add(new Pair<>("Leitura","27.5"));
        values.add(new Pair<>("TipoAlerta","T"));
        values.add(new Pair<>("Cultura","C1"));
        values.add(new Pair<>("Mensagem","Cultura Ok"));
        values.add(new Pair<>("IdUtilizador","1"));
        values.add(new Pair<>("HoraEscrita","current_time"));
        values.add(new Pair<>("NivelAlerta","BOM"));
        values.add(new Pair<>("IdParametroCultura","1"));
*/

        // Zona
        /*columns.add("Temperatura");
        columns.add("Humidade");
        columns.add("Luz");
*/
        // Medicao
        /*columns.add("IdZona");
        columns.add("IdSensor");
        columns.add("Hora");
        columns.add("Leitura");
      */
        // Utilizador
        /*columns.add("NomeInvestigador");
        columns.add("EmailUtilizador");
        columns.add("TipoUtilizador");
        columns.add("Password");
*/
        // Sensor
        /*columns.add("Tipo");
        columns.add("LimiteInferior");
        columns.add("LimiteSuperior");
        columns.add("IdZona");
*/
        // Cultura
        /*columns.add("NomeCultura");
        columns.add("IdUtilizador");
        columns.add("Estado");
*/
        // ParametroCultura
       /* columns.add("IdCultura");
        columns.add("MinHumidade");
        columns.add("MaxHumidade");
        columns.add("MinTemperatura");
        columns.add("MaxTemperatura");
        columns.add("MinLuz");
        columns.add("MaxLuz");
        columns.add("DangerZoneMinHumidade");
        columns.add("DangerZoneMaxHumidade");
        columns.add("DangerZoneMinTemeperatura");
        columns.add("DangerZoneMaxTemperatura");
        columns.add("DangerZoneMinLuz");
        columns.add("DangerZoneMaxLuz");
*/
        // Alerta
       /* columns.add("IdZona");
        columns.add("IdSensor");
        columns.add("Hora");
        columns.add("Leitura");
        columns.add("TipoAlerta");
        columns.add("Cultura");
        columns.add("Mensagem");
        columns.add("IdUtilizador");
        columns.add("HoraEscrita");
        columns.add("NivelAlerta");
        columns.add("IdParametroCultura");
*/

        //selectAllFromDbTable(connection,"user", columns);
        //selectElementFromDbTable(connection,"user",columns,"nickname","teste");
        //getElementFromDbTable(connection,"user",columns,"nickname","teste");

        createAllTablesDbCultura();

      //  String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.552906794871795}}";
       // insertMedicao(document);

        //SPCriar_Zona(connection,values,1);
        //SPAlterar_Zona(connection,columns,values,"IdZona","1");
        //SPEliminar_Zona(connection,"IdZona","4");
        //SPCriar_User(connection,values,1);
        //SPEliminar_User(connection,1);

        //System.out.println(typeOfUser(connection,1));
        // System.out.println("values");
        //System.out.println("columns");
        //deleteFromDbTable(connection,"user","nickname","teste");
        //updateFromDbTable(connection,"user",values,"email","teste");
        //connection.close();
    }
}
