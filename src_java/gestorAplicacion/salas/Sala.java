//Funcionalidad de la clase: En este se podra verificar las disponibilidades de la sala, agregar funciones y ver horarios disponibles 

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa



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
	//declaracion de atributos
	protected int numero;
	protected int filas;
	protected int columnas;
	protected int filasvip;
	protected float precio;
	protected Cine cine;

	protected ArrayList<Silla> sillas = new ArrayList<Silla>();			//contiene objetos silla que corresponden a las sillas de la sala
																		//esta depende de las filas y las columnas

	protected ArrayList<Funcion> funciones = new ArrayList<Funcion>();	//contiene las funciones creadas en dicho cine
	
	
	//
	//Constructores 
	//
	public Sala(int filas, int columnas, int filasvip, int precio, Cine cine) {
		//declaracion de atributos
		this.filas = filas;			
		this.columnas = columnas;
		this.filasvip = filasvip;
		this.precio=precio;
		this.cine=cine;
	

		this.crearSilleteria(); 			// se crea la silleteria de la sala

		cine.agregarSala(this);				// se agrega sala a el cine
		
		this.numero = cine.getSalas().size();// el número de la sala es el número de elementos que hay en 
	}

	//
	//Metodos abtractos
	//

	public abstract int cantidadSillas();
	
	public abstract void crearSilleteria();

	//
	//Metodos
	//



	
	public void agregarFuncion(Funcion funcion) {
	/*Recibe como parametro la funcion que se agregará a la lista de funciones 
	y no retorna nada*/

		funciones.add(funcion);		//se agrega la funcion a la lista de funciones
	}
	

	public boolean verificarDisponibilidad(int dia, int mes, String hora) {
	/*recibe dia, mes y hora para verificar si la sala esta disponible para 
	programar una funcion en la fecha dada, devuelve verdadero si cuenta con
	disponibilidad de lo contrario devuelve falso*/

		String consulta=dia+"/"+mes+"/"+hora;							//se crea un string con el dia mes y horario
		
		ArrayList<String>	fechasfunciones = new ArrayList<String>();	//lista que se usara para guardar las fechas de todas
																		//las funciones dadas en dicha sala 
		
		for (Funcion func:funciones) {									
			String info=func.getDia()+"/"+func.getMes()+"/"+func.getHorario();	//se crea el string de la fecha de la funcion
			fechasfunciones.add(info);											//y se añade a fechasfunciones
			
			info="";															//se resetea el string	
		}

		for (String i:fechasfunciones) {
			if (i.equals(consulta)) {							//si el stringes igual a la fecha dada por la entrada
				return false;									//la sala esta ocupada en dicha fecha
			}
		}

		return true;											// de lo contrario devuelve verdadero, es decir libre
	}
	
	
	public boolean verificarDisponibilidad(int dia, int mes) {
	/*recibe dia y mes devueve un boolean diciendo si la sala tiene todos los horarios disponibles
	dicha fecha o no*/
	
		String consulta=""+dia+mes;							//crea un estring con la fecha
		
		ArrayList<String>fechas = new ArrayList<String>();	//lista para guardar las fechas de todas las funciones dadas 
															//en dicha sala

		ArrayList<String>horarios = new ArrayList<String>();//lista que guarda los horarios en los cuales la sala tiene funcion
															//dicho dia 
		
		ArrayList<String>disponibles= new ArrayList<>(		//lista con todos los horarios, a la cual se le restara
			Arrays.asList("12:00","14:00",					//lista con todos los horarios, a la cual se le restara 
			"16:00","18:00","20:00","22:00"));				//los horarios disponibles
		
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
		
		return respuesta.equals("12:00\n14:00\n16:00\n18:00\n20:00\n22:00\n");// devuelve un valor de verdad si todos los horarios estan disponibles
	}


	public boolean almenosUnoDisponible(int dia, int mes) {
	/*recibe dia y mes devueve un boolean diciendo si la sala tiene almenos un horario disponible
	dicha fecha o no*/

		String consulta=""+dia+mes;							//crea un estring con la fecha
		
		ArrayList<String>fechas = new ArrayList<String>();	//lista para guardar las fechas de todas las funciones dadas 
														//en dicha sala

		ArrayList<String>horarios = new ArrayList<String>();//lista que guarda los horarios en los cuales la sala tiene funcion
														//dicho dia 
	
		ArrayList<String>disponibles= new ArrayList<>(		//lista con todos los horarios, a la cual se le restara
		Arrays.asList("12:00","14:00",					//lista con todos los horarios, a la cual se le restara 
		"16:00","18:00","20:00","22:00"));				//los horarios disponibles
		
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
		
		if( respuesta.length() >= 5){		//si el string contiene por lo menos un horario devuelve true
			return true;
		}else{
			return false;
		}
	}
	

	public String verHorarios(int dia, int mes) {
	/*Recibe un dia y un mes como una fecha y devuelve un String que contiene los horarios disponibles
	para dicho dia*/ 

		String consulta=""+dia+mes;								//crea un estring con la fecha
		
		ArrayList<String>fechas = new ArrayList<String>();		//lista para guardar las fechas de todas las funciones dadas 
																//en dicha sala

		ArrayList<String>horarios = new ArrayList<String>();	//lista que guarda los horarios en los cuales la sala tiene funcion
																//dicho dia 

		ArrayList<String>disponibles= new ArrayList<>(			//lista con todos los horarios, a la cual se le restara
			Arrays.asList("12:00","14:00",						//lista con todos los horarios, a la cual se le restara 
			"16:00","18:00","20:00","22:00"));					//los horarios disponibles
	
		for (Funcion func:funciones) {

			String info=""+func.getDia()+func.getMes();			//del atributo funcion se almacenan en modo string
			fechas.add(info);									//el dia y mes de las funciones
			info="";								
		
		}											
		
		for (int i=0;i<fechas.size();i++) {
			if (fechas.get(i).equals(consulta)) {				//si la fecha de las funciones que hay coincide con la fecha y hora de consulta
				horarios.add(funciones.get(i).getHorario());	//se almacena
			}
		}
		
		for (String horario:horarios) {
			disponibles.remove(horario);						//se quita de los horarios disponibles, los que ya estan ocupados en ese dia de ese mes
		}
		
		String respuesta="";
		
		for(String d:disponibles) {								//se hace un string que devuelve los horarios disponibles
			respuesta+=d+"\n";
		}
		
		return respuesta;										//se devuelve ese string
		
	}
	

	//
	//getting and setting
	//

	public String getTipo(){
		/*returna el tipo en String*/
	
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
