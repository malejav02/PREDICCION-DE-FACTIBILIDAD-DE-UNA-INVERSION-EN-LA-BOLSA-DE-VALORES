import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase MinutoVelas: Esta clase cuenta con un ArrayList arr_datos, el cual contiene la información de ColeccionDatos
 * y con ésta información hace un filtro para crear velas por minuto.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */       
public class MinutoVelas   
{
    /**
     * Atributo dinamico de Velas
     */
    private ArrayList<Dato> arr_datos;
    /**
     * Este constructor inicializa el ArrayList arr_datos.   
     */
    public MinutoVelas(){
        arr_datos = ColeccionDatos.getDatos();
    }

    /**
     * Este método crea velas por minuto, filtrando el dato de la hora, que se obtiene mediante arr_datos. 
     * Además se determinan las características de la vela a crear, como por ejemplo el color de la vela, y finalmente se imprime
     * un mensaje el cuál informa cuántas velas fueron creadas.
     */
    public void contar(){
        int n = arr_datos.size();
        if(n == 0) return;

        int cont = 0;
        int i = 0;
        boolean firstTime = true;
        String anterior = "";
        Vela v = new Vela();
        Dato d;
        int vol = 0;
        while(i < n){
            d = arr_datos.get(i);
            String actual =d.getT(); i++;
            if(firstTime){
                anterior = actual;
                firstTime = false;
                v = new Vela("null", d.getDate(), d.getT(), d.getLast(), d.getLast(), d.getLast()
                , d.getLast(), d.getTransaction());
            }else{
                if(actual.equals(anterior)){
                    if(d.getLast() > v.getMax()){
                        v.setMax(d.getLast());
                        v.setValueF(d.getLast());
                    }else if(d.getLast() < v.getMin()){
                        v.setMin(d.getLast());
                        v.setValueF(d.getLast());
                    }
                }else {
                    if(v.getValueI() < v.getValueF()){
                        v.setColor("Verde");
                    }else{
                        v.setColor("Rojo");
                    }
                    v.setTr(vol);
                    v.addVelas(v);
                    //Inicializar vela
                    cont++;
                    anterior = actual;
                    v = new Vela("null", d.getDate(), d.getT(), d.getLast(), d.getLast(), d.getLast()
                    , d.getLast(), d.getTransaction());
                    vol = 0;
                }
            }
            vol+=d.getTransaction();
        }
        if(v.getValueI() < v.getValueF()){
            v.setColor("Verde");
        }else{
            v.setColor("Rojo");
        }
        v.setTr(vol);
        v.addVelas(v);
        JOptionPane.showMessageDialog(null, "Se crearon " + (cont+1) + " velas");
    } 
}

