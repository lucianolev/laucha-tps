import java.util.LinkedList;
import java.util.ListIterator;

public class GrafoNPonderados {

	public GrafoNPonderados(int pCantNodos, LinkedList[] pAdyacencias, int[] pPesoNodos) {
		adyacencias = pAdyacencias;
		cantNodos = pCantNodos;
		pesoNodos = pPesoNodos;
		armarMatrizAdyacencias();
	}
	
	public GrafoNPonderados(GrafoNPonderados otroGrafo) {
		adyacencias = otroGrafo.adyacencias();
		cantNodos = otroGrafo.cantNodos();
		matrizAdyacencias = otroGrafo.matrizAdyacencias();
		pesoNodos = otroGrafo.pesoNodos();
	}
	
	public LinkedList[] adyacencias() {	return adyacencias;	}
	
	public boolean[][] matrizAdyacencias() { return matrizAdyacencias; }
	
	public int cantNodos() { return cantNodos; }
	
	public int[] pesoNodos() { return pesoNodos; }

	private void armarMatrizAdyacencias() {
		matrizAdyacencias = new boolean[cantNodos+1][cantNodos+1];
		for (int i = 1; i <= cantNodos; i++) {
			for (int j = 1; j <= cantNodos; j++) {
				matrizAdyacencias[i][j] = false;
			}
		}
		for (int i = 1; i <= cantNodos; i++) {
			ListIterator iter = adyacencias[i].listIterator();
			while (iter.hasNext()) {
				matrizAdyacencias[i][((Integer)iter.next()).intValue()] = true;
			}
		}
	}

	//nodo1 y nodo2 deben pertenecer al grafo
	public boolean sonAdyacentes(int nodo1, int nodo2) { 
		return matrizAdyacencias[nodo1][nodo2];	
	}
	
	public int gradoNodo(int nodo) {
		return adyacencias[nodo].size();
	}
	
	public int pesoNodo(int nodo) {
		return pesoNodos[nodo];
	}
	
	private boolean[][] matrizAdyacencias;
	private LinkedList[] adyacencias;
	private int[] pesoNodos;
	private int cantNodos;

}