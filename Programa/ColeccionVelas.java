import java.io.*;
import java.util.*;
/**
 * Clase ColeccionVelas: 
 * La implementación de esta clase permite leer desde el disco un archivo el cuál contiene los datos de las velas
 * (el nombre del archivo de le pregunta al usuario). Luego guarda las velas de dicho archivo en un ArrayList.
 * Posibilita grabar en el disco la colección de velas e imprimir el contenido de la colección.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class ColeccionVelas
{
    /**
     *Atributo dinamico de objeto Vela
     */
    private static ArrayList <Vela> arr_velas;

    /**
    * Este constructor se encarga de inicializar el ArrayList en el cual se van a coleccionar las velas (arr_vela).
    */
    public ColeccionVelas(){
        arr_velas = new ArrayList <> ();
    }

    /**
     * Este método agrega las velas a la colección (arr_velas)
     */
    public static void addVelas(Vela vela){
        arr_velas.add(vela);
    }

    /**
     * Este método lee un archivo desde el disco y crea velas con los datos de dicho archivo
     * @param nameFile Nombre del archivo a leer (el usuario ingresa el nombre del archivo)
     * @throws FileNotFoundException Arroja una excepción si no se encuentra el archivo.
     */
    public void readVela(String nameFile) throws FileNotFoundException{
        String namefile;
        if(nameFile.endsWith(".txt")){
            namefile=nameFile;
        } else{
            namefile= nameFile + ".txt";
        }
        Scanner read = new Scanner( new File(namefile));
        int cont = 0;
        while(read.hasNextLine()){
            String line = read.nextLine();
            Scanner scan = new Scanner(line);
            String color = scan.next();
            String date = scan.next();
            String hour = scan.next();
            String a = scan.next();
            double valueI = Double.parseDouble(a);
            a = scan.next();
            double min = Double.parseDouble(a);
            a = scan.next();
            double max = Double.parseDouble(a);
            a = scan.next();
            double valueF = Double.parseDouble(a);
            a = scan.next();
            int transaction = Integer.parseInt(a);
            Vela v = new Vela(color, date, hour, valueI, min, max, valueF, transaction);
            arr_velas.add(v);
            cont++;
        }
        System.out.println("Con exito se han creado " + cont + " velas");
    }

    /**
     * Este método graba un archivo en el disco, el cual contiene la colección de velas
     * @param nameFile Nombre del archivo a grabar
     */
    public void savedVelas(String nameFile) throws FileNotFoundException{
        String namefile;
        if(nameFile.endsWith(".txt")){
            namefile=nameFile;
        } else{
            namefile= nameFile + ".txt";
        }
        File file = new File(namefile);
        PrintWriter output = new PrintWriter(file);
        for(Vela v: arr_velas){
            output.println(v.toString());
        }
    }

    /**
     * Este método imprime las velas almacenadas en el disco
     */
    public void MostrarVelas () {
        for(Vela F: arr_velas){
            System.out.println (F.toString());
        }
    }

    /**
     * Este método retorna el arreglo de velas
     * @return ArrayList <Vela> Arreglo estatico de velas
     */
    public ArrayList<Vela> getArr_velas(){
        return arr_velas;
    }
}