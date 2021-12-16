//Funcionalidad: Es el lugar donde se encuentran las funciones correspondientes a la administracion del cine
//donde el usuario puede escoger que quiere hacer, y se le mostrara en pantalla el resultado de esto

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

package uiMain;

import java.util.*;

//import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import gestorAplicacion.salas.*;
import gestorAplicacion.boleteria.Horario;


public class Administrar {
/* Para la entrada de datos se define una variable de tipo scanner que segun los metodos definidos es o tipo Strin
o tipo int segun sea necesario*/
	static Scanner sc = new Scanner(System.in); 
	
	static String readStr() {
		Scanner sc= new Scanner(System.in);
		return sc.nextLine();
	}
	
	
	static int readInt() {
		return sc.nextInt();
	}
	
	
	public static void Ejecucion(Cine cine) {
/*	El proposito de este metodo es desarrollar la ejecucion de la clase, aca se define un switch que ejecuta el metodo	
correspondiente a la opcion seleccionada que debe corresponder con las mostradas en el print
*/		
		int opcion;
		
			System.out.println("Que quiere hacer?\n"
					+ "1: Agregar una pelicula\n"
					+ "2: Quitar una pelicula\n"
					+ "3: Agregar una funcion manualmente\n"
					+ "4: Generar funciones automaticamente para una fecha\n"
					+ "5: Rifar un boleto\n"
					+ "6: Agregar una sala\n"
					+ "7: Atras");
			
			opcion=readInt();
			
			switch (opcion) {
			case 1: agregarPeli(cine);
			break;
			case 2: quitarPeli(cine);
			break;
			case 3: generarFuncionManual(cine);
			break;
			case 4: generarAuto(cine);
			break;
			case 5: rifa(cine);
			break;
			case 6: agregarSala(cine);
			break;
			case 7: break;
		}
					
	}

		
	
	
	public static void agregarPeli(Cine cine) {
		/*
		 Recibe un parametro de tipo cine y no devuelve nada, su proposito es pedir los datos de la pelicula para
		 crearla e imprime un mensaje de creacion satisfactoria cuando termina el proceso
		 */
		System.out.println("Ingrese el nombre de la pelicula:");		//se piden los datos para crear la pelicula
		String nombre= readStr();
		
		ArrayList<String> generos = new ArrayList<>(Arrays.asList("Animada","Accion","Clasica","Terror","Fantasia","Drama"));	//Se define una lista de generos disponibles con fines de organizacion

		for(int i = 0; i<=6; i++){
			System.out.println(String.valueOf(i+1)+" "+generos.get(i));		//se imprimen en pantalla los generos disponibles
		}

		System.out.println("Ingrese numero del genero que desea");
		
		String genero=generos.get(readInt()-1);		//se establece el genero escogido segun el numero escogido por el usario

		int duracion=0;
		while (true) {
			System.out.println("Ingrese la duracion de la pelicula (1 o 2 horas):");	//se repite hasta que se digite un horario valido (1 o 2)
			duracion=readInt();
			if (duracion==1||duracion==2) {
				break;
			}
			else {
				System.out.println("Ingrese una duracion valida");
			}
		}
		
		System.out.println("Ingrese el idioma de la pelicula:");
		String idioma=readStr();
		
		System.out.println("Ingrese la edad minima para ver la pelicula:");
		int edad=readInt();
		
		Pelicula pelicula= new Pelicula(nombre,genero,duracion,idioma,edad);	//se crea la pelicula y se anade a la lista de peliculas del cine
		
		cine.agregarPelicula(pelicula);		//se agrega la pelicula a la lista de peliculas del cine
		
		System.out.println("La pelicula fue creada con exito");
	}
	
	
	public static void quitarPeli(Cine cine) {
		/*
		 Recibe un parametro de tipo cine y no devuelve nada, su proposito es mostrar las peliculas disponibles en el momento en el cine
		 que el usuario ingrese el nombre de la pelicula y si esta se encuentra en el cine, eliminarla
		 */
		System.out.println("Peliculas disponibles:");
		
		List<String> titulos= new ArrayList<String>();		
		for(Pelicula p:cine.getPeliculas()) {		//se muestran las peliculas disponibles y se almacenan sus nombres en una lista
			System.out.println(p.getNombre());
			titulos.add(p.getNombre());
		}
		
		System.out.println("Digite el nombre de la pelicula que quiere eliminar:");
		String eliminar=readStr();
		
		if(titulos.contains(eliminar)) {			//si el nombre ingresado esta en la lista de titulos entonces
			int pos = titulos.indexOf(eliminar);	//se coge el indice de lista correspondiente a ese titulo
			cine.getPeliculas().remove(pos);		//se elimina la pelicula correspondiente al indice
			System.out.println("La pelicula fue eliminada con exito");
		}
		else {
			System.out.println("Digite un nombre valido");	//si no esta se vuelve a ejecturar la funcion
			quitarPeli(cine);
		}
	}
	
	
	public static void generarFuncionManual(Cine cine) {
		/*
		Recibe un parametro de tipo cine, pide la fecha en la que se va a crear la funcion, se muestran las salas que tienen algun horario
		disponible para ese dia, de la sala que escoja el usuario se muestran los horarios disponibles, luego se imprimen las peliculas 
		disponibles y ya se pide el nombre de la pelicula para crear la funcion de la pelicula y sala indicadas en la fecha establecida
		 */
		System.out.println("Digite el dia que quiere crear la funcion:");
		int dia=readInt();
		
		System.out.println("Digite el mes que quiere crear la funcion:");
		int mes=readInt();
		
		ArrayList<Sala> disponibles=cine.salasDisponibles(mes,dia);		//se agrega a una lista las salas disponibles del dia y mes requeridos
		
		System.out.println("Salas disponibles para el dia/mes: "+dia+"/"+mes);
		for(Sala d:disponibles) {
			System.out.println("Sala "+d.getNumero());				//se imprimen en pantalla las salas disponibles
		}
		
		System.out.println("Seleccione el numero de la sala que quiere: ");
		int sala=readInt();
		
		Sala seleccionada = cine.buscarSala(sala);		//se busca la sala por su numero
		
		System.out.println("Horarios disponibles de la sala: ");
		
		System.out.println(seleccionada.verHorarios(dia, mes));		//se muestran los horarios disponibles de la sala seleccionada
		
		System.out.print("Ingrese el horario en el formato que se le presento arriba: ");
		String hora=readStr();
		
		Horario h=Horario.getHorario(hora);
		
		System.out.println("Peliculas en el cine");
		
		int i=1;
		
		for(Pelicula p:cine.getPeliculas()) {
			System.out.println(i+": "+p.getNombre());	//se muestra un numero y el nombre de las peliculas que hay en el cine
			i++;
		}
		
		System.out.println("Digite el numero de la pelicula seleccionada");
		int peli=readInt();
		
		Pelicula pelicula=cine.getPeliculas().get(peli-1);
			
		Funcion.crearFuncion(dia, mes, h, pelicula, seleccionada.getNumero(), cine); //se crea la pelicula en la fecha y horario seleccioado
		System.out.println("La funcion fue eliminada con exito");
	}
	

