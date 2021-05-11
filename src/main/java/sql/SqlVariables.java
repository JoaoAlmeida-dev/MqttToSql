package sql;

public class SqlVariables {
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
    public static final String ROOTPASSWORD = "pedro124";

    //cloud
    public static final String CLOUD_DB_NAME = "sid2021";
    public static final String CLOUD_PATH_DB = "jdbc:mysql://194.210.86.10:3306/"+CLOUD_DB_NAME;
    public static final String CLOUD_USERNAME = "aluno";
    public static final String CLOUD_PASSWORD = "aluno";

    //<editor-fold desc="TableMedicao">
    public static final String TABLE_MEDICAO_NAME = "medicao";

    /**
     * <p>TABLE_MEDICAO_COLLUMS</p>
     * <ul>
     *     <li>[0]IdMedicao  </li>
     *     <li>[1]IdZona     </li>
     *     <li>[2]IdSensor   </li>
     *     <li>[3]Hora       </li>
     *     <li>[4]Leitura    </li>
     * </ul>
     */
    public static final String[] TABLE_MEDICAO_COLLUMS  = {"IdMedicao", "IdZona", "IdSensor", "Hora", "Leitura"};
    /**
     * <p>TABLE_MEDICAO_COLLUMS_DATATYPES</p>
     * <ul>
     *     <li>[0]INTEGER      -IdMedicao     </li>
     *     <li>[1]INTEGER      -IdZona        </li>
     *     <li>[2]INTEGER      -IdSensor      </li>
     *     <li>[3]TIMESTAMP     -Hora          </li>
     *     <li>[4]NUMERIC (5,2) -Leitura       </li>
     * </ul>
     */
    public static final String[] TABLE_MEDICAO_DATATYPES = {"INTEGER", "INTEGER", "INTEGER", "TIMESTAMP" , "NUMERIC (5,2)"};
    /**
     * <p>TABLE_MEDICAO_COLLUMS_PARAMS</p>
     * <ul>
     *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE - IdMedicao           </li>
     *     <li>[1]NOT NULL - IdZona         </li>
     *     <li>[2]NOT NULL - IdSensor       </li>
     *     <li>[3]NOT NULL - Hora           </li>
     *     <li>[4]NOT NULL - Leitura        </li>
     * </ul>
     */
    public static final String[] TABLE_MEDICAO_PARAMS = {
             "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"   //IdMedicao
            ,"NOT NULL"                                     //IdZona
            ,"NOT NULL"                                     //IdSensor
            ,"NOT NULL"                                     //Hora
            ,"NOT NULL"                                     //Leitura
    };
    public static final String[] TABLE_MEDICAO = {
            TABLE_MEDICAO_COLLUMS[0] + " " + TABLE_MEDICAO_DATATYPES[0] + " " + TABLE_MEDICAO_PARAMS[0] , //IdMedicao
            TABLE_MEDICAO_COLLUMS[1] + " " + TABLE_MEDICAO_DATATYPES[1] + " " + TABLE_MEDICAO_PARAMS[1] , //IdZona
            TABLE_MEDICAO_COLLUMS[2] + " " + TABLE_MEDICAO_DATATYPES[2] + " " + TABLE_MEDICAO_PARAMS[2] , //IdSensor
            TABLE_MEDICAO_COLLUMS[3] + " " + TABLE_MEDICAO_DATATYPES[3] + " " + TABLE_MEDICAO_PARAMS[3] , //Hora
            TABLE_MEDICAO_COLLUMS[4] + " " + TABLE_MEDICAO_DATATYPES[4] + " " + TABLE_MEDICAO_PARAMS[4] , //Leitura
            "CONSTRAINT FK3_IdZona FOREIGN KEY (" + TABLE_MEDICAO_COLLUMS[1] + ") REFERENCES zona(" + TABLE_MEDICAO_COLLUMS[1] + ")",
            "CONSTRAINT FK2_IdSensor FOREIGN KEY (" + TABLE_MEDICAO_COLLUMS[2] + ") REFERENCES sensor(" + TABLE_MEDICAO_COLLUMS[2] + ")"};
    //</editor-fold>

