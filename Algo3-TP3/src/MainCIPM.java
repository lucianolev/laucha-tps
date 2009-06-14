import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class MainCIPM {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LectorDeGrafos lector = new LectorDeGrafos("TP3.in");
		EscritorDeSoluciones escritor = new EscritorDeSoluciones();
		while(lector.quedanGrafos()) {
			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
		
			//resuelvo el problema mediante el metodo exacto
			Solucion solucionExacta = resolvedor.resolverExacto();
			
			//encuentro una solucion mediante una heuristica constructiva
			double alfaRCL = 0.9;
			Solucion solucionHConstructiva = resolvedor.heuristicaConstructiva(alfaRCL);

			escritor.agregarSolucion(solucionExacta);
			escritor.agregarSolucion(solucionHConstructiva);

			//DEBUG
			System.out.println(solucionExacta.pesoMaximo);

			LinkedList conjuntoSolucion = solucionExacta.conjuntoSolucion;

			ListIterator iter = conjuntoSolucion.listIterator();
			while(iter.hasNext()) {
				System.out.print(((Integer)iter.next()).intValue()+" ");
			}
			System.out.println();

			//DEBUG
			System.out.println(solucionHConstructiva.pesoMaximo);

			conjuntoSolucion = solucionHConstructiva.conjuntoSolucion;

			iter = conjuntoSolucion.listIterator();
			while(iter.hasNext()) {
				System.out.print(((Integer)iter.next()).intValue()+" ");
			}
			System.out.println();
		}
		
		escritor.guardarSoluciones("TP3.out");
	}

}