set ylabel "Cantidad de Litros"
set xlabel "Cantidad de Zonas"
set zlabel "Tiempo de ejecucion (en microsegs)"
set zrange [0:10000000]
splot "Tp2Ej1-TiemposFumigacion.out" using 1:2:3 with lines, x*(y**2)/400
 
