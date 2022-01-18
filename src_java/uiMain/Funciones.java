package uiMain;
import java.util.*;

public class Funciones {
	public static void datos() {
		int cedula;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		cedula=entrada.nextInt();
		if (true) {
			//mostrar atributos del cliente
			//llamar funcion buscar por
		}
		else {
			//pedir atributos del cliente y crear un objeto nuevo tipo cliente
			//llamar funcion cliente referido, luego llamar funcion buscar por
		}
	}
	public static void buscarpor(int a) {
		int opcion=0;
		System.out.print("Quiere buscar película por:\n"
				+ "1. Recomendadas\n"
				+ "2. Funciones\n"
				+ "3. Pelicula");
		Scanner x = new Scanner(System.in);
		opcion=x.nextInt();
		switch (opcion) {
		case 1:System.out.println("Recomendadas para el cliente:");
		//llamar metodo recomendar pelicula de no se donde
		break;
		case 2:System.out.println("Funciones del dia");
		//mostrar las funciones del dia
		break;
		case 3:System.out.println("Funciones de la pelicula");
		//mostrar las diferentes funciones de una pelicula
		break;	
	}
	}
}
