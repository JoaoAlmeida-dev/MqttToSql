package sql.variables;

import sql.CulturaSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class Table_Zona {
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
	//<editor-fold desc="Stored Procedure Constants">
	public static final String SP_INSERIR_ZONA_NAME                 = "Inserir_Zona";
	public static final String SP_ALTERAR_ZONA_NAME                 = "Alterar_Zona";
	public static final String SP_ELIMINAR_ZONA_NAME                = "Eliminar_Zona";

	/**
	 * SP para inserir uma zona na db
	 *
	 * @param connection conecção mysql
	 * @throws SQLException
	 */
	public static void createSPInserir_Zona(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1, TABLE_ZONA_COLLUMS.length       ),
	            Arrays.copyOfRange(TABLE_ZONA_DATATYPES,1, TABLE_ZONA_DATATYPES.length   )
	    );
	    String statements = CulturaSP.generateINSERT(TABLE_ZONA_NAME, Arrays.copyOfRange(TABLE_ZONA_COLLUMS,1, TABLE_ZONA_COLLUMS.length));

	    createStoredProcedure(connection, SP_INSERIR_ZONA_NAME, statements, args);

	}

	/**
	 * SP para alterar uma zona da db
	 *
	 * @param connection conecção mysql
	 * @throws SQLException
	 */
	public static void createSPAlterar_Zona(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_ZONA_COLLUMS, TABLE_ZONA_DATATYPES);

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
}
