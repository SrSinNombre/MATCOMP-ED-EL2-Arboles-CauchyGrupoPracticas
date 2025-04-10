public class Nodo<T extends Comparable<T>> {
    private T dato;
    public Nodo<T> izquierdo;
    public Nodo<T> derecho;

    Nodo(T dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
    public T getDato(){
        return dato;
    }
    public void setDato(T dato){
        this.dato = dato;
    }
}
