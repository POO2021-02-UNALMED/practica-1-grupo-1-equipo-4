//Funcionalidad: 

package uiMain;

import java.util.*;

//import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;
import gestorAplicacion.cinema.Cliente;
import gestorAplicacion.salas.*;
import gestorAplicacion.boleteria.Horario;

//TODO: Falta agregar una funcion de landing donde se pregunte si se quire agregarPeli, quitar, AgregarFuncion, quitar, and so on. 
public class Administrar {
	
	static Scanner sc = new Scanner(System.in); 
	
	static String readStr() {
		Scanner sc= new Scanner(System.in);
		return sc.nextLine();
	}
	
	static int readInt() {
		return sc.nextInt();
	}
	
	public static void Ejecucion(Cine cine) {
		
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
		System.out.println("Ingrese el nombre de la pelicula:");		//se piden los datos para crear la pelicula
		String nombre= readStr();
		
		ArrayList<String> generos = new ArrayList<>(Arrays.asList("Animada","Accion","Clasica","Terror","Fantasia","Drama"));

		for(int i = 0; i<6; i++){
			System.out.println(String.valueOf(i+1)+" "+generos.get(i));
		}

		System.out.println("Ingrese numero del genero que desea");
		
		String genero=generos.get(readInt()-1);

		int duracion=0;
		while (true) {
			System.out.println("Ingrese la duracion de la pelicula (1 o 2 horas):");	//se repite hasta que agregue 1 o 2, sino lo hace paila
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
		
		Pelicula pelicula= new Pelicula(nombre,genero,duracion,idioma,edad);	//se crea la pelicula y se a�ade a la lista de peliculas del cine
		
		cine.agregarPelicula(pelicula);
	}
	
	public static void quitarPeli(Cine cine) {
		System.out.println("Peliculas disponibles:");
		
		List<String> titulos= new ArrayList<String>();		
		for(Pelicula p:cine.getPeliculas()) {		//se muestran peliculas disponibles y se almacenan sus nombres en una lista
			System.out.println(p.getNombre());
			titulos.add(p.getNombre());
		}
		
		System.out.println("Digite el nombre de la pelicula que quiere eliminar:");
		String eliminar=readStr();
		
		if(titulos.contains(eliminar)) {
			int pos = titulos.indexOf(eliminar);	
			cine.getPeliculas().remove(pos);
		}
		else {
			System.out.println("Digite un nombre valido");	//si no esta se vuelve a ejecturar la funcion
			quitarPeli(cine);
		}
	}
	
	public static void generarFuncionManual(Cine cine) {
		//aca se va a mostrar los horarios disponibles de x dia (ya esta la funcion)
		//para que el empleado llame a crear funcion y melo
		System.out.println("Digite el dia que quiere crear la funcion:");
		int dia=readInt();
		
		System.out.println("Digite el mes que quiere crear la funcion:");
		int mes=readInt();
		
		ArrayList<Sala> disponibles=cine.salasDisponibles(mes,dia);		//se agrega a una lista las salas disponibles del dia y mes
		
		System.out.println("Salas disponibles para el dia/mes: "+dia+"/"+mes);
		for(Sala d:disponibles) {
			System.out.println("Sala "+d.getNumero());				//se imprimen los numeros de las salas disponibles
		}
		
		System.out.println("Seleccione el numero de la sala que quiere: ");
		int sala=readInt();
		
		Sala seleccionada = cine.buscarSala(sala);		//se busca la sala por su numero
		
		System.out.println("Horarios disponibles de la sala: ");
		
		System.out.println(seleccionada.verHorarios(dia, mes));		//se muestran los horarios disponibles
		
		System.out.print("Ingrese el horario en el formato que se le presento arriba: ");
		String hora=readStr();
		
		Horario h=Horario.getHorario(hora);
		
		System.out.println("Peliculas en el cine");
		
		int i=1;
		
		for(Pelicula p:cine.getPeliculas()) {
			System.out.println(i+": "+p.getNombre());
			i++;
		}
		
		System.out.println("Digite el numero de la pelicula seleccionada");
		int peli=readInt();
		
		Pelicula pelicula=cine.getPeliculas().get(peli-1);
			
		Funcion.crearFuncion(dia, mes, h, pelicula, seleccionada.getNumero(), cine);
	}
	



	public static void generarAuto(Cine cine) {
		System.out.print("Digite el dia que quiere crear la funcion: ");
		int dia=readInt();
		
		System.out.print("Digite el mes que quiere crear la funcion: ");
		int mes=readInt();
		

		ArrayList<Sala> disponibles= new ArrayList<Sala>();
		//Se busca una sala sala disponible para ese dia y mes
		for(Sala sala: cine.getSalas()) { //
			if(sala.verificarDisponibilidad(dia, mes)) {
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
		
		System.out.println(Funciones.formatearFunciones(cine.programarFuncionesAuto(mes, dia, sala)));
		
		

	}
	
	public static void rifa(Cine cine) {
		System.out.print("Digite el dia para el que quiere el rifar el boleto de la funcion: ");
		int dia=readInt();
		
		System.out.print("Digite el mes para el que quiere rifar el boleto de la funcion: ");
		int mes=readInt();
		
		System.out.println("Funciones de ese dia");
		System.out.println(Funciones.formatearFunciones(cine.verFuncion(dia, mes)));
		
		System.out.print("Digite el codigo de la funcion para la que quiere hacer el sorteo: ");
		int rand=readInt();
		
		System.out.println("Clientes mas fieles candidatos a ganar el boleto:");
		for(Cliente c:cine.clientesValiosos()) {
			System.out.println(c.getNombre());
		}
		
		System.out.println();
		
		System.out.print("CLIENTE GANADOR: "+cine.rifarBoleto(rand)+"\n");
	}
	
	public static void agregarSala(Cine cine) {
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
		System.out.print("Ingresar cantidad de filas vip de la sala: ");
		int filasVip=Administrar.readInt();
		System.out.print("En caso de que la sala sea de tamano normal ingrese \"0\" para filas y columnas");
		System.out.print("Ingresar cantidad de filas: ");
		int filas=Administrar.readInt();
		System.out.print("Ingresar cantidad de columnas: ");
		int columnas=Administrar.readInt();
		if(filas==0 && columnas==0) {
			new Sala2D(filasVip, cine);
		}else {
			new Sala2D( filas,  columnas, filasVip, cine);
		}
		System.out.println("La nueva sala ha sido creada con Exito!");		
	}
	
	public static void agregarSala3D(Cine cine) {
		System.out.print("Ingresar cantidad de filas vip de la sala: ");
		int filasVip=Administrar.readInt();
		System.out.print("Ingresar cantidad de filas: ");
		int filas=Administrar.readInt();
		System.out.print("Ingresar cantidad de columnas: ");
		int columnas=Administrar.readInt();
		System.out.print("Ingresar cantidad de gafas disponibles de la sala, en caso de que se tengan suficientes ingrese \"0\": ");
		int gafas=Administrar.readInt();
		if(gafas==0) {
			new Sala3D( filas,  columnas,  filasVip, cine);
		}else {
			new Sala3D( filas,  columnas, filasVip, gafas, cine);
		}
		System.out.println("La nueva sala ha sido creada con Exito!");		
	}
		
}
