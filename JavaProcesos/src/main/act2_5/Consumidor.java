package main.act2_5;

/**
 * Clase que espera hasta que el productor ha puesto un 
 * objeto en el mostrador para cogerlo y mostrar tanto
 * el nombre del hilo que lo ha cogido como el valor del 
 * objeto recibido.
 * 
 * @author LuciaLM
 */
public class Consumidor extends Thread {

	private Mostrador mostrador;

	public Consumidor(Mostrador mostrador) {
		this.mostrador = mostrador;
	}

	@Override
	public void run() {
		try {
			// Bucle infinito
			while(true) {
				mostrador.coger();
				
				/* DESCOMENTAR PARA QUE EL PROGRAMA NO SE VUELVA LOCO (bucle inf.)
				 * Dejo esta línea (y su equivalente en el productor) para
				 * que se pueda comprobar que, aunque no se pongan a dormir los
				 * hilos, estos se esperan y se notifican correctamente.
				 */
				
				//Thread.sleep(2000); // Deja algo de tiempo entre ejecuciones
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}