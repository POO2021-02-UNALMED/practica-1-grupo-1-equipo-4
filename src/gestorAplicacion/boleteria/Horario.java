package gestorAplicacion.boleteria;

public enum Horario {
	//Enum con la intencion de limitar los horarios del cine
	//Son seis horarios, cada uno con una hora relacionada
	UNO("12:00"), DOS("14:00"), TRES("16:00"), CUATRO("18:00"), CINCO("20:00"), SEIS("22:00");
	private String hora;

	private Horario(String hora) {
		//constructor de Horario con la inteción de asociar a cada horario una hora
		this.setHora(hora);
	}
	
	public static Horario getHorario(String hora) {
	/*Metodo Statico que devuelve una hora en Horario y devuelve esa misma hora en String*/

		Horario[] horarios = {UNO,DOS,TRES,CUATRO,CINCO,SEIS};		//lista los posibles horarios posibles

		for(Horario horario: horarios) {							//for para buscar cual horario de horarios
			if(hora.equals(horario.getHora())) {					//coincide con la hora ingresada

				return horario;										//retorna el horario en string

			}
		
		}
		return null;
	}
	
	public String getHora(){
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
