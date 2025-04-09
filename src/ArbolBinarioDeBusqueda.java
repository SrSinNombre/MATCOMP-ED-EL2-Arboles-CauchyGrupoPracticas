import com.sun.jdi.Value;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private Key clave;

    private Nodo<T> raiz;
    static class Nodo<T> {
        public Integer valor;
        T dato;
        Nodo<T> izquierdo, derecho;
        Nodo(T dato) {
            this.dato = dato;
            izquierdo = derecho = null;
        }
    }


    public int getAltura(Nodo<T> izquierdo) {
            if (raiz == null) {
                return 0;
            }
            int grado = 0;
            int hijos = 0;
            if (raiz.izquierdo != null){hijos++;}
            if (raiz.derecho != null){hijos++;}
            grado = hijos;
            int AlturaIzq = getAltura(raiz.izquierdo);
            int AlturaDcha = getAltura(raiz.derecho);
            return Math.max(AlturaIzq, AlturaDcha);
        }
    public List<Integer> getListaDatosNivel(int nivel) {
        List<Integer> resultado = new ArrayList<>();
        if (raiz == null) return resultado;

        Queue<Nodo> cola = new LinkedList<>();
        Queue<Integer> niveles = new LinkedList<>();

        cola.add(raiz);
        niveles.add(0);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            int nivelActual = niveles.poll();

            if (nivelActual == nivel) {
                resultado.add(actual.valor);
            }

            if (actual.izquierdo != null) {
                cola.add(actual.izquierdo);
                niveles.add(nivelActual + 1);
            }
            if (actual.derecho != null) {
                cola.add(actual.derecho);
                niveles.add(nivelActual + 1);
            }
            return resultado;
        }
        return resultado;
    }
    public boolean isArbolHomogeneo() {
        return verificarHomogeneidad(raiz, new int[]{-1}); // usamos array para pasar "por referencia"
    }

    // Recursión para verificar homogeneidad
    private boolean verificarHomogeneidad(Nodo nodo, int[] gradoEsperado){
        if (nodo == null) {
            return true;
        }

        int gradoActual = obtenerGrado(nodo);

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

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        boolean encontradoNodoIncompleto = false;

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            // Si tiene hijo izquierdo
            if (actual.izquierdo != null) {
                if (encontradoNodoIncompleto) return false;
                cola.add(actual.izquierdo);
            } else {
                encontradoNodoIncompleto = true;
            }

            // Si tiene hijo derecho
            if (actual.derecho != null) {
                if (actual.izquierdo == null || encontradoNodoIncompleto) return false;
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

        Queue<Nodo> cola = new LinkedList<>();
        Queue<Integer> niveles = new LinkedList<>();

        cola.add(raiz);
        niveles.add(0);

        int nivelHojaMin = -1;
        int nivelHojaMax = -1;
        boolean encontradoHueco = false;
        boolean seTerminoLaZonaCompleta = false;

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            int nivel = niveles.poll();

            boolean esHoja = (actual.izquierdo == null && actual.derecho == null);

            if (esHoja) {
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
    public class sexo {
        public void add(T dato) {
            raiz = addRecursivo(raiz, dato);
        }
    }

    private Nodo<T> addRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = addRecursivo(nodo.izquierdo, dato);
        } else if (dato.compareTo(nodo.dato) > 0) {
            nodo.derecho = addRecursivo(nodo.derecho, dato);
        }
        return nodo;
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
}

