package uiMain;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Pelicula;

//Methods relacionados a la compra y vista de boleteria del Main y UI
public class Funciones {
	
	//Al ingresar a la parte de venta se espera reconocer si el cliente es viejo o nuevo
	//ingresando la cedula
	public static void clienteNuevoOViejo(Cine cine) {
		int cedula;
		int opcion;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		cedula=entrada.nextInt();
		if (cine.verificarCliente(cedula)) {
			System.out.println(cine.BuscadorCliente(cedula)); //Print algunos atributos del cliente viejo
			Funciones.buscarPorViejo(cine,cedula); //En caso de que el cliente sea viejo se llamaria a la funcion de buscarPorViejo
		
		}
		else { //En caso de que el cliente sea nuevo:
			Funciones.referido(cine); //Se llama a la funcion referido.
			Funciones.datos(cine,cedula); //Se llama a la funcion para ingresar datos.
			System.out.println("**El cliente ha sido registrado con exito**");
			System.out.println("");
			Funciones.buscarPorNuevo(cine,cedula); //Se llama a la funcion para la seccion de ver peliculas para un nuevo cliente.
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
		case 0: break; 
		default: if(cine.verificarCliente(opcion)){cine.BuscadorCliente(opcion).referidos();}
		else {Funciones.referido(cine);}
		break; }
	}
	
	//Method para entrar a la visualizacionn del metodo de busqueda para el viejo cliente.
	public static void buscarPorViejo(Cine cine,int cedula) {
		int opcion=0;
		System.out.print("Quiere buscar pelicula por:\n"
				+ "1. Recomendadas\n"
				+ "2. Funciones\n"
				+ "3. Pelicula\n");
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
	
	//Method  para entrar a la visualizacionn del metodo de busqueda para el nuevo cliente.
	public static void buscarPorNuevo(Cine cine,int cedula) {
		int opcion=0;
		System.out.print("Quiere buscar pelicula por:\n"
				+ "1. Funciones\n"
				+ "2. Pelicula\n");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1: Funciones.funcionesDia(cine, cedula); //mostrar las diferentes funciones por dia
		break;
		case 2:Funciones.funcionesPelicula(cine, cedula); //mostrar las diferentes funciones de una pelicula
		break;	}
		
	}
	
