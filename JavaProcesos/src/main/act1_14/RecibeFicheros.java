package main.act1_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Contiene la lógica para saber si una palabra es palíndroma,
 * y escribe en el Stream de salida o error dependiendo de si la
 * cadena pasada por parámetro desde PasaFicheros lo es o no.
 * @author LuciaLM
 * @version 1.0
 */
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
				if(RecibeFicheros.esPalindroma(cadena)) {
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
