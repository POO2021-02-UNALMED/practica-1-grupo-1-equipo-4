package gestorAplicacion.salas;

import java.util.ArrayList;

import gestorAplicacion.boleteria.Funcion;

public abstract class Sala {
	protected int numero;
	protected int filas;
	protected int columnas;
	protected int filasvip;
	protected float precio;
	protected ArrayList<Silla> sillas = new ArrayList<Silla>();
	protected ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	
	public Sala(int numero, int filas, int columnas, int filasvip, int precio) {
		this.numero = numero;
		this.filas = filas;
		this.columnas = columnas;
		this.filasvip = filasvip;
		this.precio=precio;
		this.crearSilleteria();
	}
	
	public void crearSilleteria() {			//  pendiente asignar "fila-columna" a cada sillaS
		
		int total = filas*columnas; 		//n�mero de filas
		int totalvip = filasvip*columnas;	//n�mero de filas vip
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
	
	public void agregarFuncion(Funcion funcion) {
		funciones.add(funcion);
	}
	
	public abstract int cantidadSillas();
	
	//verificar disponibilidad
	public boolean verificarDisponibilidad(int dia, int mes, String hora) {
		String consulta=dia+mes+hora;
		ArrayList<String>totalfunciones = new ArrayList<String>();
		
		for (Funcion func:funciones) {
			String info=func.getDia()+func.getMes()+func.getHorario();	
			totalfunciones.add(info);				//se almacena en una lista la informacion
			info="";								//de las funciones creadas
			
		}
		for (String i:totalfunciones) {
			if (i.equals(consulta)) {			//si cuando se quiere crear la funcion ya hay otra fecha ahi
				return false;					//devuelve falso y no se puede crear
			}
		}
		return true;
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
