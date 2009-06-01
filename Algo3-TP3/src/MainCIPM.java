
public class MainCIPM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LectorDeGrafo lector = new LectorDeGrafo("TP3.in");
		Grafo elgrafo = lector.dameGrafo();
		ResolvedorCIPM resolvedor = new ResolvedorCIPM(grafo);
		
		//resuelvo el problema mediante el metodo exacto
		resolvedor.resolverExacto();
		
		//resulevo el problema mediante el metodo bla bla
		//...
		
		EscritorDeSolucion escritor = new EscritorDeSolucion(resolvedor.dameSolucion());
		escritor.guardarSolucion("TP3.out");
	}

}
