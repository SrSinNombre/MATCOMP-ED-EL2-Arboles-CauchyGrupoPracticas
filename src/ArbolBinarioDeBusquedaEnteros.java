public class ArbolBinarioDeBusquedaEnteros<Integer extends Comparable<Integer>> extends ArbolBinarioDeBusqueda<Integer>{

    public int getSuma(){
        return getSumaRec(getRaiz());
    }
    private int getSumaRec(Nodo<Integer> nodo){
        if(nodo == null){
            return 0;
        }

        return ((Number) nodo.getDato()).intValue() + getSumaRec(nodo.derecho) + getSumaRec(nodo.izquierdo);
    }
}
