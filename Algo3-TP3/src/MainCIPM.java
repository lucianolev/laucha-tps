import java.io.IOException;

public class MainCIPM {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
//		mostrarGrafosAleatorios();

//		LectorDeGrafos lector = new LectorDeGrafos("TP3.in");
//		EscritorDeSoluciones escritor = new EscritorDeSoluciones();
		int tamGrafo = 40;
		GrafoNPonderados elgrafo = new GrafoNPonderados(tamGrafo);
		elgrafo.mostrarGrafo();
		
//		while(lector.quedanGrafos()) {
//			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			double alfaRCL = 0.99;
			int cantIteracionesLocal = 100;
			int cantIteracionesGrasp = 100;
			
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
		
			//resuelvo el problema mediante el metodo exacto
			Solucion solucionExacta = resolvedor.resolverExacto();
			//escritor.agregarSolucion(solucionExacta);
			//DEBUG
			System.out.println("Solucion exacta");
			solucionExacta.mostrarSolucion(elgrafo);
			
			//probarHeuristicasCons(elgrafo, resolvedor, alfaRCL);
			
			probarGrasps(elgrafo, resolvedor, alfaRCL, cantIteracionesGrasp, cantIteracionesLocal);
			
//			//encuentro una solucion mediante una heuristica constructiva
//			Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(alfaRCL);
//			//escritor.agregarSolucion(solucionHConstructiva);
//			//DEBUG
//			System.out.println("Heuristica constructiva con peso/grado con alfa = "+alfaRCL);
//			solucionHConstructiva.mostrarSolucion(elgrafo);
//			
//			//hago una busqueda local
//			Solucion mejorSolucion = resolvedor.busquedaLocal(solucionHConstructiva, cantIteracionesLocal);
//			//escritor.agregarSolucion(mejorSolucion);
//			//DEBUG
//			System.out.println("Busqueda Local");
//			mejorSolucion.mostrarSolucion(elgrafo);
			
//		}
		
//		escritor.guardarSoluciones("TP3.out");

	}
	
	public static void mostrarGrafosAleatorios() {

		int tamGrafo = 50;
		GrafoNPonderados grafoRandom = new GrafoNPonderados(tamGrafo);
		grafoRandom.mostrarGrafo();
		
//		tamGrafo = 20;
//		grafoRandom = new GrafoNPonderados(tamGrafo);
//		grafoRandom.mostrarGrafo();
//		
//		tamGrafo = 30;
//		grafoRandom = new GrafoNPonderados(tamGrafo);
//		grafoRandom.mostrarGrafo();
		
		System.out.println("0");		
	}
	
	public static void probarConAlfas(GrafoNPonderados elGrafo, ResolvedorCIPM resolvedor) {
		
		//encuentro una solucion mediante una heuristica constructiva
		Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(0);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(0.25);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.25");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(0.5);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.5");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(0.75);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.75");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(0.99);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.99");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
	}
	
	public static void probarHeuristicasCons(GrafoNPonderados elGrafo, ResolvedorCIPM resolvedor, double alfaRCL) {
	
		//encuentro una solucion mediante una heuristica constructiva
		Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado(alfaRCL);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con peso/grado con alfa = "+alfaRCL);
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaConPeso(alfaRCL);
//		//escritor.agregarSolucion(solucionHConstructiva);
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso con alfa = "+alfaRCL);
//		solucionHConstructiva.mostrarSolucion(elGrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaConGrado(alfaRCL);
//		//escritor.agregarSolucion(solucionHConstructiva);
//		//DEBUG
//		System.out.println("Heuristica constructiva con grado con alfa = "+alfaRCL);
//		solucionHConstructiva.mostrarSolucion(elGrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoVecindad(alfaRCL);
//		//escritor.agregarSolucion(solucionHConstructiva);
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso/vecindad con alfa = "+alfaRCL);
//		solucionHConstructiva.mostrarSolucion(elGrafo);
	
	}
	
	public static void probarGrasps(GrafoNPonderados elGrafo, ResolvedorCIPM resolvedor, double alfaRCL, int cantIteracionesGrasp, int cantIteracionesLocal) {
		
		//grasp
		Solucion solucionGrasp = resolvedor.graspPesoGrado(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
		//escritor.agregarSolucion(solucionExacta);
		//DEBUG
		System.out.println("Solucion GRASP con peso/grado");
		solucionGrasp.mostrarSolucion(elGrafo);
		
//		//grasp
//		solucionGrasp = resolvedor.graspPeso(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con peso");
//		solucionGrasp.mostrarSolucion(elGrafo);
//		
//		//grasp
//		solucionGrasp = resolvedor.graspGrado(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con grado");
//		solucionGrasp.mostrarSolucion(elGrafo);
//		
//		//grasp
//		solucionGrasp = resolvedor.graspPesoVecindad(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con peso/vecindad");
//		solucionGrasp.mostrarSolucion(elGrafo);
		
	}

}