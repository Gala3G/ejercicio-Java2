// confirmar conexion:

package Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.ldap.HasControls;
import javax.swing.event.AncestorEvent;

import com.mysql.cj.util.EscapeTokenizer;
import com.mysql.cj.x.protobuf.MysqlxCursor.Open;

import Datos.leerBD;
import Logica.insertarDatos;
import Logica.operaciones;
import Logica.validaciones;

/**
 * Clase principal
 * 
 * @author GALA
 *
 */
public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ArrayList<String> azar = new ArrayList<String>();
		ArrayList<String> pregunta = new ArrayList<String>();
		ArrayList<String> respuestas = new ArrayList<String>();

		String valido;
		String num;
		String sentenciaPregunta;
		String sentenciaRespuesta;
		String pais;

		int respuestaJugador;
		int menu;
		char bucle = 0;
		int puntos = 0;
		int maxJugadas = 0;
		int id;

		do {
			consola.menu();

			menu = validaciones.validarRango(1, 5);

			switch (menu) {
			case 1:
				System.out.println("EMPEZAMOS!\n");

				try {

					do {

						// MENU - PREGUNTAS Y RESPUESTAS AL AZAR

						// 1. AUTOMATIZAR EL NUMERO DE LA id PARA QUE SIEMPRE SEA DIFERENTE LA CONSULTA

						// Creo un nuevo listado con numeros que luego serán aleatorios
						// pero tengo que saber cual es el total de ID que tiene la BD
						// tengo que sacar que id hay activos

						// 1. Elimino los posibles datos que pueda tener el Array
						operaciones.eliminarDatosArray(pregunta);
						operaciones.eliminarDatosArray(respuestas);
						operaciones.eliminarDatosArray(azar);

						// Lanzo la pregunta a la BD y recojo el listado de Id en arraylist azar
						azar = leerBD.leerBD2("SELECT idpregunta FROM TRIVIAL GROUP BY IDPREGUNTA", "idpregunta");
						num = operaciones.idAzar(azar);

						// 2. FUSIONAR LA ID al azar AL CONSULTA

						sentenciaPregunta = "SELECT*FROM trivial preguntas WHERE idpregunta=" + num
								+ " ORDER BY PREGUNTA limit 1;";

						sentenciaRespuesta = "SELECT*FROM trivial respuesta WHERE fidpregunta=" + num;

						// 3. GESTIONAR LAS PREGUNTAS Y RESPUESTAS

						pregunta = leerBD.leerBD2(sentenciaPregunta, "pregunta");
						// Imprimi la pregunta adaptando el mensaje:
						operaciones.imprimePregunta(pregunta);

						// Aqui meto el resultado de la BD de las respuestas que corresponden a la
						// pregunta;
						respuestas = leerBD.leerBD2(sentenciaRespuesta, "respuesta");
						// La primera posicion siempre será la respuesta correcta, la guardo
						valido = respuestas.get(0);
						// Aqui desordeno las respuestas y las imprimo adaptando el mensaje
						operaciones.imprimeRespuestas(respuestas);

						// 4. El jugador selecciona una respuesta, con un int

						respuestaJugador = validaciones.validarRango(1, 4);
						respuestaJugador = respuestaJugador - 1;

						System.out.println("La respuesta elegida es: " + respuestas.get(respuestaJugador));
						System.out.println("La respuesta correcta es: " + valido);
						System.out.println("-------------");

						if (respuestas.get(respuestaJugador) != valido) {
							System.out.println("0 puntos conseguidos");
						} else {
							puntos = puntos + 5;
							System.out.println("Bien! has sumado " + puntos + " puntos ");

						}

						// el jugador gana puntos segun aciertos en sus respuetas

						System.out.println("Tienes un total de " + puntos);
						System.out.println("-------------");

						// 5. Seguimos o no, podrá un máximo de 5 veces
						System.out.println("Quieres seguir?");
						System.out.println("S para seguir / N para salir ");
						bucle = consola.scannerChar();

						bucle = validaciones.validarChar(bucle);// ponlo en MAY

						maxJugadas++;

						// 6. Cuando se haya jugado 5 veces, se forzará el fin del juego
						if (maxJugadas == 5) {
							System.out.println("ESTE ES EL TOTAL DE LA PUNTUACION CONSEGUIDA! " + puntos);
						}

					} while (bucle == 'S' && maxJugadas <= 5);

				} catch (Exception e) {
					System.err.println("ERROR FATAL");
				}

				break;
			case 2:

				try {

					// MENU - AÑADIR PREGUNTAS (Paises) y RESPUESTAS (capital + 3 ciudades)

					// 1. pregunto a la base de datos cual es su ultimo ID

					azar = leerBD.leerBD2("select*from preguntas ORDER BY idpregunta DESC limit 1", "idpregunta");

					num = azar.get(0);

					// ojo, recojo un String, tengo que pasarlo a int
					id = Integer.parseInt(num);

					// 2. pido al usuario el pais que quiere añadir

					System.out.println("Dime el pais?");
					pais = consola.scannerString();
					pais = validaciones.validarString(pais);
					// Inserto el la BD tabla preguntas el pais que me haya dicho el usuario

					insertarDatos.insertar("INSERT INTO preguntas (idpregunta,pregunta) VALUES (NULL, '" + pais + ":');");

					// 3. pido al usuario que me diga las ciudades que quiere añadir

					// ahora sí que uso el ID que almacene para indicar el fidpregunta que le
					// corresponde
					// pero le sumo al ID 1 para que me cuadre con las respuestas
					System.out.println("este es NUMERO DE ID " + id);

					id = id + 1;
					System.out.println("este es NUMERO DE ID + 1 " + id);

					// 4. Añado las respuestas
					operaciones.añadirCiudades(id);

				} catch (Exception e) {
					System.err.println("ERROR FATAL");
				}

				break;
			case 3:

				try {

					// MENU - MODIFICAR PREGUNTAS

					// 1. Elimino los posibles datos que pueda tener el Array
					operaciones.eliminarDatosArray(pregunta);
					operaciones.eliminarDatosArray(azar);

					// 2. Muestro al usuario todos los paises de la BD

					pregunta = leerBD.leerBD2("SELECT*FROM preguntas", "pregunta");
					operaciones.listarPreguntasdeBD(pregunta);

					// 3. Recojo los ID
					azar = leerBD.leerBD2("SELECT*FROM preguntas", "idpregunta");

					// 4. Usuario selecciona el registro a modificar = la pregunta:

					System.out.println("\nQue numero deseas modificar?");

					respuestaJugador = validaciones.validarRango(1, pregunta.size());

					respuestaJugador = respuestaJugador - 1;
					num = azar.get(respuestaJugador);

					System.out.println("Introduce el pais");
					pais = consola.scannerString();
					pais = validaciones.validarString(pais);
					insertarDatos.insertar("UPDATE preguntas SET pregunta='" + pais + ":' WHERE idpregunta=" + num + "; ");

					// 5. Usuario modifica las respuestas

					operaciones.modificarRespuestas(num);

				} catch (Exception e) {
					System.out.println("ERROR FATAL");
				}

				break;
			case 4:

				try {

					// 1. Elimino los posibles datos que pueda tener el Array
					operaciones.eliminarDatosArray(pregunta);
					operaciones.eliminarDatosArray(azar);

					// 2. Muestro al usuario todos los paises de la BD
					pregunta = leerBD.leerBD2("SELECT pregunta FROM preguntas", "pregunta");

					operaciones.listarPreguntasdeBD(pregunta);

					// 4. Usuario selecciona el registro a modificar
					System.out.println("\nQue numero quieres eliminar?");

					// Cuadro su eleccion con el arrayList y paso la posicion a un String

					respuestaJugador = validaciones.validarRango(1, pregunta.size());
					respuestaJugador = respuestaJugador - 1;
					pais = pregunta.get(respuestaJugador);
					System.out.println("AQUI IMPRIMO EL PAIS " + pais);

					// 3. Pero antes de eliminar tengo que saber que ID tiene ese pais
					// o no podré eliminar las respuestas

					azar = leerBD.leerBD2("SELECT*FROM preguntas", "idpregunta");
					operaciones.listarPreguntasdeBD(azar);
					num = azar.get(respuestaJugador);
					id = Integer.parseInt(num);

					// 5. Elimino Pregunta y Respuestas

					// Ahora ya si, puedo eliminar la pregunta y las respuestas

					insertarDatos.insertar("DELETE FROM preguntas WHERE pregunta='" + pais + "';");
					insertarDatos.insertar("DELETE FROM respuestas WHERE fidpregunta=" + id + ";");

				} catch (Exception e) {
					System.out.println("ERROR FATAL");
				}
				break;

			default:
				break;
			}

		} while (menu != 5);

	}

}
