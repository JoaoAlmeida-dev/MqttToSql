package sql;

public class SqlVariables {
    public static final String PATH_DB_USER = "javabase";
    public static final String USERNAME = "sammy";
    public static final String PASSWORD = "password";

    public static final String TABLE_MEDICAO_NAME       = "medicao";
    public static final String[] TABLE_MEDICAO_COLLUMS  = {"IdMedicao", "IdZona", "IdSensor", "Hora", "Leitura"};
    public static final String[] TABLE_MEDICAO          = {
            TABLE_MEDICAO_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE", //IdMedicao
            TABLE_MEDICAO_COLLUMS[1] + " INTEGER NOT NULL", //IdZona
            TABLE_MEDICAO_COLLUMS[2] + " INTEGER NOT NULL", //IdSensor
            TABLE_MEDICAO_COLLUMS[3] + " VARCHAR(100) NOT NULL", //Hora
            TABLE_MEDICAO_COLLUMS[4] + " VARCHAR(100) NOT NULL", //Leitura
            "CONSTRAINT FK3_IdZona FOREIGN KEY (" + TABLE_MEDICAO_COLLUMS[1] + ") REFERENCES zona(" + TABLE_MEDICAO_COLLUMS[1] + ")",
            "CONSTRAINT FK2_IdSensor FOREIGN KEY (" + TABLE_MEDICAO_COLLUMS[2] + ") REFERENCES sensor(" + TABLE_MEDICAO_COLLUMS[2] + ")"};

    public static final String TABLE_PARAMETROCULTURA_NAME = "parametrocultura";
    public static final String[] TABLE_PARAMETROCULTURA_COLLUMS = {"IdParametroCultura", "IdCultura", "MinHumidade", "MaxHumidade", "MinTemperatura", "MaxTemperatura", "MinLuz", "MaxLuz", "DangerZoneMinHumidade", "DangerZoneMaxHumidade", "DangerZoneMinTemperatura", "DangerZoneMaxTemperatura", "DangerZoneMinLuz", "DangerZoneMaxLuz"};
    public static final String[] TABLE_PARAMETROCULTURA = {
            TABLE_PARAMETROCULTURA_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE",  //IdParametroCultura
            TABLE_PARAMETROCULTURA_COLLUMS[1] + " INTEGER NOT NULL", //IdCultura
            TABLE_PARAMETROCULTURA_COLLUMS[2] + " NUMERIC NOT NULL", //MinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[3] + " NUMERIC NOT NULL", //MaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[4] + " NUMERIC NOT NULL", //MinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[5] + " NUMERIC NOT NULL", //MaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[6] + " NUMERIC NOT NULL", //MinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[7] + " NUMERIC NOT NULL", //MaxLuz
            TABLE_PARAMETROCULTURA_COLLUMS[8] + " NUMERIC NOT NULL", //DangerZoneMinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[9] + " NUMERIC NOT NULL", //DangerZoneMaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[10] + " NUMERIC NOT NULL", //DangerZoneMinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[11] + " NUMERIC NOT NULL", //DangerZoneMaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[12] + " NUMERIC NOT NULL", //DangerZoneMinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[13] + " NUMERIC NOT NULL", //DangerZoneMaxLuz
            "CONSTRAINT FK_IdCultura FOREIGN KEY (" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ") REFERENCES cultura(" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ")" +  //IdCUltura
                    ""};

    public static final String TABLE_SENSOR_NAME = "sensor";
    public static final String[] TABLE_SENSOR_NAME_COLLUMS = {"IdSensor", "Tipo", "NumeroTipo" ,"LimiteInferior", "LimiteSuperior", "IdZona",};

    public static final String[] TABLE_SENSOR = {
            TABLE_SENSOR_NAME_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE", //IdSensor
            TABLE_SENSOR_NAME_COLLUMS[1] + " VARCHAR(1) NOT NULL",       //Tipo
            TABLE_SENSOR_NAME_COLLUMS[2] + " INTEGER NOT NULL",       //NumeroTipo
            TABLE_SENSOR_NAME_COLLUMS[3] + " DECIMAL(5,2) NOT NULL",    //LimiteInferior
            TABLE_SENSOR_NAME_COLLUMS[4] + " DECIMAL(5,2) NOT NULL",    //LimiteSuperior
            TABLE_SENSOR_NAME_COLLUMS[5] + " INTEGER NOT NULL",    //IdZona
            "CONSTRAINT FK_IdZona FOREIGN KEY (" + TABLE_SENSOR_NAME_COLLUMS[5] + ") REFERENCES zona(" + TABLE_SENSOR_NAME_COLLUMS[5] + ")" //IdZona
    };
    //tablename:utilizador
    public static final String TABLE_UTILIZADOR_NAME = "utilizador";
    public static final String[] TABLE_UTILIZADOR_COLLUMS = {"IdUtilizador", "NomeInvestigador", "EmailUtilizador", "TipoUtilizador"};
    public static final String[] TABLE_UTILIZADOR = {
            TABLE_UTILIZADOR_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE",  //IdUtilizador
            TABLE_UTILIZADOR_COLLUMS[1] + " VARCHAR(100) NOT NULL",                                  //NomeInvestigador
            TABLE_UTILIZADOR_COLLUMS[2] + " VARCHAR(100) NOT NULL",                                  //EmailUtilizador
            TABLE_UTILIZADOR_COLLUMS[3] + " VARCHAR(100) NOT NULL"                                   //TipoUtilizador
        //    "\"" + TABLE_UTILIZADOR_COLLUMS[4] + "\" TEXT NOT NULL"                                //Password
    };

