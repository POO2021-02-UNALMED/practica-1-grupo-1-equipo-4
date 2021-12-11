package uiMain;

import java.util.*;

import gestorAplicacion.boleteria.Boleto;
import gestorAplicacion.boleteria.Pelicula;
import gestorAplicacion.cinema.Cine;

//TODO: Falta agregar una funcion de landing donde se pregunte si se quire agregarPeli, quitar, AgregarFuncion, quitar, and so on. 
public class Administrar {
	
	static Scanner sc = new Scanner(System.in); 
	
	static String readStr() {
		return sc.next();
	}
	
	static int readInt() {
		return sc.nextInt();
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
	
	public static void generarFuncionManual() {
		//aca se va a mostrar los horarios disponibles de x dia (ya esta la funcion)
		//para que el empleado llame a crear funcion y melo
	}
		
}