    //<editor-fold desc="TableParametroCultura">
    public static final String TABLE_PARAMETROCULTURA_NAME = "parametrocultura";
    /**
     * <p>TABLE_PARAMETROCULTURA_COLLUMS</p>
     * <ul>
     *     <li>[0]  - IdParametroCultura         </li>
     *     <li>[1]  - IdCultura                  </li>
     *     <li>[2]  - MinHumidade                </li>
     *     <li>[3]  - MaxHumidade                </li>
     *     <li>[4]  - MinTemperatura             </li>
     *     <li>[5]  - MaxTemperatura             </li>
     *     <li>[6]  - MinLuz                     </li>
     *     <li>[7]  - MaxLuz                     </li>
     *     <li>[8]  - DangerZoneMinHumidade      </li>
     *     <li>[9]  - DangerZoneMaxHumidade      </li>
     *     <li>[10] - DangerZoneMinTemperatura   </li>
     *     <li>[11] - DangerZoneMaxTemperatura   </li>
     *     <li>[12] - DangerZoneMinLuz           </li>
     *     <li>[13] - DangerZoneMaxLuz           </li>
     * </ul>
     */

    public static final String[] TABLE_PARAMETROCULTURA_COLLUMS = {
              "IdParametroCultura"
            , "IdCultura"
            , "MinHumidade"
            , "MaxHumidade"
            , "MinTemperatura"
            , "MaxTemperatura"
            , "MinLuz"
            , "MaxLuz"
            , "DangerZoneMinHumidade"
            , "DangerZoneMaxHumidade"
            , "DangerZoneMinTemperatura"
            , "DangerZoneMaxTemperatura"
            , "DangerZoneMinLuz"
            , "DangerZoneMaxLuz"
    };
    /**
     * <p>TABLE_PARAMETROCULTURA_DATATYPES</p>
     * <ul>
     *     <li>[0]  INTEGER - IdParametroCultura         </li>
     *     <li>[1]  INTEGER - IdCultura                  </li>
     *     <li>[2]  DECIMAL(5,2) - MinHumidade                </li>
     *     <li>[3]  DECIMAL(5,2) - MaxHumidade                </li>
     *     <li>[4]  DECIMAL(5,2) - MinTemperatura             </li>
     *     <li>[5]  DECIMAL(5,2) - MaxTemperatura             </li>
     *     <li>[6]  DECIMAL(5,2) - MinLuz                     </li>
     *     <li>[7]  DECIMAL(5,2) - MaxLuz                     </li>
     *     <li>[8]  DECIMAL(5,2) - DangerZoneMinHumidade      </li>
     *     <li>[9]  DECIMAL(5,2) - DangerZoneMaxHumidade      </li>
     *     <li>[10] DECIMAL(5,2) - DangerZoneMinTemperatura   </li>
     *     <li>[11] DECIMAL(5,2) - DangerZoneMaxTemperatura   </li>
     *     <li>[12] DECIMAL(5,2) - DangerZoneMinLuz           </li>
     *     <li>[13] DECIMAL(5,2) - DangerZoneMaxLuz           </li>
     * </ul>
     */
    public static final String[] TABLE_PARAMETROCULTURA_DATATYPES = {
             "INTEGER"          //IdParametroCultura
            ,"INTEGER"          //IdCultura
            ,"DECIMAL(5,2)"     //MinHumidade
            ,"DECIMAL(5,2)"     //MaxHumidade
            ,"DECIMAL(5,2)"     //MinTemperatura
            ,"DECIMAL(5,2)"     //MaxTemperatura
            ,"DECIMAL(5,2)"     //MinLuz
            ,"DECIMAL(5,2)"     //MaxLuz
            ,"DECIMAL(5,2)"     //DangerZoneMinHumidade
            ,"DECIMAL(5,2)"     //DangerZoneMaxHumidade
            ,"DECIMAL(5,2)"     //DangerZoneMinTemperatura
            ,"DECIMAL(5,2)"     //DangerZoneMaxTemperatura
            ,"DECIMAL(5,2)"     //DangerZoneMinLuz
            ,"DECIMAL(5,2)"     //DangerZoneMaxLuz
    };
    /**
     * <p>TABLE_SENSOR_PARAMS</p>
     * <ul>
     *     <li>[0]  NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE - IdParametroCultura         </li>
     *     <li>[1]  NOT NULL - IdCultura                  </li>
     *     <li>[2]  NOT NULL - MinHumidade                </li>
     *     <li>[3]  NOT NULL - MaxHumidade                </li>
     *     <li>[4]  NOT NULL - MinTemperatura             </li>
     *     <li>[5]  NOT NULL - MaxTemperatura             </li>
     *     <li>[6]  NOT NULL - MinLuz                     </li>
     *     <li>[7]  NOT NULL - MaxLuz                     </li>
     *     <li>[8]  NOT NULL - DangerZoneMinHumidade      </li>
     *     <li>[9]  NOT NULL - DangerZoneMaxHumidade      </li>
     *     <li>[10] NOT NULL - DangerZoneMinTemperatura   </li>
     *     <li>[11] NOT NULL - DangerZoneMaxTemperatura   </li>
     *     <li>[12] NOT NULL - DangerZoneMinLuz           </li>
     *     <li>[13] NOT NULL - DangerZoneMaxLuz           </li>
     * </ul>
     */
    public static final String[] TABLE_PARAMETROCULTURA_PARAMS = {
             "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"          //IdParametroCultura
            ,"NOT NULL"          //IdCultura
            ,"NOT NULL"          //MinHumidade
            ,"NOT NULL"          //MaxHumidade
            ,"NOT NULL"          //MinTemperatura
            ,"NOT NULL"          //MaxTemperatura
            ,"NOT NULL"          //MinLuz
            ,"NOT NULL"          //MaxLuz
            ,"NOT NULL"          //DangerZoneMinHumidade
            ,"NOT NULL"          //DangerZoneMaxHumidade
            ,"NOT NULL"          //DangerZoneMinTemperatura
            ,"NOT NULL"          //DangerZoneMaxTemperatura
            ,"NOT NULL"          //DangerZoneMinLuz
            ,"NOT NULL"          //DangerZoneMaxLuz
    };


