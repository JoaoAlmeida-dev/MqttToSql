package sql.variables;

import sql.CulturaSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class TableMedicao {

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
	        "CONSTRAINT FK2_IdSensor FOREIGN KEY (" + TABLE_MEDICAO_COLLUMS[2] + ") REFERENCES sensor(" + TABLE_MEDICAO_COLLUMS[2] + ")"
	};


	public static final String SP_INSERIR_MEDICAO_NAME              = "Inserir_Medicao";
	public static final String SP_ALTERAR_MEDICAO_NAME              = "Alterar_Medicao";
	public static final String SP_ELIMINAR_MEDICAO_NAME             = "Eliminar_Medicao";

	public static void createSPInserir_Medicao(Connection connection) throws SQLException {

	    String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,1, TABLE_MEDICAO_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_MEDICAO_DATATYPES,1, TABLE_MEDICAO_DATATYPES.length   )
	    );


	    String Variable_LimiteInferior_name = "medicao_" + TableSensor.TABLE_SENSOR_COLLUMS[3] ;
	    String Variable_LimiteSuperior_name = "medicao_" + TableSensor.TABLE_SENSOR_COLLUMS[4] ;

		String Variable_LimiteInferior = "DECLARE " + Variable_LimiteInferior_name + " " + TableSensor.TABLE_SENSOR_DATATYPES[3] +";";
		String Variable_LimiteSuperior = "DECLARE " + Variable_LimiteSuperior_name + " " + TableSensor.TABLE_SENSOR_DATATYPES[4] +";";
		String Set_LimiteInferior = "SELECT " + TableSensor.TABLE_SENSOR_COLLUMS[3] +" INTO " + Variable_LimiteInferior_name + " FROM " + TableSensor.TABLE_SENSOR_NAME + " WHERE " + TableSensor.TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] + "; ";
		String Set_LimiteSuperior = "SELECT " + TableSensor.TABLE_SENSOR_COLLUMS[4] +" INTO " + Variable_LimiteSuperior_name + " FROM " + TableSensor.TABLE_SENSOR_NAME + " WHERE " + TableSensor.TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] + "; ";

		String insert = CulturaSP.generateINSERT(TABLE_MEDICAO_NAME, Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,1, TABLE_MEDICAO_COLLUMS.length));

		String finalStatements =
				"\n" + Variable_LimiteInferior
				+"\n" + Variable_LimiteSuperior
				+"\n" + Set_LimiteInferior
				+"\n" + Set_LimiteSuperior;

		finalStatements += "IF sp_"+ TABLE_MEDICAO_COLLUMS[4] + " >= " + Variable_LimiteInferior_name + " AND sp_"+ TABLE_MEDICAO_COLLUMS[4]+ " <= " + Variable_LimiteSuperior_name + " THEN\n" + insert + " ;END IF";


	    createStoredProcedure(connection, SP_INSERIR_MEDICAO_NAME, finalStatements, args);

	}

	public static void createSPAlterar_Medicao(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_MEDICAO_COLLUMS, TABLE_MEDICAO_DATATYPES);

	    String statements = "UPDATE " + TABLE_MEDICAO_NAME + " SET " + TABLE_MEDICAO_COLLUMS[1] + " = sp_" + TABLE_MEDICAO_COLLUMS[1] +
	            " ," + TABLE_MEDICAO_COLLUMS[2] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] +
	            " ," + TABLE_MEDICAO_COLLUMS[3] + " = sp_" + TABLE_MEDICAO_COLLUMS[3] +
	            " WHERE " + TABLE_MEDICAO_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_MEDICAO_NAME, statements, args);

	}

	public static void createSPEliminar_Medicao(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_MEDICAO_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_MEDICAO_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_MEDICAO_NAME, statements, args);
	}
}
