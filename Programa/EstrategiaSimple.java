import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Clase EstrategiaSimple: Esta clase es una implementación de estrategia. Da a conocer al 
 * usuario si es favorable invertir bajo la fecha, hora y valor threshold que seleccione.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class EstrategiaSimple implements Estrategia
{
    protected SerieIndicador sI;
    protected double threshold; 

    /***
     * Constructor de la clase EstrategiaSimple
     * @ param si Indicador a utilizar
     * @ param t double Threshold a utilizar
     */
    public EstrategiaSimple(SerieIndicador sI, double t){
        this.sI = sI;
        this.threshold = t;
    }

    /**
     * Método sobrescrito para realizar la desicion respecto a la fecha y la hora (Ambos Strings)
     * @param fecha Fecha propuesta por el usuario
     * @param hora Hora propuesta por el usuario
     * @return Devuelve True o False de acuerdo si el valor threshold es mayor o menor a la fecha y hora indicada
     */
    @Override
    public boolean decidir(String fecha, String hora){
        Double valor  = sI.retornarValor(fecha, hora);
        if(valor != null){
            if(valor > threshold){

                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
