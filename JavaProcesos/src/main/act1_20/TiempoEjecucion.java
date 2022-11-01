package main.act1_20;

public class TiempoEjecucion {
	private long tiempoComienzo;
	private long tiempoTotalEjecucion;
	
	public void setTiempoComienzo() {
		this.tiempoComienzo = System.currentTimeMillis();
	}
	public long getTiempoComienzo() {
		return this.tiempoComienzo;
	}
	public void setTiempoTotalEjecucion() {
		this.tiempoTotalEjecucion = System.currentTimeMillis() - this.tiempoComienzo;
	}
	public long getTiempoTotalEjecucion() {
		return this.tiempoTotalEjecucion;
	}
}
