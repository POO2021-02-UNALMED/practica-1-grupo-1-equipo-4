package uiMain;
import java.util.*;


import gestorAplicacion.cinema.Cine;

public class Main {
	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL CINE BAHIA");
		//agregar opciones
		int opcion;
		Cine cine = new Cine("hola");
		
		do {
			System.out.println("Que quiere hacer?\n"
					+ "1. Vender\n"
					+ "2. Administrar\n");
			Scanner entrada = new Scanner(System.in);
			opcion=entrada.nextInt();
			switch (opcion) {
				case 1: Funciones.clienteNuevoOViejo(cine);
				//pedir datos del cliente y preguntar si es referido
				break;
				case 2:System.out.println("Administrar seleccionado");
				//llamar metodo crear funciones
				break;
			}
		} while (opcion!= 3);

	}
}
