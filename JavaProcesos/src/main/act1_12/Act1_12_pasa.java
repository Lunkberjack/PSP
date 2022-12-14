package main.act1_12;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Act1_12_pasa {
	public static void main(String[] args) {
		ProcessBuilder pb;
		Process process;
		int exitVal;
		int byteLeido;
		try {
			pb = new ProcessBuilder();
			pb.directory(new File("/home/lucia/PSP/JavaProcesos/bin"));
			
			// Llama al otro programa con un parámetro tipo cadena:
			pb.command("java", "main.act1_12.Act1_12_dame", "cadena");
			process = pb.start();
			// Recoge el stream de salida del otro proceso como entrada:
			InputStream is = process.getInputStream();
			exitVal = process.waitFor();		
			System.out.println("Llamada con un parámetro tipo cadena: " + exitVal);
			// Lee el contenido del stream del otro proceso, convirtiendo cada byte a char
			// (aquí se imprimen las repeticiones de la cadena):
			while((byteLeido = is.read()) > -1) {
				System.out.print((char)byteLeido);
			}
			is.close();
			
			// Llama al otro programa sin parámetros:
			pb.command("java", "main.act1_12.Act1_12_dame");
			process = pb.start();
			exitVal = process.waitFor();			
			System.out.println("Llamada sin parámetros: " + exitVal);
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}