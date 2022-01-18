//Funcionalidad de la clase: En este se crea la silleteria de la Sala 2D

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa


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
	
	public void crearSilleteria() {			
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

}
