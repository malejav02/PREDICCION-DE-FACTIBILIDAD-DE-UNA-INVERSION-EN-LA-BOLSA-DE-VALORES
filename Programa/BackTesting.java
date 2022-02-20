import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase BackTesting: 
 * La implementación de esta clase permite probar si es favorable o no una estragia en una coleccion de velas.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class BackTesting
{
    private Estrategia e;
    private ArrayList<Vela> cV;
    /**
     * En esta clase Backtesting se  inicializan los valores que se implementan a partir de ColeccionVelas y estrategia
     * @param cV Coleccion de velas a uilizar
     * @param e Estrategia a aplicar
     */
    public BackTesting(ColeccionVelas cV, Estrategia e){
        this.e = e;
        this.cV = cV.getArr_velas();
    }

    /**
     * En esta clase test se prueba que exista una compatibilidad de las estrategias con los datos que se entregan
     * E imprimimos cuantas estrategias tuvieron exito (esto es, que recomiendan invertir)
     */
    public void test(){
        int minSig = 0;
        int tam = cV.size();
        int contV = 0;
        int contF = 0;
        int cont = 0;
        for(int i = 0; i < tam-1; i++){
            String fecha = cV.get(i).getDateI();
            String hora = cV.get(i).getHour();
            minSig = i + 1;
            if(e.decidir(fecha, hora) == true){
                if((cV.get(minSig).getValueF() - cV.get(i).getValueF()) >= 0.5){
                    contV++;
                }else{
                    contF++;
                } 
            }else {
                cont++;
            }
        }
        JOptionPane.showMessageDialog(null, "Aplicación exitosa: " + contV + " veces\n"+
            "Aplicación fallida: " + contF + " veces\n"+
            "No se pudo aplicar: " + cont + " veces");
        if(contV > contF){
            JOptionPane.showMessageDialog(null, "¡La estrategia fue exitosa! Es rentable invertir");
        }else
            JOptionPane.showMessageDialog(null, "La estrategia no fue exitosa. No es rentable invertir");
    }
}
