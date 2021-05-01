package sql;

import util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static sql.SqlController.connectDb;
import static sql.SqlController.createTableDb;
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

    public static void createAllTablesDbCultura() throws SQLException {
        createTableDb(connection, TABLE_CULTURA_NAME, TABLE_CULTURA);
        createTableDb(connection, TABLE_ALERTA_NAME, TABLE_ALERTA);
        createTableDb(connection, TABLE_MEDICAO_NAME, TABLE_MEDICAO);
        createTableDb(connection, TABLE_PARAMETROCULTURA_NAME, TABLE_PARAMETROCULTURA);
        createTableDb(connection, TABLE_SENSOR_NAME, TABLE_SENSOR);
        createTableDb(connection, TABLE_UTILIZADOR_NAME, TABLE_UTILIZADOR);
        createTableDb(connection, TABLE_ZONA_NAME, TABLE_ZONA);
    }

    public static void insertMedicao(String medicao) throws SQLException {
        ArrayList<Pair<String,String>> values = new ArrayList<>();
        String[] splitData =medicao.split(",");
        for (String data : splitData) {
            String[] datavalues = data.trim().split("=");
            switch (datavalues[0]) {
                case ZONA: {
                    //SqlController.selectElementFromDbTable(connection,SqlVariables.TABLE_ZONA_NAME,);
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[1],datavalues[1]));
                    break;
                }case SENSOR: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[2],datavalues[1]));
                    break;
                }case DATA: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[3],datavalues[1]));
                    break;
                }case MEDICAO: {
                    values.add(new Pair<>(TABLE_MEDICAO_COLLUMS[4],datavalues[1]));
                    break;
                }
                default:{

                }
            }

        }
            SqlController.insertInDbTable(connection,TABLE_MEDICAO_NAME,values);

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
        //selectAllFromDbTable(connection,"user", columns);
        //selectElementFromDbTable(connection,"user",columns,"nickname","teste");
        //getElementFromDbTable(connection,"user",columns,"nickname","teste");

        //createAllTablesDbCultura();
        String document ="Document{{_id=603819de967bf6020c0922c8, Zona=Z1, Sensor=H1, Data=2021-02-25 at 21:42:53 GMT, Medicao=17.552906794871795}}";
        insertMedicao(document);

        // System.out.println("values");
        //System.out.println("columns");
        //deleteFromDbTable(connection,"user","nickname","teste");
        //updateFromDbTable(connection,"user",values,"email","teste");
        //connection.close();
    }
}
