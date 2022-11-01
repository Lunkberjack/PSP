package main.act1_20;

public class Alumno {
	private int id;
	private int edad;
	
	public Alumno(int id, int edad) {
		this.id = id;
		this.edad = edad;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		if(edad >= 19 && edad <= 40) {
			this.edad = edad;
		}
	}
}