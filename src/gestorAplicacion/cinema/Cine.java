package gestorAplicacion.cinema;

import java.io.Serializable;
import java.util.*;

import gestorAplicacion.boleteria.*;
//import gestorAplicacion.cinema.*;

//!!!
public class Cine implements Serializable{
	private static final long serialVersionUID = 1L;
	//!!! same question about BD
	private String nombre;//??? 
	private List<Cliente> clientes= new ArrayList<Cliente>();
	private List<Funcion> cartelera= new ArrayList<Funcion>();
	private List<Pelicula> peliculas= new ArrayList<Pelicula>();
	private float dineroGanado;
	
	
	//
	//methods
	//
	
	public static void verFuncion(Pelicula pelicula) { //??? aquí o en main
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula){
				System.out.println(funcion);
			}
		}
	}
	
	public static void verFuncion(Pelicula pelicula, String horario) {
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario){
				System.out.println(funcion);
			}
		}
	}
	
	//
	//getting and setting
	//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		Cine.clientes = clientes;
	}
	
	public List<Funcion> getCartelera() {
		return cartelera;
	}
	public void setCartelera(List<Funcion> cartelera) {
		Cine.cartelera = cartelera;
	}
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		Cine.peliculas = peliculas;
	}
	
	public float getDineroGanado() {
		return dineroGanado;
	}
	public void setDineroGanado(float dineroGanado) {
		this.dineroGanado = dineroGanado;
	}
	
	

}
