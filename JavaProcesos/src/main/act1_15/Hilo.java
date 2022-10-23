package main.act1_15;
/**
 * Clase que crea un hilo con unas repeticiones asignadas y un nombre.
 * @author LuciaLM
 * @version 1.0
 */
public class Hilo implements Runnable {
	private static final int REP = 5;
	
	private String msg;
	public Hilo(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		// No encuentro la manera de que se alterne la ejecución de 
		// iteraciones entre los dos hilos
		for (int i = 0; i < REP; i++) {
			System.out.println(msg);
		}
	}
}