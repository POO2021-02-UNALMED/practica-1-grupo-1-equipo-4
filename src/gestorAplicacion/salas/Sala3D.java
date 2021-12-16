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
		int totalsillas=sillas.size();
		if (totalsillas<cantidadGafas) {
			return totalsillas;
		}
		return cantidadGafas;
	}

	public int getCantidadGafas() {
		return cantidadGafas;
	}

	public void setCantidadGafas(int cantidadGafas) {
		this.cantidadGafas = cantidadGafas;
	}

}
