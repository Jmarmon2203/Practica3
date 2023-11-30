package ModeloVistaControlador;

import java.util.GregorianCalendar;

public class Empleado {
	int num;
	String nom;
	GregorianCalendar fecha_alta;
	double Sueldo;
	double SueldoMax;
	
	public Empleado(int num, String nom) {
		super();
		this.num = num;
		this.nom = nom;
		this.fecha_alta = new GregorianCalendar();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public GregorianCalendar getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(GregorianCalendar fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public double getSueldo() {
		return Sueldo;
	}

	public void setSueldo(double sueldo) {
		Sueldo = sueldo;
	}

	public double getSueldoMax() {
		return SueldoMax;
	}

	public void setSueldoMax(double sueldoMax) {
		SueldoMax = sueldoMax;
	}
}