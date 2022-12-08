package main.act2_1;

public class Hilo implements Runnable {
	@Override
	public void run() {
		System.out.println("Hola mundo, soy el hilo " + Thread.currentThread().getId());
	}
}
