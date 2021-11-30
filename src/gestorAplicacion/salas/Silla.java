package gestorAplicacion.salas;

public class Silla {
	private enum Tipo {VIP, SENCILLA};
	private Tipo tipo;
	private int numero;
	private int precio;
	
	
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
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
}
