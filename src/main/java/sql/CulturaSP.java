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
	    TableZona.createSPInserir_Zona                           (connection);
	    TableZona.createSPAlterar_Zona                           (connection);
	    TableZona.createSPEliminar_Zona                          (connection);

	    TableMedicao.createSPInserir_Medicao                     (connection);
	    TableMedicao.createSPAlterar_Medicao                     (connection);
	    TableMedicao.createSPEliminar_Medicao                    (connection);

	    TableSensor.createSPInserir_Sensor                       (connection);
	    TableSensor.createSPAlterar_Sensor                       (connection);
	    TableSensor.createSPEliminar_Sensor                      (connection);

	    TableUtilizador.createSPInserir_User_Investigador        (connection);
	    TableUtilizador.createSPInserir_User_Tecnico             (connection);
	    TableUtilizador.createSPInserir_User_Admin       		  (connection);
	    TableUtilizador.createSPInserir_User_MqttReader          (connection);
	    TableUtilizador.createSPAlterar_User                     (connection);
	    TableUtilizador.createSPEliminar_User                    (connection);

	    TableCultura.createSPInserir_Cultura                     (connection);
	    TableCultura.createSPAlterar_Cultura                     (connection);
	    TableCultura.createSPEliminar_Cultura                    (connection);

	    TableParametroCultura.createSPInserir_ParametroCultura   (connection);
	    TableParametroCultura.createSPAlterar_ParametroCultura   (connection);
	    TableParametroCultura.createSPEliminar_ParametroCultura  (connection);

	    TableAlerta.createSPInserir_Alerta                       (connection);
	    TableAlerta.createSPAlterar_Alerta                       (connection);
	    TableAlerta.createSPEliminar_Alerta                      (connection);
		TableAlerta.createSPSelect_Alerta                        (connection);
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
			if(!value.equals(TableUtilizador.TABLE_UTILIZADOR_COLLUMS[4]))
				insertString += " sp_" + value + ",";
			else
				insertString += " '" + role + "',";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ");\n";

		return insertString;
	}


}
