package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;
import static sql.SqlVariables.*;

public class CulturaSP {
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

	    createSPInserir_User                    (connection);
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
	}

	public static String generateARGUMENTS(String[] tableCollums, String[] tableDatatypes) {
	    String args ="";
	    if(tableCollums.length == tableDatatypes.length){
	        for(int i =0 ; i<tableCollums.length-1 && i<tableDatatypes.length-1 ;i++){
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

	    String procedureName = "Inserir_Zona";

	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1,TABLE_ZONA_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_ZONA_DATATYPES,1,TABLE_ZONA_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_ZONA_NAME, Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1,TABLE_ZONA_COLLUMS.length));

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	/**
	 * SP para alterar uma zona da db
	 *
	 * @param connection conecção mysql
	 * @throws SQLException
	 */
	public static void createSPAlterar_Zona(Connection connection) throws SQLException {

	    String procedureName = "Alterar_Zona";
	    String args =generateARGUMENTS(TABLE_ZONA_COLLUMS,TABLE_ZONA_DATATYPES);

	    String statements = "UPDATE " + TABLE_ZONA_NAME + " SET " + TABLE_ZONA_COLLUMS[1] + " = sp_" + TABLE_ZONA_COLLUMS[1] +
	            " ," + TABLE_ZONA_COLLUMS[2] + " = sp_" + TABLE_ZONA_COLLUMS[2] +
	            " ," + TABLE_ZONA_COLLUMS[3] + " = sp_" + TABLE_ZONA_COLLUMS[3] +
	            " WHERE " + TABLE_ZONA_COLLUMS[0] + " = sp_" + TABLE_ZONA_COLLUMS[0];

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_Zona(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_Zona";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ZONA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ZONA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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

	    String procedureName = "Inserir_Medicao";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,1,TABLE_MEDICAO_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_MEDICAO_DATATYPES,1, TABLE_MEDICAO_DATATYPES.length   )
	    );

	    String statements = generateINSERT(TABLE_MEDICAO_NAME, Arrays.copyOfRange(TABLE_MEDICAO_COLLUMS,0,TABLE_MEDICAO_COLLUMS.length));

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_Medicao(Connection connection) throws SQLException {

	    String procedureName = "Alterar_Medicao";
	    String args =generateARGUMENTS(TABLE_MEDICAO_COLLUMS,TABLE_MEDICAO_DATATYPES);

	    String statements = "UPDATE " + TABLE_MEDICAO_NAME + " SET " + TABLE_MEDICAO_COLLUMS[1] + " = sp_" + TABLE_MEDICAO_COLLUMS[1] +
	            " ," + TABLE_MEDICAO_COLLUMS[2] + " = sp_" + TABLE_MEDICAO_COLLUMS[2] +
	            " ," + TABLE_MEDICAO_COLLUMS[3] + " = sp_" + TABLE_MEDICAO_COLLUMS[3] +
	            " WHERE " + TABLE_MEDICAO_COLLUMS[0] + " = sp_" + TABLE_MEDICAO_COLLUMS[0];

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_Medicao(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_Medicao";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_MEDICAO_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_MEDICAO_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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
	    String procedureName = "Inserir_Sensor";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1,TABLE_SENSOR_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_SENSOR_DATATYPES,1,TABLE_SENSOR_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_SENSOR_NAME,
	            Arrays.copyOfRange(TABLE_SENSOR_COLLUMS,1,TABLE_SENSOR_COLLUMS.length)
	    );

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_Sensor(Connection connection) throws SQLException {

	    String procedureName = "Alterar_Sensor";
	    String args =generateARGUMENTS(TABLE_SENSOR_COLLUMS,TABLE_SENSOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_SENSOR_NAME + " SET " + TABLE_SENSOR_COLLUMS[1] + " = sp_" + TABLE_MEDICAO_COLLUMS[1] +
	            " ," + TABLE_SENSOR_COLLUMS[2] + " = sp_" + TABLE_SENSOR_COLLUMS[2] +
	            " ," + TABLE_SENSOR_COLLUMS[3] + " = sp_" + TABLE_SENSOR_COLLUMS[3] +
	            " ," + TABLE_SENSOR_COLLUMS[4] + " = sp_" + TABLE_SENSOR_COLLUMS[4] +
	            " WHERE " + TABLE_SENSOR_COLLUMS[0] + " = sp_" + TABLE_SENSOR_COLLUMS[0];

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_Sensor(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_Sensor";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_SENSOR_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_SENSOR_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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
	public static void createSPInserir_User(Connection connection) throws SQLException {
	    String procedureName = "Inserir_User";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_UTILIZADOR_COLLUMS,1,TABLE_UTILIZADOR_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_UTILIZADOR_DATATYPES,1,TABLE_UTILIZADOR_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_UTILIZADOR_NAME,
	            Arrays.copyOfRange(TABLE_UTILIZADOR_COLLUMS,1,TABLE_UTILIZADOR_COLLUMS.length)
	    );

	    //statements += ;

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_User(Connection connection) throws SQLException {

	    String procedureName = "Alterar_User";
	    String args =generateARGUMENTS(TABLE_UTILIZADOR_COLLUMS,TABLE_UTILIZADOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_UTILIZADOR_NAME + " SET " + TABLE_UTILIZADOR_COLLUMS[1] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[1] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[2] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[2] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[3] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[3] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[4] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[4] +
	            " WHERE " + TABLE_UTILIZADOR_COLLUMS[0] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[0];

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_User(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_User";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_UTILIZADOR_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_UTILIZADOR_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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
	    String procedureName = "Inserir_Cultura";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_CULTURA_COLLUMS,1,TABLE_CULTURA_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_CULTURA_DATATYPES,1,TABLE_CULTURA_DATATYPES.length   )
	    );
	    String statements = generateINSERT(TABLE_CULTURA_NAME,
	            Arrays.copyOfRange(TABLE_CULTURA_COLLUMS,1,TABLE_CULTURA_COLLUMS.length)
	    );

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_Cultura(Connection connection) throws SQLException {

	    String procedureName = "Alterar_Cultura";
	    String args =generateARGUMENTS(TABLE_CULTURA_COLLUMS,TABLE_CULTURA_DATATYPES);

	    String statements = "UPDATE " + TABLE_CULTURA_NAME + " SET " + TABLE_CULTURA_COLLUMS[1] + " = sp_" + TABLE_CULTURA_COLLUMS[1] +
	            " ," + TABLE_CULTURA_COLLUMS[2] + " = sp_" + TABLE_CULTURA_COLLUMS[2] +
	            " ," + TABLE_CULTURA_COLLUMS[3] + " = sp_" + TABLE_CULTURA_COLLUMS[3] +
	            " WHERE " + TABLE_CULTURA_COLLUMS[0] + " = sp_" + TABLE_CULTURA_COLLUMS[0];

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_Cultura(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_Cultura";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_CULTURA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_CULTURA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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

	    String procedureName = "Inserir_ParametroCultura";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,1,TABLE_PARAMETROCULTURA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_PARAMETROCULTURA_DATATYPES,1, TABLE_PARAMETROCULTURA_DATATYPES.length   )
	    );

	    String statements = generateINSERT(TABLE_PARAMETROCULTURA_NAME, Arrays.copyOfRange(TABLE_PARAMETROCULTURA_COLLUMS,0,TABLE_PARAMETROCULTURA_COLLUMS.length));

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_ParametroCultura(Connection connection) throws SQLException {

	    String procedureName = "Alterar_ParametroCultura";
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

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_ParametroCultura(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_ParametroCultura";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_PARAMETROCULTURA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_PARAMETROCULTURA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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

	    String procedureName = "Inserir_Alerta";
	    String args = generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1,TABLE_ALERTA_COLLUMS.length ),
	            Arrays.copyOfRange(TABLE_ALERTA_DATATYPES,1, TABLE_ALERTA_DATATYPES.length   )
	    );

	    String statements = generateINSERT(TABLE_ALERTA_NAME, Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,0,TABLE_ALERTA_COLLUMS.length));

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPAlterar_Alerta(Connection connection) throws SQLException {

	    String procedureName = "Alterar_Alerta";
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

	    createStoredProcedure(connection, procedureName, statements, args);

	}

	public static void createSPEliminar_Alerta(Connection connection) throws SQLException {

	    String procedureName = "Eliminar_Alerta";
	    String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ALERTA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ALERTA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, procedureName, statements, args);
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
