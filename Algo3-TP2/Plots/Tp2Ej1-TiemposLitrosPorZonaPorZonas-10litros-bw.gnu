set terminal png enhanced large
set title "Calculo de litros por zona (con 10 litros de 1 a 10000 zonas)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de Zonas"
set yrange [0:1000]
set output "Tp2Ej1-TiemposLitrosPorZonaPorZonas-10litros-bw.png"
plot "Tp2Ej1-Tiempos-10litros.out" using 1:4 with lines title "T(n)" lc rgb "grey", x/29 title "n/29" lc rgb "black"