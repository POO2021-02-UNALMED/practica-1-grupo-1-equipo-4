/*
Funcionalidad: Ser la clase de ejecucion del programa llamando a los metodos de Funciones y Administrar
que contienen la ejecucion de las diferentes funcionalidades
*/

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

package uiMain;
import java.util.*;

import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import baseDatos.Deserializador;
import baseDatos.Serializador;

public class Main {
	/*
	La funcion de esta clase es contener el main para la ejecucion del programa
	 */
	public static void main(String[] args) {
		
		System.out.println("BIENVENIDO AL CINE BAHIA");		
		int opcion;
		Cine cine = Deserializador.deserializar();	//lo que esta guardado en el texto lo guarda en la variable de tipo Cine
		do {
			System.out.println("Que quiere hacer?\n"	//se le presentan las opciones de lo que el usuario puede hacer
					+ "1. Vender\n"
					+ "2. Administrar\n"
					+ "3. Cerrar el programa");
			Scanner entrada = new Scanner(System.in);
			opcion=entrada.nextInt();
			Serializador.serializar(cine);		//guarda el cine cada vez que se retorna a esta parte
			switch (opcion) {
				case 1: Funciones.clienteNuevoOViejo(cine);		//se llama la funcion de Funciones para ver si el cliente es nuevo o viejo
				break;
				case 2:System.out.println("Administrar seleccionado\n");
				Administrar.Ejecucion(cine);		//se llama la funcion de Administrar que contiene su ejecucion
				break;
			
			}
		} while (opcion!= 3);		//se repite este proceso hasta que el usuario de la opcion para terminar
		

	}
}
