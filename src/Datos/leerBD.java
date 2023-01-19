package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Logica.operaciones;

/**
 * Clase que permite leer los datos que hay en mi BD obligatoria3
 * 
 * @author GALA
 *
 */
public class leerBD {

	/**
	 * Metodo CORRECTO_ que recibe por parametro:sentencia SQL y localizacion en la
	 * BD obligatoria 3 y que devuelve el resultado obtenido de la consulta
	 * 
	 * @param sentencia
	 * @param columna
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static ArrayList<String> leerBD2(String sentencia, String columna)
			throws ClassNotFoundException, SQLException {

		Connection conexion = null; // establecemos un objeto que nos permite conectarnos a un recurso externos
		Statement sentenciaSQL = null;// Statemen = sentencia que lanzamos contra una BD
		ResultSet rs = null; // guarda el resultado de los registros de la consulta realizada
		// int propietario = 1; //
		ArrayList<String> bd = new ArrayList<String>();
		String sql = "";
		String resultado = "";

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/obligatoria3", "root", "");
			

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			sql = sentencia;

			rs = sentenciaSQL.executeQuery(sql); // ejecuta consulta SELECT*

			// chequeo que el result set no sea vacío, moviendo el cursor a la
			// primer fila. (El cursor inicia antes de la primer fila)
			while (rs.next()) {
				// Si hay resultados obtengo el valor.

				// System.out.println( rs.getString(columna)); // columna de donde saca la
				// informacion
				operaciones.añadirBDenLista(bd, rs.getString(columna));
				resultado = rs.getString(columna);

			}
			// System.out.println(bd.toString());

		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {

			try {
				sentenciaSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// System.out.println("Conectado/desconectado/LeerBD");
		return bd;

	}

	

}
