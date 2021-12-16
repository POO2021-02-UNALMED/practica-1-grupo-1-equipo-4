package gestorAplicacion.salas;
import java.io.Serializable;
import gestorAplicacion.cinema.Cine;
public class Sala2D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Sala2D(int filas, int columnas, int filasvip,Cine cine) {
		super(filas, columnas, filasvip, 2000,cine);
	}
	
	//Sala 2D para un tamano de sala default
	public Sala2D(int vip, Cine cine) {
		this(8, 12, vip, cine);
	}
	
	public int cantidadSillas() {
	/*No recibe nada y devuelve un entero el cual corresponde a la cantidad de sillas
	disponibles para la creacion de los boletos de la funcion es decir la cantidad de 
	sillas*/

		return super.sillas.size();
	}
	
	

}
