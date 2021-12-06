package gestorAplicacion.boleteria;

public enum Horario {
	UNO("12:00"), DOS("14:00"), TRES("16:00"), CUATRO("18:00"), CINCO("20:00"), SEIS("22:00");
	private String hora;
	private Horario(String hora) {
		this.setHora(hora);
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
