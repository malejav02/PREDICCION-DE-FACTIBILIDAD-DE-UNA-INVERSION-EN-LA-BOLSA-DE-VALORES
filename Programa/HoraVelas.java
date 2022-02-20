
import java.util.*;
import java.io.*;
/**
 * Clase MinutoVelas: Esta clase cuenta con un ArrayList arr_datos, el cual contiene la información de ColeccionDatos
 * y con ésta información hace un filtro para crear velas por hora.
 * @author Maria Alejandra Vélez Clavijo, Santiago Ochoa Castaño y Juan Felipe Ortiz Salgado 
 * @version 5 de junio 2020
 */
public class HoraVelas
{
    private ArrayList<Dato> arr_datos;

    /**
     * Éste constructor inicializa el ArrayList arr_datos.
     */
    public HoraVelas(){
        arr_datos = ColeccionDatos.getDatos();
    }

     /**
     * Este método crea velas por hora, filtrando el dato de la hora, que se obtiene mediante arr_datos. 
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
            String actual =d.getT2(); i++;
            if(firstTime){
                anterior = actual;
                firstTime = false;
                v = new Vela("null", d.getDate(), d.getT2(), d.getLast(), d.getLast(), d.getLast()
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
                    v = new Vela("null", d.getDate(), d.getT2(), d.getLast(), d.getLast(), d.getLast()
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
        System.out.println("Se crearon " + (cont+1) + " velas");
    }

}