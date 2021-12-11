package gestorAplicacion.boleteria;
import java.io.Serializable;
import java.util.*;
import gestorAplicacion.cinema.Cine;
//import gestorAplicacion.cinema.Cliente;

public class Pelicula implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String genero;
	private int duracion;
	private String lenguaje;
	private int clasificacion;
	private int cantidadTotalBoletosVendidos;
	private int expectativaVentas;
	private Cine cine;
	
	//Constructores
	public Pelicula(String nombre, String genero, int duracion, String lenguaje, int clasificacion,Cine cine) {
		this.nombre=nombre;
		this.genero=genero;
		this.duracion=duracion;
		this.lenguaje=lenguaje;
		this.clasificacion=clasificacion;
		this.cine=cine;
	}
	
	
	public Pelicula(String nombre, String genero, int duracion, String lenguaje, int clasificacion) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.duracion = duracion;
		this.lenguaje = lenguaje;
		this.clasificacion = clasificacion;
	}



	public Pelicula(){
		
	}
	
	//
	//methods
	//
	

	
	//
	//getting and setting:
	//
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	public int getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public int getCantidadTotalBoletosVendidos() {
		return cantidadTotalBoletosVendidos;
	}
	public void setCantidadTotalBoletosVendidos(int cantidadTotalBoletosVendidos) {
		this.cantidadTotalBoletosVendidos = cantidadTotalBoletosVendidos;
	}
	
	public int getExpectativaVentas() {
		return expectativaVentas;
	}
	public void setExpectativaVentas(int expectativaVentas) {
		this.expectativaVentas = expectativaVentas;
	}

}
