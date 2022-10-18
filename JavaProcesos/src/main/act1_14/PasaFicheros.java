package main.act1_14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Se muestra el valor devuelto por la operación y se sale con él:
		System.out.println("Terminado con valor:" + exitVal);
		System.exit(exitVal);
	}
	/**
	 * Una palabra es palíndroma si se lee de igual manera hacia izquierda
	 * y derecha (ANA, SOMOS, SAAS)
	 * @param pal
	 * @return
	 */
	public static boolean esPalindroma(String pal) {
		boolean res = false;
		// StringBuilder para que sea más sencillo invertirla:
		StringBuilder aux = new StringBuilder(pal);
		// Conversión a String para poder usar equals():
		if(String.valueOf(aux.reverse()).equals(pal)) {
			res = true;
		}
		return res;
	}
}
