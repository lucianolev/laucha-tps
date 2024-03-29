set terminal png enhanced large
set title "Algoritmo exacto para grafos aleatorios (cantidad de nodos 1 a 36)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de nodos (n)"
set xrange [26:36]
set yrange [0:2e+08]
set xtics 1, 2, 36
set output "tiemposExacto.png"
plot "tiemposExacto.txt" using 1:2 with lines title "Densidad 0.2" lc rgb "yellow", "tiemposExacto.txt" using 1:3 with lines title "Densidad 0.5" lc rgb "orange", "tiemposExacto.txt" using 1:4 with lines title "Densidad 0.8" lc rgb "red", (2**x)/80 with lines title "2**n/80" lc rgb "black"