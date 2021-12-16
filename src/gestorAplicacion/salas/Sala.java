package gestorAplicacion.salas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.namespace.QName;

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
	protected Cine cine;

	protected ArrayList<Silla> sillas = new ArrayList<Silla>();			//contiene objetos silla que corresponden a las sillas de la sala
																		//esta depende de las filas y las columnas

	protected ArrayList<Funcion> funciones = new ArrayList<Funcion>();	//contiene las funciones creadas en dicho cine
	
	
	
	//Constructores 
	public Sala(int filas, int columnas, int filasvip, int precio, Cine cine) {
		this.filas = filas;
		this.columnas = columnas;
		this.filasvip = filasvip;
		this.precio=precio;
		this.crearSilleteria();
		this.cine=cine;
		cine.agregarSala(this);
		
		
		this.numero = cine.getSalas().size();
	}

	//Metodos abtractos

	public abstract int cantidadSillas();


	//Metodos
	
	private void crearSilleteria() {			
	/*No recibe ningun parametro y no retorna nada
	Es la encargada de crear cada silla dependiendo la cantidad de filasvip, filas, y columnas*/
		
	
		int total = filas*columnas; 			//numero de sillas

		int totalvip = filasvip*columnas;		//numero de sillas vip, este será reducido en uno cada que se
												//cree una nueva silla vip 
											

		String tipo = "VIP";					//tipo de silla sera cambiado cuando totalvip sea = a 0
		
		for(int i = 0;i<total;i++) {			//for que itera la cantidad de sillas 
			
			if(totalvip<=0) {					//si se acabaron las sillas vip cambia tipo a SENCILLA
				tipo = "SENCILLA";
			}
			else {								//de lo contrario se reduce totalvip en uno
				totalvip--;
			}
			
			Silla silla = new Silla(tipo,i+1);	//se crea un nuevo objeto le parametro tipo= al tipo que teniamos
												//anteriormente y un número que corresponde al numero de la iteracion + 1

			sillas.add(silla);					//se añade el objeto a la lista de sillas
		}
	}

	
	public void agregarFuncion(Funcion funcion) {
	/*Recibe como parametro la funcion que se agregará a la lista de funciones 
	y no retorna nada*/

		funciones.add(funcion);	//se agrega la funcion a la lista de funciones
	}
	
	
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
		
		return respuesta.equals("12:00\n14:00\n16:00\n18:00\n20:00\n22:00\n");
	}

	public boolean almenosUnoDisponible(int dia, int mes) {
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
		
		if( respuesta.length() >= 5){
			return true;
		}else{
			return false;
		}
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
