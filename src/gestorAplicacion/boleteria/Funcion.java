package gestorAplicacion.boleteria;

import java.io.Serializable;
import java.util.*;

public class Funcion implements Serializable{
	private static final long serialVersionUID = 2L;
	
	private Date fecha;
	private String horario;
	private int duracion;
	private Pelicula pelicula;
	private List<String> lenguajes = new ArrayList<String>();
	private int cantidadBoletosVendidos;
	
	//
	//getting and setting
	//
	
	public Date getFecha() {
		return fecha;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
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
	
	
	//
	//methods
	//
}
