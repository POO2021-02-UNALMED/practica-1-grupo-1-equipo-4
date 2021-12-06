package gestorAplicacion.boleteria;

import java.io.Serializable;
import java.util.*;

public class Funcion{
	
	private int dia;
	private int mes;
	private String horario;
	private int duracion;
	private Pelicula pelicula;
	private List<String> lenguajes = new ArrayList<String>();
	private int cantidadBoletosVendidos;
	
	public Funcion() {
		
	}
	
	
	//
	//getting and setting
	//
	

	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public List<String> getLenguajes() {
		return lenguajes;
	}
	public void setLenguajes(List<String> lenguajes) {
		this.lenguajes = lenguajes;
	}
	public int getCantidadBoletosVendidos() {
		return cantidadBoletosVendidos;
	}
	public void setCantidadBoletosVendidos(int cantidadBoletosVendidos) {
		this.cantidadBoletosVendidos = cantidadBoletosVendidos;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
	//
	//methods
	//
}
