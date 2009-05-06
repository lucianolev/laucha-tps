set terminal png enhanced large
set title "Buscar diamante en grafos aleatorios (cantidad de nodos de 1 a 500)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de nodos (n)"
set output "Tp2Ej2-Complejidad.png"
plot "Tp2Ej2-Complejidad.out" using 1:2 with lines title "T(n)" lc rgb "grey", x*x*x/15 title "n*n*n" lc rgb "black"