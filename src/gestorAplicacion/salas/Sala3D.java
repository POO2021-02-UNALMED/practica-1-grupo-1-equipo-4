package gestorAplicacion.salas;
import java.io.Serializable;
import gestorAplicacion.cinema.Cine;
public class Sala3D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;

	private int cantidadGafas;
	
	public Sala3D(int filas, int columnas, int filasvip, int cantidadgafas, Cine cine) {
		super(filas, columnas, filasvip, 30,cine);
		this.cantidadGafas=cantidadgafas;
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
