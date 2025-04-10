import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public ArbolBinarioDeBusqueda(){
        raiz = null;
    }

    public int getAltura(Nodo<T> nodo) {
        if (raiz == null) {
            return -1;
        }
        int hijos = 0;
        if (raiz.izquierdo != null) {
            hijos++;
        }
        else if (raiz.derecho != null) {
            hijos++;
        }
        return Math.max(getAltura(raiz.izquierdo), getAltura(raiz.derecho));
    }

    public List<T> getListaDatosNivel(int nivel) {
        List<T> resultado = new ArrayList<>();
        if (raiz == null) return resultado;

        Queue<Nodo<T>> cola = new LinkedList<>();
        Queue<Integer> niveles = new LinkedList<>();

        cola.add(raiz);
        niveles.add(0);

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            int nivelActual = niveles.remove();

            if (nivelActual == nivel) {
                resultado.add(actual.getDato());
            }
            if (actual.izquierdo != null) {
                cola.add(actual.izquierdo);
                niveles.add(nivelActual + 1);
            }
            if (actual.derecho != null) {
                cola.add(actual.derecho);
                niveles.add(nivelActual + 1);
            }

        }
        return resultado;
    }

    public boolean isArbolHomogeneo() {
        return verificarHomogeneidad(raiz, new int[]{-1}); // usamos array para pasar "por referencia"
    }

    // Recursión para verificar homogeneidad
    private boolean verificarHomogeneidad(Nodo<T> nodo, int[] gradoEsperado) {
        if (nodo == null) {
            return true;
        }

        int gradoActual = nodo.obtenerGrado();

        if (gradoActual > 0) { // solo evaluamos nodos que no son hojas
            if (gradoEsperado[0] == -1) {
                gradoEsperado[0] = gradoActual; // primer grado interno encontrado
            } else if (gradoActual != gradoEsperado[0]) {
                return false; // no coincide
            }
        }

        // Recursivamente evaluamos hijos
        return verificarHomogeneidad(nodo.izquierdo, gradoEsperado) &&
                verificarHomogeneidad(nodo.derecho, gradoEsperado);
    }

    public boolean isArbolCompleto() {
        if (raiz == null) return true;

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz);
        boolean encontradoNodoIncompleto = false;

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();

            // Si tiene hijo izquierdo
            if (actual.izquierdo != null) {
                if (encontradoNodoIncompleto) return false;
                cola.add(actual.izquierdo);
            } else {
                encontradoNodoIncompleto = true;
            }

            // Si tiene hijo derecho
            if (actual.derecho != null) {
                if (actual.izquierdo == null) return false;
                cola.add(actual.derecho);
            } else {
                encontradoNodoIncompleto = true;
            }
        }

        return true;
    }

    public boolean isArbolCasiCompleto() {
        if (raiz == null) {
            return true;
        }

        Queue<Nodo<T>> cola = new LinkedList<>();
        Queue<Integer> niveles = new LinkedList<>();

        cola.add(raiz);
        niveles.add(0);

        int nivelHojaMin = -1;
        int nivelHojaMax = -1;
        boolean encontradoHueco = false;
        boolean seTerminoLaZonaCompleta = false;

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            int nivel = niveles.remove();


            if (actual.esHoja()) {
                if (nivelHojaMin == -1) nivelHojaMin = nivel;
                nivelHojaMax = nivel;

                // Si encontramos una hoja después de un hueco, no es contiguo
                if (seTerminoLaZonaCompleta) {
                    return false;
                }
            } else {
                // Caso en que falta hijo izquierdo: hueco detectado
                if (actual.izquierdo == null && actual.derecho != null) {
                    return false;
                }

                if (actual.izquierdo != null) {
                    cola.add(actual.izquierdo);
                    niveles.add(nivel + 1);
                } else {
                    seTerminoLaZonaCompleta = true;
                }

                if (actual.derecho != null) {
                    cola.add(actual.derecho);
                    niveles.add(nivel + 1);
                } else {
                    seTerminoLaZonaCompleta = true;
                }
            }
        }
        // Las hojas solo deben estar en como mucho 2 niveles, y esos deben ser consecutivos
        return (nivelHojaMax - nivelHojaMin <= 1);
    }


    public void add(T dato) {
        addRecursivo(dato, raiz);
    }

    private void addRecursivo(T dato, Nodo<T> nodo) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if(raiz == null){
            raiz = nuevoNodo;
        }
        else {
            if (nodo.getDato().compareTo(dato) < 0) {
                if (nodo.derecho == null) {
                    nodo.derecho = nuevoNodo;
                } else {
                    addRecursivo(dato, nodo.derecho);
                }
            }
            if (nodo.getDato().compareTo(dato) > 0) {
                if (nodo.izquierdo == null) {
                    nodo.izquierdo = nuevoNodo;
                } else {
                    addRecursivo(dato, nodo.izquierdo);
                }
            }
            if (nodo.getDato().equals(dato)) {
                System.out.println("El elemento ya está en el árbol");
            }
        }
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>();
        subArbol.raiz = raiz.izquierdo;
        return subArbol;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>();
        subArbol.raiz = raiz.derecho;
        return subArbol;
    }
    public List<T> getListaPreOrden() {
        List<T> resultado = new ArrayList<>();
        getListaPreOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    private void getListaPreOrdenRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.getDato());
            getListaPreOrdenRecursivo(nodo.izquierdo, lista);
            getListaPreOrdenRecursivo(nodo.derecho, lista);
        }
    }
    public List<T> getListaPostOrden() {
        List<T> resultado = new ArrayList<>();
        getListaPostOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    private void getListaPostOrdenRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            getListaPostOrdenRecursivo(nodo.izquierdo, lista);
            getListaPostOrdenRecursivo(nodo.derecho, lista);
            lista.add(nodo.getDato());
        }
    }
    public List<T> getListaOrdenCentral() {
        List<T> resultado = new ArrayList<>();
        getListaOrdenCentralRecursivo(raiz, resultado);
        return resultado;
    }
    private void getListaOrdenCentralRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            getListaOrdenCentralRecursivo(nodo.izquierdo, lista);
            lista.add(nodo.getDato());
            getListaOrdenCentralRecursivo(nodo.derecho, lista);
        }
    }
}

