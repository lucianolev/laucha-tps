import java.util.LinkedList;
import java.util.ListIterator;

public class InstanciaRedAstor {

	public InstanciaRedAstor() {
		cantLocales = 0;
		cantParesAstor = 0;
		tiempoAlgoritmo = 0;
		costoProduccion = 0;
		compConexaActual = 1;
		componentePorNodo = null;
		magia = null;
		aristasPorAgregar = null;
		matrizPesos = null;
		aristasAstor = null;
		red = null;
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

	public void crearListaAristas() {
		aristasPorAgregar = new LinkedList();
		for(int i = 1; i < cantLocales; i++) {
			for(int j = 0; j < i; j++) {
				Arista nuevaArista = new Arista(i,j,matrizPesos[i][j]);
				aristasPorAgregar.add(nuevaArista);
			}
		}
	}

	public void crearListaAstor() {
		ListIterator iter = aristasAstor.listIterator();
		Arista aristaActual = new Arista();
		while(iter.hasNext()) {
			aristaActual = (Arista)iter.next();
			aristaActual.peso = matrizPesos[aristaActual.nodo1][aristaActual.nodo2];
			iter.set(aristaActual);
		}
	}

	public void meterArista(Dupla dupla) {
		if (componentePorNodo[dupla.prim] == 0 && componentePorNodo[dupla.seg] == 0) {
			componentePorNodo[dupla.prim] = compConexaActual;
			componentePorNodo[dupla.seg] = compConexaActual;
			magia[componentePorNodo[dupla.prim]] = compConexaActual;
			compConexaActual++;
		}

		if (componentePorNodo[dupla.prim] == 0 && componentePorNodo[dupla.seg] != 0) {
			componentePorNodo[dupla.prim] = componentePorNodo[dupla.seg];
		}

		if (componentePorNodo[dupla.prim] != 0 && componentePorNodo[dupla.seg] == 0) {
			componentePorNodo[dupla.seg] = componentePorNodo[dupla.prim];
		}
		
		//Si llego aca es porque tengo que unir las componentes conexas
		int componenteConexaUnida = magia[componentePorNodo[dupla.prim]];
		int componenteConexaACambiar = magia[componentePorNodo[dupla.seg]];

		for(int i = 0; i < cantLocales; i++) {
			if(magia[i] == componenteConexaACambiar) {
				magia[i] = componenteConexaUnida;
			}
		}
		red.add(dupla);
	}

	public boolean sePuedeMeter(Dupla dupla) {
		return (magia[componentePorNodo[dupla.prim]] != magia[componentePorNodo[dupla.seg]] || (componentePorNodo[dupla.prim] == 0));
	}

	public int cantLocales;
	public int cantParesAstor;
	public int[][] matrizPesos;
	public int costoProduccion;
	public long tiempoAlgoritmo;
	public int compConexaActual;
	public int[] componentePorNodo;
	public int[] magia;
	public LinkedList red;
	public LinkedList aristasAstor;
	public LinkedList aristasPorAgregar;

}