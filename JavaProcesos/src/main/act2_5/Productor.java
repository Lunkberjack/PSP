package main.act2_5;

/**
 * Clase que pone un objeto en el mostrador para cogerlo 
 * y espera hasta que el Consumidor lo coja para poner otro.
 * 
 * @author LuciaLM
 */
public class Productor extends Thread {

	private Mostrador mostrador;

	public Productor(Mostrador mostrador) {
		this.mostrador = mostrador;
	}

	@Override
	public void run() {
		try {
			// Bucle infinito
			while(true) {
				mostrador.producir();
				
				/* DESCOMENTAR PARA QUE EL PROGRAMA NO SE VUELVA LOCO (bucle inf.)
				 * Dejo esta línea (y su equivalente en el consumidor) para
				 * que se pueda comprobar que, aunque no se pongan a dormir los
				 * hilos, estos se esperan y se notifican correctamente.
				 */
				
				// Thread.sleep(2000); // Deja algo de espacio entre ejecuciones.
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}