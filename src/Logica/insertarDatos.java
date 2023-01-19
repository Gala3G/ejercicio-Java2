package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para insertar, actualizar y eliminar datos en la BD de mySQL
 * Obligatoria 3 desde Eclipse
 * 
 * @author GALA
 *
 */
public class insertarDatos {

	/**
	 * Este metodo permite insertar en la BD lo que el usuario introduzco por
	 * consola Esta método está en construcción todavía, pendiente.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void insertar(String sentencia) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;

		String sql = "";

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/obligatoria3", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			// almaceno el resultado de la sql en un resulset (conjunto de registros)

			sql = sentencia;// pon aqui la sentencia que quieras leer de BD

			// System.out.println(sql);

			resultado = sentenciaSQL.executeUpdate(sql);// para INSERT, update o delete

			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			sentenciaSQL.close();
			conexion.close();

		}

		// System.out.println("Conectado/desconectado");

	}

}
