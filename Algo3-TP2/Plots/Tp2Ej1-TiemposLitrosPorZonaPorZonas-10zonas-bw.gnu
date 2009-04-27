set terminal png enhanced large
set title "Calculo de litros por zona (de 1 a 10000 litros en 10 zonas)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de Litros"
set yrange [0:150]
set output "Tp2Ej1-TiemposLitrosPorZonaPorZonas-10zonas-bw.png"
plot "Tp2Ej1-Tiempos-10zonas.out" using 2:4 with lines title "T(n)" lc rgb "grey", x/80 title "n/80" lc rgb "black"