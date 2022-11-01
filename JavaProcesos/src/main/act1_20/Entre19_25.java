package main.act1_20;

public class Entre19_25 extends TiempoEjecucion implements Runnable {
	private Alumno[] alumnos;
	private int numAlumnos;
	
	public Entre19_25(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public void run() {
		this.setTiempoComienzo();
		
		for(Alumno x:this.alumnos) {
			if(x.getEdad() >= 19 && x.getEdad() <= 25) {
				this.numAlumnos++;
			}
		}
		System.out.println("# de alumnos entre 19-25 años: " + this.numAlumnos);
		this.setTiempoTotalEjecucion();
		System.out.println("Tiempo total Entre19_25: " + this.getTiempoTotalEjecucion());
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}
}