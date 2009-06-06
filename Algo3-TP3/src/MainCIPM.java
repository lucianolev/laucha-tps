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
			resolvedor.resolverExacto();
			
			//resulevo el problema mediante el metodo bla bla
			//...
			
			Solucion laSolucion = resolvedor.dameSolucion();

			escritor.agregarSolucion(laSolucion);

			System.out.println(laSolucion.pesoMaximo);

			LinkedList conjuntoSolucion = laSolucion.conjuntoSolucion;

			ListIterator iter = conjuntoSolucion.listIterator();
			while(iter.hasNext()) {
				System.out.print(((Integer)iter.next()).intValue()+" ");
			}
			System.out.println();
		}
		escritor.guardarSoluciones("TP3.out");
	}

}