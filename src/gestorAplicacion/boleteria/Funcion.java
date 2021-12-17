package gestorAplicacion.boleteria;
import java.io.Serializable;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import gestorAplicacion.salas.Sala;
import gestorAplicacion.salas.Silla;
import gestorAplicacion.salas.Sala2D;
import gestorAplicacion.salas.Sala3D;

public class Funcion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int dia;
	private int mes;
	private Horario horario;
	private Pelicula pelicula;
	private Sala sala;
	private ArrayList<Boleto> boletos = new ArrayList<Boleto>();
	public int cantidadBoletosVendidos = 0;  //??? porque esto esta público??
	private Cine cine;
	private static int cantidadFunciones;
	private int numero;
	
	private Funcion(int dia, int mes, Horario horario, Pelicula pelicula, Sala sala, Cine cine) {

		// se establecen parametros
		this.dia = dia;
		this.mes = mes;
		this.horario = horario;
		this.pelicula = pelicula;
		this.numero=cantidadFunciones;
		this.setSala(sala);
		this.setCine(cine);
		this.crearBoleteria();
		// agrega la funcion a sala y a el cine
		cine.agregarFuncion(this);
		sala.agregarFuncion(this);	
		// aumenta la cantidad de funciones creadas
		cantidadFunciones++;
	}
	
	//
	//		METODOS
	//
	
	//
	// PARA CREAR FUNCION SE VA A USAR ESTE METODO, NO EL CONSTRUCTOR
	//

	public static Funcion crearFuncion(int dia, int mes, Horario horario, Pelicula pelicula, int num_sala, Cine cine) {
		/*Recibe dia me y horario como una fecha, 
		pelicula es la pelicula para la cual se creara la funcion
		num_sala es el número de sala en la que se va a crear la sala,
		y cine es para asociarlo a la variable de atributo cine
		*/ 

		Sala sala = cine.buscarSala(num_sala);                                  	 //aqui se revisa si la sala existe en cine
		if(sala != null) {												    				
			if(sala.verificarDisponibilidad(dia, mes, horario.getHora())) { 		// verifica que la sala tenga disponibilidad en dicha hora
				return new Funcion(dia, mes, horario, pelicula, sala, cine);		// crea la funcion
			}		
			else {
				return null;// no la crea
			}
			
		}else{return null;}// no la crea				
	}



	private void crearBoleteria(){
		/*No recibe parametros pero trabaja con los atributos sala y boletos
		Se encarga de crear un boleto para cada silla de la sala de la funcion correspondiente,
		y se limita depende de la cantidad de sillas disponibles de sala, es decir cantidadSillas
		que se limita a la cantidad de gafas disponibles*/

		ArrayList<Silla> sillas = sala.getSillas(); 			//lista de las sillas de la sala correspondiente

		int disponibles = sala.cantidadSillas();				//cantidad de sillas disponible dependiendo de la cantidad de gafas 
																//se va a restar en una unidad a *disponibles* cada que se crea un objeto boleto de manera que solo cree
																//cantidad de boletos correspondiente

		//
		//crear la cantidad de boletos que corresponde segun cantidad de sillas
		//
		
		for(int i = 0; i < sala.getSillas().size();i++) {   	
			if(disponibles > 0){								//si es mayor que 0 crea el boleto, lo anade a la lista boletos y disponibles--			
				Boleto boleto = new Boleto(this, sillas.get(i));
				boletos.add(boleto);
				disponibles--;
			}else{												//de lo contrario ande un objeto nulo, (esto es para que verDisponibilidad funcione)
				boletos.add(null);
			}
		}
		
	}
	

	
	public String verDisponibilidad(){
	/*No recibe parametros pero trabaja sobre los atributos sala y boletos 
	Metodo que devuelve las sillas de una sala con tipo y disponibilidad de silla para una función dada*/

		ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>(); // lista de filas

		// for para hacer una lista de listas, cada lista corresponde a una fila de boletos
		for(int i = 0 ; i<sala.getFilas(); i++){
			ArrayList<String> fila = new ArrayList<String>(); 				//fila acumula los elementos de la fila para luego convertirlo en string
			for(int j = 0; j<sala.getColumnas() ; j++){		 
				Boleto boleto = boletos.get((i)*sala.getColumnas()+j); 
				if (boleto != null){										//si el boleto no es nulo, crea el string y se anade a la fila
																			//se crea un string de la forma disponibilidad/tipo/numerosilla
					String formato_boleto = boleto.disponibilidad()+boleto.tipoString()+String.valueOf(boleto.getNum_silla());
					fila.add(formato_boleto);								 
				}else{
					String formato_boleto = "";								//se crea un String vacio si el elemento es null
					fila.add(formato_boleto);								//
				}

			}
			total.add(fila);												// se anade a total que es la lista de filas
		}

		String formato = "";

		// for para formatear un string con la silletería para imprimir
		for(ArrayList<String> fila: total){							
																	//%-6s es una variable que recibe string y se llena con minimo 6 espacios y justifica a la izquierda
			String patron = "%-6s   ".repeat(sala.getColumnas()); 	// se crea el formato con la cantidad de variables necesarias (columnas)
			Object[] fila_args = fila.toArray(new String[0]); 		// se crea una lista para pasar como *args
			formato += String.format(patron,fila_args) + "\n"; 		// se le agrega a el string resultante la fila mas un salto de linea
		}
		formato += "\n"+centerString(sala.getColumnas()*9,"PANTALLA")+"\n"; //se le agrega al string final una linea con la palabra PANTALLA centrado
		return formato;
	}
	
	
	public static String centerString (int width, String s) {
	/*Recibe widht que es la cantidad minima de caracteres  y ese que es el string 
	Funcion que centra un string s a una cantidad de caracteres minima width y retorna el String centrado*/

		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s)); //se centra el string
	}


	
	public Boolean VentaBoleto(Boleto boleto, Cliente cliente) {
	/*Recibe boleto al que se le cambiara el estado, y el cliente al que se le agregara la compra,
	Metodo que vende un boleto, es decir, cambia valores, y devuelve un bool de si se pudo vender o no el boleto
	retorna true o false dependiendo si pudo hacerse la venta o no*/

		//Si el boleto se encuentra disponible y la edad del cliente es mayor a la clasificacion de la pelicula
		if (boleto.isDisponibilidad()==true && cliente.getEdad()>=this.getPelicula().getClasificacion()) {
			
			boleto.setDisponibilidad(false);		//Al comprar el boleto se quita su disponibilidad
			
			cliente.historialCompras.add(boleto);	//Agregamos el boleto que se comprar� al historial del cliente
			
			cantidadBoletosVendidos++;				
			boleto.calcularPrecioDefinitivo(cliente); //le calculamos el precio del boleto al cliente si este posee un descuento o algo
			
			float ganancia= cine.getDineroGanado()+boleto.getPrecioTotal(); //Se suma las ganancias que se tienen hasta el momento con el precio total del boleto
			
			cine.setDineroGanado(ganancia); 	// se establece el nuevo valor
			pelicula.anadirCantidadBoletos();  // se suma en uno el valor de los boletos vendidos por pelicula
			
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//
	//getting and setting
	//

	public String getHorario() {
		return horario.getHora();
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
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

	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public ArrayList<Boleto> getBoletos() {
		return boletos;
	}

	public void setBoletos(ArrayList<Boleto> boletos) {
		this.boletos = boletos;
	}

	public int getNumero() {
		return numero;
	}

	public static int getCantidadFunciones() {
		return cantidadFunciones;
	}
	
	public void setCine(Cine cine){
		this.cine = cine;
	}
	
	public Cine getCine(){
		return cine;
	}
}
