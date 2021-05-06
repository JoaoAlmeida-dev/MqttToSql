package sql;

public class SqlVariables {
    //local
    public static final String PATH_DB_USER = "culturamysql2";
    public static final String USERNAME = "aluno@hotmail.com";
    public static final String PASSWORD = "aluno";

    //cloud
    public static final String CLOUD_PATH_DB_USER = "sid2021";
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
     *     <li>[3]DATETIME     -Hora          </li>
     *     <li>[4]NUMERIC (5,2) -Leitura       </li>
     * </ul>
     */
    public static final String[] TABLE_MEDICAO_DATATYPES = {"INTEGER", "INTEGER", "INTEGER", "DATETIME" , "NUMERIC (5,2)"};
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
     *     <li>[2]  NUMERIC - MinHumidade                </li>
     *     <li>[3]  NUMERIC - MaxHumidade                </li>
     *     <li>[4]  NUMERIC - MinTemperatura             </li>
     *     <li>[5]  NUMERIC - MaxTemperatura             </li>
     *     <li>[6]  NUMERIC - MinLuz                     </li>
     *     <li>[7]  NUMERIC - MaxLuz                     </li>
     *     <li>[8]  NUMERIC - DangerZoneMinHumidade      </li>
     *     <li>[9]  NUMERIC - DangerZoneMaxHumidade      </li>
     *     <li>[10] NUMERIC - DangerZoneMinTemperatura   </li>
     *     <li>[11] NUMERIC - DangerZoneMaxTemperatura   </li>
     *     <li>[12] NUMERIC - DangerZoneMinLuz           </li>
     *     <li>[13] NUMERIC - DangerZoneMaxLuz           </li>
     * </ul>
     */
    public static final String[] TABLE_PARAMETROCULTURA_DATATYPES = {
             "INTEGER"          //IdParametroCultura
            ,"INTEGER"          //IdCultura
            ,"NUMERIC"          //MinHumidade
            ,"NUMERIC"          //MaxHumidade
            ,"NUMERIC"          //MinTemperatura
            ,"NUMERIC"          //MaxTemperatura
            ,"NUMERIC"          //MinLuz
            ,"NUMERIC"          //MaxLuz
            ,"NUMERIC"          //DangerZoneMinHumidade
            ,"NUMERIC"          //DangerZoneMaxHumidade
            ,"NUMERIC"          //DangerZoneMinTemperatura
            ,"NUMERIC"          //DangerZoneMaxTemperatura
            ,"NUMERIC"          //DangerZoneMinLuz
            ,"NUMERIC"          //DangerZoneMaxLuz
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
            TABLE_PARAMETROCULTURA_COLLUMS[0]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[0]  + " " + TABLE_PARAMETROCULTURA_PARAMS[0]   ,//IdParametroCultura
            TABLE_PARAMETROCULTURA_COLLUMS[1]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[1]  + " " + TABLE_PARAMETROCULTURA_PARAMS[1]   ,//IdCultura
            TABLE_PARAMETROCULTURA_COLLUMS[2]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[2]  + " " + TABLE_PARAMETROCULTURA_PARAMS[2]   ,//MinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[3]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[3]  + " " + TABLE_PARAMETROCULTURA_PARAMS[3]   ,//MaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[4]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[4]  + " " + TABLE_PARAMETROCULTURA_PARAMS[4]   ,//MinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[5]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[5]  + " " + TABLE_PARAMETROCULTURA_PARAMS[5]   ,//MaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[6]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[6]  + " " + TABLE_PARAMETROCULTURA_PARAMS[6]   ,//MinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[7]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[7]  + " " + TABLE_PARAMETROCULTURA_PARAMS[7]   ,//MaxLuz
            TABLE_PARAMETROCULTURA_COLLUMS[8]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[8]  + " " + TABLE_PARAMETROCULTURA_PARAMS[8]   ,//DangerZoneMinHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[9]  + " " +  TABLE_PARAMETROCULTURA_DATATYPES[9]  + " " + TABLE_PARAMETROCULTURA_PARAMS[9]   ,//DangerZoneMaxHumidade
            TABLE_PARAMETROCULTURA_COLLUMS[10] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[10] + " " + TABLE_PARAMETROCULTURA_PARAMS[10]  ,//DangerZoneMinTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[11] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[11] + " " + TABLE_PARAMETROCULTURA_PARAMS[11]  ,//DangerZoneMaxTemperatura
            TABLE_PARAMETROCULTURA_COLLUMS[12] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[12] + " " + TABLE_PARAMETROCULTURA_PARAMS[12]  ,//DangerZoneMinLuz
            TABLE_PARAMETROCULTURA_COLLUMS[13] + " " +  TABLE_PARAMETROCULTURA_DATATYPES[13] + " " + TABLE_PARAMETROCULTURA_PARAMS[13]  ,//DangerZoneMaxLuz
            "CONSTRAINT FK_IdCultura FOREIGN KEY (" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ") REFERENCES cultura(" + TABLE_PARAMETROCULTURA_COLLUMS[1] + ")" +  //IdCUltura
                    ""};
    //</editor-fold>

