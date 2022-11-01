package main.act1_20;

public class De19Anios extends TiempoEjecucion implements Runnable {
	private Alumno[] alumnos;
	private int numAlumnos;
	
	public De19Anios(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public void run() {
		this.setTiempoComienzo();
		
		for(Alumno x:this.alumnos) {
			if(x.getEdad() == 19) {
				this.numAlumnos++;
			}
		}
		System.out.println("# de alumnos con 19 años: " + this.numAlumnos);
		this.setTiempoTotalEjecucion();
		System.out.println("Tiempo total De19Anios: " + this.getTiempoTotalEjecucion());
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}
}