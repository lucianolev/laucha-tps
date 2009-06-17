import java.util.LinkedList;
import java.util.ListIterator;

public class GrafoNPonderados {

	//O(n*n)
	public GrafoNPonderados(int pCantNodos, LinkedList[] pAdyacencias, int[] pPesoNodos) {
		adyacencias = pAdyacencias;
		cantNodos = pCantNodos;
		pesoNodos = pPesoNodos;
		armarMatrizAdyacencias();
	}
	
	//genera aleatoriamente un grafo de pCantNodos
	public GrafoNPonderados(int pCantNodos) {
		cantNodos = pCantNodos;
		pesoNodos = new int[cantNodos+1];
		
		adyacencias = new LinkedList[cantNodos+1];
		for (int i = 1; i <= cantNodos; i++) { 
			adyacencias[i] = new LinkedList();
			pesoNodos[i] = (int)(Math.random()*(cantNodos)+1)*10;
		}

		for (int i = 1; i <= cantNodos; i++) {
			for(int j = 0; j < (int)(Math.random()*((cantNodos-1)+1)/3); j++) {
				int nodo = (int)(Math.random()*(cantNodos));
				if(!adyacencias[i].contains(Integer.valueOf(nodo)) && nodo != i && nodo != 0) {
					adyacencias[i].add(Integer.valueOf(nodo));
					if(!adyacencias[nodo].contains(Integer.valueOf(i))) {
						adyacencias[nodo].add(Integer.valueOf(i));
					}
				}
			}
		}
		
		armarMatrizAdyacencias();
	}
	
	//O(n*n)
	public GrafoNPonderados(GrafoNPonderados otroGrafo) {
		cantNodos = otroGrafo.cantNodos;
	
		adyacencias = new LinkedList[cantNodos+1];
		for(int i = 1; i <= cantNodos; i++) {
			ListIterator iter = otroGrafo.adyacencias[i].listIterator();
			adyacencias[i] = new LinkedList();
			while(iter.hasNext()) {
				adyacencias[i].add(new Integer(((Integer)iter.next()).intValue()));
			}
		}
		
		matrizAdyacencias = new boolean[cantNodos+1][cantNodos+1];
		for(int i = 1; i <= cantNodos; i++) {
			for(int j = 1; j <= cantNodos; j++) {
				matrizAdyacencias[i][j] = otroGrafo.matrizAdyacencias[i][j];
			}
		}

		pesoNodos = new int[cantNodos+1];
		for(int i = 1; i <= cantNodos; i++) {
			pesoNodos[i] = otroGrafo.pesoNodos[i];
		}
	}
	
	public LinkedList[] adyacencias() {	return adyacencias;	}
	
	public boolean[][] matrizAdyacencias() { return matrizAdyacencias; }
	
	public int cantNodos() { return cantNodos; }
	
	public int[] pesoNodos() { return pesoNodos; }
	
	public boolean hayNodos() { return ((cantNodos - nodosBorrados) > 0); }

	//O(n*n)
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

	public boolean existeNodo(int nodo) {
		return (adyacencias[nodo] != null);
	}
	
	//nodo1 y nodo2 deben pertenecer al grafo
	public boolean sonAdyacentes(int nodo1, int nodo2) { 
		return matrizAdyacencias[nodo1][nodo2];	
	}
	
	public LinkedList adyacentes(int nodo) {
		return adyacencias[nodo];
	}
	
	public int gradoNodo(int nodo) {
		return adyacencias[nodo].size();
	}
	
	public int pesoNodo(int nodo) {
		return pesoNodos[nodo];
	}
	
	public void borrarNodoGradoCero(int nodo) {
		if (gradoNodo(nodo) == 0) {
			adyacencias[nodo] = null;
			nodosBorrados++;
		} else {
			System.out.println("borrarNodoGradoCero: El nodo no tiene grado cero!");
		}
	}
	
	//O(m + n)
	public void borrarVecindad(int superNodo) {
		for(int nodo = 1; nodo <= cantNodos; nodo++) {
			if (nodo == superNodo || sonAdyacentes(nodo, superNodo)) {
				adyacencias[nodo] = null;
				nodosBorrados++;
			} else if(adyacencias[nodo] != null) {
				ListIterator iterAdyacentes = adyacencias[nodo].listIterator();
				while(iterAdyacentes.hasNext()) {
					int nodo2 = ((Integer)iterAdyacentes.next()).intValue();
					if(sonAdyacentes(nodo2, superNodo)) {
						iterAdyacentes.remove();
					}
				}
			}
		}
	}
	
	public int pesoVecindad(int superNodo) {
		int pesoAcum = 0;
		ListIterator iterAdyacentes = adyacencias[superNodo].listIterator();
		while(iterAdyacentes.hasNext()) {
			int nodo = ((Integer)iterAdyacentes.next()).intValue();
			pesoAcum += pesoNodo(nodo);
		}
		return pesoAcum;
	}
	
	public void mostrarGrafo() {
		System.out.println(cantNodos);
		for (int i = 1; i <= cantNodos; i++) {
			Integer[] arrayAdy = new Integer[adyacencias[i].size()]; 
			adyacencias[i].toArray(arrayAdy);
			String salida = pesoNodos[i]+" ";
			for(int j = 0; j < adyacencias[i].size(); j++){
				salida = salida+arrayAdy[j]+" ";
			}
			System.out.println(salida);
		}
	}
	
	private boolean[][] matrizAdyacencias;
	private LinkedList[] adyacencias;
	private int[] pesoNodos;
	private int cantNodos;
	private int nodosBorrados;

}