    public static final String[] TABLE_PARAMETROCULTURA = {
            TABLE_PARAMETROCULTURA_COLLUMS[0]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[0]  + " " + TABLE_PARAMETROCULTURA_PARAMS[0]                           ,//IdParametroCultura
            TABLE_PARAMETROCULTURA_COLLUMS[1]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[1]  + " " + TABLE_PARAMETROCULTURA_PARAMS[1]                           ,//IdCultura
            TABLE_PARAMETROCULTURA_COLLUMS[2]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[2]  + " " + TABLE_PARAMETROCULTURA_PARAMS[2]                           ,//MinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[3]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[3]  + " " + TABLE_PARAMETROCULTURA_PARAMS[3]                           ,//MaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[4]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[4]  + " " + TABLE_PARAMETROCULTURA_PARAMS[4]                           ,//MinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[5]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[5]  + " " + TABLE_PARAMETROCULTURA_PARAMS[5]                           ,//MaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[6]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[6]  + " " + TABLE_PARAMETROCULTURA_PARAMS[6]                           ,//MinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[7]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[7]  + " " + TABLE_PARAMETROCULTURA_PARAMS[7]                           ,//MaxLuz
            TABLE_PARAMETROCULTURA_COLLUMS[8]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[8]  + " " + TABLE_PARAMETROCULTURA_PARAMS[8]                           ,//DangerZoneMinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[9]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[9]  + " " + TABLE_PARAMETROCULTURA_PARAMS[9]                           ,//DangerZoneMaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[10] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[10] + " " + TABLE_PARAMETROCULTURA_PARAMS[10]                          ,//DangerZoneMinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[11] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[11] + " " + TABLE_PARAMETROCULTURA_PARAMS[11]                          ,//DangerZoneMaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[12] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[12] + " " + TABLE_PARAMETROCULTURA_PARAMS[12]                          ,//DangerZoneMinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[13] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[13] + " " + TABLE_PARAMETROCULTURA_PARAMS[13]                          ,//DangerZoneMaxLuz
            "CONSTRAINT FK_IdCultura FOREIGN KEY (" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ") REFERENCES cultura(" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ")"     //IdCUltura
            };
    //</editor-fold>

    //<editor-fold desc="TableSensor">
    public static final String TABLE_SENSOR_NAME = "sensor";


    // Estamos com dúvidas na coluna NumeroTipo, ela existe para imitar a estrutura do sensor na cloud
    /**
     * <p>TABLE_SENSOR_COLLUMS</p>
     * <ul>
     *     <li>[0]IdSensor       </li>
     *     <li>[1]Tipo           </li>
     *     <li>[2]NumeroTipo     </li>
     *     <li>[3]LimiteInferior </li>
     *     <li>[4]LimiteSuperior </li>
     *     <li>[5]IdZona         </li>
     * </ul>
     */

