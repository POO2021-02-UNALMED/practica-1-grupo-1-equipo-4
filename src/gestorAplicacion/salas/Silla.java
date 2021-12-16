package gestorAplicacion.salas;
import java.io.Serializable;
public class Silla implements Serializable{
	private static final long serialVersionUID = 1L;
	private Tipo tipo;
	private int numero;
	private float precio;
	
	
	public Silla(String tipo, int numero) {
		setTipo(tipo); // se debe establecer el precio depende del tipo
		setNumero(numero);
	}
	
	
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		if(tipo.equals("VIP")) {
			this.tipo = Tipo.VIP;
		}else {
			this.tipo = Tipo.SENCILLA;
		}
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setPrecio(float precio) {
		this.precio=precio;
	}
	
	//Se ponen los precios de VIP como 7000 y 5000
	public float getPrecio() {
		if(this.tipo == Tipo.VIP) {
			return 7000;
		}else {
			return 5000;
		}
	}
	
}
