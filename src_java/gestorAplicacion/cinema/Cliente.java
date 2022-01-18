/*
Funcionalidad: Almacenar toda la informacion respectiva a los clientes asi como regular el descuento que se le hace
añadir a sus referidos y determinar su genero preferido
 */

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

package gestorAplicacion.cinema;
import java.io.Serializable;
import java.util.*;
import gestorAplicacion.boleteria.*;

public class Cliente implements Serializable{ 
	/*
	Para establecer todo lo referido a los datos del cliente, sus descuentos 
	 */
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	private int edad;
	private String ocupacion;
	private float descuento;
	private List<Boleto> historialCompras= new ArrayList<Boleto>(); //una lista con los boletos que ha comprado el cliente en el cine a traves de su vida
	private int referidos;
	private Cine cine;
	
	///Constructor
	
	public Cliente(int cedula, String nombre, int edad, String ocupacion, Cine cine) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.ocupacion = ocupacion;
		cine.agregarCliente(this);
		this.cine=cine;
		if (this.ocupacion.equals("Estudiante") ) { //Si los descuentos no sobrepasan el 0.4, se aplican 
			this.descuento+=(float)0.1;
		}
	}
	
	
	
	//
	//methods
	//
	
	//Aplicacion de descuentos
	public String descuentoCliente() {
		/*
		Este metodo no recibe nada, pero devuelve un String informando que se le realizo el descuento al cliente mas valioso o al que tenga referidos
		 */
		cine.mostValueClient();		//se le otorga un descuento del 40% al cliente que mas ha comprado
		
		if(descuento<=(float)0.39 && referidos>0) {      		//Si los descuentos no sobrepasan el 0.4, se aplican
			this.descuento+=(float) (0.01*referidos);
		}
		
		return "Descuento aplicado";
	}
	
	
	//Reflejar la pelicula más vista
	public String mostWatchedGenre() {
		/*
		No recibe nada pero devuelve una string con el genero mas visto del cliente
		 */
		List<String> genreList=new ArrayList<String>();		//lista para tener los generos que ha visto el cliente
		for(Boleto boleto: historialCompras) {
			genreList.add(boleto.getFuncion().getPelicula().getGenero()); 	//Recorre el historial de compras del cliente y anexa de los boletos sus generos
		}
		List<Integer> cuenta=new ArrayList<Integer>();		//lista para guardar la frecuencia de cada genero
		for(String genre: genreList) {
			int occ = Collections.frequency(genreList, genre);	//De la lista de géneros extrae la frecuencia de cada elemento
			cuenta.add(occ);
		}
		
		return genreList.get(cuenta.indexOf(Collections.max(cuenta))); 	//se devuelve el genero de la lista que tenga el indice correspondiente al maximo de las ocurrencias de cada genero
		
	}

	
	
	public void referidos() {
		/*
		no recibe ni devuelve nada, su proposito es sumar referidos al cliente y llamar el metodo para aplicar el descuento por cada referido
		 */
		this.referidos++;
		this.descuentoCliente();
	}
	
	
	@Override
	public String toString() {
		/*
		toString en cliente para facilitar el print de algunos de sus atributos en el UI.
		 */
		return "Cliente: " + nombre + "-" + String.valueOf(edad) + "-" + ocupacion;
		
	}
	
	
	//
	//getting and setting
	//
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public int getReferidos() {
		return referidos;
	}
	public void setReferidos(int referidos) {
		this.referidos = referidos;
		
	}
	
	public List<Boleto> getHistorialCompras() {
		return historialCompras;
	}
	public void setHistorialCompras(List<Boleto> historialCompras) {
		this.historialCompras = historialCompras;
	}


	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}
	
	
	

}
