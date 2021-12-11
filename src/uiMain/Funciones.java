package uiMain;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;

//Methods relacionados a la compra y vista de boleteria del Main y UI
public class Funciones {
	
	//Al ingresar a la parte de venta se espera reconocer si el cliente es viejo o nuevo
	//ingresando la cédula
	public static void clienteNuevoOViejo(Cine cine) {
		int cedula;
		int opcion;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		cedula=entrada.nextInt();
		if (cine.verificarCliente(cedula)) {
			Funciones.buscarPorViejo(cine,cedula); //En caso de que el cliente sea viejo se llamará a la función de buscarPorViejo
			//TODO:mostrar atributos del cliente
		}
		else { //En caso de que el cliente sea nuevo:
			Funciones.referido(cine); //Se llama a la función referido.
			Funciones.datos(cine,cedula); //Se llama a la función para ingresar datos.
			System.out.println("");
			Funciones.buscarPorNuevo(cine,cedula); //Se llama a la función para la sección de ver películas para un nuevo cliente.
		}
	}
	
	//Method para ingresar datos del nuevo cliente
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
		Cliente cliente = new Cliente(cedula,nombre, edad, ocupacion,cine); //Cliente se agrega a la lista de clientes desde el constructor
	}
	
	//Method para sumar referidos al cliente que referio al nuevo cliente
	public static void referido(Cine cine) {
		System.out.print("Digite la cedula del cliente quien lo refirio en caso de que no exista ingrese \"0\": ");
		int opcion;
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 0: break; //TODO: Falta implementar que al cliente se le sume 51 al field referidos
		default://cine.getCliente().setReferidos( cine.getCliente().getReferidos() + 1);
			;
		}
	}
	
	//Method para entrar a la visualización del metodo de busqueda para el viejo cliente.
	public static void buscarPorViejo(Cine cine,int cedula) {
		int opcion=0;
		System.out.print("Quiere buscar película por:\n"
				+ "1. Recomendadas\n"
				+ "2. Funciones\n"
				+ "3. Pelicula");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1:Funciones.recomendadas(cine, cedula);
		break;
		case 2:Funciones.funcionesDia(cine, cedula);
		break;
		case 3:Funciones.funcionesPelicula(cine, cedula);
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
		case 1:; 
		break;
		case 2:System.out.println("Funciones de la pelicula");
		//mostrar las diferentes funciones de una pelicula
		break;	}
		
	}
	
	//Method para ver funciones por película para un día en específico
	public static void funcionesPelicula(Cine cine, int cedula) {
		int opcion=0;
		int dia, mes;
		String pelicula;
		Scanner entrada= new Scanner(System.in);
		System.out.println("Ingrese el dia, mes y la pelicula de las funciones que desea ver: ");
		System.out.print("Dia: ");
		dia=entrada.nextInt();
		System.out.print("Mes: ");
		mes=entrada.nextInt();
		System.out.print("Pelicula: ");
		pelicula=entrada.next(); //TODO:Falta implementar obtener la pelicula por el nombre y agregar el objeto a verFuncion
		System.out.println(cine.verFuncion(null, dia, mes));
		
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("¿Que desea hacer?\n"
				+ "1. comprar"
				+ "2. volver");
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1: Funciones.comprar(cine, cedula); //seccion para comprar boletas
		break;
		case 2: if(cine.verificarCliente(cedula)) { //Volver a la seccion de seleccion respectiva de búsqueda si se es cliente viejo.
			Funciones.buscarPorViejo(cine, cedula);
		}else {
			Funciones.buscarPorNuevo(cine, cedula); //Volver a la seccion de seleccion respectiva de búsqueda si se es cliente nuevo.
		}
		break; }
		
		
	}
	
	//Method para ver todas las funciones de un día y mes especifico
	public static void funcionesDia(Cine cine,int cedula) {
		int opcion=0;
		int dia, mes;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese el dia y mes de las funciones que desea ver");
		System.out.print("Dia: ");
		dia=entrada.nextInt();
		System.out.print("Mes: ");
		mes=entrada.nextInt();
		System.out.println(cine.verFuncion(dia, mes));
		
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("¿Que desea hacer?\n"
				+ "1. comprar"
				+ "2. volver");
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1: Funciones.comprar(cine, cedula); //seccion para comprar boletas
		break;
		case 2: if(cine.verificarCliente(cedula)) { //Volver a la seccion de seleccion respectiva de búsqueda si se es cliente viejo.
			Funciones.buscarPorViejo(cine, cedula);
		}else {
			Funciones.buscarPorNuevo(cine, cedula); //Volver a la seccion de seleccion respectiva de búsqueda si se es cliente nuevo.
		}
		break; }
	}
	
	//Method para ver preguntas recomendadas a cliente viejo
	public static void recomendadas(Cine cine,int cedula) {
		int opcion=0;
		System.out.println(cine.verFuncion(cine.BuscadorCliente(cedula))); //Busca al cliente por la cedula en el cine, luego llama al método de funcion
		//??? I think methods verFuncion and BuscadorCliente are not part of cine but of their own Classes: Funcion and Cliente respectively.
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("¿Que desea hacer?"
				+ "1. comprar"
				+ "2. volver");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch(opcion) {
		case 1: Funciones.comprar(cine, cedula);
		break;
		case 2: Funciones.buscarPorViejo(cine, cedula); //Vuelve a la sección de búsqueda de cliente viejo
		break; }
		
		
	}
	
	//Method para comprar boleta luego de ver funciones
	public static void comprar(Cine cine, int cedula) {
		int numeroFuncion;
		int numeroBoleto;
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Ingrese el código de la función a la que desea asistir: ");
		numeroFuncion=entrada.nextInt();
		//TODO: Get Funcion with the field numero 
		//System.out.println((numeroFuncion).verDisponibildad);
		//TODO:
		//System.out.println(funcion.verDisponibilidad())
		
		//???A nivel de sistema no es raro que primero no se de el precio
		//Creo que no porque el precio debería darse en físico, 
		//lo que se calcula siempre será menor debido a que es un descuento
		System.out.print("Ingrese el código del boleto que desea comprar: ");
		numeroBoleto=entrada.nextInt();
		//TODO: Get boleto by its number
		//funcion.ventaBoleto(getBoleto(numBoleto),BuscadorCliente(cedula),cine)
		System.out.print("El preico final de su boleto es:");
		//TODO: Get boleto by its number
		//System.out.print(getBoleto(numBoleto).getPrecioTotal());
		//??? Que podríamos hacer para este 
		
		
	}
}
