package uiMain;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL CINE BAHIA");
		//agregar opciones
		//Cine cine = new Cine(/*nombre archivo*/)
		int opcion;
		
		do {
			System.out.println("Que quiere hacer?\n"
					+ "1. Vender\n"
					+ "2. Administrar\n");
			Scanner entrada = new Scanner(System.in);
			opcion=entrada.nextInt();
			switch (opcion) {
				case 1:System.out.println("Vender seleccionado");
				//pedir datos del cliente y preguntar si es referido
				break;
				case 2:System.out.println("Administrar seleccionado");
				//llamar metodo crear funciones
				break;
			}
		} while (opcion!= 3);

	}
}
