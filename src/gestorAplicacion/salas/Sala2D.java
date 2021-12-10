package gestorAplicacion.salas;
import java.io.Serializable;
public class Sala2D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Sala2D(int numero, int filas, int columnas, int filasvip) {
		super(numero, filas, columnas, filasvip, 15);
	}
	public int cantidadSillas() {
		return super.sillas.size();
	}
	

}
