package main.act1_20;

public class MediaAritmetica extends TiempoEjecucion implements Runnable {
	private Alumno[] alumnos;
	private double media;
	
	public MediaAritmetica(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}
	
	@Override
	public void run() {
		this.setTiempoComienzo();
		double res = 0;
		
		for(Alumno x:alumnos) {
			res += x.getEdad();
		}
		
		this.media = res/this.alumnos.length;
		System.out.println("La media es: " + this.media);
		this.setTiempoTotalEjecucion();
		System.out.println("Tiempo total MediaAritmética: " + this.getTiempoTotalEjecucion());
	}

	public double getMedia() {
		return media;
	}
}
