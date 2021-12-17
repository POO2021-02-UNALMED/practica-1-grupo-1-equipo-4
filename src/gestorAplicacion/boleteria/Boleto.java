//Funcionalidad de la clase:

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa


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



	public float calcularPrecio() {		
	/*No recibe nada y devuelve un float el cual corresponde al calculo del precio bruto del boleto 
	el cual depende del precio de la sala y el precio de la silla */
	
		float bruto=funcion.getSala().getPrecio()+precio_silla;	 	// Se suma el precio de la sala y el precio de la silla
		
		return bruto;
	}
	
	
	public void calcularPrecioDefinitivo(Cliente cliente) {
		/*Recibe a un cliente  y no devuelve nada, este precio se le descuenta un descuento(Si este cliente lo tiene)
		 */
		float total= calcularPrecio()-(calcularPrecio()*(cliente.getDescuento())); //Al precio bruto le resta el descuento del cliente si este lo posee
		this.setPrecioTotal(total);				//Se establece el resultado de la linea anterior al atributo PrecioTotal
	}
	
	public String disponibilidad() {
	/*No recibe nada y devuelve String el cual corresponde al simbolo de la disponibilidad
	 del boleto, en caso de que est� libre entrara en el if de lo contrario return X	
	 */
		if(disponibilidad) {	//Si la disponibilidad del boleto es true imprime 0 
			return "D|";
		}
		return "O|";		
	}
	
	public String tipoString() {
		/*No recibe nada  y  devuelve un String el cual indica si el tipo de la silla al cual esta relacionado el boleto
		 */
		if(tipo_silla==Tipo.VIP) {	//El tipo de la silla es VIP retornara V-
			return "V-";
		}
		return "S-";		//En caso de que sea sencilla retornara S-
	}

	//
	// getting and setting:
	//
	

	public float getPrecio_silla() {
		return precio_silla;
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

	private void setAtr_silla(Silla silla) {	
		/*Recibe la silla con la que deseo asignarle los atributos de numero,tipo de silla y precio de silla  y no devuelve nada	
		 */

		this.num_silla = silla.getNumero();			// Se establece al atributo de num_silla  el numero de la silla que recibe
		this.setTipo_silla(silla.getTipo());		//Se establece al atributo tipo_silla el tipo de la silla que recibe 
		this.setPrecio_silla(silla.getPrecio());	//Se establece al atributo precio_silla el precio de la silla que recibe
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
