package gestorAplicacion.boleteria;

public class Pelicula {
	private String nombre;
	private int duracion;
	private String lenguaje;
	private int clasificacion;
	private int cantidadTotalBoletosVendidos;
	private int expectativaVentas;
	
	
	//
	//getting and setting:
	//
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	public int getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public int getCantidadTotalBoletosVendidos() {
		return cantidadTotalBoletosVendidos;
	}
	public void setCantidadTotalBoletosVendidos(int cantidadTotalBoletosVendidos) {
		this.cantidadTotalBoletosVendidos = cantidadTotalBoletosVendidos;
	}
	
	public int getExpectativaVentas() {
		return expectativaVentas;
	}
	public void setExpectativaVentas(int expectativaVentas) {
		this.expectativaVentas = expectativaVentas;
	}

}
