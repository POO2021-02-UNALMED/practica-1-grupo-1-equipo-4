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
	private static final int descuentoMVC=40;	//Descuento al cliente mas fiel
	
	//Constructor
	public Cine(String nombre) {
		this.nombre = nombre;
	}
	
	
	//
	//methods
	//
	public void programarFuncionesAuto(int dias_atras,int dia, int mes){
		//debo hacer una lista de las peliculas más vendidas en funciones de 3 días antes
		int dia_aux = dia;

		for(int i = 0; i < dias_atras; i++){
			dia_aux -= 1;

		}
	}
	

	//??? Podemos mejor retornar una lista de los clientes que cumplan el m�ximo,
	//??? Hacerlo al mes

	//Reflejar cliente mas fiel y aplicarle el descuento
	public String mostValueClient() {
		List<Integer> clienteList=new ArrayList<Integer>();
		for(Cliente cliente: clientes) {
			clienteList.add(cliente.historialCompras.size()); 	//Recorre el historial de compras del cliente y anexa el tama�o de su historial de compra
		}
		int valormax=Collections.max(clienteList);		//Se establece el mayor numero de boletos comprados por parte de un cliente
		for (Cliente cliente: clientes) {
			if (valormax==cliente.historialCompras.size()) {	//Si la cantidad de boletos comprados es igual a valor max conseguir el nombre de este
				cliente.setDescuento(descuentoMVC);
				return cliente.getNombre();
			}
		}
		return "Se ha aplicado el descuentos  a nuestro cliente mas fiel ";
	}

	public ArrayList<Funcion> verFuncion(Pelicula pelicula, int dia, int mes) { //Ver funciones luego de un d�a de un mes de una pel�cula
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

		return funciones;
	}
	

	public ArrayList<Funcion> verFuncion(Cliente cliente) { //Ver funcion por las recomendaciones del cliente
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula().getGenero()== cliente.mostWatchedGenre()){	//Compara genero mas visto del historial de compra del cliente
				funciones.add(funcion);											//con los generos del las peliculas en cartelera
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario) {				//Mostrar funciones de una pelicula en un horario especifico
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario){
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario, int dia, int mes) {		//Mostrar funcion en una fecha y hora de una pelicula en especifico
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario && funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}

	

	public ArrayList<Funcion> verFuncion( int dia, int mes) { //peliculas del dia 
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}



	public ArrayList<Funcion> verFuncion(int numero) {	//Mostrar funcion por el numero de la funcion
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getNumero()==numero) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Boleto boleto) {  //Mostrar funcion por boleto
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if(funcion==boleto.getFuncion()) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}



	// funcion para formatear el texto para imprimir en pantallas las funciones
	// este es llamado desde la función verFuncion()


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
	
	// funcion para encontrar la pelicula ingresando el nombre
	public Pelicula BuscadorPelicula(String nombre) {
		ArrayList<String> lista = new ArrayList<String>();
		for(Pelicula pelicula: peliculas) {
			lista.add(pelicula.getNombre());
			if (pelicula.getNombre()==nombre) {
				return pelicula;
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


	public static int getDescuentomvc() {
		return descuentoMVC;
	}

}
