package Logica;

import java.util.InputMismatchException;
import java.util.Scanner;

import Usuario.consola;

public class validaciones {

	public static int validarRango(int uno, int dos) {
		int num = 0;

		num = consola.scannerInt();
		// valido rango

		try {

			do {

				if (num < uno || num > dos) {
					System.err.println("Dime un numero del menu");
					num = consola.scannerInt();
				}

			} while (num < uno || num > dos);

		} catch (InputMismatchException ex) {
			System.err.println("ERROR FATAL, no se ha introducido un numero");

		}
		return num;
	}

	public static String validarString(String dato) {

		boolean valido;

		try {

			do {

				valido = true;

				if (dato.length() > 30) {
					valido = false;
					System.err.println("Dato no puede ser mayor de 30 caracteres");
					dato = consola.scannerString();

				}

			} while (!valido);
		} catch (InputMismatchException e) {
			System.err.println("ERROR FATAL, no se ha podido procesar el dato introducido");
		}

		return dato;
	}

	public static char validarChar(char dato) {

		boolean valido;

		try {

			do {
				valido = false;

				if (dato != 'S' && dato != 'N') {
					System.err.println("Introduzca S o N");
					dato = consola.scannerChar();
					dato = Character.toUpperCase(dato);
					valido = true;
				}

			} while (valido);

		} catch (InputMismatchException e) {
			System.err.println("ERROR FATAL, no se ha podido procesar el dato introducido");
		}

		return dato;

	}

}
