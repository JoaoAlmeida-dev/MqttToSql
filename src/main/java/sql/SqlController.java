package sql;

import util.Pair;

import java.sql.*;
import java.util.ArrayList;


public class SqlController{

    /**
     * ATTENTION: important to close the connection after using the db
     * */
    public static Connection connectDb(String pathToDb) throws SQLException {
        // SQLite connection string
        String url = "jdbc:sqlite:"+ pathToDb;
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

    public static void createDb(String fileName) {

        String url = "jdbc:sqlite:db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTableDb(Connection connection, String tableName, ArrayList<String> columns) throws SQLException {

        String columnsString = "";
        for(String column: columns){
            columnsString+=column + ",\n";
        }

        String sql = "CREATE TABLE IF NOT EXISTS "+ tableName+ " (\n"
                + columnsString
                + ");";

        executeSQL(connection , sql);
    }



    public static void insertInDbTable(Connection connection, String tableName, ArrayList<Pair> values) throws SQLException {
        String columnsString ="";
        String valuesString ="";
        for(Pair value: values){
            columnsString+=value.getA()+",";
            valuesString+="\""+ value.getB()+"\",";
        }

        columnsString = columnsString.substring(0, columnsString.length()-1);
        valuesString = valuesString.substring(0, valuesString.length()-1);

        String sql  ="INSERT INTO "+ tableName+" ("+columnsString+") VALUES ("+valuesString+")";

        System.out.println(sql);

        executeSQL(connection , sql);


    }

    public static void selectAllFromDbTable(Connection connection, String tableName, ArrayList<String> columns) throws SQLException {
        String sql = "SELECT * FROM "+tableName;

        ResultSet rs = executeQuerry(connection, sql);

        // loop through the result set
        while (rs.next()) {
            for (String column : columns) {
                System.out.println(rs.getString(column));
            }
        }

    }

    private static ResultSet executeQuerry(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void selectElementFromDbTable(Connection connection, String tableName, ArrayList<String> columns, String param, String paramValue) throws SQLException {
        String sql = "SELECT * FROM "+tableName+ " WHERE "+param +" = " +"\""+paramValue+"\"";

        ResultSet rs = executeQuerry(connection, sql);

        while (rs.next()) {
            for(String column: columns){
                System.out.println(rs.getString(column));
            }
        }
    }

    public static ArrayList<String> getElementFromDbTable(Connection connection, String tableName, ArrayList<String> columns, String param, String paramValue) throws SQLException {
        String sql = "SELECT * FROM "+tableName+ " WHERE "+param +" = " +"\""+paramValue+"\"";

        ResultSet rs = executeQuerry(connection, sql);

        ArrayList<String> element = new ArrayList<>();
        while (rs.next()) {
            for(String column: columns){
                element.add(rs.getString(column));
            }
        }

        return element;
    }

    public static void deleteFromDbTable(Connection connection,String table ,String param, String paramValue) throws SQLException {
        String sql = "DELETE FROM " + table+ " WHERE "+param +" = " +"\""+paramValue+"\"";
        executeSQL(connection , sql);
    }

    public static void updateFromDbTable(Connection connection, String table, ArrayList<Pair> valuesToChange, String param, String paramValue) throws SQLException {

        String sql = "UPDATE "+ table+ " SET";
        for(Pair value: valuesToChange){
            sql += " "+value.getA() + " = \"" + value.getB() + "\" ,";
        }
        sql = sql.substring(0, sql.length()-1);
        sql +="WHERE "+param+" = \""+paramValue+"\"";

        executeSQL(connection , sql);
    }

    public static void executeSQL(Connection connection, String stattement) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(stattement);

        System.out.println("Executed "+ stattement);
    }
/*
    public static void SP1(){
        executeSQL("storedprocedure bababoy");
    }
*/

    /**
     For testing purposes only
     */
    public static void main(String[] args) throws SQLException {

    }
}
