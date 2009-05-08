set terminal png enhanced large
set title "Calculo de litros por zona (de 1 a 1000 zonas, con 1 a 1000 litros)"
set ylabel "Cantidad de Litros" offset 0,-1
set xlabel "Cantidad de Zonas" offset 4,0
unset zlabel
set label "Tiempo de ejecucion (en microsegs)" at -800,40 rotate by 90
set xrange [0:1000]
set yrange [0:1000]
set zrange [0:300]
set xtics offset 1,0
set ytics offset -2,0
set grid
set output "Tp2Ej1-TiemposLitrosPorZona-3d-bw.png"
set isosamples 20
set view 60,330
splot "Tp2Ej1-Tiempos-3d.out" using 1:2:4 with lines title "T(n,m)" lc rgb "black", (x/8+y/72) title "n/8 + m/72" lc rgb "grey"