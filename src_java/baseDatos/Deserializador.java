//Funcionalidad de la clase:	Contiene el metodo que deserializa el objeto

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa
package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import gestorAplicacion.cinema.Cine;


public class Deserializador {
	private static File rutaTemp = new File("src\\baseDatos\\tmp");	//aca se obtiene la ruta de la carpeta de tmp
	
	public static Cine deserializar() {
	/* no recibe parametros y retorna una variable del tipo Cine*/

		File[] docs = rutaTemp.listFiles();					// se obtienen la ruta de los documetos en tmp (solo es Cine.txt)
		FileInputStream fis;
		ObjectInputStream ois;
		
		for(File file : docs) {
			if (file.getAbsolutePath().contains("Cine")) {  // si el archivo tiene algo escrito lo pone en blanco para evitar errores
				try {
					fis = new FileInputStream(file);		
					ois = new ObjectInputStream(fis);
					
					return (Cine) ois.readObject();			//se devuelve el objeto decodificado casteado a la Clase Cine y se retorna
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				
				} catch (IOException e) {
					e.printStackTrace();
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
