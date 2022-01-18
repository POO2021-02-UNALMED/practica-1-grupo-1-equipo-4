//Funcionalidad de la clase: Este contiene anadir la cantidad de Boletos, en este se guardan los atributos de la pelicula 
// entre los que se encuentran nombre, genero, duracion, lenguaje y clasificacion

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa


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
		cine.agregarPelicula(this);			//Se agrega la pelicula a la lista de peliculas del cine
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


	public void anadirCantidadBoletos(){
		/*No recibe nada y tampoco devuelve nada, este metodo se usa para sumar la cantidad de boletos vendidos		 
		*/
		cantidadTotalBoletosVendidos++;	//Sumar 1 al atributo de la cantidad de total de boletos
		
	}

	
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
