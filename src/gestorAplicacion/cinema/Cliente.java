package gestorAplicacion.cinema;

import java.util.*;
import gestorAplicacion.boleteria.*;

public class Cliente {
	private int cedula;
	private String nombre;
	private int edad;
	private String ocupacion;
	private float descuento;
	private List<Boleto> historialCompras= new ArrayList<Boleto>();//!!!
	private int referidos;
	
	//
	//methods
	//
	
	//!!! Falta declarar clase Funcion.
	return string mostWatchedGenre() {
		List<Boleto> genreList=new ArrayList<Boleto>();
		for(Boleto boleto: historialCompras) {
			genreList.add(boleto.getFuncion().getPelicula().getGenero());
		}
		List<Integer> cuenta=new ArrayList<Integer>();
		for(String genre: genreList) {
			int occ = Collections.frequency(genreList, genre);
			cuenta.add(occ);
		}
		
		return genreList.get(cuenta.indexOf(Collections.max(cuenta)));
		
	}
	
	//
	//getting and setting
	//
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public int getReferidos() {
		return referidos;
	}
	public void setReferidos(int referidos) {
		this.referidos = referidos;
		
	}
	
	public List<Boleto> getHistorialCompras() {
		return historialCompras;
	}
	public void setHistorialCompras(List<Boleto> historialCompras) {
		this.historialCompras = historialCompras;
	}
	
	
	

}
