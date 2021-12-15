package uiMain;

import java.util.*;

//import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Funcion;
import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;
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
		
			System.out.println("¿Que quiere hacer?\n"
					+ "1: Agregar una pelicula\n"
					+ "2: Quitar una pelicual\n"
					+ "3: Agregar una funcion manualmente\n"
					+ "4: Agregar una funcion automaticamente\\n"
					+ "5: Atras");
			
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
			case 5: break;
		}
					
	}

		
	
	
	public static void agregarPeli(Cine cine) {
		System.out.println("Ingrese el nombre de la pelicula:");		//se piden los datos para crear la pelicula
		String nombre= readStr();
		
		System.out.println("Ingrese el genero de la pelicula:");
		String genero=readStr();

		int duracion=0;
		while (true) {
			System.out.println("Ingrese la duracion de la pelicula (1 o 2 horas):");	//se repite hasta que agregue 1 o 2, sino lo hace paila
			duracion=readInt();
			if (duracion==1||duracion==2) {
				break;
			}
			else {
				System.out.println("Ingrese una duración valida");
			}
		}
		
		System.out.println("Ingrese el idioma de la pelicula:");
		String idioma=readStr();
		
		System.out.println("Ingrese la edad minima para ver la pelicula:");
		int edad=readInt();
		
		Pelicula pelicula= new Pelicula(nombre,genero,duracion,idioma,edad);	//se crea la pelicula y se añade a la lista de peliculas del cine
		
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
		
		System.out.println("Ingrese el horario en el formato que se le presento arriba");
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
		System.out.println("Digite el dia que quiere crear la funcion:");
		int dia=readInt();
		
		System.out.println("Digite el mes que quiere crear la funcion:");
		int mes=readInt();
		
		cine.programarFuncionesAuto(mes, dia);
	}
		
}
