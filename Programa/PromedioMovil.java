
/**
 * Clase SerieIndicador: Esta clase hereda de la clase SerieIndicador, donde a través de una coleccion de velas
 * realiza un promedio movil por un periodo dado por el usuario.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado
 * @version 5 de junio 2020
 */
public class PromedioMovil extends SerieIndicador
{
    private double [] xT; 
    private double [] yL;
    /**
     *Este constructor recibe una coleccion de velas y el periodo de la regresión lineal.
     *También inicializa los arreglos de xT y yL. Finalmente ejecuta los metodos correspondientes.
     * @param cV Coleccion de velas creada
     * @param n Periodo de la regresión lineal (Número de velas de velas que se utilizan para hallar la regresión lineal)
     */
    public PromedioMovil(ColeccionVelas cV, int n){
        super(cV);
        super.n = n;
        int size = arr_velas.size();
        xT = new double [size];
        yL = new double [size];
        asignarValoresArreglos();
        calcularEntradasPromedio();
    }

    /**
     * Este método permite agregar indicadores al ArrayList arr_indicadores.
     */
    public void asignarValoresArreglos(){
        int i = 0;
        for(Vela v: arr_velas){
            xT[i] = i;
            yL[i] = v.getValueF();
            i++;
        }
    }
    
    /**
     * Se calculan los valores del promedio movil para cada minuto
     * de la colección de Velas.
     * En cada punto se usa el valor actual y n-1 valores hacia atrás en el tiempo
     */
    public void calcularEntradasPromedio() {
        for(int i = 0; i < arr_velas.size(); i++) {
            String fecha = arr_velas.get(i).getDateI();
            String hora = arr_velas.get(i).getHour();
            if(i < (n - 1)) {
                // Se usa null para indicar que no se puede calcular el valor
                Indicador ind = new Indicador(fecha,hora, null);
                arr_indicadores.add(ind);
            } else {
                // Calcular la regresión lineal devolviéndose n-1 posiciones
                double promedio = promedio(i - n + 1, n);
                Indicador ind = new Indicador(fecha, hora , promedio);
                arr_indicadores.add(ind);
            }
        }
    }
    
    /**
     * Calcula el promedio movil
     * @param ini Dónde comenzar en el arreglo
     * @param elem número de elementos a usar para el cálculo
     * @return Devuelve el promedio con respecto al periodo
     */
    public double promedio(int ini, int elem){
        double promedio = 0;
        double suma = 0;
        for(int i = ini; i < ini + elem; i++){
            suma+=yL[i];
        }
        promedio = suma/elem;
        return promedio;
    }

    public String toString() {
        String s = "Promedios: \n";
        for(Indicador i: arr_indicadores) {
            s += i.toString() + "" + i.getValue() + "\n";
        }
        return s;
    }  

}
