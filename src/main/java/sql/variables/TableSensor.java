package sql.variables;

import sql.CulturaSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class TableSensor {


	// Estamos com dúvidas na coluna NumeroTipo, ela existe para imitar a estrutura do sensor na cloud

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
	public static final String SP_INSERIR_SENSOR_NAME               = "Inserir_Sensor";
	public static final String SP_ALTERAR_SENSOR_NAME               = "Alterar_Sensor";
	public static final String SP_ELIMINAR_SENSOR_NAME              = "Eliminar_Sensor";

	public static void createSPInserir_Sensor(Connection connection) throws SQLException {
		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1, TABLE_SENSOR_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_SENSOR_DATATYPES,1, TABLE_SENSOR_DATATYPES.length   )
	    );
	    String statements = CulturaSP.generateINSERT(TABLE_SENSOR_NAME,
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1, TABLE_SENSOR_COLLUMS.length)
	    );

	    createStoredProcedure(connection, SP_INSERIR_SENSOR_NAME, statements, args);

	}

	public static void createSPAlterar_Sensor(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_SENSOR_COLLUMS, TABLE_SENSOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_SENSOR_NAME + " SET " + TABLE_SENSOR_COLLUMS[1] + " = sp_" + TableMedicao.TABLE_MEDICAO_COLLUMS[1] +
	            " ," + TABLE_SENSOR_COLLUMS[2] + " = sp_" + TABLE_SENSOR_COLLUMS[2] +
	            " ," + TABLE_SENSOR_COLLUMS[3] + " = sp_" + TABLE_SENSOR_COLLUMS[3] +
	            " ," + TABLE_SENSOR_COLLUMS[4] + " = sp_" + TABLE_SENSOR_COLLUMS[4] +
	            " WHERE " + TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_SENSOR_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_SENSOR_NAME, statements, args);

	}

	public static void createSPEliminar_Sensor(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_SENSOR_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_SENSOR_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_SENSOR_NAME, statements, args);
	}
}