    public static final String[] TABLE_SENSOR_COLLUMS = {"IdSensor", "Tipo", "NumeroTipo" ,"LimiteInferior", "LimiteSuperior", "IdZona"};
     /**
      * <p>TABLE_SENSOR_DATATYPES</p>
      * <ul>
      *     <li>[0]INTEGER         - IdSensor       </li>
      *     <li>[1]VARCHAR(1)      - Tipo           </li>
      *     <li>[2]INTEGER         - NumeroTipo     </li>
      *     <li>[3]DECIMAL(5,2)    - LimiteInferior </li>
      *     <li>[4]DECIMAL(5,2)    - LimiteSuperior </li>
      *     <li>[5]INTEGER         - IdZona         </li>
      * </ul>
      */
    public static final String[] TABLE_SENSOR_DATATYPES = {
             "INTEGER"          //IdSensor
            ,"VARCHAR(1)"       //Tipo
            ,"INTEGER"          //NumeroTipo
            ,"DECIMAL(5,2)"     //LimiteInferior
            ,"DECIMAL(5,2)"     //LimiteSuperior
            ,"INTEGER"          //IdZona
    };
    /**
     * <p>TABLE_SENSOR_PARAMS</p>
     * <ul>
     *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE    - IdSensor       </li>
     *     <li>[1]DEFAULT '' NOT NULL                           - Tipo           </li>
     *     <li>[2]NOT NULL                                      - NumeroTipo     </li>
     *     <li>[3]DEFAULT '0' NOT NULL                          - LimiteInferior </li>
     *     <li>[4]DEFAULT '0' NOT NULL                          - LimiteSuperior </li>
     *     <li>[5]DEFAULT '1' NOT NULL                          - IdZona         </li>
     * </ul>
     */
    public static final String[] TABLE_SENSOR_PARAMS = {
             "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"   //IdSensor
            ,"DEFAULT '' NOT NULL"                          //Tipo
            ,"NOT NULL"                                     //NumeroTipo
            ,"DEFAULT '0' NOT NULL"                         //LimiteInferior
            ,"DEFAULT '0' NOT NULL"                         //LimiteSuperior
            ,"DEFAULT '1' NOT NULL"                         //IdZona
    };
    public static final String[] TABLE_SENSOR = {
            TABLE_SENSOR_COLLUMS[0] + " " + TABLE_SENSOR_DATATYPES[0] + " " + TABLE_SENSOR_PARAMS[0],  //IdSensor
            TABLE_SENSOR_COLLUMS[1] + " " + TABLE_SENSOR_DATATYPES[1] + " " + TABLE_SENSOR_PARAMS[1],  //Tipo
            TABLE_SENSOR_COLLUMS[2] + " " + TABLE_SENSOR_DATATYPES[2] + " " + TABLE_SENSOR_PARAMS[2],  //NumeroTipo
            TABLE_SENSOR_COLLUMS[3] + " " + TABLE_SENSOR_DATATYPES[3] + " " + TABLE_SENSOR_PARAMS[3],  //LimiteInferior
            TABLE_SENSOR_COLLUMS[4] + " " + TABLE_SENSOR_DATATYPES[4] + " " + TABLE_SENSOR_PARAMS[4],  //LimiteSuperior
            TABLE_SENSOR_COLLUMS[5] + " " + TABLE_SENSOR_DATATYPES[5] + " " + TABLE_SENSOR_PARAMS[5],  //IdZona
            "CONSTRAINT FK_IdZona FOREIGN KEY (" + TABLE_SENSOR_COLLUMS[5] + ") REFERENCES zona(" + TABLE_SENSOR_COLLUMS[5] + ")" //IdZona
    };
    //</editor-fold>

