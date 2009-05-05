import java.util.Stack;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Arrays;

public class InstanciaDiamante {

	public InstanciaDiamante(int paramCantNodos) {
		cantNodos = paramCantNodos;
		adyacencias = new LinkedList[cantNodos+1];
		tiempoDeBusqueda = 0;
	}

	public void eliminarNodosChicos() {
		//Saco los nodos de grado 0 y 1
		int[] nodosDeGradoUno = new int[cantNodos+1];
		for(int i = 1; i <= cantNodos; i++) {
			if (adyacencias[i].size() == 1) {
				nodosDeGradoUno[((Integer)adyacencias[i].getFirst()).intValue()] = i;
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
				if(((Integer)iter.next()).intValue() == nodosDeGradoUno[i]) {
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
				matrizAdyacencias[i][((Integer)iter.next()).intValue()] = true;
			}
		}
	}

	public LinkedList[] crearVecindad(Integer superNodo) {
		LinkedList[] vecindadDelSuperNodo = new LinkedList[cantNodos+1];

		ListIterator adyacentesASuperNodo = adyacencias[superNodo.intValue()].listIterator();
		while(adyacentesASuperNodo.hasNext()) {
			Integer adyacenteASuperNodo = ((Integer)adyacentesASuperNodo.next());
			LinkedList listaAdyacencia = new LinkedList();

			ListIterator adyacentesAAdyacenteASuperNodo = adyacencias[adyacenteASuperNodo.intValue()].listIterator();
			while(adyacentesAAdyacenteASuperNodo.hasNext()) {
				Integer adyacenteAAdyacenteASuperNodo = (Integer)adyacentesAAdyacenteASuperNodo.next();
				if(matrizAdyacencias[adyacenteAAdyacenteASuperNodo.intValue()][superNodo.intValue()]) {
					listaAdyacencia.add(adyacenteAAdyacenteASuperNodo);
				}
			}
			vecindadDelSuperNodo[adyacenteASuperNodo.intValue()] = listaAdyacencia;
		}

		System.out.println("Vecindad de "+superNodo.toString());
		Diamante.mostrarAdyacencias(vecindadDelSuperNodo, cantNodos);
		System.out.println("----------------------");

		return vecindadDelSuperNodo;
	}

	public Integer[] buscarDiamanteMinimoEnVecindad(Integer superNodo, LinkedList[] vecindadDeSuperNodo) {
		Stack pilaDFS = new Stack();
		diamanteMinimo = null;

		ListIterator adyacentesASuperNodo = adyacencias[superNodo.intValue()].listIterator();
		boolean[] marcados = new boolean[cantNodos+1];
		while(adyacentesASuperNodo.hasNext()) {
			Integer nodoActual = (Integer)adyacentesASuperNodo.next();
			if(!marcados[nodoActual.intValue()]) {
				pilaDFS.push(nodoActual);
				LinkedList nodosCompConexa = new LinkedList();
				Integer nodoMinimoCompConexa = new Integer(cantNodos);
				int sumaGradosCompConexa = 0;
				while(!pilaDFS.empty()) {
					nodoActual = (Integer)pilaDFS.pop();
					marcados[nodoActual.intValue()] = true;
					sumaGradosCompConexa =+ vecindadDeSuperNodo[nodoActual.intValue()].size(); 
					nodosCompConexa.add(nodoActual);
					if (nodoActual.intValue() < nodoMinimoCompConexa.intValue()) {
						nodoMinimoCompConexa = nodoActual;
					}
					ListIterator adyacentesANodoActual = vecindadDeSuperNodo[nodoActual.intValue()].listIterator();
					while(adyacentesANodoActual.hasNext()) {
						Integer adyacenteANodoActual = (Integer)adyacentesANodoActual.next();
						if(!marcados[adyacenteANodoActual.intValue()]) {
							sumaGradosCompConexa =+ vecindadDeSuperNodo[adyacenteANodoActual.intValue()].size();
							pilaDFS.push(adyacenteANodoActual);
						}
					}
				}
				if (sumaGradosCompConexa != (nodosCompConexa.size()*(nodosCompConexa.size()-1))) {
					if (diamanteMinimo == null) {
						diamanteMinimo = buscarDiamanteMinimoDeCompConexa(superNodo, vecindadDeSuperNodo, nodosCompConexa);
					}
					if(nodoMinimoCompConexa.intValue() <= diamanteMinimo[0].intValue()) {
						Integer[] diamante = buscarDiamanteMinimoDeCompConexa(superNodo, vecindadDeSuperNodo, nodosCompConexa);
						if(diamante != null && compararDiamantes(diamante, diamanteMinimo)) {
							diamanteMinimo = diamante;
						}
					}
				}
			}
		}
		if (diamanteMinimo == null) {
			System.out.println("En la vecindad del superNodo "+superNodo.intValue()+" no hay diamante");
		}

		return diamanteMinimo;
	}

	public Integer[] buscarDiamanteMinimoDeCompConexa(Integer superNodo, LinkedList[] vecindadDeSuperNodo, LinkedList nodosCompConexa) {
		Integer nodo1 = superNodo;
		
		Integer nodo2 = new Integer(cantNodos);
		ListIterator iterNodosCompConexa = nodosCompConexa.listIterator();
		while(iterNodosCompConexa.hasNext()) {
			Integer nodoActual = (Integer)iterNodosCompConexa.next();
			if ((vecindadDeSuperNodo[nodoActual.intValue()].size() < nodosCompConexa.size()-1) 
				&& (nodoActual.intValue() < nodo2.intValue())) {
				nodo2 = nodoActual;
			}
		}

		Integer nodo3 = new Integer(cantNodos);
		Integer nodo4 = new Integer(cantNodos);
		Integer nodoChico;
		Integer nodoGrande;
		ListIterator adyacentesANodo2 = vecindadDeSuperNodo[nodo2.intValue()].listIterator();
		while(adyacentesANodo2.hasNext()) {
			Integer adyacenteANodo2 = (Integer)adyacentesANodo2.next();
			ListIterator adyacentesAAdyacenteANodo2 = vecindadDeSuperNodo[adyacenteANodo2.intValue()].listIterator();
			while(adyacentesAAdyacenteANodo2.hasNext()) {
				Integer adyacenteAAdyacenteANodo2 = (Integer)adyacentesAAdyacenteANodo2.next();
				if(!matrizAdyacencias[adyacenteAAdyacenteANodo2.intValue()][nodo2.intValue()]) {
					if (adyacenteAAdyacenteANodo2.intValue() < adyacenteANodo2.intValue()) {
						nodoChico = adyacenteAAdyacenteANodo2;
						nodoGrande = adyacenteANodo2;
					} else {
						nodoChico = adyacenteANodo2;
						nodoGrande = adyacenteAAdyacenteANodo2;
					}
					if(nodoChico.intValue() < nodo3.intValue()) {
						nodo3 = nodoChico;
						nodo4 = nodoGrande;
					} else if (nodoChico.intValue() == nodo3.intValue()) {
						if(nodoGrande.intValue() < nodo4.intValue()) {
							nodo4 = nodoGrande;
						}
					}
				}
			}
		}

		Integer[] diamanteMinimo = new Integer[4];
		diamanteMinimo[0] = nodo1;
		diamanteMinimo[1] = nodo2;
		diamanteMinimo[2] = nodo3;
		diamanteMinimo[3] = nodo4;
		Arrays.sort(diamanteMinimo);

		return diamanteMinimo;
	}

	//retorno true si diamante1 < diamante2
	public static boolean compararDiamantes(Integer[] diamante1, Integer[] diamante2) {
		if(diamante1[0] != diamante2[0]) {
			if (diamante1[0].intValue() < diamante2[0].intValue()) {
				return true;
			}
		} else if(diamante1[1].intValue() != diamante2[1].intValue()) {
			if (diamante1[1].intValue() < diamante2[1].intValue()) {
				return true;
			}
		} else if(diamante1[2].intValue() != diamante2[2].intValue()) {
			if (diamante1[2].intValue() < diamante2[2].intValue()) {
				return true;
			}
		} else if(diamante1[3].intValue() != diamante2[3].intValue()) {
			if (diamante1[3].intValue() < diamante2[3].intValue()) {
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
	public boolean hayDiamante;
	public Integer[] diamanteMinimo;
	public boolean[][] matrizAdyacencias;
	public long tiempoDeBusqueda;
}