package Logica;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import Usuario.consola;

public class operaciones {

	public static void añadirBDenLista(ArrayList<String> bd, String dato) {

		bd.add(dato);
	}

	public static void añadirBDPregunta(ArrayList<String> bd, ArrayList<String> pregunta) {
		pregunta.add(bd.get(0));
	}

	public static void imprimeRespuestas(ArrayList<String> bd) {

		Collections.shuffle(bd);

		System.out.println("1. " + bd.get(0));
		System.out.println("2. " + bd.get(1));
		System.out.println("3. " + bd.get(2));
		System.out.println("4. " + bd.get(3));

	}

	public static void imprimePregunta(ArrayList<String> bd) {
		System.out.println("Cual es la capital de " + bd.get(0));

	}

	public static String idAzar(ArrayList<String> azar) {
		String num;

		Collections.shuffle(azar);
		num = azar.get(0);

		return num;
	}

	public static void listarPreguntasdeBD(ArrayList<String> bd) {
		int num = 0;

		try {

			System.out.println("Estos son los paises que hay:");

			for (int i = 0; i < bd.size(); i++) {
				num++;
				System.out.println(num + ". " + bd.get(i));

			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR FATAL, el ranto de datos no son correctos");
		}
	}

	public static void eliminarDatosArray(ArrayList<String> bd) {

		try {
			for (int i = 0; i < bd.size(); i++) {
				bd.remove(i);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR FATAL, el ranto de datos no son correctos");
		}

	}

	public static void añadirCiudades(int id) throws ClassNotFoundException, SQLException {
		String ciudades;

		try {

			System.out.println("Dime la capital");
			ciudades = consola.scannerString();
			ciudades = validaciones.validarString(ciudades);
			insertarDatos.insertar("INSERT INTO respuestas (idrespuesta,fidpregunta,respuesta) VALUES (NULL," + id
					+ ",'" + ciudades + "');");

			System.out.println("Dime otra ciudad del pais");
			ciudades = consola.scannerString();
			ciudades = validaciones.validarString(ciudades);
			insertarDatos.insertar("INSERT INTO respuestas (idrespuesta,fidpregunta,respuesta) VALUES (NULL," + id
					+ ",'" + ciudades + "');");

			System.out.println("Dime otra ciudad del pais");
			ciudades = consola.scannerString();
			ciudades = validaciones.validarString(ciudades);
			insertarDatos.insertar("INSERT INTO respuestas (idrespuesta,fidpregunta,respuesta) VALUES (NULL," + id
					+ ",'" + ciudades + "');");

			System.out.println("Dime otra ciudad del pais");
			ciudades = consola.scannerString();
			ciudades = validaciones.validarString(ciudades);
			insertarDatos.insertar("INSERT INTO respuestas (idrespuesta,fidpregunta,respuesta) VALUES (NULL," + id
					+ ",'" + ciudades + "');");

		} catch (SQLException e) {
			System.err.println("Error al añadir las ciudades");
		}

	}

	public static void modificarRespuestas(String num) throws ClassNotFoundException, SQLException {
		String pais;

		try {
			

			System.out.println("Introduce la capital que corresponda");
			pais = consola.scannerString();
			pais = validaciones.validarString(pais);
			insertarDatos.insertar("UPDATE respuestas SET respuesta='" + pais + ":' WHERE fidpregunta=" + num + "; ");

			System.out.println("Introduce otra ciudad");
			pais = consola.scannerString();
			pais = validaciones.validarString(pais);
			insertarDatos.insertar("UPDATE respuestas SET respuesta='" + pais + ":' WHERE fidpregunta=" + num + "; ");

			System.out.println("Introduce otra ciudad");
			pais = consola.scannerString();
			pais = validaciones.validarString(pais);
			insertarDatos.insertar("UPDATE respuestas SET respuesta='" + pais + ":' WHERE fidpregunta=" + num + "; ");

			System.out.println("Introduce otra ciudad");
			pais = consola.scannerString();
			pais = validaciones.validarString(pais);
			insertarDatos.insertar("UPDATE respuestas SET respuesta='" + pais + ":' WHERE fidpregunta=" + num + "; ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