    //<editor-fold desc="TableUtilizador">
    public static final String TABLE_UTILIZADOR_NAME = "utilizador";
    /**
     * <p>TABLE_UTILIZADOR_COLLUMS</p>
     * <ul>
     *     <li>[0]IdUtilizador      </li>
     *     <li>[1]NomeInvestigador  </li>
     *     <li>[2]EmailUtilizador   </li>
     *     <li>[3]Password          </li>
     *     <li>[4]TipoUtilizador    </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_COLLUMS = {"IdUtilizador", "NomeInvestigador", "EmailUtilizador", "Password", "TipoUtilizador"};
    /**
     * <p>TABLE_UTILIZADOR_DATATYPES</p>
     * <ul>
     *     <li>[0]INTEGER -IdUtilizador      </li>
     *     <li>[1]VARCHAR(100) -NomeInvestigador  </li>
     *     <li>[2]VARCHAR(100) -EmailUtilizador   </li>
     *     <li>[3]VARCHAR(100) -Password    </li>
     *     <li>[4]VARCHAR(100) -TipoUtilizador    </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_DATATYPES = {
              "INTEGER"         //IdUtilizador
            , "VARCHAR(100)"    //NomeInvestigador
            , "VARCHAR(100)"    //EmailUtilizador
            , "VARCHAR(100)"    //Password
            , "VARCHAR(100)"    //TipoUtilizador
    };
    /**
     * <p>TABLE_UTILIZADOR_PARAMS</p>
     * <ul>
     *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE -IdUtilizador      </li>
     *     <li>[1]NOT NULL -NomeInvestigador    </li>
     *     <li>[2]NOT NULL -EmailUtilizador     </li>
     *     <li>[3]NOT NULL -Password            </li>
     *     <li>[4]NOT NULL -TipoUtilizador      </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_PARAMS = {
              "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"      //IdUtilizador
            , "NOT NULL"                                        //NomeInvestigador
            , "NOT NULL"                                        //EmailUtilizador
            , "NOT NULL"                                        //Password
            , "NOT NULL"                                        //TipoUtilizador
    };
    public static final String[] TABLE_UTILIZADOR = {
             TABLE_UTILIZADOR_COLLUMS[0] + " " + TABLE_UTILIZADOR_DATATYPES[0] + " " + TABLE_UTILIZADOR_PARAMS[0]   //IdUtilizador
            ,TABLE_UTILIZADOR_COLLUMS[1] + " " + TABLE_UTILIZADOR_DATATYPES[1] + " " + TABLE_UTILIZADOR_PARAMS[1]   //NomeInvestigador
            ,TABLE_UTILIZADOR_COLLUMS[2] + " " + TABLE_UTILIZADOR_DATATYPES[2] + " " + TABLE_UTILIZADOR_PARAMS[2]   //EmailUtilizador
            ,TABLE_UTILIZADOR_COLLUMS[3] + " " + TABLE_UTILIZADOR_DATATYPES[3] + " " + TABLE_UTILIZADOR_PARAMS[3]   //Password
            ,TABLE_UTILIZADOR_COLLUMS[4] + " " + TABLE_UTILIZADOR_DATATYPES[4] + " " + TABLE_UTILIZADOR_PARAMS[4]   //TipoUtilizador
    };
    //</editor-fold>

    //<editor-fold desc="TableZona">
    public static final String TABLE_ZONA_NAME = "zona";
    /**
     * [0]IdZona
     * [1]Temperatura
     * [2]Humidade
     * [3]Luz
     */
    public static final String[] TABLE_ZONA_COLLUMS = {"IdZona", "Temperatura", "Humidade", "Luz"};
    public static final String[] TABLE_ZONA_DATATYPES = {
              "INTEGER"
            , "DECIMAL(5,2)"
            , "DECIMAL(5,2)"
            , "DECIMAL(5,2)"
    };
    public static final String[] TABLE_ZONA_PARAMS = {
              "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"
            , "NOT NULL"
            , "NOT NULL"
            , "NOT NULL"
    };

    /**
     * [0]  IdZona        INTEGER       NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"
     * [1]  Temperatura   DECIMAL(5,2)  NOT NULL
     * [2]  Humidade      DECIMAL(5,2)  NOT NULL
     * [3]  Luz           DECIMAL(5,2)  NOT NULL
     */
    public static final String[] TABLE_ZONA = {
            TABLE_ZONA_COLLUMS[0] + " " + TABLE_ZONA_DATATYPES[0] + " " + TABLE_ZONA_PARAMS[0] ,
            TABLE_ZONA_COLLUMS[1] + " " + TABLE_ZONA_DATATYPES[1] + " " + TABLE_ZONA_PARAMS[1] ,
            TABLE_ZONA_COLLUMS[2] + " " + TABLE_ZONA_DATATYPES[2] + " " + TABLE_ZONA_PARAMS[2] ,
            TABLE_ZONA_COLLUMS[3] + " " + TABLE_ZONA_DATATYPES[3] + " " + TABLE_ZONA_PARAMS[3]
    };
    //</editor-fold>

    //<editor-fold desc="TableCultura">

