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
			Funciones.buscarPorViejo(cine,cedula);
			//mostrar atributos del cliente
			//llamar funcion buscar por
		}
		else {
			Funciones.referido(cine);
			Funciones.datos(cine,cedula);
			System.out.println("");
			Funciones.buscarPorNuevo(cine,cedula);
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
		Cliente cliente = new Cliente(cedula,nombre, edad, ocupacion,cine); //Falta cine
	}
	
	public static void referido(Cine cine) {
		System.out.print("Digite la cedula del cliente quien lo refirio en caso de que no exista ingrese \"0\": ");
		int opcion;
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
	public static void buscarPorViejo(Cine cine,int cedula) {
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
		break;	}
		
	}
	
	public static void buscarPorNuevo(Cine cine,int cedula) {
		int opcion=0;
		System.out.print("Quiere buscar película por:\n"
				+ "1. Funciones\n"
				+ "2. Pelicula");
		Scanner x = new Scanner(System.in);
		opcion=x.nextInt();
		switch (opcion) {
		case 1:System.out.println("Funciones del dia");
		//mostrar las funciones del dia
		break;
		case 2:System.out.println("Funciones de la pelicula");
		//mostrar las diferentes funciones de una pelicula
		break;	}
		
	}
	
	public static void funcionesDia(Cine cine,int cedula) {
		int opcion=0;
		int dia, mes;
		Scanner x = new Scanner(System.in);
		System.out.println("Ingrese el dia y mes de las funciones que desea ver");
		System.out.print("Dia: ");
		dia=x.nextInt();
		System.out.print("Mes: ");
		mes=x.nextInt();
		System.out.println(cine.verFuncion(dia, mes));
		System.out.println("¿Que desea hacer?\n"
				+ "1. comprar"
				+ "2. volver");
		opcion=x.nextInt();
		switch (opcion) {
		case 1: //aquí compra;
			;
		case 2: //aquí se devuelve
		}
	}
	
	public static void recomendadas(Cine cine,int cedula) {
		//System.out.println(cine.verFuncion(cine.getCliente(cedula)));
		System.out.println("a");
	}
}
