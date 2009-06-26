set terminal png enhanced large
set title "Iteraciones GRASP para grafo 500 nodos"
set ylabel "Peso"
set xlabel "Cantidad de iteraciones"
set output "pruebaCantIter.png"
plot "pruebaCantIter.txt" using 1:2 with lines title "" lc rgb "black"