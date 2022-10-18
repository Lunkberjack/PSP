package main.act1_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecibeFicheros {
	public static void main(String[] args) {
		//obtención de un lector a partir del flujo de entrada
		//estándar (definido por el proceso del programa que llama
		//a éste o bien por la entrada por consola)
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String cadena = "";
		int linea = 0;
		int exitVal = 0;
		try {
			//lectura del flujo de entrada estándar hasta que no haya nada 
			//que leer
			while(bReader.ready()) {
				cadena = bReader.readLine();
				//se escribe en el fichero definido por la redirección
				//del programa que llama éste
				System.out.println("línea="+ ++linea + "tamaño=;" + cadena.length()
				+ ";espacios=" + (cadena.split(" ").length-1));
			}
		} catch (IOException e) {
			exitVal = -1;
		}
		System.exit(exitVal);
	}
}