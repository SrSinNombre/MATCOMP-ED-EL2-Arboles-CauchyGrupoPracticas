import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PruebaSuma2 {
    public static void main(String[] args){
        ArbolBinarioDeBusquedaEnteros<Integer> arbol = new ArbolBinarioDeBusquedaEnteros<>();

        List<Integer> lista = new ArrayList<>();
        for(int i = 0; i < 128; i++){
            lista.add(i);
        }
        Collections.shuffle(lista);

        for(int j : lista){
            arbol.add(j);
        }

        System.out.println("La suma de los elementos del árbol es: " + arbol.getSuma());

        List<Integer> preorden = arbol.getListaPreOrden();
        int sumaPreorden = 0;
        for(int i : preorden){
            sumaPreorden += i;
        }
        System.out.println("La suma de los elementos en preorden es: " + sumaPreorden);

        List<Integer> postorden = arbol.getListaPostOrden();
        int sumaPostorden = 0;
        for(int i : postorden){
            sumaPostorden += i;
        }
        System.out.println("La suma de los elementos en postorden es: " + sumaPostorden);

        List<Integer> ordenCentral = arbol.getListaOrdenCentral();
        int sumaOrdenCentral = 0;
        for(int i : ordenCentral){
            sumaOrdenCentral += i;
        }
        System.out.println("La suma de los elementos en orden central es: " + sumaOrdenCentral);

        System.out.println("La altura del árbol es " + arbol.getAltura());
        
        List<Integer> camino = arbol.getCamino(110);
        for(int i : camino){
            System.out.print(i + " ");
        }
        System.out.println("\nLa longitud del camino es " + camino.size());
    }
}