    public static final String TABLE_CULTURA_NAME = "cultura";
    /**
     * <p>TABLE_CULTURA_COLLUMS</p>
     * <ul>
     *     <li>[0]IdCultura     </li>
     *     <li>[1]NomeCultura   </li>
     *     <li>[2]IdUtilizador  </li>
     *     <li>[3]Estado        </li>
     * </ul>
     */
    public static final String[] TABLE_CULTURA_COLLUMS = {
              "IdCultura"
            , "NomeCultura"
            , "IdUtilizador"
            , "Estado"
    };
    /**
     * <p>TABLE_UTILIZADOR_DATATYPES</p>
     * <ul>
     *     <li>[0]INTEGER       - IdCultura       </li>
     *     <li>[1]VARCHAR(50)   - NomeCultura     </li>
     *     <li>[2]INTEGER       - IdUtilizador    </li>
     *     <li>[3]INTEGER       - Estado          </li>
     * </ul>
     */
    public static final String[] TABLE_CULTURA_DATATYPES = {
              "INTEGER"         //IdCultura
            , "VARCHAR(50)"     //NomeCultura
            , "INTEGER"         //IdUtilizador
            , "INTEGER"         //Estado
    };
    /**
     * <p>TABLE_UTILIZADOR_PARAMS</p>
     * <ul>
     *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE    - IdCultura     </li>
     *     <li>[1]NOT NULL UNIQUE                               - NomeCultura   </li>
     *     <li>[2]NOT NULL                                      - IdUtilizador  </li>
     *     <li>[3]NOT NULL                                      - Estado        </li>
     * </ul>
     */
    public static final String[] TABLE_CULTURA_PARAMS = {
              "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"  //IdUtilizador
            , "NOT NULL UNIQUE"                             //NomeInvestigador
            , "NOT NULL"                                    //EmailUtilizador
            , "NOT NULL"                                    //TipoUtilizador
    };

    public static final String[] TABLE_CULTURA = {
            TABLE_CULTURA_COLLUMS[0] + " " + TABLE_CULTURA_DATATYPES[0] + " " + TABLE_CULTURA_PARAMS[0] ,
            TABLE_CULTURA_COLLUMS[1] + " " + TABLE_CULTURA_DATATYPES[1] + " " + TABLE_CULTURA_PARAMS[1] ,
            TABLE_CULTURA_COLLUMS[2] + " " + TABLE_CULTURA_DATATYPES[2] + " " + TABLE_CULTURA_PARAMS[2] ,
            TABLE_CULTURA_COLLUMS[3] + " " + TABLE_CULTURA_DATATYPES[3] + " " + TABLE_CULTURA_PARAMS[3],
            "CONSTRAINT FK_IdUtilizador FOREIGN KEY (" + TABLE_CULTURA_COLLUMS[2] + ") REFERENCES utilizador(" + TABLE_CULTURA_COLLUMS[2] + ")"
    };
    //</editor-fold>

    //<editor-fold desc="TableAlerta">

