import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase EstrategiaCompuestaDeCruce: Esta clase es una implementación de estrategia. Da a conocer al 
 * usuario si es favorable invertir bajo la fecha, hora y la comparción de dos indicadores previamente creados.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class EstrategiaCompuestaDeCruce implements Estrategia
{
    protected SerieIndicador sI; //serie menor
    protected SerieIndicador sI2; //serie mayor
    /**
     * Se  inicializan los valores de las SeriesIndicador a comparar
     * @param s1 SerieIndicador a utilizar
     * @param s2 SerieIndicador a utiizar
     */
     
    public EstrategiaCompuestaDeCruce(SerieIndicador s1, SerieIndicador s2){
        if(s1.getPeriodo() > s2.getPeriodo()){
            this.sI2 = s1;
            this.sI = s2;
        }else{
            this.sI = s1;
            this.sI2 = s2;
        }
    }

    /** 
     * Esta clase compara las fechas y  las horas de dos series indicadores
     * Para decidir si es rentable invertir o no en el activo
     * @param fecha Fecha propuesta por el usuario
     * @param hora Hora propuesta por el usuario
     * @return Devuelve True o False de acuerdo a la comparación de los indicadores durante esa fecha
     */
    @Override    
    public boolean decidir(String fecha, String hora){
        String minutoAnterior = hora.substring(0,2) + String.valueOf(Integer.parseInt(hora.substring(2,4))-1) + "00";
        Double valor  = sI.retornarValor(fecha, minutoAnterior);
        Double valor2 = sI2.retornarValor(fecha, minutoAnterior);
        if((valor != null) && (valor2 != null)){
            if(valor < valor2){
                if(sI.retornarValor(fecha, hora) > sI2.retornarValor(fecha, hora)){
                    return true;
                }
            }               
        }
        return false;  
    }
}
