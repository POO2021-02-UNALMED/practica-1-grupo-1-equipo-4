package gestorAplicacion.salas;

public class Sala2D extends Sala {
	
	public Sala2D(int numero, int filas, int columnas, int filasvip) {
		super(numero, filas, columnas, filasvip, 15);
	}
	public Sala2D(boolean validar) {// metodo auxiliar para crear función, ignorar porfa
		super(validar);
	}

	public int cantidadSillas() {
		return super.sillas.size();
	}
	

}
