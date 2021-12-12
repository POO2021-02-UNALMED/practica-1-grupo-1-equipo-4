package gestorAplicacion.cinema;
import java.io.Serializable;
import java.util.*;
import gestorAplicacion.boleteria.*;

public class Cliente implements Serializable{ //??? should this implements Serializable
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	private int edad; //??? Es necesaria una edad para el cliente
	private String ocupacion;
	private float descuento;
	public List<Boleto> historialCompras= new ArrayList<Boleto>();//??? Why is this public?
	private int referidos;
	private Cine cine;
	
	///Constructor
	
	public Cliente(int cedula, String nombre, int edad, String ocupacion, Cine cine) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.ocupacion = ocupacion;
		cine.agregarCliente(this);
		this.cine=cine;
	}
	
	
	
	//
	//methods
	//
	
	
	//Reflejar la película más vista
	public String mostWatchedGenre() {
		List<String> genreList=new ArrayList<String>();
		for(Boleto boleto: historialCompras) {
			genreList.add(boleto.getFuncion().getPelicula().getGenero()); 	//Recorre el historial de compras del cliente y anexa de los boletos sus generos
		}
		List<Integer> cuenta=new ArrayList<Integer>();
		for(String genre: genreList) {
			int occ = Collections.frequency(genreList, genre);	//De la lista de géneros extrae la frecuencia de cada elemento
			cuenta.add(occ);
		}
		
		return genreList.get(cuenta.indexOf(Collections.max(cuenta))); 
		
	}

	
	//Sumar referidos al cliente
	public void referidos() {
		this.referidos++;
	}//!!!Revisar
	
	// Method toString en cliente para facilitar el print de algunos de sus atributos en el UI.
	@Override
	public String toString() { 
		return "Cliente: " + nombre + "-" + String.valueOf(edad) + "-" + ocupacion;
		
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


	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}
	
	
	

}
