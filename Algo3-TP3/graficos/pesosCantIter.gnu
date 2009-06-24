set terminal png enhanced large
set title "Iteraciones GRASP para grafo 500 nodos"
set ylabel "Peso"
set xlabel "Cantidad de iteraciones"
set output "pesosCantIter.png"
plot "pesosCantIter.txt" using 1:2 with lines title "Grafo" lc rgb "black"