	public static void generarAuto(Cine cine) {
		/*
		 Recibe un parametro de tipo cine y no devuelve nada, su funcion es recibir una fecha para la cual se quieren crear unas funciones
		 pero al contrario de hacerlo manualmente, se hace de forma automatica llamando a la funcion programarFuncionesAuto() de cine. Para esto
		 antes se muestran las salas con horarios disponibles para que el usario escoja donde quiere crear sus funciones
		 */
		System.out.print("Digite el dia que quiere crear la funcion: ");
		int dia=readInt();
		
		System.out.print("Digite el mes que quiere crear la funcion: ");
		int mes=readInt();
		

		ArrayList<Sala> disponibles= new ArrayList<Sala>();	//Se hace una lista para almacenar las salas disponibles que haya en una fecha
		
		for(Sala sala: cine.getSalas()) { //
			if(sala.verificarDisponibilidad(dia, mes)) {	//Se busca una sala sala disponible para el dia y mes requeridos
				disponibles.add(sala);
			}
		}
		
		if( disponibles.size() == 0){
			System.out.println("No hay salas totalmente disponibles para este dia, seleccione nuevamente dia y mes");
		}else{
			System.out.println("Salas disponibles completamente disponibles para el dia/mes: "+dia+"/"+mes);
			for(Sala d:disponibles) {
				System.out.println("Sala "+d.getNumero());				//se imprimen los numeros de las salas disponibles
			}
		}
		System.out.print("Ingrese el numero la sala para la que desea programar las funciones automaticamente: ");
		Sala sala = cine.buscarSala(readInt());
		
		System.out.print("Estas fueron las funciones creadas automaticamente: ");
		
		System.out.println(Funciones.formatearFunciones(cine.programarFuncionesAuto(mes, dia, sala)));   //se llama al metodo que organiza el print de las funciones para mostrar las funciones que se crearon para la fecha seleccionada
		
		

	}
	
