package uiMain;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;

public class Funciones {
	
	public static void clienteNuevoOViejo(Cine cine) {
		int cedula;
		int opcion;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		cedula=entrada.nextInt();
		if (cine.verificarCliente(cedula)) {
			Funciones.buscarpor(cine);
			//mostrar atributos del cliente
			//llamar funcion buscar por
		}
		else {
			Funciones.referido(cine);
			Funciones.datos(cine,cedula);
			System.out.println("");
			Funciones.buscarpor(cine);
			//pedir atributos del cliente y crear un objeto nuevo tipo cliente
			//llamar funcion cliente referido, luego llamar funcion buscar por
		}
	}
	
	public static void datos(Cine cine, int cedula) {
		String nombre;
		int edad;
		String ocupacion;
		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite el nombre del cliente: ");
		nombre=entrada.next();
		System.out.print("Digite la edad del cliente: ");
		edad=entrada.nextInt();
		System.out.print("Digite la ocupacion del cliente: ");
		ocupacion=entrada.next();
		//Cliente cliente = new Cliente(cedula,nombre, edad, ocupacion) 
	}
	
	public static void referido(Cine cine) {
		System.out.print("Digite la cedula del cliente quien lo refirio, en caso de que no exista ingrese \"0\": ");
		int opcion;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 0: break;
		default://cine.getCliente().setReferidos( cine.getCliente().getReferidos() + 1);
			;
		}
	}
	
	/* Esto lo dejo por si problemas
	public static void clienteNuevoOViejo(Cine cine) {
		int opcion=0;
		System.out.println("¿El cliente es nuevo o viejo?\n"
				+"1. Viejo\n"
				+"2. Nuevo\n");
		Scanner x = new Scanner(System.in);
		opcion=x.nextInt();
		switch(opcion) {
		case 1: Funciones.datos(cine);
		break;
		//case 2: Funciones.clienteNuevo();
		}
		
	}
	*/
	public static void buscarpor(Cine cine) {
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
