# Diferencias entre PruebaSuma y PruebaSuma2

## Suma:
Ninguna diferencia entre ambos programas de prueba. El intervalo es el mismo así que la suma es la misma, sin importar el orden en el que se inserten.
Además, se obtiene la misma suma independientemente de cómo se recorra el árbol, como debería ser.

## Altura:
En el primer programa de prueba la altura del árbol es 128 porque hay una única rama. Esto es porque añadimos los elementos en orden, es decir, el siguiente elemento a añadir siempre es mayor que el anterior y va a la derecha siempre.
En el segundo programa la altura del árbol es distinta cada vez que se ejecuta, ya que los elementos se añaden de manera aleatoria.

## Camino:
En el primer programa el camino es [0, 1, ..., 110] por el mismo motivo que en el anterior apartado.
En el segundo programa el camino es un array distinto cada vez que se ejecuta por el mismo motivo que en el anterior apartado.
