set terminal png enhanced large
set title "Armar red de vias (cantidad de locales de 1 a 300, locales fijados 4)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de locales (n)"
set output "Tp2Ej3-Complejidad.png"
plot "Tp2Ej3-Complejidad.out" using 1:2 with lines title "T(n)" lc rgb "grey", x*x*x/2.5 title "n*n*n" lc rgb "black", x*x*70 title "n*n" lc rgb "#888888"