package gestorAplicacion.boleteria;

import java.io.Serializable;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.salas.Sala;

public class Funcion{
	
	private int dia;
	private int mes;
	private Horario horario;
	private Pelicula pelicula;
	private Sala sala;
	private int cantidadBoletosVendidos = 0;
	private Cine cine;
	
	private Funcion(int dia, int mes, Horario horario, Pelicula pelicula, Sala sala) {
		this.dia = dia;
		this.mes = mes;
		this.horario = horario;
		this.pelicula = pelicula;
		this.sala = sala;
		sala.agregarFuncion(this);
	}
	
	
	//
	//metodo para crear sala verificando existencia de sala y disponibilidad de esta
	//
	// PARA CREAR FUNCIÓN SE VA A USAR ESTE METODO, NO EL CONSTRUCTOR
	//
	public Funcion crearFuncion(int dia, int mes, Horario horario, Pelicula pelicula, int num) { 
		Sala sala = cine.buscarSala(num);
		if(sala != null) {
			if(sala.verificarDisponibilidad(dia, mes, horario.getHora())) {
				return new Funcion(dia, mes, horario, pelicula, sala);
			}
			else {
				return null;
			}
			
		}
		else{
			return null;
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
	
	//
	//methods
	//
}