	public static void rifa(Cine cine) {
		/*
		 Recibe un parametro de tipo cine y no devuelve nada, su proposito es mostrar los clientes fieles candidatos a ganarse la rifa del boleto
		 y mostrar al cliente ganador de boleto. Para esto se pide una fecha para la cual se quiera rifar, se muestran las funciones de ese dia y se selecciona alguna de las disponibles
		 */
		System.out.print("Digite el dia para el que quiere el rifar el boleto de la funcion: ");
		int dia=readInt();
		
		System.out.print("Digite el mes para el que quiere rifar el boleto de la funcion: ");
		int mes=readInt();
		
		System.out.println("Funciones de ese dia");
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(dia, mes)));		//se llama al metodo que organiza el print de las funciones para mostrar las funciones en la fecha seleccionada
		
		System.out.print("Digite el codigo de la funcion para la que quiere hacer el sorteo: ");
		int rand=readInt();
		
		System.out.println("Clientes mas fieles candidatos a ganar el boleto:");
		for(Cliente c:cine.clientesValiosos()) {
			System.out.println(c.getNombre());			//se imprimen los nombres de los clientes fieles candidatos a la rifa
		}
		
		System.out.println();
		
		System.out.print("CLIENTE GANADOR: "+cine.rifarBoleto(rand)+"\n");
	}
	
	public static void agregarSala(Cine cine) {
		/*
		Recibe un parametro de tipo cine y no devuelve nada, su proposito es imprimir en pantalla las diferentes opciones de crear sala que hay
		y segun la opcion seleccionada ejecutar el metodo agregarSala3D o agregarSala2D
		 */
		System.out.println("Que tipo de sala quiere agregar?: \n"
				+ "1. Sala 3D\n"
				+ "2. Sala 2D\n");
		int opcion=Administrar.readInt();
		switch(opcion) {
		case 1: agregarSala3D(cine);
		break;
		case 2: agregarSala2D(cine);
		break; }
		
	}
	
	
	public static void agregarSala2D(Cine cine) {
		/*
		Recibe un parametro de tipo cine y no devuelve nada, su proposito es pedir los datos necesarios al usuario para crear una Sala2D
		y se da una opcion default para crear una sala de 8x5 que es cuando se da 0 y 0 para filas y columnas
		 */
		System.out.print("Ingresar cantidad de filas vip de la sala: ");
		int filasVip=Administrar.readInt();
		System.out.print("En caso de que la sala sea de tamano normal (8x5) ingrese \"0\" para filas y columnas");
		System.out.print("Ingresar cantidad de filas: ");
		int filas=Administrar.readInt();
		System.out.print("Ingresar cantidad de columnas: ");
		int columnas=Administrar.readInt();
		if(filas==0 && columnas==0) {
			new Sala2D(filasVip, cine);			//se crea la sala solo pasando el parametro de filasVip (y cine) en el caso de ser una sala default
		}else {
			new Sala2D( filas,  columnas, filasVip, cine);	//se crea la sala pasando los parametros de filas, columnas y filasVip (y cine) si no es una sala default
		}
		System.out.println("La nueva sala ha sido creada con Exito!");		
	}
	
	public static void agregarSala3D(Cine cine) {
		/*
		Recibe un parametro de tipo cine y no devuelve nada, su proposito es pedir los datos necesarios al usuario para crear una Sala3D
		y se da una opcion default para crear una sala de con igual cantidad de gafas que de sillas que es cuando se ingresa 0 para la cantidad de gafas disponibles
		 */
		System.out.print("Ingresar cantidad de filas vip de la sala: ");
		int filasVip=Administrar.readInt();
		System.out.print("Ingresar cantidad de filas: ");
		int filas=Administrar.readInt();
		System.out.print("Ingresar cantidad de columnas: ");
		int columnas=Administrar.readInt();
		System.out.print("Ingresar cantidad de gafas disponibles de la sala, en caso de que se tengan suficientes ingrese \"0\": ");
		int gafas=Administrar.readInt();
		if(gafas==0) {
			new Sala3D( filas,  columnas,  filasVip, cine);		//se crea la sala solo pasando los parametros de filas, columnas y filasVip (y cine)
		}else {
			new Sala3D( filas,  columnas, filasVip, gafas, cine);	//se crea pasando todos los parametros de filas, columnas y filasVip (y cine)
		}
		System.out.println("La nueva sala ha sido creada con Exito!");		
	}
		
}
