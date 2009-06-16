import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

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
		
//		while(lector.quedanGrafos()) {
//			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			double alfaRCL = 0.5;
			int cantIteracionesLocal = 300;
			int cantIteracionesGrasp = 100;
			
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
		
			//resuelvo el problema mediante el metodo exacto
			Solucion solucionExacta = resolvedor.resolverExacto();
			//escritor.agregarSolucion(solucionExacta);
			//DEBUG
			System.out.println("Solucion exacta");
			solucionExacta.mostrarSolucion();
			
			//encuentro una solucion mediante una heuristica constructiva
			Solucion solucionHConstructiva = resolvedor.heuristicaConstructiva(alfaRCL);
			//escritor.agregarSolucion(solucionHConstructiva);
			//DEBUG
			System.out.println("Heuristica constructiva");
			solucionHConstructiva.mostrarSolucion();
			
			//hago una busqueda local
			Solucion mejorSolucion = resolvedor.busquedaLocal(solucionHConstructiva, cantIteracionesLocal);
			//escritor.agregarSolucion(mejorSolucion);
			//DEBUG
			System.out.println("Busqueda Local");
			mejorSolucion.mostrarSolucion();
			
			//grasp
			Solucion solucionGrasp = resolvedor.grasp(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
			//escritor.agregarSolucion(solucionExacta);
			//DEBUG
			System.out.println("Solucion GRASP");
			solucionGrasp.mostrarSolucion();
			
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

}