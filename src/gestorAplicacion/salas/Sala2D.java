package gestorAplicacion.salas;
import java.io.Serializable;
import gestorAplicacion.cinema.Cine;
public class Sala2D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Sala2D(int filas, int columnas, int filasvip,Cine cine) {
		super(filas, columnas, filasvip, 15,cine);
	}
	public int cantidadSillas() {
		return super.sillas.size();
	}
	

}
