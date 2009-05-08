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

	public void generarInstanciaRandom(int paramCantLocales, int rangoRandom) {
		cantLocales = paramCantLocales;
		aristasAstor = new LinkedList();
		matrizPesos = new int[cantLocales][cantLocales];
		for (int i = 0; i < cantLocales; i++) {
			for (int j = 0; j < cantLocales; j++) {
				if(i == j) {
					matrizPesos[i][j] = 0;
				} else {
					matrizPesos[i][j] = (int)((Math.random()*rangoRandom));
				}
			}
		}
		
		cantParesAstor = 0;
		int nodo1 = (int)((Math.random()*(cantLocales-1))+1);
		int nodo2 = nodo1+(int)((Math.random()*(cantLocales-1))+1);

		while(nodo2 <= cantLocales) {
			Arista nueva = new Arista();
			nueva.nodo1 = nodo1;
			nueva.nodo2 = nodo2;
			nodo1 = nodo1+(int)((Math.random()*(cantLocales-1))+1);
			nodo2 = nodo1+(int)((Math.random()*(cantLocales-1))+1);
			cantParesAstor++;
		}
	}

	public void crearListaAristas() {
		aristasPorAgregar = new LinkedList();
		for(int i = 1; i < cantLocales; i++) {
			for(int j = 0; j < i; j++) {
				Arista nuevaArista = new Arista(i+1,j+1,matrizPesos[i][j]);
				aristasPorAgregar.add(nuevaArista);
			}
		}
	}

	public void crearListaAstor() {
		ListIterator iter = aristasAstor.listIterator();
		Arista aristaActual = new Arista();
		while(iter.hasNext()) {
			aristaActual = (Arista)iter.next();
			aristaActual.peso = matrizPesos[aristaActual.nodo1-1][aristaActual.nodo2-1];
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