
class Sala2D(Sala):


    public Sala2D(int filas, int columnas, int filasvip,Cine cine) {
		super(filas, columnas, filasvip, 2000,cine);
	}
	
	//Sala 2D para un tamano de sala default
	public Sala2D(int vip, Cine cine) {
		this(8, 12, vip, cine);
	}