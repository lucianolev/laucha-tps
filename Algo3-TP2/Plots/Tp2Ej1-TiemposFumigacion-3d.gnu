set terminal png enhanced large
set ylabel "Cantidad de Litros" offset 0,-1
set xlabel "Cantidad de Zonas" offset 4,0
unset zlabel
set label "Tiempo de ejecucion (en microsegs)" at -800,40 rotate by 90
set xrange [0:1000]
set yrange [0:1000]
set zrange [0:10000000]
set xtics offset 1,0
set ytics offset -2,0
set grid
set output "Tp2Ej1-TiemposFumigacion-3d-bw.png"
set isosamples 20
set view 60,330
splot "Tp2Ej1-Tiempos-3d.out" using 1:2:3 with lines title "T(n,m)", (x*(y**2)/200) title "n * m * m"