package gestorAplicacion.cinema;
import java.io.Serializable;
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
	
	//Constructor
	public Cine(String nombre) {
		this.nombre = nombre;
	}
	
	
	//
	//methods
	//
	

	public String verFuncion(Pelicula pelicula, int dia, int mes) { //Ver funciones luego de un d�a de un mes de una pel�cula
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
	
	public String verFuncion(Cliente cliente) { //Ver funcion por las recomendaciones del cliente
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula().getGenero()== cliente.mostWatchedGenre()){
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
	
	public String verFuncion( int dia, int mes) { //peliculas del dia 
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return formatearFunciones(funciones);
	}



	// funcion para formatear el texto para imprimir en pantallas las funciones
	// este es llamado desde la función verFuncion()
	public static String formatearFunciones(ArrayList<Funcion> funciones){
		String resultado = "\n\n"; // string en el que va todo el texto
		for(Funcion funcion: funciones){
			//     formato para mostrar el " horario | Sala # | (2/3)D | #funcion "
			String formato = "%s|%s|%s|%s";
			String fecha = "Fecha: " + String.format("%02d/%02d",funcion.getDia(),funcion.getMes());
			resultado += funcion.getPelicula().getNombre() + "\n"; // añade nombre de la pelicula y salto de linea
			
			
			resultado += String.format(							   // añade la linea con la info
				formato, 
				centerString(6,funcion.getHorario()), 						// pone el horario	centrado	 
				centerString(8,"Sala "+funcion.getSala().getNumero()),		// pone la sala centrada
				centerString(4,funcion.getSala().getTipo()),				// pone el tipo de sala centrada
				centerString(5,String.format("%03d", funcion.getNumero())));// pone el número de sala centrada
			resultado += "\n"+ fecha;
			resultado += "\n\n";
		}
		return resultado;
	}
	

	// función para centrar el texto a un tamaño minimo
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
	public void agregarFuncion(Funcion nuevo){
		cartelera.add(nuevo);
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
	
	// funcion para encontrar el cliente ingresando la c�dula
	public Cliente BuscadorCliente(int num) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
			if (cliente.getCedula()==num) {
				return cliente;
			}
		}
		return null ;
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
