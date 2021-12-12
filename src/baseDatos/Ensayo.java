package baseDatos;
import java.io.Serializable;
import gestorAplicacion.boleteria.*;
import gestorAplicacion.cinema.*;
import gestorAplicacion.salas.*;
import java.util.*;

public class Ensayo {
	public static void main(String[] args){

        // pelis 
		/*
		Pelicula rey_leon = new Pelicula();
		Pelicula avenger = new Pelicula();
		rey_leon.setNombre("El Rey Leon");
		avenger.setNombre("Avengers End Game");


        // cine 
        Cine vizcaya = new Cine("Vizcaya");
		
		Sala2D sala1 = new Sala2D(1, 7, 8, 2);
        Sala3D sala2 = new Sala3D(2, 9, 9, 2, 36);

        vizcaya.agregarSala(sala1);
        vizcaya.agregarSala(sala2);

		Funcion funcion_1 = Funcion.crearFuncion(1, 3, Horario.UNO, rey_leon, 1, vizcaya);
		//funcion_1.getBoletos().get(3).setDisponibilidad(false);
		//System.out.println(funcion_1.verDisponibilidad());
		Funcion funcion_2 = Funcion.crearFuncion(1,3,Horario.DOS, avenger, 2, vizcaya);

		Serializador.serializar(vizcaya);*/
		/*Cine cine = Deserializador.deserializar();
		
		System.out.println(cine.getPeliculas().get(0).verDisponibilidad());
		
		public String verHorarios(int dia, int mes){
			String fechaAVerificar=dia+mes;
			ArrayList<String> fechas = new ArrayList<String>();
			ArrayList<Horario> horarios = new ArrayList<Horario>();

			for (Funcion func:funciones) {
				String info=func.getDia()+func.getMes();	
				totalfunciones.add(info);				//se almacena en una lista la informacion
				info="";								//de las funciones creadas
			}

			for(int i =0; i<fechas.size();i++){ 	//lista de indices de funciones de la fecha dada
				if(fechas.get(i).equals(consulta)){
					horarios.add(funciones.get(i).getHorario().getHora());
				}
			}
		}*/
	}    
}
