package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.List;
import java.io.ObjectInputStream;

import gestorAplicacion.cinema.Cine;


public class Deserializador {
    private static File rutaTemp = new File("src\baseDatos\tmp");

    public static Cine deserializar() {

        File[] docs = rutaTemp.listFiles();
        FileInputStream fis;
        ObjectInputStream ois;

        for(File file : docs) {
        	
            if (file.getAbsolutePath().contains("Cine")) {
            	
                try {
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);

                    return (Cine) ois.readObject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return new Cine("no dio");
    }
}
