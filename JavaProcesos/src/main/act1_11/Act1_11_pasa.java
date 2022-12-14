package main.act1_11;
import java.io.File;
import java.io.IOException;
/**
 * @author LuciaLM
 * @version 1.0
 */
public class Act1_11_pasa {
	public static void main(String[] args) {
		ProcessBuilder pb;
		Process process;
		int exitVal;
		try {
			pb = new ProcessBuilder();
			pb.directory(new File("/home/lucia/PSP/JavaProcesos/bin"));
			
			pb.command("java", "main.act1_11.Act1_11_dame", "Hola!");
			process = pb.start();
			exitVal = process.waitFor();			
			System.out.println("Llamada con un parámetro tipo cadena: " + exitVal);
			
			pb.command("java", "main.act1_11.Act1_11_dame");
			process = pb.start();			
			exitVal = process.waitFor();			
			System.out.println("Llamada sin parámetros: " + exitVal);
			
			pb.command("java", "main.act1_11.Act1_11_dame", "-83");
			process = pb.start();			
			exitVal = process.waitFor();			
			System.out.println("Llamada con un entero menor que 0: " + exitVal);
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}