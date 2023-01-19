package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase permite conectarme a los datos de mi BD obligatoria3 mediante el
 * metodo basico de conectar
 * 
 * @author GALA
 *
 */
public class conexionBD {

	// 30/3/2022

	/**
	 * metodo que permite conectar Eclipse con mi BD
	 */

	public static void conectar() {

		Connection conexion = null;
		// Statement sentenciaSQL = null;

		try {
			// error
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Intenta establecer una conexión con la URL de la base de datos dada.
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/obligatoria3", "root", "");
			////// si usamos la nuestra=localhost=
			////// "jdbc:mysql://localhost","root","contraseña"
			// mi puerto es 3306

			// conexion =
			// DriverManager.getConnection("jdbc:mysql://mysql-5603.dinaserver.com",//ENALCE
			// "betec_admin", "Morcilla01.");//NOMBRE Y CONTRASEÑA

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("El login es incorrecto o no hay conexión con la base de datos");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CONEXION");
			e.printStackTrace();

		} finally {

			// cerrar la conexión
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Se ejecuta finalmente");
		}

	}

}
