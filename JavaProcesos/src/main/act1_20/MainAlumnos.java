package main.act1_20;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Creamos 4 hilos para realizar 4 operaciones sobre el mismo array
 * de 4000 alumnos, y calculamos el tiempo de ejecución tanto de cada 
 * hilo como del hilo principal.
 * 
 * @author LucíaLM
 * @version 1.0
 */
public class MainAlumnos {
	private static final int MIN_EDAD = 19;
	private static final int MAY_EDAD = 40;
	
	public static void main(String[] args) {
		long tiempoInicioMain = System.currentTimeMillis();
		Alumno[] alumnos = new Alumno[4000];
		
		for(int i = 0; i < 4000; i++) {
			alumnos[i] = new Alumno(i, ThreadLocalRandom.current().nextInt(MIN_EDAD, MAY_EDAD + 1));
			System.out.println(alumnos[i].getEdad());
		}
		
		MediaAritmetica media = new MediaAritmetica(alumnos);
		Mas25 mas25 = new Mas25(alumnos);
		Entre19_25 entre19_25 = new Entre19_25(alumnos);
		De19Anios con19 = new De19Anios(alumnos);
		
		System.out.println("\nIniciamos los hilos:");
		Thread hiloMedia = new Thread(media);
		hiloMedia.start();
		Thread hiloMas25 = new Thread(mas25);
		hiloMas25.start();
		Thread hilo19_25 = new Thread(entre19_25);
		hilo19_25.start();
		Thread hiloCon19 = new Thread(con19);
		hiloCon19.start();

		// Para que no quede nada más que el hilo principal.
		while(Thread.activeCount() > 1);
		System.out.println("\nEn total, se han tardado: " + (System.currentTimeMillis() - tiempoInicioMain));
	}
}