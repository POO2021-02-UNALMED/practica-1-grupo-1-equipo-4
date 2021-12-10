package gestorAplicacion.cinema;

import java.io.Serializable;
import java.util.*;

import gestorAplicacion.boleteria.*;
import gestorAplicacion.salas.Sala;
import gestorAplicacion.salas.Sala2D;
//import gestorAplicacion.cinema.*;

//!!!
public class Cine implements Serializable{
	private static final long serialVersionUID = 1L;
	//!!! same question about BD
	private String nombre;//??? 
	private List<Cliente> clientes= new ArrayList<Cliente>();
	private List<Funcion> cartelera= new ArrayList<Funcion>();
	private List<Pelicula> peliculas= new ArrayList<Pelicula>();
	private List<Sala> salas = new ArrayList<Sala>();
	private float dineroGanado;
	
	
	//
	//methods
	//
	

	public String verFuncion(Pelicula pelicula, int dia, int mes) { 
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getDia()>=dia && funcion.getMes()==mes ){
				funciones.add(funcion);
			}
		}
		
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getMes()>mes ){
				funciones.add(funcion);
			}
		}

		return formatearFunciones(funciones);
	}
	
	public String verFuncion(Pelicula pelicula, String horario) {
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario){
				funciones.add(funcion);
			}
		}
		return formatearFunciones(funciones);
	}
	
	public String verFuncion(Pelicula pelicula, String horario, int dia, int mes) {
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario && funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return formatearFunciones(funciones);
	}


	// funcion para formatear el texto para imprimir en pantallas las funciones
	// este es llamado desde la función verFuncion()
	private String formatearFunciones(ArrayList<Funcion> funciones){
		String resultado = "";
		for(Funcion funcion: funciones){
			String formato = "%s|%s|%s|%s";
			resultado += funcion.getPelicula().getNombre() + "\n";
			resultado += String.format(
				formato, 
				centerString(6,funcion.getHorario()), 
				centerString(9,"Sala "+funcion.getSala().getNumero()),
				centerString(4,funcion.getSala().getTipo()),
				centerString(5,/*numero formateado a la derecha con 0*/);
				 );
			resultado += "\n\n";
		}
		return resultado;
	}

	public static String centerString (int width, String s) {
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}

	//
	//Metodos para agregar elementos a las listas
	//
	public void agregarCliente(Cliente nuevo) {
		clientes.add(nuevo);
	}
	public void agregarPelicula(Pelicula nuevo) {
		peliculas.add(nuevo);
	}
	public void agregarSala(Sala nuevo){
		salas.add(nuevo);
	}

	// funcion para ver si el cliente ya se encuentar inscrito en el cine
	public Boolean verificarCliente(int num) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
		}
		
		if (lista.contains(num)) {
			return true;
		}else {
			return false;
		}
		
	}


	// metodo para obtener la sala por numero
	public Sala buscarSala(int num) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Sala sala: salas) {
			lista.add(sala.getNumero());
		}
		
		if (lista.contains(num)) {
			return salas.get(lista.indexOf(num));
		}else {
			return null;
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
		this.clientes = clientes;
	}
	
	public List<Funcion> getCartelera() {
		return cartelera;
	}
	public void setCartelera(List<Funcion> cartelera) {
		this.cartelera = cartelera;
	}
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	public float getDineroGanado() {
		return dineroGanado;
	}
	public void setDineroGanado(float dineroGanado) {
		this.dineroGanado = dineroGanado;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}
