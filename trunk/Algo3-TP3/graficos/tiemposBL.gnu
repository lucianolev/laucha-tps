set terminal png enhanced large
set title "Busqueda Local en grafos aleatorios (cantidad de nodos de 20 a 4000)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de nodos (n)"
set output "tiemposBL.png"
plot "tiemposBL.txt" using 1:2 with lines title "Densidad 0.2" lc rgb "yellow", "tiemposBL.txt" using 1:3 with lines title "Densidad 0.5" lc rgb "orange", "tiemposBL.txt" using 1:4  with lines title "Densidad 0.8" lc rgb "red", x*x*x/10000 title "n^3" lc rgb "black"