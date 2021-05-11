package sql.variables;

import sql.CulturaSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class TableParametroCultura {

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
	public static final String SP_INSERIR_PARAMETRO_CULTURA_NAME    = "Inserir_ParametroCultura";
	public static final String SP_ALTERAR_PARAMETRO_CULTURA_NAME    = "Alterar_ParametroCultura";
	public static final String SP_ELIMINAR_PARAMETRO_CULTURA_NAME   = "Eliminar_ParametroCultura";

	public static void createSPInserir_ParametroCultura(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,1, TABLE_PARAMETROCULTURA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_DATATYPES,1, TABLE_PARAMETROCULTURA_DATATYPES.length   )
	    );

	    String statements = CulturaSP.generateINSERT(TABLE_PARAMETROCULTURA_NAME, Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,1, TABLE_PARAMETROCULTURA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_PARAMETRO_CULTURA_NAME, statements, args);

	}

	public static void createSPAlterar_ParametroCultura(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_PARAMETROCULTURA_COLLUMS, TABLE_PARAMETROCULTURA_DATATYPES);



	    String statements = "UPDATE " + TABLE_PARAMETROCULTURA_NAME + " SET " + TABLE_PARAMETROCULTURA_COLLUMS[1] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[1] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[2] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[2] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[3] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[3] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[4] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[4] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[5] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[5] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[6] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[6] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[7] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[7] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[8] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[8] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[9] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[9] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[10] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[10] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[11] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[11] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[12] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[12] +
	            " ," + TABLE_PARAMETROCULTURA_COLLUMS[13] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[13] +
	            " WHERE " + TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_PARAMETROCULTURA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_PARAMETRO_CULTURA_NAME, statements, args);

	}

	public static void createSPEliminar_ParametroCultura(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_PARAMETROCULTURA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_PARAMETROCULTURA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_PARAMETRO_CULTURA_NAME, statements, args);
	}
}
