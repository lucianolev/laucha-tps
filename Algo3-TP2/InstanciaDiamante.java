import java.lang.Math;
import java.util.Stack

public class InstanciaDiamante {

	public InstanciaDiamante(int paramCantNodos) {
		cantNodos = paramCantNodos;
		adyacencias = new int[cantNodos+1];
		tiempoDeBusqueda = 0;
	}

	public void eliminarNodosChicos() {
		//Saco los nodos de grado 0 y 1
		int[] superMapping = new int[cantNodosNuevoGrafo];
		LinkedList[] nuevasAdyacencias = new LinkedList[cantNodosNuevoGrafo];
		int k = 0;
		for(int i = 1; i <= cantNodos; i++) {
			if (adyacencias[i].size() == 1) {
				nodosDeGradoUno[adyacencias[i].getFirst()] = i;
			}
			if (adyacencias[i].size() < 2) {
				adyacencias[i] = null;
			}
		}

		//Borro los adyacentes a los nodos de grado 1 que elimine antes para mantener la consistencia
		for(int i = 1; i <= cantNodos; i++) {
			ListIterator iter = adyacencias[i].listIterator();
			boolean elimino = false;
			while(iter.hasNext() && !elimino) {
				if(iter.next() == nodosGradoUno[i]) {
					iter.remove();
					elimino = true;
				}
			}
		}
	}

	public void armarMatrizDeAdyacencia() {
		matrizAdyacencias = new boolean[cantNodos+1][cantNodos+1];

		for(int i = 0; i <= cantNodos; i++) {
			for(int j = 0; j <= cantNodos; j++) {
				matrizAdyacencias[i][j] = false;
			}
		}

		for(int i = 1; i <= cantNodos; i++) {
			ListIterator iter = adyacencias[i].listIterator();
			while(iter.hasNext()) {
				matrizAdyacencias[i][(int)iter.next()] = true;
			}
		}
	}

	public LinkedList[] crearVecindad(int superNodo) {
		LinkedList[] vecindadDelSuperNodo = new LinkedList[cantNodos+1];

		ListIterator adyacentesASuperNodo = adyacencias[superNodo].listIterator();
		while(adyacentesASuperNodo.hasNext()) {
			int adyacenteASuperNodo = (int)adyacentesASuperNodo.next();
			LinkedList listaAdyacencia = new LinkedList();

			ListIterator adyacentesAAdyacenteASuperNodo = adyacencias[adyacenteASuperNodo].listIterator();
			while(adyacentesAAdyacenteASuperNodo.hasNext()) {
				int adyacenteAAdyacenteASuperNodo = (int)adyacentesAAdyacenteASuperNodo.next();
				if(matrizAdyacencia[adyacenteAAdyacenteASuperNodo][superNodo]) {
					listaAdyacencia.add(adyacenteAAdyacenteASuperNodo);
				}
			}
			vecindadDelSuperNodo[adyacenteASuperNodo] = listaAdyacencia;
		}

		return vecindadDelSuperNodo;
	}

