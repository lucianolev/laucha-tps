import java.util.Stack;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Random;

public class ResolvedorCIPM {

	public ResolvedorCIPM(GrafoNPonderados unGrafo) {
		elGrafo = unGrafo;
	}

	public Solucion resolverExacto() {
		Solucion laSolucion = new Solucion();
		
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
				if (acumuladorDePeso > laSolucion.pesoMaximo) {
					laSolucion.conjuntoSolucion = new LinkedList(pila);
					laSolucion.pesoMaximo = acumuladorDePeso;
				}
				nodoActual = ((Integer)pila.pop()).intValue();
				acumuladorDePeso -= elGrafo.pesoNodo(nodoActual);
			}
		}
		
		return laSolucion;
	}

	//0 <= alfaRCL < 1
	public Solucion heuristicaConstructiva(double alfaRCL) {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		ArrayList listaRCL = null;
		
		while(grafoTemporal.hayNodos()) {
			double maxRelPesoGrado = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo) && 
					(grafoTemporal.gradoNodo(nodo) != 0))
				{
					double relPesoGrado = grafoTemporal.pesoNodo(nodo)/grafoTemporal.gradoNodo(nodo);
					if(relPesoGrado > maxRelPesoGrado) {
						maxRelPesoGrado = relPesoGrado;
					}
				}
			}
			
			listaRCL = new ArrayList();
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0) {
						laSolucion.conjuntoSolucion.add(new Integer(nodo));
						laSolucion.pesoMaximo += grafoTemporal.pesoNodo(nodo);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						double relPesoGrado = grafoTemporal.pesoNodo(nodo)/grafoTemporal.gradoNodo(nodo);
						if (relPesoGrado >= (1-alfaRCL)*maxRelPesoGrado) {
							listaRCL.add(new Integer(nodo));
						}
					}
				}
			}
			
			if (listaRCL.size() > 0) {
				Random generador = new Random();
				int rand = generador.nextInt(listaRCL.size());
				int superNodo = ((Integer)listaRCL.get(rand)).intValue();
				
				laSolucion.conjuntoSolucion.add(new Integer(superNodo));
				laSolucion.pesoMaximo += grafoTemporal.pesoNodo(superNodo);
				
				grafoTemporal.borrarVecindad(superNodo);
			}
		}
		
		return laSolucion;
	}


	//O(n*m)
	//TODO: hay que verificar que el nodo a sacar tenga menos peso que el nodo a insertar!
	public Solucion busquedaLocal(Solucion unaSolucion) {
	
		if(unaSolucion.conjuntoSolucion.size() == 1) {
			return unaSolucion;
		}
		
		ListIterator iterSolucion = unaSolucion.conjuntoSolucion.listIterator();

		int diferenciaMaximaDePeso = 0;
		int nodoAInsertar = 0;
		int nodoASacar = 0;

		while(iterSolucion.hasNext()) {
			int nodoSolucion = ((Integer)iterSolucion.next()).intValue();
			ListIterator adyacentesANodoSolucion = elGrafo.adyacentes(nodoSolucion).listIterator();
			while(adyacentesANodoSolucion.hasNext()) {
				int adyacenteANodoSolucion = ((Integer)adyacentesANodoSolucion.next()).intValue();
				ListIterator otroIterSolucion = unaSolucion.conjuntoSolucion.listIterator();
				boolean adyacenteAAlguno = false;
				//Me fijo si el adyacenteANodoSolucion es adyacente a alguno de la solucion
				while(otroIterSolucion.hasNext() && !adyacenteAAlguno) {
					int otroNodoSolucion = ((Integer)otroIterSolucion.next()).intValue();
					if(otroNodoSolucion != nodoSolucion) {
						adyacenteAAlguno = elGrafo.sonAdyacentes(otroNodoSolucion, adyacenteANodoSolucion);
					}
				}
				//Me fijo ademas si la diferencia de pesos de los nodos que estoy intercambiando es la maxima
				int diferenciaActual = elGrafo.pesoNodo(adyacenteANodoSolucion) - elGrafo.pesoNodo(nodoSolucion);
				if (!adyacenteAAlguno && diferenciaActual > diferenciaMaximaDePeso) {
					nodoAInsertar = adyacenteANodoSolucion;
					diferenciaMaximaDePeso = diferenciaActual;
					nodoASacar = nodoSolucion;
				}
			}
		}
		
		if(diferenciaMaximaDePeso > 0) {
			iterSolucion = unaSolucion.conjuntoSolucion.listIterator();
			boolean encontro = false;
			while(iterSolucion.hasNext() && !encontro) {
				encontro = ((Integer)iterSolucion.next()).intValue() == nodoASacar;
			}
			iterSolucion.remove();
			unaSolucion.conjuntoSolucion.add(new Integer(nodoAInsertar));
			unaSolucion.pesoMaximo += diferenciaMaximaDePeso;
		}

		return unaSolucion;

	}
	
	private GrafoNPonderados elGrafo;

}