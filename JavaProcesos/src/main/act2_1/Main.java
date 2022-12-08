package main.act2_1;
/**
 * Duerme el hilo para que éste espere un tiempo proporcional 
 * a su id antes de ejecutarse.
 * 
 * El hilo1 se inicia a los 12 segundos de ejecución en mi caso.
 * El hilo2 a los 13 segundos.
 * El hilo3 a los 14.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		Hilo hilo = new Hilo();
		Hilo hilo2 = new Hilo();
		Hilo hilo3 = new Hilo();
		
		Thread th1 = new Thread(hilo);
		Thread th2 = new Thread(hilo2);
		Thread th3 = new Thread(hilo3);
		
		try {
			Thread.sleep(th1.getId()*1000);
			th1.start();
			Thread.sleep(th2.getId()*1000);
			th2.start();
			Thread.sleep(th3.getId()*1000);
			th3.start();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}