package main.act1_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecibeFicheros {
	public static void main(String[] args) {
		// Obtención de un lector a partir del flujo de entrada
		// estándar (definido por el proceso del programa que llama
		// a éste o bien por la entrada por consola):
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String cadena = "";
		int exitVal = 0;
		try {
			// Lectura del flujo de entrada estándar hasta que no quede nada 
			// para leer:
			while(bReader.ready()) {
				cadena = bReader.readLine();
				if(PasaFicheros.esPalindroma(cadena)) {
					System.out.println("La palabra " + cadena + " es palíndroma.");
				} else {
					System.err.println("La palabra " + cadena + " no es palíndroma.");
				}
			}
		} catch (IOException e) {
			exitVal = -1;
		}
		System.exit(exitVal);
	}
}
