import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase ColeccionDatos: 
 * La implementación de esta clase permite leer desde el disco un archivo, luego guarda los datos de dicho archivo en un ArrayList.
 * Posibilita grabar en el disco la colección de datos e imprimir el contenido de la colección.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class ColeccionDatos extends ArrayList<Dato>
{
    private int cont = 0;
    private static ArrayList<Dato> arr_datos;
    /**
     * Constructor de la Clase ColeccionDatos:
     * La finaldad de este constructor es inicializar el ArrayList 
     * que contiene los datos de los activos (arr_datos).
     */
    public ColeccionDatos(){
        this.arr_datos = new ArrayList<>();
    }    

    /**
     * La utilidad de este método es leer desde el disco un archivo que contiene los datos de los activos. 
     * El nombre del archivo el cual se va a leer se le pregunta al usuario.
     * Los datos de los activos quedan guardados en un ArrayList (arr_datos).
     * @param archivo Se refiere al nombre del archivo a leer.
     * @throws FileNotFoundException Se arroja una excepción si el archivo que el usuario desea leer no existe.
     */    
    public void leerArchivos(String archivo) throws FileNotFoundException{
        String archivo1;
        if(archivo.endsWith(".txt")){
            archivo1 = archivo;
        } else{
            archivo1 = archivo + ".txt";
        }
        Scanner read = new Scanner(new File(archivo1));
        while(read.hasNextLine()){
            String line = read.nextLine();
            line = line.replace(";" , " "); 
            Scanner lineScan = new Scanner (line);
            String date = lineScan.next();
            String hour = lineScan.next();
            String aux = lineScan.next();
            double secM = Double.parseDouble(aux);
            aux = lineScan.next();
            double bid = Double.parseDouble(aux);
            aux = lineScan.next();
            double ask = Double.parseDouble(aux);
            aux = lineScan.next();
            double last = Double.parseDouble(aux);
            aux = lineScan.next();
            int transaction = Integer.parseInt(aux);
            Dato dato = new Dato(date, hour, secM, bid, ask, last, transaction);
            this.arr_datos.add(dato);
            cont++;   
        }   
        JOptionPane.showMessageDialog(null, "Fueron leidos " + cont + " datos de forma satisfactoria.");
    }

    /**
     * Este método permite fijar el contenido de la colección de datos en el disco.
     * @throws FileNotFoundException Arroja una excepción si no se encuentra el archivo.
     * @param fileName Nombre del archivo a grabar
     */  
    public void grabarArchivo(String fileName) throws FileNotFoundException{
        PrintStream name = new PrintStream(new File(fileName));
        for(Dato d: arr_datos){
            name.println (d.getDate() + " " + d.getHour() + " " + d.getSecM() 
                + d.getAsk() + " " + d.getLast() + " " + d.getTransaction());
        }
    }

    /**
     * El fin de este método es imprimir los datos que hay en la colección.
     */
    public static void imprimir() {
        for(Dato d: arr_datos) {
            arr_datos.forEach(value -> System.out.println(value));
        }
    }

    /**
     * Este método permite acceder al ArrayList que contiene los datos de los activos (arr_datos).
     * @return Devuelve un ArrayList con los datos de los activos.
     */
    public static ArrayList <Dato> getDatos(){
        return arr_datos;
    }
}