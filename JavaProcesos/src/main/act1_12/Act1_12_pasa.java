package main.act1_12;

import java.io.File;
import java.io.IOException;

public class Act1_12_pasa {
	public static void main(String[] args) {
		ProcessBuilder pb;
		Process process;
		int exitVal;
		try {
			pb = new ProcessBuilder();
			pb.directory(new File("/home/lucia/PSP/JavaProcesos/bin"));
			// Llama al otro programa con un parámetro tipo cadena:
			pb.command("java", "main.act1_12.Act1_12_dame", "cadena");
			process = pb.start();
			exitVal = process.waitFor();			
			System.out.println("Llamada con un parámetro tipo cadena: " + exitVal);
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