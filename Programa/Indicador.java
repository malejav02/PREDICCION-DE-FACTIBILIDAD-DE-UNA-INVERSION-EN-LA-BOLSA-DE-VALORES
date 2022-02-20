
/**
 * Clase Indicador:  La finalidad de esta clase es inicializar los valores que toman los diferentes datos del indicador,
 * además permite el acceso a dichos datos mediante sus respectivos métodos. 
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class Indicador
{
    private String date;
    private String hour;
    private Double value;
    /**
     * Este constructor inicializa la fecha, hora y el valor del indicador.
     * @param d Fecha del indicador
     * @param h Hora del indicador
     * @param v Valor del indicador
     * La hora se guarda los segundos en cero
     */
    public Indicador ( String d, String h, Double v){    
        this.date=d;
        this.hour = h.substring(0,4) + "00";
        this.value=v;
    }

    /**
     *Este método permite mostrar la información completa del objeto de la clase indicador, es decir, el valor de sus atributos.
     * @return Devuelve un string con el valor de los atributos del objeto 
     */
    public String toString() {
        String indicador= "Indicador : " + this.date+ " "+ this.hour + " " + this.value + "\n";
        return indicador;
    }

    /**
     * Este método permite acceder a la fecha del indicador.
     * @return Devuelve un String con la fecha del indicador.
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Este método permite acceder a la hora del indicador.
     * @return Devuelve un String con la hora del indicador.
     */
    public String getHour(){
        return this.hour;
    }

    /**
     * Este método permite acceder al valor del indicador.
     * @return Devuelve un double con el valor del indicador.
     */
    public Double getValue(){
        return this.value;
    }
}

