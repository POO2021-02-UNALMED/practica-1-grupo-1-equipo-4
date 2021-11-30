package gestorAplicacion.salas;

import java.util.ArrayList;
import gestorAplicacion.boleteria.Funcion;

public class Sala {
	private int numero;
	private int filas;
	private int columnas;
	private int filasvip;
	private float precio;
	private ArrayList<Silla> sillas = new ArrayList<Silla>();
	private ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	
	public Sala(int numero, int filas, int columnas, int filasvip, int precio) {
		this.numero = numero;
		this.filas = filas;
		this.columnas = columnas;
		this.filasvip = filasvip;
	}

	
	
	public void crearSilleteria() {
		
		int total = filas*columnas; 		//número de filas
		int totalvip = filasvip*columnas;	//número de filas vip
		String tipo = "VIP";
		
		for(int i = 0;i<total;i++) {
			
			if(totalvip<=0) {
				tipo = "SENCILLA";
			}
			else {
				totalvip--;
			}
			
			Silla silla = new Silla(tipo,i+1);
			sillas.add(silla);
		}
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public ArrayList<Silla> getSillas() {
		return sillas;
	}

	public void setSillas(ArrayList<Silla> sillas) {
		this.sillas = sillas;
	}

	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(ArrayList<Funcion> funciones) {
		this.funciones = funciones;
	}

	public int getFilasVip() {
		return filasvip;
	}

	public void setFilasVip(int vip) {
		this.filasvip = vip;
	}
	
}
