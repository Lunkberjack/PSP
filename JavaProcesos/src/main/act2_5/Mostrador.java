package main.act2_5;

import java.util.Random;

/**
 * Clase en la que se prueba el funcionamiento tanto del 
 * Productor como del Consumidor.
 * 
 * @author LuciaLM
 */
public class Mostrador {
	private Random rnd;
	private int dato;
	boolean disponible; // No veo la funcionalidad de esta variable en este momento
	
	Mostrador() {
		this.rnd = new Random();
	}
	
	public static void main(String[] args) {
		Mostrador m = new Mostrador();
		Productor p = new Productor(m);
		Consumidor c = new Consumidor(m);
		
		p.start();
		c.start();
	}

    public synchronized void producir() throws InterruptedException {
    	// Como máximo valor entero encontraremos un 50
    	this.dato = rnd.nextInt(50) + 1;
    	System.out.println("Puesto en el mostrador el dato: " + dato);
        notify(); // Informa de que ha generado el dato, y espera
        wait(); // Cuando el Consumidor recoja el dato creará otro, no antes
    }

    public synchronized void coger() throws InterruptedException {
        notify(); // Informa de que ha cogido el dato
        System.out.println("Recogido del mostrador el dato: " + this.dato);
        System.out.println(); // Separador de ejecuciones
        wait(); // Espera hasta que el Productor genere el dato e informe
    }
}
