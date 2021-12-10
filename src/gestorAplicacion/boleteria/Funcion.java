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
	private int cantidadBoletosVendidos = 0;
	private Cine cine;
	private static int cantidadFunciones;
	private int numero;
	
	private Funcion(int dia, int mes, Horario horario, Pelicula pelicula, Sala sala, Cine cine) {
		this.dia = dia;
		this.mes = mes;
		this.horario = horario;
		this.pelicula = pelicula;
		this.setSala(sala);
		sala.agregarFuncion(this);
		this.crearBoleteria();
		cantidadFunciones++;
		this.numero=cantidadFunciones;
		this.setCine(cine);
	}
	
	//
	//		METODOS
	//
	
	//
	// PARA CREAR FUNCIï¿½N SE VA A USAR ESTE METODO, NO EL CONSTRUCTOR
	//

	public static Funcion crearFuncion(int dia, int mes, Horario horario, Pelicula pelicula, int num, Cine cine) { 
		Sala sala = cine.buscarSala(num);                                   //acï¿½ se revisa si la sala existe en cine
		if(sala != null) {												    				
			if(sala.verificarDisponibilidad(dia, mes, horario.getHora())) { // verifica que la sala tenga disponibilidad en dicha hora
				return new Funcion(dia, mes, horario, pelicula, sala, cine);		// crea la funciï¿½n
			}		
			else {
				return null;												// no la crea
			}
			
		}
		else{
			return null;													// sala inexistente
		}
	}


	// 
	// metodo para crear los boletos de la sala
	private void crearBoleteria(){
		ArrayList<Silla> sillas = sala.getSillas(); 
		int disponibles = sala.cantidadSillas();

		for(int i = 0; i < sala.getSillas().size();i++) {    //crear la cantidad de boletos que corresponde segï¿½n cantidad de sillas
			if(disponibles > 0){
				Boleto boleto = new Boleto(this, sillas.get(i));
				boletos.add(boleto);
				disponibles--;
			}else{
				boletos.add(null);
			}
		}
		
	}

	public String verDisponibilidad(){
		ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>(); // lista de filas

		// for para hacer una lista de listas, cada lista corresponde a una fila de boletos
		for(int i = 0 ; i<sala.getFilas(); i++){
			ArrayList<String> fila = new ArrayList<String>(); 
			for(int j = 0; j<sala.getColumnas() ; j++){		 
				Boleto boleto = boletos.get((i)*sala.getColumnas()+j); 
				if (boleto != null){
					String formato_boleto = boleto.disponibilidad()+boleto.tipoString()+String.valueOf(boleto.getNum_silla());
					fila.add(formato_boleto);
				}else{
					String formato_boleto = "";
					fila.add(formato_boleto);
				}

			}
			total.add(fila);
		}

		String formato = "";

		// for para formatear un string con la silleterÃ­a para imprimir
		for(ArrayList<String> fila: total){
			String patron = "%-6s   ".repeat(sala.getColumnas()); // se crea el formato con la cantidad de variables necesarias (columnas) por fila
			Object[] fila_args = fila.toArray(new String[0]); // se crea una lista para pasar como *args
			formato += String.format(patron,fila_args) + "\n"; // se le agrega a el string resultante la fila
		}
		formato += "\n"+centerString(sala.getColumnas()*9,"PANTALLA")+"\n";
		return formato;
	}

	public static String centerString (int width, String s) {
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}


	//Método de venta de boletos 
	public void VentaBoleto(Boleto boleto, Cliente cliente,Cine cine ) {
		boleto.setDisponibilidad(false);		//Al comprar el boleto se quita su disponibilidad
		cliente.historialCompras.add(boleto);	//Agregamos el boleto que se comprará al historial del cliente
		cantidadBoletosVendidos++;				
		boleto.calcularPrecioDefinitivo(cliente); //le calculamos el precio del boleto al cliente si este posee un descuento o algo
		float ganancia= cine.getDineroGanado()+boleto.getPrecioTotal(); //Se suma las ganancias que se tienen hasta el momento con el precio total del boleto
		cine.setDineroGanado(ganancia); // se establece el nuevo valor
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
		cine.agregarFuncion(this);
	}
	
	public Cine getCine(){
		return cine;
	}
	
	// MAIN PARA ENSAYAR
	
	public static void main(String[] args){
		Pelicula rey_leon = new Pelicula();
		Pelicula avenger = new Pelicula();
		rey_leon.setNombre("El Rey Leon");
		avenger.setNombre("Avengers End Game");
		Sala3D sala1 = new Sala3D(1, 7, 8, 2, 49);
		Funcion funcion_1 = new Funcion(1, 3, Horario.UNO, rey_leon, sala1);
		funcion_1.getBoletos().get(3).setDisponibilidad(false);
		System.out.println(funcion_1.verDisponibilidad());
		/*Funcion funcion_2 = new Funcion(1,3,Horario.DOS, avenger, sala1);
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		funciones.add(funcion_1);
		funciones.add(funcion_2);
		System.out.println(Cine.formatearFunciones(funciones));*/
	}
	
}
