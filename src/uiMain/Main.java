package uiMain;
import java.util.*;

import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;
import baseDatos.Deserializador;
import baseDatos.Serializador;

public class Main {
	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL CINE BAHIA");
		//agregar opciones
		int opcion;
		Cine cine = Deserializador.deserializar();
		for(Pelicula peli: cine.getPeliculas()) {
			System.out.println(peli);
		}
		do {
			System.out.println("Que quiere hacer?\n"
					+ "1. Vender\n"
					+ "2. Administrar\n");
			Scanner entrada = new Scanner(System.in);
			opcion=entrada.nextInt();
			Serializador.serializar(cine);
			switch (opcion) {
				case 1: Funciones.clienteNuevoOViejo(cine);
				//pedir datos del cliente y preguntar si es referido
				break;
				case 2:System.out.println("Administrar seleccionado\n");
				Administrar.Ejecucion(cine);
				break;
			
			}
		} while (opcion!= 3);
		

	}
}
