set terminal png enhanced large
set title "Algoritmo GRASP en grafos aleatorios (cantidad de nodos de 10 a 800)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de nodos (n)"
set output "tiemposGrasp.png"
plot "tiemposGrasp.txt" using 1:2 with lines title "Densidad 0.2" lc rgb "yellow", "tiemposGrasp.txt" using 1:3 with lines title "Densidad 0.5" lc rgb "orange", "tiemposGrasp.txt" using 1:4  with lines title "Densidad 0.8" lc rgb "red", x*x*x/20 title "n*n*n/20" lc rgb "black"