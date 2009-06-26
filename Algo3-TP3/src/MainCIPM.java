import java.io.IOException;

public class MainCIPM {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		LectorDeGrafos lector = new LectorDeGrafos("TP3.in");
		EscritorDeSoluciones escritorExacto = new EscritorDeSoluciones();
		EscritorDeSoluciones escritorHC = new EscritorDeSoluciones();
		EscritorDeSoluciones escritorBL = new EscritorDeSoluciones();
		EscritorDeSoluciones escritorGrasp = new EscritorDeSoluciones();
		
		while(lector.quedanGrafos()) {
			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
			
			//resuelvo el problema mediante el metodo exacto
			Solucion solucionExacta = resolvedor.resolverExacto();
			escritorExacto.agregarSolucion(solucionExacta);
			
			//encuentro una solucion mediante una heuristica constructiva
			Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado();
			escritorHC.agregarSolucion(solucionHConstructiva);
			
			//busqueda local
			int cantIteracionesLocal = 200;
			Solucion solucionInicial = resolvedor.heuristicaConstructivaConGrado();
			Solucion solucionBLocal = resolvedor.busquedaLocal2(solucionInicial, cantIteracionesLocal);
			escritorBL.agregarSolucion(solucionBLocal);
			
			//grasp
			double alfaRCL = 0.2;
			int cantIteracionesGrasp = 70;
			Solucion solucionGrasp = resolvedor.grasp(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
			escritorGrasp.agregarSolucion(solucionGrasp);		
		}
		
		escritorExacto.guardarSoluciones("TP3exacto.out");
		escritorHC.guardarSoluciones("TP3hc.out");
		escritorBL.guardarSoluciones("TP3hbl.out");
		escritorGrasp.guardarSoluciones("TP3grasp.out");
	}

}