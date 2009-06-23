import java.util.Stack;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Random;

public class ResolvedorCIPM {

	public ResolvedorCIPM(GrafoNPonderados unGrafo) {
		elGrafo = unGrafo;
	}

	/** ----- ALGORITMO DE BACKTRACKING EXACTO ----- **/
	
	//O(n!)
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
				if (acumuladorDePeso > laSolucion.peso()) {
					laSolucion.asignarSolucion(new LinkedList(pila), acumuladorDePeso);
				}
				nodoActual = ((Integer)pila.pop()).intValue();
				acumuladorDePeso -= elGrafo.pesoNodo(nodoActual);
			}
		}
		
		return laSolucion;
	}

	/** ----- HEURISTICAS CONSTRUCTIVAS ----- **/
	
	public Solucion heuristicaConstructivaPesoGrado() {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		
		while(grafoTemporal.hayNodos()) {
			
			double maxRelPesoGrado = 0;
			int nodoMax = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0)
					{
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						double relPesoGrado = grafoTemporal.pesoNodo(nodo)/grafoTemporal.gradoNodo(nodo);
						if(relPesoGrado >= maxRelPesoGrado) {
							maxRelPesoGrado = relPesoGrado;
							nodoMax = nodo;
						}	
					}
				}
			}
			
			if (nodoMax != 0) {
				laSolucion.agregarNodo(nodoMax, grafoTemporal);
				grafoTemporal.borrarVecindad(nodoMax);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion heuristicaConstructivaConPeso() {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		
		while(grafoTemporal.hayNodos()) {
			
			double maxPeso = 0;
			int nodoMax = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0)
					{
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						if(grafoTemporal.pesoNodo(nodo) >= maxPeso) {
							maxPeso = grafoTemporal.pesoNodo(nodo);
							nodoMax = nodo;
						}
					}
				}
			}
			
			if (nodoMax != 0) {
				laSolucion.agregarNodo(nodoMax, grafoTemporal);
				grafoTemporal.borrarVecindad(nodoMax);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion heuristicaConstructivaConGrado() {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		
		while(grafoTemporal.hayNodos()) {
			
			int minGrado = grafoTemporal.cantNodos();
			int nodoMin = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0)
					{
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						if(grafoTemporal.gradoNodo(nodo) <= minGrado) {
							minGrado = grafoTemporal.gradoNodo(nodo);
							nodoMin = nodo;
						}
					}
				}
			}
			
			if (nodoMin != 0) {
				laSolucion.agregarNodo(nodoMin, grafoTemporal);
				grafoTemporal.borrarVecindad(nodoMin);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion heuristicaConstructivaPesoVecindad() {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		
		while(grafoTemporal.hayNodos()) {
			
			double maxRelPesoVecindad = 0;
			int nodoMax = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0)
					{
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						double relPesoVecindad = grafoTemporal.pesoNodo(nodo)/(grafoTemporal.pesoVecindad(nodo)/grafoTemporal.gradoNodo(nodo));
						if(relPesoVecindad >= maxRelPesoVecindad) {
							maxRelPesoVecindad = relPesoVecindad;
							nodoMax = nodo;
						}
					}
				}
			}
			
			if (nodoMax != 0) {
				laSolucion.agregarNodo(nodoMax, grafoTemporal);
				grafoTemporal.borrarVecindad(nodoMax);
			}
		}
		
		return laSolucion;
	}
		
	/** ----- BUSQUEDAS LOCALES ----- **/

	//O(n*m)
	private Solucion intercambioDeNodo(Solucion unaSolucion) {
	
		if(unaSolucion.tamSolucion() == 1) {
			return unaSolucion;
		}
		
		ListIterator iterSolucion = unaSolucion.iterSolucion();

		int diferenciaMaximaDePeso = 0;
		int nodoAInsertar = 0;
		int nodoASacar = 0;

		while(iterSolucion.hasNext()) {
			int nodoSolucion = ((Integer)iterSolucion.next()).intValue();
			ListIterator adyacentesANodoSolucion = elGrafo.adyacentes(nodoSolucion).listIterator();
			while(adyacentesANodoSolucion.hasNext()) {
				int adyacenteANodoSolucion = ((Integer)adyacentesANodoSolucion.next()).intValue();
				ListIterator otroIterSolucion = unaSolucion.iterSolucion();
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
			unaSolucion.sacarNodo(nodoASacar, elGrafo);
			unaSolucion.agregarNodo(nodoAInsertar, elGrafo);
		}

		return unaSolucion;

	}
	
	//O(n*n*n)
	private Solucion intercambioDeUnoAMuchos(Solucion unaSolucion) {
	
		if(unaSolucion.tamSolucion() == 1) {
			return unaSolucion;
		}
		
		ListIterator iterSolucion = unaSolucion.iterSolucion();

		int diferenciaMaximaDePeso = 0;
		LinkedList nodosAInsertar = new LinkedList();
		int nodoASacar = 0;

		while(iterSolucion.hasNext()) {
			int pesosNodosAInsertarActual = 0;
			LinkedList nodosAInsertarActual = new LinkedList();
			
			int nodoSolucion = ((Integer)iterSolucion.next()).intValue();
			ListIterator adyacentesANodoSolucion = elGrafo.adyacentes(nodoSolucion).listIterator();
			while(adyacentesANodoSolucion.hasNext()) {
				int adyacenteANodoSolucion = ((Integer)adyacentesANodoSolucion.next()).intValue();
				ListIterator otroIterSolucion = unaSolucion.iterSolucion();
				boolean adyacenteAAlguno = false;
				//Me fijo si el adyacenteANodoSolucion es adyacente a alguno de la solucion
				while(otroIterSolucion.hasNext() && !adyacenteAAlguno) {
					int otroNodoSolucion = ((Integer)otroIterSolucion.next()).intValue();
					if(otroNodoSolucion != nodoSolucion) {
						adyacenteAAlguno = elGrafo.sonAdyacentes(otroNodoSolucion, adyacenteANodoSolucion);
					}
				}
				if(!adyacenteAAlguno) {
					ListIterator iterNodosAInsertarActual = nodosAInsertarActual.listIterator();
					boolean adyacenteANodoAInsertar = false;
					while(iterNodosAInsertarActual.hasNext() && !adyacenteANodoAInsertar) {
						int nodoAInsertarAnterior = ((Integer)iterNodosAInsertarActual.next()).intValue();
						adyacenteANodoAInsertar = elGrafo.sonAdyacentes(nodoAInsertarAnterior, adyacenteANodoSolucion);
					}
					if(!adyacenteANodoAInsertar) {
						nodosAInsertarActual.add(new Integer(adyacenteANodoSolucion));
						pesosNodosAInsertarActual += elGrafo.pesoNodo(adyacenteANodoSolucion);
					}
				}
			}
			int diferenciaActual = pesosNodosAInsertarActual - elGrafo.pesoNodo(nodoSolucion);
			if (diferenciaActual > diferenciaMaximaDePeso) {
				nodosAInsertar = nodosAInsertarActual;
				diferenciaMaximaDePeso = diferenciaActual;
				nodoASacar = nodoSolucion;
			}
		}
		
		if(diferenciaMaximaDePeso > 0) {
			unaSolucion.sacarNodo(nodoASacar, elGrafo);
			ListIterator iterNodosAInsertar = nodosAInsertar.listIterator();
			while(iterNodosAInsertar.hasNext()) {
				unaSolucion.agregarNodo(((Integer)iterNodosAInsertar.next()).intValue(), elGrafo);
			}
		}

		return unaSolucion;
	}
	
	public Solucion busquedaLocal(Solucion unaSolucion, int cantIteraciones) {
		Solucion mejorSolucionVecina = unaSolucion;
		int pesoMaximoAnterior;
		for(int i = 0; i < cantIteraciones; i++) {
			pesoMaximoAnterior = mejorSolucionVecina.peso();
			mejorSolucionVecina = intercambioDeNodo(mejorSolucionVecina);
			if(mejorSolucionVecina.peso() == pesoMaximoAnterior) {
				cantIteracionesBL = i+1;
				return mejorSolucionVecina;
			}
		}
		cantIteracionesBL = cantIteraciones;
		return mejorSolucionVecina;
	}
	
	public Solucion busquedaLocal2(Solucion unaSolucion, int cantIteraciones) {
		Solucion mejorSolucionVecina = unaSolucion;
		int pesoMaximoAnterior;
		for(int i = 0; i < cantIteraciones; i++) {
			pesoMaximoAnterior = mejorSolucionVecina.peso();
			mejorSolucionVecina = intercambioDeUnoAMuchos(mejorSolucionVecina);
			if(mejorSolucionVecina.peso() == pesoMaximoAnterior) {
				cantIteracionesBL = i+1;
				return mejorSolucionVecina;
			}
		}
		cantIteracionesBL = cantIteraciones;
		return mejorSolucionVecina;
	}
	
	/** ----- GRASP ----- **/
	
	//0 <= alfaRCL < 1
	public Solucion heuristicaConstructivaGrasp(double alfaRCL) {
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
						laSolucion.agregarNodo(nodo, grafoTemporal);
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
				laSolucion.agregarNodo(superNodo, grafoTemporal);
				grafoTemporal.borrarVecindad(superNodo);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion grasp(int cantIteracionesGrasp, double alfaRCL, int cantIteracionesLocal) {
		Solucion unaSolucion = null;
		Solucion laMejorSolucion = null;
		int pesoMaximo = 0;
		for(int i = 0; i < cantIteracionesGrasp; i++) {
			unaSolucion = heuristicaConstructivaConGradoGrasp(alfaRCL);
			unaSolucion = busquedaLocal2(unaSolucion, cantIteracionesLocal);
			if(unaSolucion.peso() > pesoMaximo) {
				laMejorSolucion = unaSolucion;
				pesoMaximo = laMejorSolucion.peso();
			}
		}
		return laMejorSolucion;
	}
	
	/** ----- OTRAS HEURISTICAS CONSTRUCTIVAS PARA GRASP ----- **/
	
	public Solucion heuristicaConstructivaConPesoGrasp(double alfaRCL) {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		ArrayList listaRCL = null;
		
		while(grafoTemporal.hayNodos()) {
			
			double maxPeso = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo))
				{
					if(grafoTemporal.pesoNodo(nodo) > maxPeso) {
						maxPeso = grafoTemporal.pesoNodo(nodo);
					}
				}
			}
			
			listaRCL = new ArrayList();
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0) {
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						if (grafoTemporal.pesoNodo(nodo) >= (1-alfaRCL)*maxPeso) {
							listaRCL.add(new Integer(nodo));
						}
					}
				}
			}
			
			if (listaRCL.size() > 0) {
				Random generador = new Random();
				int rand = generador.nextInt(listaRCL.size());
				int superNodo = ((Integer)listaRCL.get(rand)).intValue();
				laSolucion.agregarNodo(superNodo, grafoTemporal);
				grafoTemporal.borrarVecindad(superNodo);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion heuristicaConstructivaConGradoGrasp(double alfaRCL) {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		ArrayList listaRCL = null;
		
		while(grafoTemporal.hayNodos()) {
			
			int minGrado = grafoTemporal.cantNodos();
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo))
				{
					if(grafoTemporal.gradoNodo(nodo) < minGrado) {
						minGrado = grafoTemporal.gradoNodo(nodo);
					}
				}
			}
			
			listaRCL = new ArrayList();
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0) {
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						if (grafoTemporal.gradoNodo(nodo) <= minGrado/(1-alfaRCL)) {
							listaRCL.add(new Integer(nodo));
						}
					}
				}
			}
			
			if (listaRCL.size() > 0) {
				Random generador = new Random();
				int rand = generador.nextInt(listaRCL.size());
				int superNodo = ((Integer)listaRCL.get(rand)).intValue();
				laSolucion.agregarNodo(superNodo, grafoTemporal);
				grafoTemporal.borrarVecindad(superNodo);
			}
		}
		
		return laSolucion;
	}
	
	public Solucion heuristicaConstructivaPesoVecindadGrasp(double alfaRCL) {
		Solucion laSolucion = new Solucion();
		
		GrafoNPonderados grafoTemporal = new GrafoNPonderados(elGrafo);
		ArrayList listaRCL = null;
		
		while(grafoTemporal.hayNodos()) {
			
			double maxRelPesoVecindad = 0;
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo) && 
					(grafoTemporal.gradoNodo(nodo) != 0))
				{
					double relPesoVecindad = grafoTemporal.pesoNodo(nodo)/(grafoTemporal.pesoVecindad(nodo)/grafoTemporal.gradoNodo(nodo));
					if(relPesoVecindad > maxRelPesoVecindad) {
						maxRelPesoVecindad = relPesoVecindad;
					}
				}
			}
			
			listaRCL = new ArrayList();
			for(int nodo = 1; nodo <= grafoTemporal.cantNodos(); nodo++) {
				if (grafoTemporal.existeNodo(nodo)) {
					if (grafoTemporal.gradoNodo(nodo) == 0) {
						laSolucion.agregarNodo(nodo, grafoTemporal);
						grafoTemporal.borrarNodoGradoCero(nodo);
					} else {
						double relPesoVecindad = grafoTemporal.pesoNodo(nodo)/(grafoTemporal.pesoVecindad(nodo)/grafoTemporal.gradoNodo(nodo));
						if (relPesoVecindad >= (1-alfaRCL)*maxRelPesoVecindad) {
							listaRCL.add(new Integer(nodo));
						}
					}
				}
			}
			
			if (listaRCL.size() > 0) {
				Random generador = new Random();
				int rand = generador.nextInt(listaRCL.size());
				int superNodo = ((Integer)listaRCL.get(rand)).intValue();
				laSolucion.agregarNodo(superNodo, grafoTemporal);
				grafoTemporal.borrarVecindad(superNodo);
			}
		}
		
		return laSolucion;
	}
	
	private GrafoNPonderados elGrafo;
	public int cantIteracionesBL;

}