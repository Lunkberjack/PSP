package main.act2_2;

import java.util.Random;
/**
 * Clase que representa un jugador que tira dos dados.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Jugador extends Thread {
	private String nombre;
	private Random rnd;
	private int dado1, dado2, total;
	
	Jugador(String nombre) {
		super(nombre);
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		this.dado1 = this.rnd.nextInt(6)+1;
		this.dado2 = this.rnd.nextInt(6)+1;
		this.total = this.dado1 + this.dado2;
	}

	public int getDado1() {
		return dado1;
	}

	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}

	public String getNombre() {
		return nombre;
	}

	public Random getRnd() {
		return rnd;
	}

	public int getDado2() {
		return dado2;
	}

	public int getTotal() {
		return total;
	}
	
}