	//Method para ver funciones por pelicula de un dia en especifico
	public static void funcionesPelicula(Cine cine, int cedula) {
		int opcion=0;
		int dia, mes;
		String peliculaNombre;
		Scanner entrada= new Scanner(System.in);
		Scanner entrada1= new Scanner(System.in);
		System.out.println("Ingrese el dia, mes y la pelicula de las funciones que desea ver: ");
		System.out.print("Dia: ");
		dia=entrada.nextInt();
		System.out.print("Mes: ");
		mes=entrada.nextInt();
		System.out.print("Pelicula: ");
		peliculaNombre=entrada1.nextLine(); 
		Pelicula pelicula= cine.BuscadorPelicula(peliculaNombre);
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(pelicula, dia, mes)));
		
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("¿Que desea hacer?\n"
				+ "1. comprar\n"
				+ "2. volver\n");
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1: Funciones.comprar(cine, cedula); //seccion para comprar boletas
		break;
		case 2: if(cine.verificarCliente(cedula)) { //Volver a la seccion de seleccion respectiva de busqueda si se es cliente viejo.
			Funciones.buscarPorViejo(cine, cedula);
		}else {
			Funciones.buscarPorNuevo(cine, cedula); //Volver a la seccion de seleccion respectiva de busqueda si se es cliente nuevo.
		}
		break; }
		
		
	}
	
	//Method para ver todas las funciones de un dia y mes especifico
	public static void funcionesDia(Cine cine,int cedula) {
		int opcion=0;
		int dia, mes;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese el dia y mes de las funciones que desea ver");
		System.out.print("Dia: ");
		dia=entrada.nextInt();
		System.out.print("Mes: ");
		mes=entrada.nextInt();
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(dia, mes)));
		
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("¿Que desea hacer?\n"
				+ "1. comprar\n"
				+ "2. volver\n");
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1: Funciones.comprar(cine, cedula); //seccion para comprar boletas
		break;
		case 2: if(cine.verificarCliente(cedula)) { //Volver a la seccion de seleccion respectiva de busqueda si se es cliente viejo.
			Funciones.buscarPorViejo(cine, cedula);
		}else {
			Funciones.buscarPorNuevo(cine, cedula); //Volver a la seccion de seleccion respectiva de busqueda si se es cliente nuevo.
		}
		break; }
	}
	
	//Method para ver preguntas recomendadas a cliente viejo
	public static void recomendadas(Cine cine,int cedula) {
		int opcion=0;
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(cine.BuscadorCliente(cedula)))); //Busca al cliente por la cedula en el cine, luego llama al metodo de funcion
		//??? I think methods verFuncion and BuscadorCliente are not part of cine but of their own Classes: Funcion and Cliente respectively.
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("ï¿½Que desea hacer?"
				+ "1. comprar"
				+ "2. volver");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch(opcion) {
		case 1: Funciones.comprar(cine, cedula);
		break;
		case 2: Funciones.buscarPorViejo(cine, cedula); //Vuelve a la seccion de busqueda de cliente viejo
		break; }
		
		
	}
	
	//Method para comprar boleta luego de ver funciones
	public static void comprar(Cine cine, int cedula) {
		int numeroFuncion;
		int numeroBoleto;
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Ingrese el codigo de la funcion a la que desea asistir: ");
		numeroFuncion=entrada.nextInt();
		Funcion funcion=cine.BuscadorFuncion(numeroFuncion);
		System.out.println(funcion.verDisponibilidad());
		
		//???A nivel de sistema no es raro que primero no se de el precio
		//Creo que no porque el precio deberia darse en fisico, 
		//lo que se calcula siempre seria menor debido a que es un descuento
		
		System.out.print("Ingrese el codigo del boleto que desea comprar: ");
		numeroBoleto=entrada.nextInt();
		Boleto boleto= cine.BuscadorBoleto(numeroBoleto, funcion);
		funcion.VentaBoleto(boleto,cine.BuscadorCliente(cedula));
		
		System.out.print("El precio final de su boleto es:");
		System.out.println(boleto.getPrecioTotal());
		//??? Que mas podriamos hacer para este 
		//TODO: Falta hacer lo de si la silla está disponible
		
	}
	
	// funcion para formatear el texto para imprimir en pantallas las funciones
	// este es llamado desde las funciones print
	public static String formatearFunciones(ArrayList<Funcion> funciones){
		String resultado = "\n\n"; // string en el que va todo el texto
		for(Funcion funcion: funciones){
			//     formato para mostrar el " horario | Sala # | (2/3)D | #funcion "
			String formato = "%s|%s|%s|%s";
			String fecha = "Fecha: " + String.format("%02d/%02d",funcion.getDia(),funcion.getMes());
			resultado += funcion.getPelicula().getNombre() + "\n"; // anade nombre de la pelicula y salto de linea
			
			
			resultado += String.format(							   // anade la linea con la info
				formato, 
				centerString(6,funcion.getHorario()), 						// pone el horario	centrado	 
				centerString(8,"Sala "+funcion.getSala().getNumero()),		// pone la sala centrada
				centerString(4,funcion.getSala().getTipo()),				// pone el tipo de sala centrada
				centerString(5,String.format("%03d", funcion.getNumero())));// pone el numero de sala centrada
			resultado += "\n"+ fecha;
			resultado += "\n\n";
		}
		return resultado;
	}
	

	// funcion para centrar el texto a un tamano minimo
	public static String centerString (int width, String s) {
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
}
