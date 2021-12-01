package gestorAplicacion.cinema;

import java.util.*;

import gestorAplicacion.boleteria.*;
//import gestorAplicacion.cinema.*;

//!!!
public class Cine {
	
	//!!! same question about BD
	private String nombre;//??? 
	private static List<Cliente> clientes= new ArrayList<Cliente>();
	private static List<Funcion> cartelera= new ArrayList<Funcion>();
	private static List<Pelicula> peliculas= new ArrayList<Pelicula>();
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
	
	public static List<Cliente> getClientes() {
		return clientes;
	}
	public static void setClientes(List<Cliente> clientes) {
		Cine.clientes = clientes;
	}
	
	public static List<Funcion> getCartelera() {
		return cartelera;
	}
	public static void setCartelera(List<Funcion> cartelera) {
		Cine.cartelera = cartelera;
	}
	
	public static List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public static void setPeliculas(List<Pelicula> peliculas) {
		Cine.peliculas = peliculas;
	}
	
	public float getDineroGanado() {
		return dineroGanado;
	}
	public void setDineroGanado(float dineroGanado) {
		this.dineroGanado = dineroGanado;
	}
	
	

}
