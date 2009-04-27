set terminal png enhanced large
set title "Fumigacion (de 1 a 10000 zonas con 10 litros)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de Zonas"
set yrange [0:15000]
set output "Tp2Ej1-TiemposFumigacionPorZonas-10litros-bw.png"
plot "Tp2Ej1-TiemposFumigacion-10litros.out" using 1:3 with lines title "T(n)" lc rgb "grey", x/1.15 lc rgb "black"