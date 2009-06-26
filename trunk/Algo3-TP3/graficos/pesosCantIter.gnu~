set terminal png enhanced large
set title "Iteraciones GRASP para grafo 500 nodos"
set ylabel "Peso"
set xlabel "Cantidad de iteraciones"
set output "pesosCantIter.png"
plot "pesosCantIter.txt" using 1:2 with lines title "HC peso/grado" lc rgb "black", "pesosCantIter.txt" using 1:3 with lines title "HC grado" lc rgb "grey"