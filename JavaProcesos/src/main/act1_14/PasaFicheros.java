package main.act1_14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PasaFicheros {
	private static final int ESCAPE=27;
	public static void main(String[] args) {
		int exitVal=0;
		Scanner sc = new Scanner(System.in);
		String cadena="go";
		try {
			//se crea un fichero de texto a partir de las entradas
			//introducidas por consola
			PrintWriter pw =
					new PrintWriter(new FileWriter("./src/main/act1_14/datos.txt"));
			while(cadena.length() > 1 && cadena.charAt(0) != 0) {
				System.out.println("Introduce una cadena palíndroma o no, "
						+ "o vacía:(0 para terminar)");
				cadena = sc.nextLine();
				pw.println(cadena);
			}
			sc.close();
			pw.close();
			ProcessBuilder pb =
					new ProcessBuilder("java", "main.act1_14.RecibeFicheros");
			//directorio de trabajo
			pb.directory(
					new File("/home/lucia/PSP/JavaProcesos/bin"));
			//redirección del flujo de entrada del proceso del programa
			//al que se llama (las palabras introducidas por teclado aquí)
			pb.redirectInput(
					new File("./src/main/act1_14/datos.txt"));
			//redirección del flujo de salida del proceso del programa al 
			//que se llama
			pb.redirectOutput(
					new File("./src/main/act1_14/datosProcesados.txt"));
			//ejecución del proceso
			Process process = pb.start();
			//se espera para el valor retornado
			exitVal = process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		//se muestra el resultado de la operación
		System.out.println("Terminado con valor:" + exitVal);
		System.exit(exitVal);
	}
}
