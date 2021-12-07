package gestorAplicacion.boleteria;

import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.salas.Sala;
import gestorAplicacion.salas.Silla;

public class Funcion{
	
	private int dia;
	private int mes;
	private Horario horario;
	private Pelicula pelicula;
	private Sala sala;
	private ArrayList<Boleto> boletos = new ArrayList<Boleto>();
	private int cantidadBoletosVendidos = 0;
	private Cine cine;
	
	private Funcion(int dia, int mes, Horario horario, Pelicula pelicula, Sala sala) {
		this.dia = dia;
		this.mes = mes;
		this.horario = horario;
		this.pelicula = pelicula;
		this.setSala(sala);
		sala.agregarFuncion(this);
		this.crearBoleteria();
	}
	
	//
	//methods
	//
	
	//
	// PARA CREAR FUNCIÓN SE VA A USAR ESTE METODO, NO EL CONSTRUCTOR
	//
	public Funcion crearFuncion(int dia, int mes, Horario horario, Pelicula pelicula, int num) { 
		Sala sala = cine.buscarSala(num);                                   //acá se revisa si la sala existe en cine
		if(sala != null) {												    				
			if(sala.verificarDisponibilidad(dia, mes, horario.getHora())) { // verifica que la sala tenga disponibilidad en dicha hora
				return new Funcion(dia, mes, horario, pelicula, sala);		// crea la función
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
		
		for(int i = 0; i <= sala.cantidadSillas();i++) {    //crear la cantidad de boletos que corresponde según cantidad de sillas
			Boleto boleto = new Boleto(this, sillas.get(i));
			boletos.add(boleto);
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
	

}
