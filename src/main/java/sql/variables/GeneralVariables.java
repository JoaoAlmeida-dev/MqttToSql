package sql.variables;

public class GeneralVariables {
    //local
    //TODO ter um programa ou uma funcao que cria a db toda com uns credenciais (root?) e depois usamos o role do mqtt apenas para migrar, a db só será criada uma vez por pc
    /**
     * Create a user in phpMyAdmin with these credentials in order to use this program on windows
     */
    public static final String LOCAL_PATH_MYSQL= "jdbc:mysql://localhost:3306/";
    public static final String DB_NAME = "culturamysql";
    public static final String LOCAL_PATH_DB= LOCAL_PATH_MYSQL+DB_NAME;
    public static final String MQTTUSERNAME = "mqttreader";
    public static final String MQTTPASSWORD = "dHbK5ddPuOLi8f(i";

    public static final String ROOTUSERNAME = "root";
    public static final String ROOTPASSWORD = "";

    //cloud
    public static final String CLOUD_DB_NAME = "sid2021";
    public static final String CLOUD_PATH_DB = "jdbc:mysql://194.210.86.10:3306/"+CLOUD_DB_NAME;
    public static final String CLOUD_USERNAME = "aluno";
    public static final String CLOUD_PASSWORD = "aluno";


    /** Columns for document reading on insert medição*/
    public static final String ZONA            = "Zona";
    /** Columns for document reading on insert medição*/
    public static final String SENSOR          = "Sensor";
    /** Columns for document reading on insert medição*/
    public static final String DATA            = "Data";
    /** Columns for document reading on insert medição*/
    public static final String MEDICAO         = "Medicao";

    //Columns for table Sensor in cloud
    public static final String[] sensorCloudColumns = {"idsensor", "tipo", "limiteinferior", "limitesuperior", "idzona"};

}
