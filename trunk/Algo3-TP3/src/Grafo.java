import java.util.*;

public class GrafoNPonderados {

	public GrafoNPonderados(LinkedList[] pListaDeAdyacencias, int pCantNodos, int[] pPesoNodos) {
		listaDeAdyacencias = pListaDeAdyacencias;
		cantNodos = pCantNodos;
		pesoNodos = pPesoNodos;
		armarMatrizAdyacencias();
	}

	private void armarMatrizAdyacencias() {
		matrizAdyacencias = new boolean[cantNodos+1][cantNodos+1];
		for (int i = 1; i <= cantNodos; i++) {
			for (int j = 1; j <= cantNodos; j++) {
				matrizAdyacencias[i][j] = false;
			}
		}
		for (int i = 1; i <= cantNodos; i++) {
			ListIterator iter = listaDeAdyacencias[i].listIterator();
			while (iter.hasNext()) {
				matrizAdyacencias[i][iter.next()] = true;
			}
		}
	}

	public boolean sonAdyacentes(int nodo1, int nodo2) { 
		return matrizAdyacencias[nodo1][nodo2];	
	}

	public int pesoNodo(int nodo) {
		return pesoNodos[nodo];
	}

	public int cantNodos() {
		return cantNodos;
	}
	
	private boolean[][] matrizAdyacencias;
	private LinkedList[] listaDeAdyacencias;		Grafo elgrafo = lector.dameGrafo();

	private int[] pesoNodos;
	private int cantNodos;

}