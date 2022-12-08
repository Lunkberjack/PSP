package main.act2_4;

public class Hilo extends Thread {
	private Main m;    
	public Hilo(Main m) {        
		this.m = m;
	}
	
	@Override
	public void run() {
		while(m.getContador() < 5000) {
			m.aumentarContador();
		}
	}
}