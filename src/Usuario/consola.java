package Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class consola {

	public static void menu() {
		System.out.println("BIENVENIDO AL TRIVIAL DE GEOGRAFIA!");
		System.out.println("1. Jugar");
		System.out.println("2. Añadir preguntas ");
		System.out.println("3. Modificar preguntas");
		System.out.println("4. Eliminar preguntas");
		System.out.println("5. Salir");
		System.out.println("\nPulse una opcion");
	}

	public static int scannerInt() {
		Scanner lector = new Scanner(System.in);
		int num = 0;

		try {

			num = lector.nextInt();

		} catch (InputMismatchException ex) {
			System.err.println("Error fatal, no se ha introducido un número");

		} catch (Exception e) {
			System.err.println("ERROR FATAL, dato no corresponde a número");
		}

		return num;
	}

	public static String scannerString() {
		Scanner lector = new Scanner(System.in);
		String respuesta = " ";

		try {

			respuesta = lector.nextLine();

		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}

		return respuesta;
	}

	public static char scannerChar() {
		Scanner lector = new Scanner(System.in);
		char respuesta = ' ';

		try {
			respuesta = lector.next().charAt(0);
			respuesta = Character.toUpperCase(respuesta);

		} catch (InputMismatchException ex) {
			System.err.println("ERROR FATAL, no se ha introducido una letra");
			respuesta = lector.next().charAt(0);
		} catch (Exception e) {
			System.err.println("ERROR FATAL");
		}

		return respuesta;
	}

	// ------------------por si lo tengo que usar-------------//
	public static String absorverScanner() {
		Scanner lector = new Scanner(System.in);
		return lector.next();
	}

}
