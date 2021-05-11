package sql;

import sql.variables.*;

import java.sql.Connection;
import java.sql.SQLException;

public class CulturaSP {

	/*TODO
			   SP select Alerta
			   inserir alerta automaticamente (MQTTREADER)

			* */
	//---------------------------------- SPs ----------------------------------
	//<editor-fold desc="SP">
	public static void createAllSP(Connection connection) throws SQLException {
	    Table_Zona.createSPInserir_Zona                           (connection);
	    Table_Zona.createSPAlterar_Zona                           (connection);
	    Table_Zona.createSPEliminar_Zona                          (connection);

	    Table_Medicao.createSPInserir_Medicao                     (connection);
	    Table_Medicao.createSPAlterar_Medicao                     (connection);
	    Table_Medicao.createSPEliminar_Medicao                    (connection);

	    Table_Sensor.createSPInserir_Sensor                       (connection);
	    Table_Sensor.createSPAlterar_Sensor                       (connection);
	    Table_Sensor.createSPEliminar_Sensor                      (connection);

	    Table_Utilizador.createSPInserir_User_Investigador        (connection);
	    Table_Utilizador.createSPInserir_User_Tecnico             (connection);
	    Table_Utilizador.createSPInserir_User_Admin       		  (connection);
	    Table_Utilizador.createSPInserir_User_MqttReader          (connection);
	    Table_Utilizador.createSPAlterar_User                     (connection);
	    Table_Utilizador.createSPEliminar_User                    (connection);

	    Table_Cultura.createSPInserir_Cultura                     (connection);
	    Table_Cultura.createSPAlterar_Cultura                     (connection);
	    Table_Cultura.createSPEliminar_Cultura                    (connection);

	    Table_ParametroCultura.createSPInserir_ParametroCultura   (connection);
	    Table_ParametroCultura.createSPAlterar_ParametroCultura   (connection);
	    Table_ParametroCultura.createSPEliminar_ParametroCultura  (connection);

	    Table_Alerta.createSPInserir_Alerta                       (connection);
	    Table_Alerta.createSPAlterar_Alerta                       (connection);
	    Table_Alerta.createSPEliminar_Alerta                      (connection);
		Table_Alerta.createSPSelect_Alerta                        (connection);
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

	public static String generateINSERTForUser(String tableMedicaoName, String[] tableCollums,String role) {
		String insertString = "INSERT INTO " + tableMedicaoName + " (";
		for (String value :
				tableCollums) {
			insertString += " " + value + ",";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ") VALUES ( ";
		for (String value :
				tableCollums) {
			if(!value.equals(Table_Utilizador.TABLE_UTILIZADOR_COLLUMS[4]))
				insertString += " sp_" + value + ",";
			else
				insertString += " '" + role + "',";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ");\n";

		return insertString;
	}


}
