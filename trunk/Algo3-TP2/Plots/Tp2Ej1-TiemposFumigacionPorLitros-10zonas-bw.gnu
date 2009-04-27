set terminal png enhanced large
set title "Fumigacion (de 1 a 10000 litros en 10 zonas)"
set ylabel "Tiempo de Ejecucion (en microsegs)"
set xlabel "Cantidad de Litros"
set yrange [0:8000000]
set ytics 0,1000000,8000000
set output "Tp2Ej1-TiemposFumigacionPorLitros-10zonas-bw.png"
plot "Tp2Ej1-Tiempos-10zonas.out" using 2:3 with lines title "T(n)" lc rgb "grey", x**2/19 title "n^2/19" lc rgb "black"