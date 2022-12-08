package main.act2_2;
/**
 * Clase donde se crean los hilos y se ejecutan los métodos run().
 * También se esperan con join() y se informa sobre los apartados
 * pedidos en el enunciado.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Main {
	private static final int NUM_JUGADORES = 3;
	private Jugador[] jugadores = new Jugador[NUM_JUGADORES];
	
	public static void main(String[] args) {
		Main hiloPrin = new Main();
		
		for(int i = 0; i < NUM_JUGADORES; i++) {
			hiloPrin.jugadores[i] = new Jugador("jugador" + i);
			hiloPrin.jugadores[i].run();
			try {
				hiloPrin.jugadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(Thread.activeCount()>1);
		System.out.println("El juego ha terminado");
		hiloPrin.jugadorMasPuntos();
		hiloPrin.sumaPuntuaciones();
	}
	
	public void jugadorMasPuntos() {
		// Guardamos en variables por comodidad
		int totalAux = 0;
		int dado1 = 0, dado2 = 0;
		boolean empate = false;
		String nombreMasPuntos = "";
		
		int cont = 0;
		while(!empate && cont < Main.NUM_JUGADORES) {
			if(this.jugadores[cont].getTotal() > totalAux) {
				totalAux = this.jugadores[cont].getTotal();
				nombreMasPuntos = this.jugadores[cont].getName();
				dado1 = this.jugadores[cont].getDado1();
				dado2 = this.jugadores[cont].getDado2();
			} else if(this.jugadores[cont].getTotal() == totalAux) {
				empate = true;
			}
			// Aumentar contador al final
			cont++;
		}
		
		if(empate) {
			System.out.println("Hubo un empate");
		} else {
			System.out.println("El jugador con más puntos fue " + nombreMasPuntos + ", con " + totalAux + " puntos.");	
			System.out.println("Dado 1: " +  dado1);
			System.out.println("Dado 2: " +  dado2);
		}
	}
	
	public void sumaPuntuaciones() {
		int suma = 0;
		
		for (Jugador jugador : jugadores) {
			suma += jugador.getTotal();
		}
		
		System.out.println("La suma total de las puntuaciones es " + suma);
	}
}