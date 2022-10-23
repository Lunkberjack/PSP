package main.act1_15;
/**
 * Ping se ejecuta siempre antes que pong, pero solo en bloques de iteraciones,
 * no se alternan los mensajes.
 * @author LuciaLM
 * @version 1.0
 */
public class Main extends Thread {
	
	public static void main(String[] args) {
		Hilo ping = new Hilo("PING");
		Hilo pong = new Hilo("PONG");
				
		Thread hilo1 = new Thread(ping);
		Thread hilo2 = new Thread(pong);
		
		hilo1.start();
		
		try {
			// Hace que pong espere a la finalización de ping
			hilo1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hilo2.start();

	}
}