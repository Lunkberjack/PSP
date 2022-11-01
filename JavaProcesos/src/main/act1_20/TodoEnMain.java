package main.act1_20;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Todas las operaciones en el mismo hilo.
 * 
 * @author LucíaLM
 * @version 1.0
 */
public class TodoEnMain {
	private static final int MIN_EDAD = 19;
	private static final int MAY_EDAD = 40;
	
	public static void main(String[] args) {
		long inicioMain = System.currentTimeMillis();
		Alumno[] alumnos = new Alumno[4000];
		
		for(int i = 0; i < 4000; i++) {
			alumnos[i] = new Alumno(i, ThreadLocalRandom.current().nextInt(MIN_EDAD, MAY_EDAD + 1));
			System.out.println(alumnos[i].getEdad());
		}
		
		// MEDIA
		double res = 0;
		for(Alumno x:alumnos) {
			res += x.getEdad();
		}
		System.out.println("La media es: " + res/alumnos.length);
		
		// MAS25
		int mas25 = 0;
		for(Alumno x:alumnos) {
			if(x.getEdad() > 25) {
				mas25++;
			}
		}
		System.out.println("# de alumnos con +25 años: " + mas25);
		
		// ENTRE 19 Y 25
		int entre = 0;
		for(Alumno x:alumnos) {
			if(x.getEdad() >= 19 && x.getEdad() <= 25) {
				entre++;
			}
		}
		System.out.println("# de alumnos entre 19-25 años: " + entre);
		
		// CON 19
		int con19 = 0;
		for(Alumno x:alumnos) {
			if(x.getEdad() == 19) {
				con19++;
			}
		}
		System.out.println("# de alumnos con 19 años: " + con19);
		
		System.out.println("\nTiempo total: " + (System.currentTimeMillis() - inicioMain));
	}
}