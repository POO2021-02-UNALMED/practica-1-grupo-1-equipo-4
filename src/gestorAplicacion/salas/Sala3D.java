package gestorAplicacion.salas;

public class Sala3D extends Sala{

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
