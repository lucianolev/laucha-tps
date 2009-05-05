import java.lang.Math;

public class InstanciaDiamante {

	public InstanciaDiamante(int paramCantNodos) {
		cantNodos = paramCantNodos;
		adyacencias = new int[cantNodos+1];
		tiempoDeBusqueda = 0;
	}

	public void eliminarNodosChicos() {
		int cantNodosNuevoGrafo = 0;
		for(int i = 1; i <= cantNodos; i++) {
			if (adyacencias[i].size > 1) {
				cantNodosNuevoGrafo++;
			}
		}
		LinkedList[] nuevasAdyacencias = new LinkedList[cantNodosNuevoGrafo];
		for(int i = 1; i < cantNodos; i++) {
			if (adyacencias[i].size() > 1)
				nuevasAdyacencias[k] = adyacencias[i];
// 				ListIterator iter = adyacencias[i].listIterator();
// 				boolean elimino = false;
// 				while(iter.hasNext() && !elimino) {
// 					if(iter.next() == nodosGradoUno[i]) {
// 						iter.remove();
// 						elimino = true;
// 					}
// 				}
		}
	}

	public void armarMatrizDeAdyacencia() {
		matrizAdyacencias = new int[][]
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
	public int[4] diamanteMinimo;
	public int[][] matrizAdyacencias;
	public int[] superMapping;
	public long tiempoDeBusqueda;
}