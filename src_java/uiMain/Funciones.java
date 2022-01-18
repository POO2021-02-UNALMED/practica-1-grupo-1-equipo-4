/*
Funcionalidad: contener los metodos necesarios para la compra, vista y venta de boleteria del Main y UI
 */

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

package uiMain;
import java.util.*;

import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Pelicula;
import java.util.stream.Collectors;

//Methods relacionados a la compra y vista de boleteria del Main y UI
public class Funciones {
	
	public static void clienteNuevoOViejo(Cine cine) {
	/*
	Recibe un parametro de tipo cine no devuelve nada, y se ejecuta al inicio de la venta y verifica si el cliente es nuevo o viejo 
	ingresando la cedula
	 */
		int cedula;
		int opcion;
		System.out.print("Digite la cedula del cliente: ");
		Scanner entrada = new Scanner(System.in);
		cedula=entrada.nextInt();
		if (cine.verificarCliente(cedula)) {
			System.out.println(cine.BuscadorCliente(cedula)); 	//Se muestra en pantalla el nombre, edad y ocupacion del cliente viejo
			Funciones.buscarPorViejo(cine,cedula); 				//En caso de que el cliente sea viejo se llamaria a la funcion de buscarPorViejo
		
		}
		else { 								//En caso de que el cliente sea nuevo:
			Funciones.referido(cine); 		//Se llama a la funcion referido para ver quien lo refirio.
			Funciones.datos(cine,cedula); 	//Se llama a la funcion para ingresar datos.
			System.out.println("**El cliente ha sido registrado con exito**");
			System.out.println("");
			Funciones.buscarPorNuevo(cine,cedula); //Se llama a la funcion para la seccion de ver peliculas para un nuevo cliente.
		}
	}
	
	
	public static void datos(Cine cine, int cedula) {
		/*
		Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada, y su proposito es ingresar los datos del nuevo cliente
 		 */
		String nombre;
		int edad;
		String ocupacion;
		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite el nombre del cliente: ");
		nombre=entrada.nextLine();
		System.out.print("Digite la edad del cliente: ");
		edad=entrada.nextInt();
		System.out.print("Digite la ocupacion del cliente: ");
		ocupacion=entrada.next();
		Cliente cliente = new Cliente(cedula,nombre, edad, ocupacion,cine); //Cliente se agrega a la lista de clientes desde el constructor
	}
	
	
	public static void referido(Cine cine) {
		/*
		Recibe un parametro de tipo cine, no devuelve nada y su proposito es sumar referidos al cliente que refirio al nuevo cliente
		 */
		System.out.print("Digite la cedula del cliente quien lo refirio en caso de que no exista ingrese \"0\": ");
		int opcion;
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 0: break; 
		default: if(cine.verificarCliente(opcion)){cine.BuscadorCliente(opcion).referidos();}		//si el cliente que lo refirio existe, se busca al cliente por su cedula y se llama al metodo referidos para que se lo agregue
		else {Funciones.referido(cine);}		//si el cliente referido no existe, se vuelve a ejecutar le metodo
		break; }
	}
	
	
	public static void buscarPorViejo(Cine cine,int cedula) {
		/*
		Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada y su proposito es entrar a la visualizacion del metodo de busqueda para el cliente viejo
		 */
		int opcion=0;
		System.out.print("Quiere buscar pelicula por:\n"
				+ "1. Recomendadas\n"
				+ "2. Funciones\n"
				+ "3. Pelicula\n");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch (opcion) {
		case 1:Funciones.recomendadas(cine, cedula);			//se ejecuta el metodo seleccionado segun si se quiere buscar por recomendadas, por funciones del dia o por el nombre de la pelicula
		break;
		case 2:Funciones.funcionesDia(cine, cedula);
		break;
		case 3:Funciones.funcionesPelicula(cine, cedula);
		break;	}
		
	}
	
	
	public static void buscarPorNuevo(Cine cine,int cedula) {
		/*
		Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada y su proposito es entrar a la visualizacion del metodo de busqueda para el cliente nuevo
		 */
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
	
	
	public static void funcionesPelicula(Cine cine, int cedula) {
		/*
		Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada y su proposito es mostrar en pantalla las funciones por pelicula de un dia en especifico
		 */
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
		
		if(cine.verFuncion(dia, mes).size()==0) {
			System.out.println("No hay funciones para esta fecha, escoja una fecha valida");		//si no hay funciones para ese dia, hace que se vuelva a ejecutar el metodo hasta que se ingrese una fecha con funcion
			funcionesPelicula(cine,cedula);
		}
		else {
				ArrayList<Funcion> funciones = cine.verFuncion(dia, mes);	//se hace una lista de las funciones dadas ese dia y mes
			List<Pelicula> peliculasMes = new ArrayList<Pelicula>();

			
			for(Funcion funcion: funciones){		 
				peliculasMes.add(funcion.getPelicula()); //obtengo una lista de las peliculas dadas ese dia y mes
			}
			peliculasMes = peliculasMes.stream().distinct().collect(Collectors.toList()); //borro las repeticiones
			System.out.println("Peliculas en el cine");
			int i=1;
			for(Pelicula p:peliculasMes) {
				System.out.println(i+": "+p.getNombre());		//se imprime las peliculas en el cine 
				i++;
			}
			System.out.println("Digite el numero de la pelicula seleccionada");
			int peli=entrada.nextInt();
			Pelicula pelicula = peliculasMes.get(peli-1);		
			System.out.println(Funciones.formatearFunciones(cine.verFuncion(pelicula, dia, mes)));		//se imprimen las Funciones de la pelicula consultada en la fecha dada
			
			//Pregunta para ver a que seccion se desea ir luego de ver funciones
			System.out.println("Que desea hacer?\n"
					+ "1. Comprar\n"
					+ "2. Volver\n");
			opcion=entrada.nextInt();
			switch (opcion) {
			case 1: Funciones.comprar(cine, cedula); //se llama a la seccion para comprar boletas
			break;
			case 2: if(cine.verificarCliente(cedula)) { //Volver a la seccion de seleccion respectiva de busqueda si se es cliente viejo.
				Funciones.buscarPorViejo(cine, cedula);
			}else {
				Funciones.buscarPorNuevo(cine, cedula); //Volver a la seccion de seleccion respectiva de busqueda si se es cliente nuevo.
			}
			break; }
		}


		
		
		
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
		if(cine.verFuncion(dia, mes).size()==0) {
			System.out.println("No hay funciones para esta fecha, escoja una fecha valida");		//si no hay funciones para ese dia, hace que se vuelva a ejecutar el metodo hasta que se ingrese una fecha con funcion
			funcionesDia(cine,cedula);
		}
		else {
			System.out.println(Funciones.formatearFunciones(cine.verFuncion(dia, mes)));
			System.out.println("Que desea hacer?\n"
			+ "1. comprar\n"
			+ "2. volver\n");
			opcion=entrada.nextInt();

			//Pregunta para ver a que seccion se desea ir luego de ver funciones
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
		
	}
	
	//Method para ver preguntas recomendadas a cliente viejo
	public static void recomendadas(Cine cine,int cedula) {
		int opcion=0;
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(cine.BuscadorCliente(cedula)))); //Busca al cliente por la cedula en el cine, luego llama al metodo de funcion
		//??? I think methods verFuncion and BuscadorCliente are not part of cine but of their own Classes: Funcion and Cliente respectively.
		//Pregunta para ver a que seccion se desea ir luego de ver funciones
		System.out.println("Que desea hacer?\n"
				+ "1. comprar\n"
				+ "2. volver\n");
		Scanner entrada = new Scanner(System.in);
		opcion=entrada.nextInt();
		switch(opcion) {
		case 1: Funciones.comprar(cine, cedula);
		break;
		case 2: Funciones.buscarPorViejo(cine, cedula); //Vuelve a la seccion de busqueda de cliente viejo
		break; }
		
		
	}
	
	
	public static void comprar(Cine cine, int cedula) {
		/*
		Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada y su proposito es ejecutar y realizar lo respectivo a la compra de boletos
		 */
		int numeroFuncion;
		int numeroBoleto;
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Ingrese el codigo de la funcion a la que desea asistir: ");
		numeroFuncion=entrada.nextInt();
		Funcion funcion=cine.BuscadorFuncion(numeroFuncion);		//se escoge la funcion segun el codigo ingresado
		System.out.println(funcion.verDisponibilidad());			//se imprime la silleteria respectiva a esa funcion
		
		System.out.print("Ingrese el codigo del boleto que desea comprar: ");
		numeroBoleto=entrada.nextInt();
		Boleto boleto= cine.BuscadorBoleto(numeroBoleto, funcion);		//se escoge el boleto segun el numero ingresado
		
		if(funcion.VentaBoleto(boleto,cine.BuscadorCliente(cedula))) {		//se ejecuta la venta del boleto con el boleto seleccionado y el cliente segun su cedula
			System.out.print("El precio sin descuento de su boleto es: ");
			System.out.println(boleto.calcularPrecio());
			
			System.out.print("El precio final de su boleto es: ");
			System.out.println(boleto.getPrecioTotal());
		}
		else {
			System.out.println("El boleto no esta disponible o no cumple con la edad minima para la pelicula");
		}
	}
	
	
	public static String formatearFunciones(ArrayList<Funcion> funciones){
		/*
		Recibe una lista de tipo funcion con las funciones en el cine y devuelve un String, su proposito es que este String es el que se imprime en pantalla
		las sillas de la sala de la funcion de forma ordenada indicando el tipo de silla, disponibilidad y numero de silla
		 */
		String resultado = "\n\n"; // string en el que va todo el texto
		for(Funcion funcion: funciones){
			//     formato para mostrar el " horario | Sala # | (2/3)D | #funcion "
			String formato = "%s|%s|%s|%s";
			String fecha = "Fecha: " + String.format("%02d/%02d",funcion.getDia(),funcion.getMes());
			resultado += funcion.getPelicula().getNombre()+" "+funcion.getPelicula().getClasificacion()+"+" + "\n"; // anade nombre de la pelicula y salto de linea
			
			
			resultado += String.format(							   // anade la linea con la info
				formato, 
				centerString(6,funcion.getHorario()), 						// pone el horario	centrado	 
				centerString(8,"Sala "+funcion.getSala().getNumero()),		// pone la sala centrada
				centerString(4,funcion.getSala().getTipo()),				// pone el tipo de sala centrada
				centerString(5,String.format("%03d", funcion.getNumero())));// pone el numero de sala centrada
			resultado += "\n"+ fecha;
			resultado += "\n\n";
		}
		return resultado;		//se deveulve el string organizado para imprimirlo en alguna otra funcion
	}
	

	public static String centerString (int width, String s) {
		/*
		esta funcion recibe un entero (ancho) y una String y devuelve un String centrado a un tamano minimo especificado
		 */
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
}
