# Pruebas #

Hay que hacer una funcion q nos genere 9 archivos con un tipo de grafo cada uno (variando tamaño y densidad) y cada archivo con 10 grafos distintos o algo asi.

## heuristica constructiva ##

(para cada prueba con 20 grafos distintos de ese tipo)

  * Probar las 4 heuristicas con grafos de tamaño 50, 300 y 700 con densidad 0.2, 0.5 y 0.8. Columnas: Peso con heuristicas (4 heuristicas, una col x cada una), peso exacto (solo para los de 50)

Concluimos que heurística es mejor.

## búsqueda local ##

(para cada prueba con 20 grafos distintos de ese tipo)

  * Probar las 2 heuristicas locales para los grafos anteriores.
Columnas con: peso/interaciones para Heuristica 1, peso/interaciones para herusistca 2, peso exacto (solo para los de 50)

Concluimos mejor busqueda local y cota de iteraciones.

## GRASP ##

  * Tres tablas, una para 30 iteraciones, otra para 100 y otra para 300:
Para cada tabla: Probar con 10 alfas distintos para los grafos anteriores (10 veces con cada grafo y sacar promedio). Cada celda son los pesos.

Concluimos el mejor alfa.

  * Probamos variar la cantidad de iteraciones para los 9 grafos. Vemos como depende de m y de n la cantidad de iteraciones.

Concluimos como seria la formula q determina la cantidad de iteraciones.