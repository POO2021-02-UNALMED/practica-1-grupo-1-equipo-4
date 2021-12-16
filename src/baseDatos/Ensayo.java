package baseDatos;
import java.io.Serializable;
import gestorAplicacion.boleteria.*;
import gestorAplicacion.cinema.*;
import gestorAplicacion.salas.*;
import java.util.*;

public class Ensayo {
	public static void main(String[] args){

        // pelis 
		Cine cine = new Cine("Bahia");
		Pelicula rey_leon = new Pelicula("Rey Leon", "Animada", 2, "Espanol", 0, cine);
		Pelicula avengers = new Pelicula("Avengers", "Accion", 2, "Espanol", 12, cine);
		Pelicula naranja = new Pelicula("La Naranja Mecanica", "Clasica", 2, "Espanol", 0, cine);
		Pelicula resplandor = new Pelicula("El Resplandor", "Terror", 2, "Espanol", 15, cine);
		Pelicula toystory = new Pelicula("Toy Stroy", "Animada", 2, "Espanol", 8, cine);
		Pelicula harrypotter = new Pelicula("Harry Potter", "Fantasia", 2, "Espanol", 10, cine);
		Pelicula avatar = new Pelicula("Avatar", "Fantasia", 2, "Espanol", 15, cine);
		Pelicula pianista = new Pelicula("El Pianista", "Drama", 2, "Espanol", 18, cine);

		Cliente cliente1 = new Cliente(1001, "Pedro", 15, "Estudiante", cine);
		Cliente cliente2 = new Cliente(1002, "Daniel Santiago", 20, "Estudiante", cine);
		Cliente cliente3 = new Cliente(1003, "Daniel Daza", 20, "Estudiante", cine);
		Cliente cliente4 = new Cliente(1004, "Marlon Calle", 20, "Estudiante", cine);
		Cliente cliente5 = new Cliente(1005, "Jose Daniel Bustamante", 21, "Estudiante", cine);
		Cliente cliente6 = new Cliente(1006, "Jaime Guzman", 40, "Trabajador", cine);
		Cliente cliente7 = new Cliente(1007, "Luisa Guarin", 30, "Trabajadora", cine);
		Cliente cliente8 = new Cliente(1008, "Pedro Gomez", 15, "Estudiante", cine);
		Cliente cliente9 = new Cliente(1009, "Yeison", 20, "Trabajador", cine);
		Cliente cliente10 = new Cliente(1010, "Alejandra", 20, "Trabajadora", cine);
		Cliente cliente11 = new Cliente(1011, "Sofia", 25, "Estudiante", cine);
		Cliente cliente12 = new Cliente(1012, "Jose Daniel Bustamante", 21, "Trabajadora", cine);
		Cliente cliente13 = new Cliente(1013, "Luisa Guarin", 30, "Trabajadora", cine);

		Sala2D sala1 = new Sala2D(7, 8, 2,cine);
		Sala2D sala2 = new Sala2D(9, 8, 2,cine);
        Sala3D sala3 = new Sala3D(9, 9, 2, 36,cine);
		Sala3D sala4 = new Sala3D(8, 9, 2, 36,cine);

		//funciones para sala 1
		Funcion funcion_1 = Funcion.crearFuncion(17, 12, Horario.UNO, rey_leon, 1, cine);
		Funcion funcion_2 = Funcion.crearFuncion(17, 12, Horario.DOS, naranja, 1, cine);
		Funcion funcion_3 = Funcion.crearFuncion(17, 12, Horario.TRES, naranja, 1, cine);
		Funcion funcion_4 = Funcion.crearFuncion(17, 12, Horario.CINCO, resplandor, 3, cine);
		Funcion funcion_5 = Funcion.crearFuncion(17, 12, Horario.SEIS, avengers, 3, cine);
		Funcion funcion_6 = Funcion.crearFuncion(17, 12, Horario.UNO, toystory, 4, cine);
		Funcion funcion_7 = Funcion.crearFuncion(16, 12, Horario.UNO, toystory, 4, cine);
		Funcion funcion_8 = Funcion.crearFuncion(17, 12, Horario.CUATRO, toystory, 2, cine);
		Funcion funcion_9 = Funcion.crearFuncion(16, 12, Horario.TRES, toystory, 2, cine);

		funcion_1.VentaBoleto(funcion_1.getBoletos().get(1), cliente1);
		funcion_1.VentaBoleto(funcion_1.getBoletos().get(2), cliente1);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(3), cliente2);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(4), cliente2);
		funcion_1.VentaBoleto(funcion_1.getBoletos().get(5), cliente2);
		funcion_1.VentaBoleto(funcion_1.getBoletos().get(6), cliente3);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(7), cliente3);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(8), cliente3);
		funcion_1.VentaBoleto(funcion_1.getBoletos().get(9), cliente3);
		funcion_1.VentaBoleto(funcion_1.getBoletos().get(25), cliente3);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(15), cliente11);
		funcion_2.VentaBoleto(funcion_2.getBoletos().get(17), cliente5);

		funcion_6.VentaBoleto(funcion_6.getBoletos().get(9), cliente13);
		funcion_6.VentaBoleto(funcion_6.getBoletos().get(3), cliente13);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(25), cliente13);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(14), cliente13);
		funcion_6.VentaBoleto(funcion_6.getBoletos().get(5), cliente7);
		funcion_6.VentaBoleto(funcion_6.getBoletos().get(6), cliente11);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(7), cliente6);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(8), cliente8);
		funcion_6.VentaBoleto(funcion_6.getBoletos().get(9), cliente9);
		funcion_6.VentaBoleto(funcion_6.getBoletos().get(25), cliente10);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(15), cliente9);
		funcion_4.VentaBoleto(funcion_4.getBoletos().get(17), cliente4);

		funcion_3.VentaBoleto(funcion_3.getBoletos().get(45), cliente7);
		funcion_3.VentaBoleto(funcion_3.getBoletos().get(3), cliente7);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(25), cliente5);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(14), cliente5);
		funcion_3.VentaBoleto(funcion_3.getBoletos().get(5), cliente5);
		funcion_3.VentaBoleto(funcion_3.getBoletos().get(6), cliente6);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(7), cliente6);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(8), cliente6);
		funcion_3.VentaBoleto(funcion_3.getBoletos().get(9), cliente6);
		funcion_3.VentaBoleto(funcion_3.getBoletos().get(25), cliente12);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(15), cliente4);
		funcion_9.VentaBoleto(funcion_9.getBoletos().get(17), cliente4);
		//funciones para sala 2
		
		
		Serializador.serializar(cine);
		/*Cine cine = Deserializador.deserializar();
		
		System.out.println(cine.getPeliculas().get(0).verDisponibilidad());
		
		public String verHorarios(int dia, int mes){
			String fechaAVerificar=dia+mes;
			ArrayList<String> fechas = new ArrayList<String>();
			ArrayList<Horario> horarios = new ArrayList<Horario>();

			for (Funcion func:funciones) {
				String info=func.getDia()+func.getMes();	
				totalfunciones.add(info);				//se almacena en una lista la informacion
				info="";								//de las funciones creadas
			}

			for(int i =0; i<fechas.size();i++){ 	//lista de indices de funciones de la fecha dada
				if(fechas.get(i).equals(consulta)){
					horarios.add(funciones.get(i).getHorario().getHora());
				}
			}
		}*/
	}    
}
