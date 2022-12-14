package main.act1_14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Clase que pide una palabra palíndroma, la envía al programa
 * RecibeFicheros y maneja las redirecciones de entrada y salida
 * estándar.
 * @author LuciaLM
 * @version 1.0
 */
public class PasaFicheros {
	public static void main(String[] args) {
		int exitVal = 0;
		Scanner sc = new Scanner(System.in);
		String cadena = "go";
		try {
			// Se crea un fichero de texto a partir de las entradas
			// introducidas por consola:
			PrintWriter pw =
					new PrintWriter(new FileWriter("./src/main/act1_14/todas.txt"));
			ProcessBuilder pb =
					new ProcessBuilder("java", "main.act1_14.RecibeFicheros");

			// Directorio trabajo:
			pb.directory(new File("/home/lucia/PSP/JavaProcesos/bin"));
			while(cadena.length() > 1 && cadena.charAt(0) != 0) {
				System.out.println("Introduce una cadena palíndroma o no, "
						+ "o vacía (0 para terminar): ");
				cadena = sc.nextLine();
				pw.println(cadena);

				// Redirección del flujo de entrada del proceso del programa
				// al que se llama (las palabras introducidas por teclado aquí):
				pb.redirectInput(
						new File("./src/main/act1_14/todas.txt"));
				pb.redirectOutput(
						new File("./src/main/act1_14/palindromas.txt"));
				pb.redirectError(
						new File("./src/main/act1_14/noPalindromas.txt"));
			}
			sc.close();
			pw.close();
			// Ejecución de RecibeFicheros:
			Process process = pb.start();
			exitVal = process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Se muestra el valor devuelto por la operación y se sale con él:
		System.out.println("Terminado con valor:" + exitVal);
		System.exit(exitVal);
	}
}
