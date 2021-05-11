package sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;
import static sql.SqlVariables.*;

public class CulturaSP {

	/*TODO
			   SP select Alerta
			   inserir alerta automaticamente (MQTTREADER)

			* */
	//---------------------------------- SPs ----------------------------------
	//<editor-fold desc="SP">
	public static void createAllSP(Connection connection) throws SQLException {
	    createSPInserir_Zona                    (connection);
	    createSPAlterar_Zona                    (connection);
	    createSPEliminar_Zona                   (connection);

	    createSPInserir_Medicao                 (connection);
	    createSPAlterar_Medicao                 (connection);
	    createSPEliminar_Medicao                (connection);

	    createSPInserir_Sensor                  (connection);
	    createSPAlterar_Sensor                  (connection);
	    createSPEliminar_Sensor                 (connection);

	    createSPInserir_User_Investigador       (connection);
	    createSPInserir_User_Tecnico       		(connection);
	    createSPInserir_User_Admin       		(connection);
	    createSPInserir_User_MqttReader       	(connection);
	    createSPAlterar_User                    (connection);
	    createSPEliminar_User                   (connection);

	    createSPInserir_Cultura                 (connection);
	    createSPAlterar_Cultura                 (connection);
	    createSPEliminar_Cultura                (connection);

	    createSPInserir_ParametroCultura        (connection);
	    createSPAlterar_ParametroCultura        (connection);
	    createSPEliminar_ParametroCultura       (connection);

	    createSPInserir_Alerta                  (connection);
	    createSPAlterar_Alerta                  (connection);
	    createSPEliminar_Alerta                 (connection);
		createSPSelect_Alerta                   (connection);
	}

	public static String generateARGUMENTS(String[] tableCollums, String[] tableDatatypes) {
	    String args ="";
	    if(tableCollums.length == tableDatatypes.length){
	        for(int i =0 ; i<tableCollums.length && i<tableDatatypes.length ;i++){
	            args += "IN sp_" + tableCollums[i] + " " + tableDatatypes[i] +",";
	        }
	        args = args.substring(0,args.length()-1);
	        return args;

	    }else{
	        System.out.println("Error generating IN String");
	        System.out.println("Tables dont have the same length!!");
	        System.out.println("Check sqlVariables.java to fix");
	        return "";
	    }

	}

	public static String generateINSERT(String tableMedicaoName, String[] tableCollums) {
	    String insertString = "INSERT INTO " + tableMedicaoName + " (";
	    for (String value :
	            tableCollums) {
	        insertString += " " + value + ",";
	    }
	    insertString = insertString.substring(0,insertString.length() - 1);
	    insertString += ") VALUES ( ";
	    for (String value :
	            tableCollums) {
	        insertString += " sp_" + value + ",";
	    }
	    insertString = insertString.substring(0,insertString.length() - 1);
	    insertString += ")";

	    return insertString;
	}

