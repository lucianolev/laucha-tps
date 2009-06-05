import java.io.IOException;

public class MainCIPM {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LectorDeGrafos lector = new LectorDeGrafos("TP3.in");
/*		EscritorDeSoluciones escritor = new EscritorDeSoluciones();*/
		while(lector.quedanGrafos()) {
			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
		
			//resuelvo el problema mediante el metodo exacto
			resolvedor.resolverExacto();
			
			//resulevo el problema mediante el metodo bla bla
			//...
			
/*			escritor.agregarSolucion(resolvedor.dameSolucion());*/
			System.out.println(resolvedor.damePesoMaximo());
		}
/*		escritor.guardarSoluciones("TP3.out");*/
	}

}