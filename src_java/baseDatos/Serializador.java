//Funcionalidad de la clase:

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa
package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestorAplicacion.cinema.Cine;

public class Serializador {

	private static File rutaTemp = new File("src\\baseDatos\\tmp");		//aca se obtiene la ruta de la carpeta de tmp
	
	
	public static void serializar(Cine cine) {
		/*Recibe el parametro cine del tipo Cine que es el objeto que guarda todo
		y se guarda melo*/

		FileOutputStream fos;
		ObjectOutputStream oos;
		File[] docs = rutaTemp.listFiles();		// se obtienen la ruta de los documetos en tmp (solo es Cine.txt)
		PrintWriter pw;
		
		for (File file: docs) {
			try {
				pw = new PrintWriter(file);		// si el archivo tiene algo escrito lo pone en blanco para evitar errores
			} catch (FileNotFoundException e) {
				e.printStackTrace();			// por si no se encuentra el archivo
			}
		}
		
		for (File file: docs) {
			if (file.getAbsolutePath().contains("Cine")) {	//si uno de los objetos en docs tiene la palabra Cine
				try {		
					fos = new FileOutputStream(file);		//
					oos = new ObjectOutputStream(fos);		//
					oos.writeObject(cine);					//Se escribe el objeto codificado en el archivo Cine.txt
				} catch (FileNotFoundException e) {
					e.printStackTrace();					//para errores
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