    public static final String TABLE_ALERTA_NAME = "alerta";
    /**
     * <p>TABLE_ALERTA_COLLUMS</p>
     * <ul>
     *     <li>[0]IdAlerta              </li>
     *     <li>[1]IdZona                </li>
     *     <li>[2]IdSensor              </li>
     *     <li>[3]Hora                  </li>
     *     <li>[4]Leitura               </li>
     *     <li>[5]TipoAlerta            </li>
     *     <li>[6]Cultura               </li>
     *     <li>[7]IdUtilizador          </li>
     *     <li>[8]HoraEscrita           </li>
     *     <li>[9]NivelAlerta          </li>
     *     <li>[10]IdParametroCultura   </li>
     * </ul>
     */
    public static final String[] TABLE_ALERTA_COLLUMS = {
              "IdAlerta"                //IdAlerta
            , "IdZona"                  //IdZona
            , "IdSensor"                //IdSensor
            , "Hora"                    //Hora
            , "Leitura"                 //Leitura
            , "TipoAlerta"              //TipoAlerta
            , "Cultura"                 //Cultura
            , "IdUtilizador"            //IdUtilizador
            , "HoraEscrita"             //HoraEscrita
            , "NivelAlerta"             //NivelAlerta
            , "IdParametroCultura"      //IdParametroCultura
    };
    /**
     * <p>TABLE_ALERTA_DATATYPES</p>
     * <ul>
     *     <li>[0]  INTEGER         - IdAlerta              </li>
     *     <li>[1]  INTEGER         - IdZona                </li>
     *     <li>[2]  INTEGER         - IdSensor              </li>
     *     <li>[3]  TIMESTAMP       - Hora                  </li>
     *     <li>[4]  DECIMAL(1,0)    - Leitura               </li>
     *     <li>[5]  VARCHAR(1)      - TipoAlerta            </li>
     *     <li>[6]  VARCHAR(100)    - Cultura               </li>
     *     <li>[7]  INTEGER         - IdUtilizador          </li>
     *     <li>[8]  TIMESTAMP       - HoraEscrita           </li>
     *     <li>[9]  VARCHAR(100)    - NivelAlerta           </li>
     *     <li>[10] INTEGER         - IdParametroCultura    </li>
     * </ul>
     */
    public static final String[] TABLE_ALERTA_DATATYPES = {
            "INTEGER"               ,//IdAlerta
            "INTEGER"               ,//IdZona
            "INTEGER"               ,//IdSensor
            "TIMESTAMP"             ,//Hora
            "DECIMAL(1,0)"          ,//Leitura
            "VARCHAR(1)"            ,//TipoAlerta
            "VARCHAR(100)"          ,//Cultura
            "INTEGER"               ,//IdUtilizador
            "TIMESTAMP"             ,//HoraEscrita
            "VARCHAR(100)"          ,//NivelAlerta
            "INTEGER"               ,//IdParametroCultura
    };
    /**
     * <p>TABLE_ALERTA_PARAMS</p>
     * <ul>
     *     <li>[0]  NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE      - IdAlerta              </li>
     *     <li>[1]  NOT NULL                                        - IdZona                </li>
     *     <li>[2]  NOT NULL                                        - IdSensor              </li>
     *     <li>[3]  NOT NULL                                        - Hora                  </li>
     *     <li>[4]  NOT NULL                                        - Leitura               </li>
     *     <li>[5]  NOT NULL                                        - TipoAlerta            </li>
     *     <li>[6]  NOT NULL                                        - Cultura               </li>
     *     <li>[7]  NOT NULL                                        - IdUtilizador          </li>
     *     <li>[8]  NOT NULL UNIQUE                                 - HoraEscrita           </li>
     *     <li>[9] NOT NULL                                         - NivelAlerta           </li>
     *     <li>[10] NOT NULL                                        - IdParametroCultura    </li>
     * </ul>
     */
    public static final String[] TABLE_ALERTA_PARAMS = {
            "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"    ,//IdAlerta
            "NOT NULL"                                      ,//IdZona
            "NOT NULL"                                      ,//IdSensor
            "NOT NULL"                                      ,//Hora
            "NOT NULL"                                      ,//Leitura
            "NOT NULL"                                      ,//TipoAlerta
            "NOT NULL"                                      ,//Cultura
            "NOT NULL"                                      ,//IdUtilizador
            "DEFAULT CURRENT_TIMESTAMP NOT NULL UNIQUE"     ,//HoraEscrita
            "NOT NULL"                                      ,//NivelAlerta
            "NOT NULL"                                      ,//IdParametroCultura
    };

    public static final String[] TABLE_ALERTA = {
            TABLE_ALERTA_COLLUMS[0]  + " " + TABLE_ALERTA_DATATYPES[0]  + " " + TABLE_ALERTA_PARAMS[0],     //IdAlerta
            TABLE_ALERTA_COLLUMS[1]  + " " + TABLE_ALERTA_DATATYPES[1]  + " " + TABLE_ALERTA_PARAMS[1],     //IdZona
            TABLE_ALERTA_COLLUMS[2]  + " " + TABLE_ALERTA_DATATYPES[2]  + " " + TABLE_ALERTA_PARAMS[2],     //IdSensor
            TABLE_ALERTA_COLLUMS[3]  + " " + TABLE_ALERTA_DATATYPES[3]  + " " + TABLE_ALERTA_PARAMS[3],     //Hora
            TABLE_ALERTA_COLLUMS[4]  + " " + TABLE_ALERTA_DATATYPES[4]  + " " + TABLE_ALERTA_PARAMS[4],     //Leitura
            TABLE_ALERTA_COLLUMS[5]  + " " + TABLE_ALERTA_DATATYPES[5]  + " " + TABLE_ALERTA_PARAMS[5],     //TipoAlerta
            TABLE_ALERTA_COLLUMS[6]  + " " + TABLE_ALERTA_DATATYPES[6]  + " " + TABLE_ALERTA_PARAMS[6],     //Cultura
            TABLE_ALERTA_COLLUMS[7]  + " " + TABLE_ALERTA_DATATYPES[7]  + " " + TABLE_ALERTA_PARAMS[7],     //IdUtilizador
            TABLE_ALERTA_COLLUMS[8]  + " " + TABLE_ALERTA_DATATYPES[8]  + " " + TABLE_ALERTA_PARAMS[8],     //HoraEscrita
            TABLE_ALERTA_COLLUMS[9]  + " " + TABLE_ALERTA_DATATYPES[9]  + " " + TABLE_ALERTA_PARAMS[9],     //NivelAlerta
            TABLE_ALERTA_COLLUMS[10] + " " + TABLE_ALERTA_DATATYPES[10] + " " + TABLE_ALERTA_PARAMS[10],    //IdParametroCultura
            "CONSTRAINT FK2_"+TABLE_ALERTA_COLLUMS[1]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[1] + ") REFERENCES " + TABLE_ZONA_NAME     +"(" + TABLE_ALERTA_COLLUMS[1] + ")",
            "CONSTRAINT FK_" +TABLE_ALERTA_COLLUMS[2]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[2] + ") REFERENCES " + TABLE_SENSOR_NAME       +"(" + TABLE_ALERTA_COLLUMS[2] + ")",
            "CONSTRAINT FK2_" +TABLE_ALERTA_COLLUMS[7]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[7] + " ) REFERENCES " + TABLE_UTILIZADOR_NAME +"(" + TABLE_ALERTA_COLLUMS[7] + ")",
    };
    //</editor-fold>