	public int[] buscarDiamanteMinimoEnVecindad(int superNodo, LinkedList[] vecindadDeSuperNodo) {
		Stack pilaDFS = new Stack();
		diamanteMinimo = null;

		ListIterator adyacentesASuperNodo = adyacencias[superNodo].listIterator();
		boolean marcados[cantNodo+1];
		while(adyacentesASuperNodo.hasNext()) {
			int nodoActual = (int)adyacentesASuperNodo.next();
			if(!marcados[nodoActual]) {
				pilaDFS.push(nodoActual);
				LinkedList nodosCompConexa = new LinkedList();
				int nodoMinimoCompConexa = cantNodos;
				int sumaGradosCompConexa = 0;
				while(!pilaDFS.empty()) {
					nodoActual = pilaDFS.pop();
					marcados[nodoActual] = true;
					sumaGradosCompConexa =+ vecindadDeSuperNodo[nodoActual].size(); //grado(nodoActual)
					nodosCompConexa.add(nodoActual);
					if (nodoActual < nodoMinimoCompConexa) {
						nodoMinimoCompConexa = nodoActual;
					}
					ListIterator adyacentesANodoActtual = vecindadDeSuperNodo[nodoActual].listIterator();
					while(adyacentesANodoActtual.hasNext()) {
						int adyacenteANodoActual = (int)adyacentesANodoActtual.next();
						if(!marcados[adyacenteANodoActual]) {
							sumaGradosCompConexa =+ vecindadDeSuperNodo[adyacenteANodoActual].size();
							pilaDFS.push();
						}
					}
				}
				if (sumaGradosCompConexa != (nodosCompConexa.size()*(nodosCompConexa.size()-1))) {
					if(diamanteMinimo == null || nodoMinimoCompConexa <= Arrays.sort(diamanteMinimo)[0]) {
						diamante = buscarDiamanteMinimoDeCompConexa(superNodo, vecindadDeSuperNodo, nodosCompConexa);
					}
					if(diamanteMinimo == null || compararDiamantes(diamante, diamanteMinimo) {
						diamanteMinimo = diamante;
					}
				}
			}
		}

		return diamanteMinimo;
	}

	public int[] buscarDiamanteMinimoDeCompConexa(int superNodo, LinkedList[] vecindadDeSuperNodo, LinkedList nodosCompConexa) {
		int nodo1 = superNodo;
		
		int nodo2 = cantNodos;
		ListIterator iterNodosCompConexa = nodosCompConexa.listIterator();
		while(iterNodosCompConexa.hasNext()) {
			int nodoActual = (int)iterNodosCompConexa.next();
			if ((vecindadDeSuperNodo[nodoActual].size() < nodosCompConexa.size()-1) 
				&& (nodoActual < nodo2)) {
				nodo2 = nodoActual;
			}
		}

		int nodo3 = cantNodos;
		int nodo4 = cantNodos;
		int nodoChico;
		int nodoGrande;
		ListIterator adyacentesANodo2 = vecindadDeSuperNodo[nodo2].listIterator();
		while(adyacentesANodo2.hasNext()) {
			int adyacenteANodo2 = (int)adyacentesANodo2.next();
			ListIterator adyacentesAAdyacenteANodo2 = vecindadDeSuperNodo[adyacenteANodo2].listIterator();
			while(adyacentesAAdyacenteANodo2.hasNext()) {
				int adyacenteAAdyacenteANodo2 = (int)adyacentesANodo2.next();
				if(!matrizAdyacencias[adyacenteAAdyacenteANodo2][nodo2]) {
					if (adyacenteAAdyacenteANodo2 < adyacenteANodo2) {
						nodoChico = adyacenteAAdyacenteANodo2;
						nodoGrande = adyacenteANodo2;
					} else {
						nodoChico = adyacenteANodo2;
						nodoGrande = adyacenteAAdyacenteANodo2;
					}
					if(nodoChico < nodo3) {
						nodo3 = nodoChico;
						nodo4 = nodoGrande;
					} else if (nodoChico == nodo3) {
						if(nodoGrande < nodo4) {
							nodo4 = nodoGrande;
						}
					}
				}
			}
		}

		int[] diamanteMinimo = new int[4];
		diamanteMinimo[0] = nodo1;
		diamanteMinimo[1] = nodo2;
		diamanteMinimo[2] = nodo3;
		diamanteMinimo[3] = nodo4;

		return diamanteMinimo;
	}

	//retorno true si diamante1 < diamante2
	static boolean compararDiamantes(int diamante1[4], int diamante2[4]) {
		Arrays.sort(diamante1);
		Arrays.sort(diamante2);

		if(diamante1[0] != diamante2[0]) {
			if (diamante1[0] < diamante2[0]) {
				return true;
			}
		} else if(diamante1[1] != diamante2[1]) {
			if (diamante1[1] < diamante2[1]) {
				return true;
			}
		} else if(diamante1[2] != diamante2[2]) {
			if (diamante1[2] < diamante2[2]) {
				return true;
			}
		} else if(diamante1[3] != diamante2[3]) {
			if (diamante1[3] < diamante2[3]) {
				return true;
			}
		}
		return false;
	}

// 	public void generarInstanciaRandom(int paramZonas, int paramLitros, int rangoRandom) {
// 		zonas = paramZonas;
// 		litros = paramLitros;
// 		mosquitosMuertos = new int[zonas][litros+1];
// 		for (int i = 0; i < paramZonas; i++) {
// 			mosquitosMuertos[i][0] = 0; //el litro 0 es 0 para cada zona
// 			for (int j = 1; j <= paramLitros; j++) {
// 				mosquitosMuertos[i][j] = (int)(Math.random()*rangoRandom);
// 			}
// 		}
// 	}

	public int cantNodos;
	public LinkedList[] adyacencias;
	public int[] superMapping;
	public boolean hayDiamante;
	public int[] diamanteMinimo;
	public int[][] matrizAdyacencias;
	public long tiempoDeBusqueda;
}