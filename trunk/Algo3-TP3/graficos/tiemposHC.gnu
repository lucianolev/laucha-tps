set terminal png enhanced large
set title "H. Constructiva en grafos aleatorios (cantidad de nodos de 10 a 1400)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de nodos (n)"
set output "tiemposHC.png"
plot "tiemposHC.txt" using 1:2 with lines title "Densidad 0.2" lc rgb "yellow", "tiemposHC.txt" using 1:3 with lines title "Densidad 0.5" lc rgb "orange", "tiemposHC.txt" using 1:4 with lines title "Densidad 0.8" lc rgb "red", x*55 title "n*55" lc rgb "black"