    //<editor-fold desc="Stored Procedure Constants">
    public static final String SP_INSERIR_ZONA_NAME                 = "Inserir_Zona";
    public static final String SP_ALTERAR_ZONA_NAME                 = "Alterar_Zona";
    public static final String SP_ELIMINAR_ZONA_NAME                = "Eliminar_Zona";
    public static final String SP_INSERIR_MEDICAO_NAME              = "Inserir_Medicao";
    public static final String SP_ALTERAR_MEDICAO_NAME              = "Alterar_Medicao";
    public static final String SP_ELIMINAR_MEDICAO_NAME             = "Eliminar_Medicao";
    public static final String SP_INSERIR_SENSOR_NAME               = "Inserir_Sensor";
    public static final String SP_ALTERAR_SENSOR_NAME               = "Alterar_Sensor";
    public static final String SP_ELIMINAR_SENSOR_NAME              = "Eliminar_Sensor";
    public static final String SP_INSERIR_USER_INVESTIGADOR_NAME    = "Inserir_User_Investigador";
    public static final String SP_INSERIR_USER_TECNICO_NAME         = "Inserir_User_Tecnico";
    public static final String SP_INSERIR_USER_ADMIN_NAME           = "Inserir_User_Admin";
    public static final String SP_INSERIR_USER_MQTTREADER_NAME      = "Inserir_User_MqttReader";
    public static final String SP_ALTERAR_USER_NAME                 = "Alterar_User";
    public static final String SP_ELIMINAR_USER_NAME                = "Eliminar_User";
    public static final String SP_INSERIR_CULTURA_NAME              = "Inserir_Cultura";
    public static final String SP_ALTERAR_CULTURA_NAME              = "Alterar_Cultura";
    public static final String SP_ELIMINAR_CULTURA_NAME             = "Eliminar_Cultura";
    public static final String SP_INSERIR_PARAMETRO_CULTURA_NAME    = "Inserir_ParametroCultura";
    public static final String SP_ALTERAR_PARAMETRO_CULTURA_NAME    = "Alterar_ParametroCultura";
    public static final String SP_ELIMINAR_PARAMETRO_CULTURA_NAME   = "Eliminar_ParametroCultura";
    public static final String SP_INSERIR_ALERTA_NAME               = "Inserir_Alerta";
    public static final String SP_ALTERAR_ALERTA_NAME               = "Alterar_Alerta";
    public static final String SP_ELIMINAR_ALERTA_NAME              = "Eliminar_Alerta";
    public static final String SP_SELECT_ALERTA_NAME                = "Selecionar_Alerta";
    //</editor-fold>

    /** Columns for document reading on insert medição*/
    static final String ZONA            = "Zona";
    /** Columns for document reading on insert medição*/
    static final String SENSOR          = "Sensor";
    /** Columns for document reading on insert medição*/
    static final String DATA            = "Data";
    /** Columns for document reading on insert medição*/
    static final String MEDICAO         = "Medicao";

    //Roles for Application
    static final String ROLE_INVESTIGADOR = "Investigador";
    static final String ROLE_TECNICO = "Tecnico";
    static final String ROLE_ADMIN = "Admin";
    static final String ROLE_MQTTREADER = "MqttReader";

    //Columns for table Sensor in cloud
    static final String[] sensorCloudColumns = {"idsensor", "tipo", "limiteinferior", "limitesuperior", "idzona"};

}
