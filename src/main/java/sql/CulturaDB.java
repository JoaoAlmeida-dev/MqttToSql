package sql;

import util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static sql.SqlController.connectDb;
import static sql.SqlController.createTableDb;
import static sql.SqlVariables.*;

public class CulturaDB {

    public static void createAllTablesDbCultura(Connection connection) throws SQLException {
        createTableDb(connection, TABLE_CULTURA_NAME, TABLE_CULTURA);
        createTableDb(connection, TABLE_ALERTA_NAME, TABLE_ALERTA);
        createTableDb(connection, TABLE_MEDICAO_NAME, TABLE_MEDICAO);
        createTableDb(connection, TABLE_PARAMETROCULTURA_NAME, TABLE_PARAMETROCULTURA);
        createTableDb(connection, TABLE_SENSOR_NAME, TABLE_SENSOR);
        createTableDb(connection, TABLE_UTILIZADOR_NAME, TABLE_UTILIZADOR);
        createTableDb(connection, TABLE_ZONA_NAME, TABLE_ZONA);
    }

    /**
     * For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

        Connection connection = connectDb(PATH_DB_USER);
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
        createAllTablesDbCultura(connection);
        //System.out.println("values");
        //System.out.println("columns");
        //deleteFromDbTable(connection,"user","nickname","teste");
        //updateFromDbTable(connection,"user",values,"email","teste");
        //connection.close();
    }
}
