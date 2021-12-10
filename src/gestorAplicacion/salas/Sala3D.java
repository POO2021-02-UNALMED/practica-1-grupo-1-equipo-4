package gestorAplicacion.salas;
import java.io.Serializable;
public class Sala3D extends Sala implements Serializable{
	private static final long serialVersionUID = 1L;

	private int cantidadGafas;
	
	public Sala3D(int numero, int filas, int columnas, int filasvip, int cantidadgafas) {
		super(numero, filas, columnas, filasvip, 30);
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
