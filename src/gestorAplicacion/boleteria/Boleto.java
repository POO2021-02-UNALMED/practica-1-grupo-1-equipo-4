package gestorAplicacion.boleteria;
import java.io.Serializable;
import gestorAplicacion.salas.Silla;

import gestorAplicacion.salas.Tipo;

import gestorAplicacion.cinema.Cliente;

public class Boleto implements Serializable{
	private static final long serialVersionUID = 1L;
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
	
	// Se calcula el precio bruto 
	// Se suma el precio de la sala y el precio de la silla
	// El precio de la silla puede ser VIP o sencilla los precios de estas son fijos
	private float calcularPrecio() {						 
		float bruto=funcion.getSala().getPrecio()+precio_silla;	
		
		return bruto;
	}
	
	// Se realiza el respectivo descuento con el atributo de descuento del cliente 
	public float calcularPrecioDefinitivo(Cliente cliente) {
		float total= calcularPrecio()-(calcularPrecio()*(cliente.getDescuento()/100));
		return total ;
	}
	
	public String disponibilidad() {
		if(disponibilidad) {
			return "O|";
		}
		return "X|";
	}
	
	public String tipoString() {
		if(tipo_silla==Tipo.VIP) {
			return "V-";
		}
		return "S-";
	}
	//
	// getting and setting:
	//
	
	public long getId() {
		return id;
	}
	public float getPrecio_silla() {
		return precio_silla;
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