	//---------------------------------- Zona ----------------------------------
	//<editor-fold desc="SPZona">
	/**
	 * SP para inserir uma zona na db
	 *
	 * @param connection conecção mysql
	 * @throws SQLException
	 */
	public static void createSPInserir_Zona(Connection connection) throws SQLException {

		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1,TABLE_ZONA_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_ZONA_DATATYPES,1,TABLE_ZONA_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_ZONA_NAME, Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1,TABLE_ZONA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_ZONA_NAME, statements, args);

	}

	/**
	 * SP para alterar uma zona da db
	 *
	 * @param connection conecção mysql
	 * @throws SQLException
	 */
	public static void createSPAlterar_Zona(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_ZONA_COLLUMS,TABLE_ZONA_DATATYPES);

	    String statements = "UPDATE " + TABLE_ZONA_NAME + " SET " + TABLE_ZONA_COLLUMS[1] + " = sp_" + TABLE_ZONA_COLLUMS[1] +
	            " ," + TABLE_ZONA_COLLUMS[2] + " = sp_" + TABLE_ZONA_COLLUMS[2] +
	            " ," + TABLE_ZONA_COLLUMS[3] + " = sp_" + TABLE_ZONA_COLLUMS[3] +
	            " WHERE " + TABLE_ZONA_COLLUMS[0] + " = sp_" + TABLE_ZONA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_ZONA_NAME, statements, args);

	}

	public static void createSPEliminar_Zona(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ZONA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ZONA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_ZONA_NAME, statements, args);
	}

	/*
    public static void SPCriar_Zona(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)){
            insertInDbTable(connection, TABLE_ZONA_NAME, values);

        }
    }
    public static void SPAlterar_Zona(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue , int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_ZONA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_ZONA_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_ZONA_NAME, values, param, paramValue);
        }


    }
    public static void SPEliminar_Zona(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_ZONA_NAME, param, paramValue);
        }
    }
*/
	//</editor-fold>
	//---------------------------------- Medição ----------------------------------
	//<editor-fold desc="SPMedicao">

	public static void createSPInserir_Medicao(Connection connection) throws SQLException {

	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,1,TABLE_MEDICAO_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_MEDICAO_DATATYPES,1, TABLE_MEDICAO_DATATYPES.length   )
	    );


	    String Variable_LimiteInferior_name = "medicao_" + TABLE_SENSOR_COLLUMS[3] ;
	    String Variable_LimiteSuperior_name = "medicao_" + TABLE_SENSOR_COLLUMS[4] ;

		String Variable_LimiteInferior = "DECLARE " + Variable_LimiteInferior_name + " " + TABLE_SENSOR_DATATYPES[3] +";";
		String Variable_LimiteSuperior = "DECLARE " + Variable_LimiteSuperior_name + " " + TABLE_SENSOR_DATATYPES[4] +";";
		String Set_LimiteInferior = "SELECT " + TABLE_SENSOR_COLLUMS[3] +" INTO " + Variable_LimiteInferior_name + " FROM " + TABLE_SENSOR_NAME + " WHERE " +TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] + "; ";
		String Set_LimiteSuperior = "SELECT " + TABLE_SENSOR_COLLUMS[4] +" INTO " + Variable_LimiteSuperior_name + " FROM " + TABLE_SENSOR_NAME + " WHERE " +TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] + "; ";

		String insert = generateINSERT(TABLE_MEDICAO_NAME, Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,1,TABLE_MEDICAO_COLLUMS.length));

		String finalStatements =
				"\n" + Variable_LimiteInferior
				+"\n" + Variable_LimiteSuperior
				+"\n" + Set_LimiteInferior
				+"\n" + Set_LimiteSuperior;

		finalStatements += "IF sp_"+TABLE_MEDICAO_COLLUMS[4] + " >= " + Variable_LimiteInferior_name + " AND sp_"+TABLE_MEDICAO_COLLUMS[4]+" < "+Variable_LimiteSuperior_name + " THEN\n" + insert + " ;END IF";


	    createStoredProcedure(connection, SP_INSERIR_MEDICAO_NAME, finalStatements, args);

	}

	public static void createSPAlterar_Medicao(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_MEDICAO_COLLUMS,TABLE_MEDICAO_DATATYPES);

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

	 /*
    public static void SPCriar_Medicao(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            insertInDbTable(connection, TABLE_MEDICAO_NAME, values);
        }
    }

    public static void SPAlterar_Medicao(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_MEDICAO_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection,TABLE_MEDICAO_NAME,columns,param,paramValue);
            updateFromDbTable(connection, TABLE_MEDICAO_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Medicao(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_MEDICAO_NAME, param, paramValue);
        }
    }
*/
	//</editor-fold>
	//---------------------------------- Sensor ----------------------------------
	//<editor-fold desc="SPSensor">

	public static void createSPInserir_Sensor(Connection connection) throws SQLException {
		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1,TABLE_SENSOR_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_SENSOR_DATATYPES,1,TABLE_SENSOR_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_SENSOR_NAME,
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1,TABLE_SENSOR_COLLUMS.length)
	    );

	    createStoredProcedure(connection, SP_INSERIR_SENSOR_NAME, statements, args);

	}

	public static void createSPAlterar_Sensor(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_SENSOR_COLLUMS,TABLE_SENSOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_SENSOR_NAME + " SET " + TABLE_SENSOR_COLLUMS[1] + " = sp_" + TABLE_MEDICAO_COLLUMS[1] +
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
	/*
		public static void SPCriar_Sensor(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
			if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
				insertInDbTable(connection, TABLE_SENSOR_NAME, values);
			}
		}

		public static void SPAlterar_Sensor(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
			if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
				//selectElementFromDbTable(connection, TABLE_SENSOR_NAME, columns, param, paramValue);
				result = getElementFromDbTable(connection, TABLE_SENSOR_NAME, columns, param, paramValue);
				updateFromDbTable(connection, TABLE_SENSOR_NAME, values, param, paramValue);
			}
		}

		public static void SPEliminar_Sensor(Connection connection, String param, String paramValue, int userID) throws SQLException {
			if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
				deleteFromDbTable(connection, TABLE_SENSOR_NAME, param, paramValue);
			}
		}

	 */
	//</editor-fold>
	//---------------------------------- User ----------------------------------

	//<editor-fold desc="SPUser">
	private static String[] createSPInserir_User_Base(String role) {
		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_UTILIZADOR_COLLUMS,1,TABLE_UTILIZADOR_COLLUMS.length-1),
	            Arrays.copyOfRange(TABLE_UTILIZADOR_DATATYPES,1,TABLE_UTILIZADOR_DATATYPES.length-1)
	    );
	    String statements = generateINSERT(TABLE_UTILIZADOR_NAME,
	            Arrays.copyOfRange(TABLE_UTILIZADOR_COLLUMS,1,TABLE_UTILIZADOR_COLLUMS.length-1)
	    ) + ";\n";

	    String create = "SET @query = CONCAT('CREATE USER \"', sp_"+TABLE_UTILIZADOR_COLLUMS[2]+
				", '\"@\"', 'localhost', '\" IDENTIFIED BY \"', sp_"+TABLE_UTILIZADOR_COLLUMS[3]+", '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt;";

	    String addRole = "SET @query = CONCAT('GRANT \"', "+role+
				", '\" TO \"', sp_"+TABLE_UTILIZADOR_COLLUMS[2]+", '\"@\"', 'localhost', '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt;";

	    String setDefaultRole = "SET @query = CONCAT('SET DEFAULT ROLE \"', "+role+
				", '\" FOR \"', sp_"+TABLE_UTILIZADOR_COLLUMS[2]+", '\"@\"', 'localhost', '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt";

	    statements += create + "\n" + addRole + "\n" + setDefaultRole;

		return new String[]{statements,args};
	}

	public static void createSPInserir_User_Investigador(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_INVESTIGADOR);

		createStoredProcedure(connection, SP_INSERIR_USER_INVESTIGADOR_NAME, args_statements[0], args_statements[1]);
	}
	public static void createSPInserir_User_Tecnico(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_TECNICO);

		createStoredProcedure(connection, SP_INSERIR_USER_TECNICO_NAME, args_statements[0], args_statements[1]);
	}
	public static void createSPInserir_User_Admin(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_ADMIN);

		createStoredProcedure(connection, SP_INSERIR_USER_ADMIN_NAME, args_statements[0], args_statements[1]);
	}
	public static void createSPInserir_User_MqttReader(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_MQTTREADER);

		createStoredProcedure(connection, SP_INSERIR_USER_MQTTREADER_NAME, args_statements[0], args_statements[1]);
	}

	public static void createSPAlterar_User(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_UTILIZADOR_COLLUMS,TABLE_UTILIZADOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_UTILIZADOR_NAME + " SET " + TABLE_UTILIZADOR_COLLUMS[1] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[1] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[2] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[2] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[3] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[3] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[4] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[4] +
	            " WHERE " + TABLE_UTILIZADOR_COLLUMS[0] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_USER_NAME, statements, args);

	}

	public static void createSPEliminar_User(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_UTILIZADOR_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_UTILIZADOR_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_USER_NAME, statements, args);
	}
	/*
		public static void SPCriar_User(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
			//if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
			insertInDbTable(connection, TABLE_UTILIZADOR_NAME, values);
			//}
		}

		public static void SPAlterar_User(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
			if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
				//selectElementFromDbTable(connection,TABLE_UTILIZADOR_NAME,columns,param,paramValue);
				result = getElementFromDbTable(connection, TABLE_UTILIZADOR_NAME, columns, param, paramValue);
				updateFromDbTable(connection, TABLE_UTILIZADOR_NAME, values, param, paramValue);
			}
		}

		public static void SPEliminar_User(Connection connection, int userID) throws SQLException {
			//if(typeOfUser(connection,userID).equals(USER_ADMIN)) {
			deleteFromDbTable(connection, TABLE_UTILIZADOR_NAME, "IdUtilizador", String.valueOf(userID));
			//}
		}
	*/
	//</editor-fold>
	//---------------------------------- Cultura ----------------------------------
	//<editor-fold desc="SPCultura">
	public static void createSPInserir_Cultura(Connection connection) throws SQLException {
		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_CULTURA_COLLUMS,1,TABLE_CULTURA_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_CULTURA_DATATYPES,1,TABLE_CULTURA_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_CULTURA_NAME,
	            Arrays.copyOfRange(TABLE_CULTURA_COLLUMS,1,TABLE_CULTURA_COLLUMS.length)
	    );

	    createStoredProcedure(connection, SP_INSERIR_CULTURA_NAME, statements, args);

	}

	public static void createSPAlterar_Cultura(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_CULTURA_COLLUMS,TABLE_CULTURA_DATATYPES);

	    String statements = "UPDATE " + TABLE_CULTURA_NAME + " SET " + TABLE_CULTURA_COLLUMS[1] + " = sp_" + TABLE_CULTURA_COLLUMS[1] +
	            " ," + TABLE_CULTURA_COLLUMS[2] + " = sp_" + TABLE_CULTURA_COLLUMS[2] +
	            " ," + TABLE_CULTURA_COLLUMS[3] + " = sp_" + TABLE_CULTURA_COLLUMS[3] +
	            " WHERE " + TABLE_CULTURA_COLLUMS[0] + " = sp_" + TABLE_CULTURA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_CULTURA_NAME, statements, args);

	}

	public static void createSPEliminar_Cultura(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_CULTURA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_CULTURA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_CULTURA_NAME, statements, args);
	}
	/*
	  public static void SPCriar_Cultura(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
		  if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
			  insertInDbTable(connection, TABLE_CULTURA_NAME, values);
		  }
	  }

	  public static void SPAlterar_Cultura(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
		  if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
			  //selectElementFromDbTable(connection, TABLE_CULTURA_NAME, columns, param, paramValue);
			  result = getElementFromDbTable(connection, TABLE_CULTURA_NAME, columns, param, paramValue);
			  updateFromDbTable(connection, TABLE_CULTURA_NAME, values, param, paramValue);
		  }
	  }

	  public static void SPEliminar_Cultura(Connection connection, String param, String paramValue, int userID) throws SQLException {
		  if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
			  deleteFromDbTable(connection, TABLE_CULTURA_NAME, param, paramValue);
		  }
	  }

   */
	//</editor-fold>
	//---------------------------------- ParametroCultura ----------------------------------
	//<editor-fold desc="SPParametroCultura">
	public static void createSPInserir_ParametroCultura(Connection connection) throws SQLException {

		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,1,TABLE_PARAMETROCULTURA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_DATATYPES,1, TABLE_PARAMETROCULTURA_DATATYPES.length   )
	    );

	    String statements = generateINSERT(TABLE_PARAMETROCULTURA_NAME, Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,1,TABLE_PARAMETROCULTURA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_PARAMETRO_CULTURA_NAME, statements, args);

	}

	public static void createSPAlterar_ParametroCultura(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_PARAMETROCULTURA_COLLUMS,TABLE_PARAMETROCULTURA_DATATYPES);



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

    /*public static void SPCriar_ParametroCultura(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_INVESTIGATOR)) {
            insertInDbTable(connection, TABLE_PARAMETROCULTURA_NAME, values);
        }
    }

    public static void SPAlterar_ParametroCultura(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_INVESTIGATOR)) {
            //selectElementFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, columns, param, paramValue);
            updateFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_ParametroCultura(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_INVESTIGATOR)) {
            deleteFromDbTable(connection, TABLE_PARAMETROCULTURA_NAME, param, paramValue);
        }
    }

     */
	//</editor-fold>
	//---------------------------------- Alerta ----------------------------------
	//<editor-fold desc="SPAlerta">

	public static void createSPInserir_Alerta(Connection connection) throws SQLException {

		String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1,TABLE_ALERTA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_ALERTA_DATATYPES,1, TABLE_ALERTA_DATATYPES.length   )
	    );

	    String statements = generateINSERT(TABLE_ALERTA_NAME, Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1,TABLE_ALERTA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_ALERTA_NAME, statements, args);

	}

	public static void createSPAlterar_Alerta(Connection connection) throws SQLException {

		String args =generateARGUMENTS(TABLE_ALERTA_COLLUMS,TABLE_ALERTA_DATATYPES);


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
	            " ," + TABLE_ALERTA_COLLUMS[11] + " = sp_" + TABLE_ALERTA_COLLUMS[11] +
	            " WHERE " + TABLE_ALERTA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_ALERTA_NAME, statements, args);

	}

	public static void createSPEliminar_Alerta(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ALERTA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ALERTA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_ALERTA_NAME, statements, args);
	}
	public static void createSPSelect_Alerta(Connection connection) throws SQLException {

		String args = "IN sp_"+TABLE_ALERTA_COLLUMS[8] + " " + TABLE_ALERTA_DATATYPES[8];
	    String statements = "SELECT * FROM " + TABLE_ALERTA_NAME + " WHERE sp_" + TABLE_ALERTA_COLLUMS[8] + " = " + TABLE_ALERTA_NAME+"."+TABLE_ALERTA_COLLUMS[8];

	    createStoredProcedure(connection, SP_SELECT_ALERTA_NAME, statements, args);
	}

	public static ResultSet callSPSelect_Alerta(Connection connection, int IdUtilizador) throws SQLException {
		CallableStatement cst = connection.prepareCall("{call " +SP_SELECT_ALERTA_NAME +"(?)}");
		cst.setInt(1,IdUtilizador);


		return cst.executeQuery();
	}


/*
    public static void SPCriar_Alerta(Connection connection, ArrayList<Pair> values, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
            insertInDbTable(connection, TABLE_ALERTA_NAME, values);
        }
    }


    public static void SPAlterar_Alerta(Connection connection, String[] columns, ArrayList<Pair> values, ArrayList<String> result, String param, String paramValue, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
            //selectElementFromDbTable(connection, TABLE_ALERTA_NAME, columns, param, paramValue);
            result = getElementFromDbTable(connection, TABLE_ALERTA_NAME, columns, param, paramValue);
            updateFromDbTable(connection, TABLE_ALERTA_NAME, values, param, paramValue);
        }
    }

    public static void SPEliminar_Alerta(Connection connection, String param, String paramValue, int userID) throws SQLException {
        if (typeOfUser(connection, userID).equals(USER_ADMIN)) {
            deleteFromDbTable(connection, TABLE_ALERTA_NAME, param, paramValue);
        }
    }

 */
	//</editor-fold>

	//</editor-fold>

}
