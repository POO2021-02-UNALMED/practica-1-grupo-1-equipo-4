package gestorAplicacion.boleteria;

import gestorAplicacion.salas.Silla;
import gestorAplicacion.salas.Tipo;

public class Boleto {
	
	//
	//Atributos 
	//
	
	private long id;
	private int num_silla;
	private Tipo tipo_silla; 
	private float precioTotal;
	private boolean disponibilidad;
	private Funcion funcion;
	
	
	public Boleto(Funcion funcion, Silla silla) {
		this.funcion = funcion;
		this.setNum_silla(silla);
		this.disponibilidad = true;
		this.precioTotal = this.calcularPrecio();
	}

	private float calcularPrecio() { // PENDIENTE							 
									// PENDIENTE
		return 0;
	}
	
	public String disponibilidad() {
		if(disponibilidad) {
			return "D";
		}
		return "X";
	}
	
	public String tipostring() {
		if(tipo_silla==Tipo.VIP) {
			return "V";
		}
		return "S";
	}
	//
	// getting and setting:
	//
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Funcion getFuncion() {
		return funcion;
	}
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public int getNum_silla() {
		return num_silla;
	}

	public void setNum_silla(Silla silla) {
		this.num_silla = silla.getNumero();
		this.setTipo_silla(silla.getTipo());
	}

	public Tipo getTipo_silla() {
		return tipo_silla;
	}

	public void setTipo_silla(Tipo tipo_silla) {
		this.tipo_silla = tipo_silla;
	}
	
}
