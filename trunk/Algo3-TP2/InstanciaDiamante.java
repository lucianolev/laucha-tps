import java.lang.Math;

public class InstanciaDiamante {

	public InstanciaDiamante(int paramCantNodos) {
		cantNodos = paramCantNodos;
		adyacencias = new int[cantNodos+1];
		tiempoDeBusqueda = 0;
		matrizAdyacencias
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
	public int[] adyacencias;
	public boolean hayDiamante;
	public int[4] diamanteMinimo;
	public int[][] matrizAdyacencias;
	public long tiempoDeBusqueda;
}