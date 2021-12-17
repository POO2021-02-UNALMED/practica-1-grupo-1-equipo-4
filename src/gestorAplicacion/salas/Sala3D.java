package gestorAplicacion.salas;
import java.io.Serializable;
import gestorAplicacion.cinema.Cine;
public class Sala3D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;

	private int cantidadGafas;
	
	public Sala3D(int filas, int columnas, int filasvip, int cantidadgafas, Cine cine) {
		super(filas, columnas, filasvip, 5000,cine);
		this.cantidadGafas=cantidadgafas;
	}
	
	//Constructor para cuando la cantidad de gafas es igual a la cantidad de sillas
	public Sala3D(int filas, int columnas, int filasvip, Cine cine) {
		this(filas, columnas, filasvip, filas*columnas, cine);
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
