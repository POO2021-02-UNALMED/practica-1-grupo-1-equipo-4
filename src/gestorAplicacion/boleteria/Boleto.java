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
	private float precio_silla;
	
	
	public Boleto(Funcion funcion, Silla silla) {
		this.funcion = funcion;
		this.setAtr_silla(silla);
		this.disponibilidad = true;
		this.precioTotal = this.calcularPrecio();
	}

	private float calcularPrecio() {						 
		float bruto=funcion.getSala().getPrecio()+precio_silla;		//falta añadir el descuento
		
		return bruto;
	}
	
	public String disponibilidad() {
		if(disponibilidad) {
			return "D";
		}
		return "X";
	}
	
	public String Tipostring() {
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

	private void setAtr_silla(Silla silla) {		//se setean los atributos de la silla para accederlos
		this.num_silla = silla.getNumero();			//facilmente
		this.setTipo_silla(silla.getTipo());
		this.setPrecio_silla(silla.getPrecio());
	}

	public void setPrecio_silla(float precio) {
		this.precio_silla=precio;
	}

	public Tipo getTipo_silla() {
		return tipo_silla;
	}

	public void setTipo_silla(Tipo tipo_silla) {
		this.tipo_silla = tipo_silla;
	}
	
}
