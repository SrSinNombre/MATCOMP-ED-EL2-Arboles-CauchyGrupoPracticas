public class ArbolBinarioDeBusquedaEnteros<Integer extends Comparable<Integer>> extends ArbolBinarioDeBusqueda<Integer>{

    public int getSuma(Nodo<java.lang.Integer> nodo){
        int suma = 0;
        if(nodo == null){
            return 0;
        }
        return nodo.getElemento() + getSuma(nodo.getHijoDer()) + getSuma(nodo.getHijoIzq());
    }
}
