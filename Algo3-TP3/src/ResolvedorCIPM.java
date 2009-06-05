import java.util.Stack;
import java.util.LinkedList;
import java.util.ListIterator;

public class ResolvedorCIPM {

	public ResolvedorCIPM(GrafoNPonderados unGrafo) {
		elGrafo = unGrafo;
		conjuntoSolucion = null;
		pesoMaximo = 0;
	}

	public void resolverExacto() {
		Stack pila = new Stack();

		for (int superNodo = 1; superNodo <= elGrafo.cantNodos(); superNodo++) {
			int nodoActual = superNodo;
			pila.push(new Integer(nodoActual));
			int acumuladorDePeso = elGrafo.pesoNodo(nodoActual);
			while(!pila.empty()) {
				for (int nodo = nodoActual+1; nodo <= elGrafo.cantNodos(); nodo++) {
					ListIterator iterpila = pila.listIterator();
					boolean hayAdyacente = false;
					while(iterpila.hasNext() && !hayAdyacente) {
						hayAdyacente = elGrafo.sonAdyacentes(((Integer)iterpila.next()).intValue(), nodo);
					}
					if(!hayAdyacente) {
						pila.push(new Integer(nodo));
						acumuladorDePeso += elGrafo.pesoNodo(nodo);
					}
				}
				if (acumuladorDePeso > pesoMaximo) {
					conjuntoSolucion = new LinkedList(pila);
					pesoMaximo = acumuladorDePeso;
				}
				nodoActual = ((Integer)pila.pop()).intValue();
				acumuladorDePeso -= elGrafo.pesoNodo(nodoActual);
			}
		}
	}

	public LinkedList dameConjuntoSolucion() {
		return conjuntoSolucion;
	}
	
	public int damePesoMaximo() {
		return pesoMaximo;
	}

	private GrafoNPonderados elGrafo;
	private LinkedList conjuntoSolucion;
	private int pesoMaximo;

}