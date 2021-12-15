package gestorAplicacion.salas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Horario;
import gestorAplicacion.cinema.Cine;

public abstract class Sala implements Serializable,Agregar{
	private static final long serialVersionUID = 1L;
	protected int numero;
	protected int filas;
	protected int columnas;
	protected int filasvip;
	protected float precio;
	protected ArrayList<Silla> sillas = new ArrayList<Silla>();
	protected ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	protected Cine cine;
	
	
	//Constructores 
	//??? Porque no hacer la creación de Sala como la de de función, se usa un static que guarde la cantidad de salas y se va sumando, para no tener que pasar número de sala
	public Sala(int numero, int filas, int columnas, int filasvip, int precio, Cine cine) {
		this.numero = numero;
		this.filas = filas;
		this.columnas = columnas;
		this.filasvip = filasvip;
		this.precio=precio;
		this.crearSilleteria();
		this.cine=cine;
	}
	
	public Sala(int numero, int filas, int columnas, int filasvip, int precio) {
		this.numero = numero;
		this.filas = filas;
		this.columnas = columnas;
		this.filasvip = filasvip;
		this.precio=precio;
		this.crearSilleteria();
	}
	
	
	//M�todos 
	
	private void crearSilleteria() {			//  pendiente asignar "fila-columna" a cada sillaS
		
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
	
	//Metodos abtractos
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
	
	//Verificar disponibilidad total para un dia de un mes
	public boolean verificarDisponibilidad(int dia, int mes) {
		String consulta=""+dia+mes;
		ArrayList<String>fechas = new ArrayList<String>();
		ArrayList<String>horarios = new ArrayList<String>();
		ArrayList<String>disponibles= new ArrayList<>(Arrays.asList("12:00","14:00","16:00","18:00","20:00","22:00"));
		
		for (Funcion func:funciones) {
			String info=""+func.getDia()+func.getMes();		//del atributo funcion se almacenan en modo string
			fechas.add(info);								//el dia y mes de las funciones
			info="";								
			
		}
		
		for (int i=0;i<fechas.size();i++) {
			if (fechas.get(i).equals(consulta)) {				//si la fecha de las funciones que hay coincide con la fecha y hora de consulta
				horarios.add(funciones.get(i).getHorario());	//se almacena
			}
		}
		
		for (String horario:horarios) {
			disponibles.remove(horario);		//se quita de los horarios disponibles, los que ya estan ocupados en ese dia de ese mes
		}
		
		String respuesta="";
		
		for(String d:disponibles) {			//se hace un string que devuelve los horarios disponibles
			respuesta+=d+"\n";
		}
		
		return respuesta.equals("12:00\n14:00\n16:00\n18:00\n20:00\n22:00");
	}
	
	
	public String verHorarios(int dia, int mes) {
		String consulta=""+dia+mes;
		ArrayList<String>fechas = new ArrayList<String>();
		ArrayList<String>horarios = new ArrayList<String>();
		ArrayList<String>disponibles= new ArrayList<>(Arrays.asList("12:00","14:00","16:00","18:00","20:00","22:00"));
		
		for (Funcion func:funciones) {
			String info=""+func.getDia()+func.getMes();		//del atributo funcion se almacenan en modo string
			fechas.add(info);								//el dia y mes de las funciones
			info="";								
			
		}
		
		for (int i=0;i<fechas.size();i++) {
			if (fechas.get(i).equals(consulta)) {				//si la fecha de las funciones que hay coincide con la fecha y hora de consulta
				horarios.add(funciones.get(i).getHorario());	//se almacena
			}
		}
		
		for (String horario:horarios) {
			disponibles.remove(horario);		//se quita de los horarios disponibles, los que ya estan ocupados en ese dia de ese mes
		}
		
		String respuesta="";
		
		for(String d:disponibles) {			//se hace un string que devuelve los horarios disponibles
			respuesta+=d+"\n";
		}
		
		return respuesta;		//se devuelve ese string
		
	}
	
	public String getTipo(){
		if(this instanceof Sala2D){
			return "2D";
		}else{
			return "3D";
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
