public class ArbolBinarioDeBusqueda {
    private K clave;
    private V valor;

    public int getAltura() {
        if (raiz == null) {
            return 0
        }
        int grado = 0;
        int hijos = 0;
        if (raiz.izquierdo != null){hijos++}
        if (raiz.derecho != null){hijos++}
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
        public boolean isArbolHomogeneo() {
            return verificarHomogeneidad(raiz, new int[]{-1}); // usamos array para pasar "por referencia"
        }

        // Recursión para verificar homogeneidad
        private boolean verificarHomogeneidad(Nodo nodo, int[] gradoEsperado){
            if (nodo == null) {
                return true
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
    }
