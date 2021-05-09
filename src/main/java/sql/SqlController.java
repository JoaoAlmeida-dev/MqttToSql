package sql;

import util.Pair;

import java.sql.*;
import java.util.ArrayList;


public class SqlController{


    //---------------------------------- Criação, ligação e execução da db ----------------------------------

    /**
     * ATTENTION: important to close the connection after using the db
     * */
    public static Connection connectDb(String dbURL, String username, String password) throws SQLException {

        return DriverManager.getConnection(dbURL, username, password);
    }

    public static void createDb(String dbURL, String username, String password,String dbName) {

        try (Connection conn = connectDb(dbURL, username, password);
             Statement stmt = conn.createStatement();

        ) {
            if (conn != null) {

                String sql = "DROP DATABASE IF EXISTS " + dbName + ";CREATE DATABASE " + dbName;
                stmt.executeUpdate(sql);
                //DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTableDb(Connection connection, String tableName, String[] columns) throws SQLException {

        /** Usage example each line is a element in the ArrayList columns
           CREATE TABLE "USER" (
           "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE
           "nickname" TEXT NOT NULL UNIQUE,
           "email" TEXT NOT NULL UNIQUE,
           "password" TEXT NOT NULL
           );
           */

        //CREATE TABLE visitor (
        //        id int NOT NULL,
        //        activity_id int NOT NULL,
        //        PRIMARY KEY (id),
        //        FOREIGN KEY (activity_id) REFERENCES activity(activity_id)
        //);

        //"SQL> CREATE TABLE cultura
        //"(int IdCultura NOT NULL,
        //"string NomeCultura NOT NULL,
        //"int IdUtilizador NOT NULL,
        //"enum Estado NOT NULL,
        //"PRIMARY KEY (IdCultura),
        //"FOREIGN KEY (IdUtilizador)
        //");

        String columnsString = "";
        for(String column: columns){
            columnsString+=column + ",";
        }

        columnsString = columnsString.substring(0,columnsString.length()-1);
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n "
                + columnsString
                + ");";

        System.out.println(sql);
        executeSQL(connection , sql);
    }

    public static void dropTableDb(Connection connection, String tableName) throws SQLException {
        String sqlDrop = "DROP TABLE IF EXISTS " +  tableName;
        executeSQL(connection,sqlDrop);
    }



    public static void executeSqlDb(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    //---------------------------------- Comandos SQL ----------------------------------


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

    public static ArrayList<ArrayList<Pair>> getAllFromDbTable(Connection connection, String tableName, ArrayList<String> columns) throws SQLException {
        ArrayList<Pair> allEntrylist = new ArrayList<>();

        String sql = "SELECT * FROM "+tableName;
        ResultSet rs = executeQuerry(connection, sql);

        // loop through the result set
        while (rs.next()) {
            for (String column : columns) {
                allEntrylist.add(new Pair<>(column,rs.getString(column)));
            }
        }

        ArrayList<ArrayList<Pair>> entriesList = new ArrayList<>();
        String firstColumn = "";
        ArrayList<Pair> entryValues = new ArrayList<>();
        for (Pair entryValue : allEntrylist) {
            if(entryValue.getA().toString().equals(firstColumn)) {
                entriesList.add(entryValues);
                entryValues = new ArrayList<>();
            }

            if(entryValue.toString().equals(allEntrylist.get(0).toString())) firstColumn = entryValue.getA().toString();

            entryValues.add(new Pair<>(entryValue.getA(),entryValue.getB()));
        }
        entriesList.add(entryValues);
        return entriesList;
    }

    private static ResultSet executeQuerry(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void selectElementFromDbTable(Connection connection, String tableName, String[] columns, String param, String paramValue) throws SQLException {
        String sql = "SELECT * FROM "+tableName+ " WHERE "+param +" = " +"\""+paramValue+"\"";

        ResultSet rs = executeQuerry(connection, sql);

        while (rs.next()) {
            for(String column: columns){
                System.out.println(rs.getString(column));
            }
        }
    }

    public static ArrayList<String> getElementFromDbTable(Connection connection, String tableName, String[] columns, String param, String paramValue) throws SQLException {
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

    public static Object getElementFromDbTable(Connection connection, String tableName, String column, String param, String paramValue) throws SQLException {
        String sql = "SELECT * FROM "+tableName+ " WHERE "+param +" = " +"\""+paramValue+"\"";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        String element = "";
        while (rs.next()) {
            element=rs.getString(column);
        }

        return element;
    }

    public static Object getElementsFromDbTable(Connection connection, String tableName, String column, ArrayList<Pair> paramsValues) throws SQLException {
        String sql = "SELECT * FROM "+tableName+ " WHERE ";

        for(Pair paramValue : paramsValues){
            sql+=paramValue.getA() +" = "+"\""+paramValue.getB()+"\"" +" AND ";
        }

        sql = sql.substring(0, sql.length()-5);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        String element = "";
        while (rs.next()) {
            element=rs.getString(column);
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
    /**
     *  IN input parameter
     *  OUT parameter that is gonna be used after this is done
     *  INOUT parameter that can be changed inside the procedure
     *  @param args List of args example:         {"IN title VARCHAR(45)", "OUT totalValue DOUBLE", "INOUT highPrice DOUBLE"}
     *
     * */
    public static void createStoredProcedure(Connection connection, String procedureName, String statements, String args) throws SQLException {
        String sqlDrop = "DROP PROCEDURE IF EXISTS " +  procedureName;

        String sql =
                "CREATE DEFINER = `root`@`localhost` PROCEDURE " + procedureName + "(" +args +")" +
                "BEGIN " + statements+"; END";

        executeSQL(connection,sqlDrop);

        //executeSQL(connection,"delimiter //");
        executeSQL(connection,sql);
        //executeSQL(connection,"delimiter ;");
    }


    public static void createRole(Connection connection, String roleName) throws SQLException {
        String sqlDrop = "DROP ROLE IF EXISTS " + roleName + ";";
        executeSQL(connection,sqlDrop);
        String sqlCreate = "CREATE ROLE " + roleName + ";";
        executeSQL(connection,sqlCreate);
    }

    public static void grantPermissionRole(Connection connection, String roleName, String operation, String table, boolean isProcedure  ) throws SQLException {
        String sql = "GRANT " + operation;
        if (isProcedure) {
            sql+=" ON PROCEDURE ";
        } else {
            sql+=" ON ";
        }
        sql+= table + " TO \'" + roleName + "\';" ;

        executeSQL(connection,sql);
    }

    public static void executeSQL(Connection connection, String stattement) throws SQLException {
        System.out.println("Trying to execute: "+ stattement);
        Statement statement = connection.createStatement();
        statement.execute(stattement);

        System.out.println("Executed "+ stattement);
    }
}
