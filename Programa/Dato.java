import java.io.*;
import java.util.*;
/**
 * Clase Dato:
 * La utilidad de esta clase es inicializar los valores que toman los diferentes datos del activo,
 * además permite el acceso a dichos datos mediante sus respectivos métodos.  
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */

public class Dato
{
    /**
     * Fecha del activo
     */
    private String date;
    /**
     * Tiempo del activo(hhmmss)
    */
    private String time;
    /**
    * Cierre del activo
    */
    private double last;
    /**
    * Diezmillonésimas de segundo del activo
    */
    private double secM;
    /**
    * Precio más alto del activo
    */
    private double ask;
    /**
    * Precio más bajo del activo
    */
    private double bid;
    /**
    * Número de transacciones del activo
    */
    private int transaction;

    /***
     * Inicializa los valores que toman los diferentes datos del activo.
     * @ param d Fecha del activo
     * @ param t Hora del activo
     * @ param s DiezMillonésimas de segundo del activo
     * @ param b Bid (precio más alto) del activo
     * @ param a Ask (precio más bajo) del activo
     * @ param l Last (precio final) del activo
     * @ param v Cantidad de transacciones 
     */
    public Dato(String d, String t, double s, double b, double a, double l, int v){
        this.date = d;
        this.time = t;
        this.last = l;
        this.secM = s;
        this.ask = a;
        this.bid = b;
        this.transaction = v;
    }

    /**
     * Este método se utiliza para acceder a la fecha del activo
     * 
     * @return Fecha del activo
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Este método se utiliza para acceder al precio final del activo 
     * 
     * @return Precio final del activo
     */
    public double getLast(){
        return this.last;
    }

    /**
     * Este método se utiliza para acceder a las diezmillonésimas de segundo del activo 
     * 
     * @return Diezmillonésimas de segundo del activo 
     */
    public double getSecM(){
        return this.secM;
    }

    /**
     * Este método se utiliza para acceder a la hora del activo 
     * 
     * @return Hora del activo
     */
    public String getTime(){
        return this.time;
    }

    /**
     * Este método se utiliza para acceder al minuto del activo 
     * 
     * @return Minuto del activo
     */
    public int getMinute(){
        int min;
        min = Integer.parseInt(this.time.substring(2,4));
        return min;
    }

    /**
     * Este método se utiliza para acceder al precio más bajo del activo 
     * 
     * @return Precio más bajo del activo 
     */
    public double getAsk(){
        return this.ask;
    }

    /**
     * Este método se utiliza para acceder a la cantidad de transacciones del activo 
     * 
     * @return Cantidad de transacciones del activo
     */
    public int getTransaction(){
        return this.transaction;
    }

    /**
     * Este método se utiliza para acceder al precio más alto del activo 
     * 
     * @return Precio más alto del activo 
     */
    public double getBid(){
        return this.bid;
    }

    /**
     * Este método se utiliza para acceder a la hora del activo 
     * 
     * @return Hora del activo
     */
    public int getHour(){
        int hour;
        hour = Integer.parseInt(this.time.substring(0,2));
        return hour;
    }

    /**
     * Este método se utiliza para acceder a los segundos del activo 
     * 
     * @return Segundos del activo
     */
    public int getSec(){    
        int sec;
        sec = Integer.parseInt(this.time.substring(4,6));
        return sec;
    }

    /**
     * Éste método devuelve la hora (sin los segundos) de la vela
     * @return Retorna un String con la hora (sin los segundos) de la vela
     */
    public String getT(){

        return this.time.substring(0,4)+ "00";
    }

    /**
     * Éste método devuelve la hora (sin los minutos, ni segundos) de la vela
     * @return Retorna un String con la hora (sin los minutos, ni segundos) de la vela
     */
    public String getT2(){

        return this.time.substring(0,2)+ "0000";
    }

    /**
     *Éste método permite mostrar la información completa del objeto de la clase dato, es decir, el valor de sus atributos.
     * @return Devuelve un string con el valor de los atributos del objeto 
     */
    public String toString(){
        String dato = getDate() + " " + getTime() + " " + getSecM() + " " + getBid() +
            getAsk() + " " + getLast() + " " + getTransaction();
        return dato;
    }
}