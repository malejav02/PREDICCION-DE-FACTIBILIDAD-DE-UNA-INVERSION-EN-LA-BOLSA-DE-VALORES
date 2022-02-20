import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase Principal: 
 * La implementación de esta clase permite ejecutar el programa.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class Principal 
{
    private static ColeccionDatos d1;
    private static ColeccionVelas cV;
    private static MinutoVelas v1;
    private static ArrayList<SerieIndicador> sI = new ArrayList<>(); 
    private static ArrayList<Estrategia> e = new ArrayList<>();
    private static String m;
    private static boolean condicion;
    private static int i1 = 0;
    private static int i2 = 0;
    private static int cont1 = 0;
    private static BackTesting b;

    /**
     * Metodo principal que ejecuta el programa
     */
    public static void main(String args[]){
        JOptionPane.showMessageDialog(null,"Bienvenido, ¡invierte con S8A!");
        inicio();
        serieIndicador();
        estrategiasSyC();
        estrategiaBooleana();
        backTesting();
    }

    /**
     * Metodo inicial.
     * 1) Pide al usuario que ingrese archivo.txt que contenga los datos del activo a ser estudiado.
     * 2) Da la opción al usuario de crear las velas o salirse del programa.
     */
    public static void inicio(){              
        try{
            String datos = JOptionPane.showInputDialog(null,"Ingrese datos de estudio");
            d1 = new ColeccionDatos();
            d1.leerArchivos(datos);
        } catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"El archivo no se encontro ");
            inicio();
        }        
        m = "Ingrese la opcion que desee realizar \n"+
        " 1) Crear velas\n"+
        " 99) Salir";
        condicion = true;
        while(condicion){
            String opcion = JOptionPane.showInputDialog(null,m);
            switch (opcion){
                case "1":
                cV = new ColeccionVelas();
                v1 = new MinutoVelas();
                v1.contar();
                condicion = false;
                break;
                case "99":
                JOptionPane.showMessageDialog(null, "Fin del programa");
                System.exit(0);
                break;
                default:
                JOptionPane.showMessageDialog(null, "Error, vuelva a intentar");
                break;
            }            
        }
    }

    /**
     * Metodo 2.
     * Permite al usuario crear un indicador con las velas previamente creadas.
     * Puede ser un promedio movil o una regresión lineal.
     */   
    public static void serieIndicador(){
        m = "Ingrese la opcion que desee realizar \n"+
        " 1) Calcular promedio movil\n"+
        " 2) Calcular regresion lineal\n"+
        " 3) Seguir el programa\n" +
        " 99) Salir";
        SerieIndicador s1;
        condicion = true;
        int i = 0;
        while(condicion){
            String opcion = JOptionPane.showInputDialog(null,m);
            String periodo;
            switch (opcion){
                case "1":
                periodo = JOptionPane.showInputDialog(null,"Ingrese valor periodo para promedio movil");
                int p = Integer.parseInt(periodo);
                s1 = new PromedioMovil(cV, p);
                sI.add(i, s1);
                JOptionPane.showMessageDialog(null, "Se hizo el promedio movil correctamente. \n"+  "(Recuerde esta en la posición: " + i+")");
                i++;
                break;
                case "2":
                periodo = JOptionPane.showInputDialog(null,"Ingrese valor periodo regresión lineal");
                int p2 = Integer.parseInt(periodo);
                s1 = new RegresionLineal(cV, p2);
                sI.add(i, s1);
                JOptionPane.showMessageDialog(null, "Se hizo la regresión lineal correctamente. \n"+  "(Recuerde esta en la posición: " + i+")");
                i++;
                break;
                case "3":
                condicion = false;
                break;
                case "99":
                JOptionPane.showMessageDialog(null, "Fin del programa");
                System.exit(0);
                break;
                default:
                JOptionPane.showMessageDialog(null, "Error, vuelva a intentar");
                break;
            }      
        }
    }

    /**
     * Metodo 3.
     * Permite aplicar diferentes estrategias a los indicadores previamente creados.
     */    
    public static void estrategiasSyC(){
        String m = "Ingrese la opcion que desee realizar \n"+
            " 1) Estrategia simple\n"+
            " 2) Estrategia compuesta de cruce\n" + 
            " 3) Seguir el programa\n" + 
            " 99) Salir";
        condicion = true;
        Estrategia e1;
        String i; 
        while(condicion){
            String opcion = JOptionPane.showInputDialog(null,m);
            String threshold;
            switch (opcion){
                case "1": 
                if(sI.size() < 2){
                    JOptionPane.showMessageDialog(null, "Esta estrategia requiere un indicador\n");  
                    serieIndicador();
                }
                threshold = JOptionPane.showInputDialog(null,"Ingrese threshold para estrategia simple");
                double t = Double.parseDouble(threshold);
                i = JOptionPane.showInputDialog(null, "Ingrese la posición del indicador previamente creado");
                i1 = Integer.parseInt(i);
                e1 = new EstrategiaSimple(sI.get(i1), t);
                String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha año/mes/dia\n"+"ej: 20200429");
                String hora = JOptionPane.showInputDialog(null, "Ingrese la hora hh/mm/ss\n"+"ej: 053000");
                e1.decidir(fecha, hora);
                e.add(cont1, e1);
                if(e1.decidir(fecha, hora) == true){
                    JOptionPane.showMessageDialog(null, "¡Hora de invertir! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }else{
                    JOptionPane.showMessageDialog(null, "¡Va perder la platica! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }
                cont1++;
                break;
                case "2":
                if(sI.size() < 2){
                    JOptionPane.showMessageDialog(null, "Esta estrategia requiere otro indicador\n" + "(recomedación: Preferiblemente promedio movil)");               
                    String periodo = JOptionPane.showInputDialog(null,"Ingrese valor periodo para promedio movil");
                    int p = Integer.parseInt(periodo);
                    SerieIndicador s2 = new PromedioMovil(cV, p);
                    sI.add(1, s2);
                    JOptionPane.showMessageDialog(null, "Se hizo el promedio movil correctamente.\n" + "(Se guardo en la posición: 1)");
                }
                i = JOptionPane.showInputDialog(null, "Ingrese la posición del indicador previamente creado");
                i1 = Integer.parseInt(i);
                i = JOptionPane.showInputDialog(null, "Ingrese la posición del segundo indicador previamente creado");
                i2 = Integer.parseInt(i);
                e1 = new EstrategiaCompuestaDeCruce(sI.get(i1), sI.get(i2));
                String fecha2 = JOptionPane.showInputDialog(null, "Ingrese la fecha año/mes/dia para estrategia compuesta\n"+"ej: 20200429");
                String hora2 = JOptionPane.showInputDialog(null, "Ingrese la hora hh/mm/ss para estrategia compuesta\n "+"ej: 053000");
                e1.decidir(fecha2, hora2);
                e.add(cont1, e1);
                if(e1.decidir(fecha2, hora2) == true){
                    JOptionPane.showMessageDialog(null, "¡Hora de invertir! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }else{
                    JOptionPane.showMessageDialog(null, "¡Va perder la platica! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }
                cont1++;
                break;    
                case "3":
                condicion = false;
                break;
                case "99":
                JOptionPane.showMessageDialog(null, "Fin del programa");
                System.exit(0);
                break;
                default:
                JOptionPane.showMessageDialog(null, "Error, vuelva a intentar");
                break;
            }            
        }
    }
    
    /**
     * Metodo 4.
     * Permite aplicar la estrategiaBooleana.
     */
    public static void estrategiaBooleana(){
        String m = "Ingrese la opcion que desee realizar \n"+
            " 1) Estrategia compuesta booleana\n" +
            " 3) Seguir el programa\n" +
            " 99) Salir";
        condicion = true;
        Estrategia e1;
        String i;
        while(condicion){
            String opcion = JOptionPane.showInputDialog(null,m);
            switch (opcion){
                case "1":  
                if(e.size() < 2){
                    JOptionPane.showMessageDialog(null, "¡Debes crear al menos dos estrategias!");
                    estrategiasSyC();
                }
                i = JOptionPane.showInputDialog(null, "Ingrese la posición de la estrategia previamente creada");
                i1 = Integer.parseInt(i);
                i = JOptionPane.showInputDialog(null, "Ingrese la posición de la segunda estrategia previamente creada");
                i2 = Integer.parseInt(i);
                e1 = new EstrategiaCompuestaBooleana(e.get(i1), e.get(i2));
                String fecha2 = JOptionPane.showInputDialog(null, "Ingrese la fecha año/mes/dia para estrategia compuesta booleana\n"+"ej: 20200429");
                String hora2 = JOptionPane.showInputDialog(null, "Ingrese la hora hh/mm/ss para estrategia compuesta booleana\n "+"ej: 053000");
                e1.decidir(fecha2, hora2);
                e.add(cont1, e1);
                if(e1.decidir(fecha2, hora2) == true){
                    JOptionPane.showMessageDialog(null, "¡Hora de invertir! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }else{
                    JOptionPane.showMessageDialog(null, "¡Va perder la platica! \n" + "(La estrategia se guardo en la posición: "+ cont1+ ")" );
                }
                cont1++;
                break;
                case "3":
                condicion = false;
                break;
                case "99":
                JOptionPane.showMessageDialog(null, "Fin del programa");
                System.exit(0);
                break;
                default:
                JOptionPane.showMessageDialog(null, "Error, vuelva a intentar");
                break;
            }
        }
    }
    
    /**
     * Metodo 5.
     * Permite aplicar el backTesting a través de una colección de velas y una estrategia previamente creada.
     */
    public static void backTesting(){
        String m = "Ingrese la opcion que desee realizar \n"+
            " 1) BackTesting\n" +
            " 99) Salir"; 
        condicion = true;
        Estrategia e1; 
        String i;
        while(condicion){
            String opcion = JOptionPane.showInputDialog(null,m);
            switch (opcion){
                case "1":  
                if(e.size() < 1){
                    JOptionPane.showMessageDialog(null, "Hace falta una estrategia");
                    estrategiasSyC();
                }
                i = JOptionPane.showInputDialog(null, "Ingrese la posición de la estrategia previamente creada");
                i1 = Integer.parseInt(i);
                b = new BackTesting(cV, e.get(i1));
                b.test();
                break;
                case "99":
                JOptionPane.showMessageDialog(null, "Fin del programa");
                System.exit(0);
                break;
                default:
                JOptionPane.showMessageDialog(null, "Error, vuelva a intentar");
                break;
            }
        }
    }
}
