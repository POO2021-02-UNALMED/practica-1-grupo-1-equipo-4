package baseDatos;
import java.io.Serializable;
import gestorAplicacion.boleteria.*;
import gestorAplicacion.cinema.*;
import gestorAplicacion.salas.*;
import java.util.*;

public class Ensayo {
	public static void main(String[] args){

        // pelis 

		Pelicula rey_leon = new Pelicula();
		Pelicula avenger = new Pelicula();
		rey_leon.setNombre("El Rey Leon");
		avenger.setNombre("Avengers End Game");


        // cine 
        Cine vizcaya = new Cine();

		Sala2D sala1 = new Sala2D(1, 7, 8, 2);
        Sala3D sala2 = new Sala3D(2, 9, 9, 2, 36);

        vizcaya.agregarSala(sala1);
        vizcaya.agregarSala(sala2);

		Funcion funcion_1 = Funcion.crearFuncion(1, 3, Horario.UNO, rey_leon, 1, vizcaya);
		//funcion_1.getBoletos().get(3).setDisponibilidad(false);
		//System.out.println(funcion_1.verDisponibilidad());
		Funcion funcion_2 = Funcion.crearFuncion(1,3,Horario.DOS, avenger, 2, vizcaya);

		Serializador.serializar(vizcaya);
		
	}    
}
