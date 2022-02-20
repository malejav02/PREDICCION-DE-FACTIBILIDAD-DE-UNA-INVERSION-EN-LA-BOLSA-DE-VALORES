
/**
 * Clase EstrategiaCompuestaBooleana: Esta clase es una implementación de estrategia. Da a conocer al 
 * usuario si es favorable invertir bajo la fecha, hora y la comparción de dos estrategias previamente creadas.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class EstrategiaCompuestaBooleana implements Estrategia
{
    private Estrategia e1;
    private Estrategia e2;
    /**
     * Se  inicializan los valores de las Estrategias a comparar
     * @param e1 Estrategia a utilizar
     * @param e2 Estrategia a utiizar
     */
    public EstrategiaCompuestaBooleana(Estrategia e1, Estrategia e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    /**  
     * Esta clase compara las fechas y  las horas de dos series indicadores
     * Para decidir si es rentable invertir o no en el activo
     * @param fecha Fecha propuesta por el usuario
     * @param hora Hora propuesta por el usuario
     * @return Devuelve True o False de acuerdo a la comparación de las estrategias durante esa fecha
     */
    public boolean decidir(String fecha, String hora){
        if(e1.decidir(fecha, hora) == true && e2.decidir(fecha, hora) == true){
            return true;
        }
        return false;
    }
}
