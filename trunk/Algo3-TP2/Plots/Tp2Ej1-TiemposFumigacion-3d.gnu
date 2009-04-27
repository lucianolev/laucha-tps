set terminal png enhanced large
set ylabel "Cantidad de Litros" rotate by -45 left
set xlabel "Cantidad de Zonas" rotate by 45
set zlabel "Tiempo de ejecucion (en microsegs)"
set xrange [0:1000]
set yrange [0:1000]
set zrange [0:5000000]
set grid
set view 45,50
set output "Tp2Ej1-TiemposFumigacion-3d-bw.png"
splot "Tp2Ej1-Tiempos-3d.out" using 1:2:3 with lines, x*(y**2)/200
 
