//Funcionalidad de la clase: En este se crea la silleteria y se puede observar la cantidad de sillas que posee esta Sala 3D

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa


package gestorAplicacion.salas;
import java.io.Serializable;
import gestorAplicacion.cinema.Cine;
public class Sala3D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;

	private int cantidadGafas;
	
	public Sala3D(int filas, int columnas,  int cantidadgafas, Cine cine) {
		super(filas, columnas, 0, 5000,cine);
		this.cantidadGafas=cantidadgafas;
	}
	
	//Constructor para cuando la cantidad de gafas es igual a la cantidad de sillas
	public Sala3D(int filas, int columnas,  Cine cine) {
		this(filas, columnas, filas*columnas, cine);
	}
	

	public int cantidadSillas() {
	/*No recibe nada y devuelve un entero el cual corresponde a la cantidad de sillas
	disponibles para la creacion de los boletos de la funcion
	esta se encuentra limitada por la cantidad de gafas 3d disponibles para dicha sala*/
		
		int totalsillas=sillas.size();	 //corresponde a la cantidad de sillas del cine

		if (totalsillas<cantidadGafas) { //Si la cantidad de sillas es menor a la cantidad de gafas
			return totalsillas;			 //se devuelve la cantidad de sillas
		}	
		return cantidadGafas;			 //de lo contrario se devuelve la cantidad de gafas
	}

	public void crearSilleteria() {			
	/*No recibe ningun parametro y no retorna nada
	Es la encargada de crear cada silla dependiendo la cantidad de filas, y columnas
	por ser la sala 3D crea todas las sillas vip*/
			
		
		int total = filas*columnas; 			//numero de sillas

												
	
		String tipo = "VIP";				
			
		for(int i = 0;i<total;i++) {			//for para crear las sillas dependiendo del total
				
			Silla silla = new Silla(tipo,i+1);	
			

			sillas.add(silla);					
		}
	}

	//
	//getting and setting
	//
	public int getCantidadGafas() {			
		return cantidadGafas;
	}

	public void setCantidadGafas(int cantidadGafas) {
		this.cantidadGafas = cantidadGafas;
	}

}