    public static final String TABLE_ZONA_NAME = "zona";
    /**
     * [0]IdZona
     * [1]Temperatura
     * [2]Name
     * [3]Humidade
     * [4]Luz
     */
    public static final String[] TABLE_ZONA_COLLUMS = {"IdZona", "Temperatura", "Humidade", "Luz"};
    public static final String[] TABLE_ZONA = {
            TABLE_ZONA_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE",
            TABLE_ZONA_COLLUMS[1] + " DECIMAL(5,2) NOT NULL",
            TABLE_ZONA_COLLUMS[2] + " DECIMAL(5,2) NOT NULL",
            TABLE_ZONA_COLLUMS[3] + " DECIMAL(5,2) NOT NULL"
    };
    public static final String TABLE_CULTURA_NAME = "cultura";
    public static final String TABLE_ALERTA_NAME = "alerta";
    private static final String[] TABLE_CULTURA_COLLUMS = {"IdCultura", "NomeCultura", "IdUtilizador", "Estado"};
    public static final String[] TABLE_CULTURA = {
            TABLE_CULTURA_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE",      //IdCultura
            TABLE_CULTURA_COLLUMS[1] + " VARCHAR(50) NOT NULL UNIQUE",                         //NomeCultura
            TABLE_CULTURA_COLLUMS[2] + " INTEGER NOT NULL",                                   //IdUtilizador
            TABLE_CULTURA_COLLUMS[3] + " INTEGER NOT NULL",                                   //Estado
            "CONSTRAINT FK_IdUtilizador FOREIGN KEY (" + TABLE_CULTURA_COLLUMS[2] + ") REFERENCES utilizador(" + TABLE_CULTURA_COLLUMS[2] + ")"
    };
    private static final String[] TABLE_ALERTA_COLLUMS = {"IdAlerta", "IdZona", "IdSensor", "Hora", "Leitura", "TipoAlerta", "Cultura", "Mensagem", "IdUtilizador", "HoraEscrita", "NivelAlerta", "IdParametroCultura"};
    public static final String[] TABLE_ALERTA = {
            TABLE_ALERTA_COLLUMS[0] + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE",     //IdAlerta
            TABLE_ALERTA_COLLUMS[1] + " INTEGER NOT NULL",                                      //IdZona
            TABLE_ALERTA_COLLUMS[2] + " INTEGER NOT NULL",                                      //IdSensor
            TABLE_ALERTA_COLLUMS[3] + " VARCHAR(100) NOT NULL",                                         //Hora
            TABLE_ALERTA_COLLUMS[4] + " VARCHAR(100) NOT NULL",                                         //Leitura
            TABLE_ALERTA_COLLUMS[5] + " VARCHAR(100) NOT NULL",                                         //TipoAlerta
            TABLE_ALERTA_COLLUMS[6] + " VARCHAR(100) NOT NULL",                                         //Cultura
            TABLE_ALERTA_COLLUMS[7] + " VARCHAR(100) NOT NULL",                                         //Mensagem
            TABLE_ALERTA_COLLUMS[8] + " INTEGER NOT NULL",                                      //IdUtilizador
            TABLE_ALERTA_COLLUMS[9] + " VARCHAR(100) NOT NULL UNIQUE",                                  //HoraEscrita
            TABLE_ALERTA_COLLUMS[10] + " VARCHAR(100) NOT NULL",                                        //NivelAlerta
            TABLE_ALERTA_COLLUMS[11] + " INTEGER NOT NULL"                                      //IdParametroCultura
            , "CONSTRAINT FK2_IdZona FOREIGN KEY ( " + TABLE_ALERTA_COLLUMS[1] + ") REFERENCES zona(" + TABLE_ALERTA_COLLUMS[1] + ")"
            , "CONSTRAINT FK_IdSensor FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[2] + ") REFERENCES sensor(" + TABLE_ALERTA_COLLUMS[2] + ")"};

    public static final String USER_ADMIN = "Adm";
    public static final String USER_INVESTIGATOR = "Invest";
    public static final String USER_TECHNIC = "Tec";


}
