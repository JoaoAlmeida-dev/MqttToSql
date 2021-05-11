package sql.variables;

import sql.CulturaSP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class Table_Alerta {

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
	        "CONSTRAINT FK2_"+TABLE_ALERTA_COLLUMS[1]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[1] + ") REFERENCES " + Table_Zona.TABLE_ZONA_NAME     +"(" + TABLE_ALERTA_COLLUMS[1] + ")",
	        "CONSTRAINT FK_" +TABLE_ALERTA_COLLUMS[2]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[2] + ") REFERENCES " + Table_Sensor.TABLE_SENSOR_NAME       +"(" + TABLE_ALERTA_COLLUMS[2] + ")",
	        "CONSTRAINT FK2_" +TABLE_ALERTA_COLLUMS[7]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[7] + " ) REFERENCES " + Table_Utilizador.TABLE_UTILIZADOR_NAME +"(" + TABLE_ALERTA_COLLUMS[7] + ")",
	};

	public static final String SP_INSERIR_ALERTA_NAME               = "Inserir_Alerta";
	public static final String SP_ALTERAR_ALERTA_NAME               = "Alterar_Alerta";
	public static final String SP_ELIMINAR_ALERTA_NAME              = "Eliminar_Alerta";
	public static final String SP_SELECT_ALERTA_NAME                = "Selecionar_Alerta";

	public static void createSPInserir_Alerta(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1, TABLE_ALERTA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_ALERTA_DATATYPES,1, TABLE_ALERTA_DATATYPES.length   )
	    );

	    String statements = CulturaSP.generateINSERT(TABLE_ALERTA_NAME, Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1, TABLE_ALERTA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_ALERTA_NAME, statements, args);

	}

	public static void createSPAlterar_Alerta(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_ALERTA_COLLUMS, TABLE_ALERTA_DATATYPES);


	    String statements = "UPDATE " + TABLE_ALERTA_NAME + " SET " + TABLE_ALERTA_COLLUMS[1] + " = sp_" + TABLE_ALERTA_COLLUMS[1] +
	            " ," + TABLE_ALERTA_COLLUMS[2] + " = sp_" + TABLE_ALERTA_COLLUMS[2] +
	            " ," + TABLE_ALERTA_COLLUMS[3] + " = sp_" + TABLE_ALERTA_COLLUMS[3] +
	            " ," + TABLE_ALERTA_COLLUMS[4] + " = sp_" + TABLE_ALERTA_COLLUMS[4] +
	            " ," + TABLE_ALERTA_COLLUMS[5] + " = sp_" + TABLE_ALERTA_COLLUMS[5] +
	            " ," + TABLE_ALERTA_COLLUMS[6] + " = sp_" + TABLE_ALERTA_COLLUMS[6] +
	            " ," + TABLE_ALERTA_COLLUMS[7] + " = sp_" + TABLE_ALERTA_COLLUMS[7] +
	            " ," + TABLE_ALERTA_COLLUMS[8] + " = sp_" + TABLE_ALERTA_COLLUMS[8] +
	            " ," + TABLE_ALERTA_COLLUMS[9] + " = sp_" + TABLE_ALERTA_COLLUMS[9] +
	            " ," + TABLE_ALERTA_COLLUMS[10] + " = sp_" + TABLE_ALERTA_COLLUMS[10] +
	            " WHERE " + TABLE_ALERTA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_ALERTA_NAME, statements, args);

	}

	public static void createSPEliminar_Alerta(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ALERTA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ALERTA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_ALERTA_NAME, statements, args);
	}

	public static void createSPSelect_Alerta(Connection connection) throws SQLException {

		String args = "IN sp_"+ TABLE_ALERTA_COLLUMS[7] + " " + TABLE_ALERTA_DATATYPES[7];
	    String statements = "SELECT * FROM " + TABLE_ALERTA_NAME + " WHERE sp_" + TABLE_ALERTA_COLLUMS[7] + " = " + TABLE_ALERTA_NAME +"."+ TABLE_ALERTA_COLLUMS[7];

	    createStoredProcedure(connection, SP_SELECT_ALERTA_NAME, statements, args);
	}

	public static ResultSet callSPSelect_Alerta(Connection connection, int IdUtilizador) throws SQLException {
		CallableStatement cst = connection.prepareCall("{call " + SP_SELECT_ALERTA_NAME +"(?)}");
		cst.setInt(1,IdUtilizador);


		return cst.executeQuery();
	}
}