    //<editor-fold desc="TableSensor">
    public static final String TABLE_SENSOR_NAME = "sensor";


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
     *     <li>[3]TipoUtilizador    </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_COLLUMS = {"IdUtilizador", "NomeInvestigador", "EmailUtilizador", "TipoUtilizador"};
    /**
     * <p>TABLE_UTILIZADOR_DATATYPES</p>
     * <ul>
     *     <li>[0]INTEGER -IdUtilizador      </li>
     *     <li>[1]VARCHAR(100) -NomeInvestigador  </li>
     *     <li>[2]VARCHAR(100) -EmailUtilizador   </li>
     *     <li>[3]VARCHAR(100) -TipoUtilizador    </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_DATATYPES = {
              "INTEGER"         //IdUtilizador
            , "VARCHAR(100)"    //NomeInvestigador
            , "VARCHAR(100)"    //EmailUtilizador
            , "VARCHAR(100)"    //TipoUtilizador
            //, "TEXT"            //Password
    };
    /**
     * <p>TABLE_UTILIZADOR_PARAMS</p>
     * <ul>
     *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE -IdUtilizador      </li>
     *     <li>[1]NOT NULL -NomeInvestigador  </li>
     *     <li>[2]NOT NULL -EmailUtilizador   </li>
     *     <li>[3]NOT NULL -TipoUtilizador    </li>
     * </ul>
     */
    public static final String[] TABLE_UTILIZADOR_PARAMS = {
              "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"      //IdUtilizador
            , "NOT NULL"        //NomeInvestigador
            , "NOT NULL"        //EmailUtilizador
            , "NOT NULL"        //TipoUtilizador
            //, "NOT NULL"        //Password
    };
    public static final String[] TABLE_UTILIZADOR = {
             TABLE_UTILIZADOR_COLLUMS[0] + " " + TABLE_UTILIZADOR_DATATYPES[0] + " " + TABLE_UTILIZADOR_PARAMS[0]   //IdUtilizador
            ,TABLE_UTILIZADOR_COLLUMS[1] + " " + TABLE_UTILIZADOR_DATATYPES[1] + " " + TABLE_UTILIZADOR_PARAMS[1]   //NomeInvestigador
            ,TABLE_UTILIZADOR_COLLUMS[2] + " " + TABLE_UTILIZADOR_DATATYPES[2] + " " + TABLE_UTILIZADOR_PARAMS[2]   //EmailUtilizador
            ,TABLE_UTILIZADOR_COLLUMS[3] + " " + TABLE_UTILIZADOR_DATATYPES[3] + " " + TABLE_UTILIZADOR_PARAMS[3]   //TipoUtilizador
        //  ,TABLE_UTILIZADOR_COLLUMS[4] + " " + TABLE_UTILIZADOR_DATATYPES[4] + " " + TABLE_UTILIZADOR_PARAMS[4]"  //Password
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
     *     <li>[7]Mensagem              </li>
     *     <li>[8]IdUtilizador          </li>
     *     <li>[9]HoraEscrita           </li>
     *     <li>[10]NivelAlerta          </li>
     *     <li>[11]IdParametroCultura   </li>
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
            , "Mensagem"                //Mensagem
            , "IdUtilizador"            //IdUtilizador
            , "HoraEscrita"             //HoraEscrita
            , "NivelAlerta"             //]NivelAlerta
            , "IdParametroCultura"      //]IdParametroCultura
    };
    /**
     * <p>TABLE_ALERTA_DATATYPES</p>
     * <ul>
     *     <li>[0]  INTEGER         - IdAlerta              </li>
     *     <li>[1]  INTEGER         - IdZona                </li>
     *     <li>[2]  INTEGER         - IdSensor              </li>
     *     <li>[3]  VARCHAR         - Hora                  </li>
     *     <li>[4]  VARCHAR(100)    - Leitura               </li>
     *     <li>[5]  VARCHAR(100)    - TipoAlerta            </li>
     *     <li>[6]  VARCHAR(100)    - Cultura               </li>
     *     <li>[7]  VARCHAR(100)    - Mensagem              </li>
     *     <li>[8]  INTEGER         - IdUtilizador          </li>
     *     <li>[9]  VARCHAR(100)    - HoraEscrita           </li>
     *     <li>[10] VARCHAR(100)    - NivelAlerta           </li>
     *     <li>[11] INTEGER         - IdParametroCultura    </li>
     * </ul>
     */
    public static final String[] TABLE_ALERTA_DATATYPES = {
            "INTEGER"               ,//IdAlerta
            "INTEGER"               ,//IdZona
            "INTEGER"               ,//IdSensor
            "VARCHAR(100)"          ,//Hora
            "VARCHAR(100)"          ,//Leitura
            "VARCHAR(100)"          ,//TipoAlerta
            "VARCHAR(100)"          ,//Cultura
            "VARCHAR(100)"          ,//Mensagem
            "INTEGER"               ,//IdUtilizador
            "VARCHAR(100)"          ,//HoraEscrita
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
     *     <li>[7]  NOT NULL                                        - Mensagem              </li>
     *     <li>[8]  NOT NULL                                        - IdUtilizador          </li>
     *     <li>[9]  NOT NULL UNIQUE                                 - HoraEscrita           </li>
     *     <li>[10] NOT NULL                                        - NivelAlerta           </li>
     *     <li>[11] NOT NULL                                        - IdParametroCultura    </li>
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
            "NOT NULL"                                      ,//Mensagem
            "NOT NULL"                                      ,//IdUtilizador
            "NOT NULL UNIQUE"                               ,//HoraEscrita
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
            TABLE_ALERTA_COLLUMS[7]  + " " + TABLE_ALERTA_DATATYPES[7]  + " " + TABLE_ALERTA_PARAMS[7],     //Mensagem
            TABLE_ALERTA_COLLUMS[8]  + " " + TABLE_ALERTA_DATATYPES[8]  + " " + TABLE_ALERTA_PARAMS[8],     //IdUtilizador
            TABLE_ALERTA_COLLUMS[9]  + " " + TABLE_ALERTA_DATATYPES[9]  + " " + TABLE_ALERTA_PARAMS[9],     //HoraEscrita
            TABLE_ALERTA_COLLUMS[10] + " " + TABLE_ALERTA_DATATYPES[10] + " " + TABLE_ALERTA_PARAMS[10],     //NivelAlerta
            TABLE_ALERTA_COLLUMS[11] + " " + TABLE_ALERTA_DATATYPES[11] + " " + TABLE_ALERTA_PARAMS[11],      //IdParametroCultura
            "CONSTRAINT FK2_IdZona FOREIGN KEY ( " + TABLE_ALERTA_COLLUMS[1] + ") REFERENCES zona(" + TABLE_ALERTA_COLLUMS[1] + ")"     ,
            "CONSTRAINT FK_IdSensor FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[2] + ") REFERENCES sensor(" + TABLE_ALERTA_COLLUMS[2] + ")"   ,
    };
    //</editor-fold>

    public static final String USER_ADMIN = "Adm";
    public static final String USER_INVESTIGATOR = "Invest";
    public static final String USER_TECHNIC = "Tec";


}
