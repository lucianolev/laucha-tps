import java.util.LinkedList;
import java.util.ListIterator;

public class Solucion {

	public Solucion() {
		conjuntoSolucion = new LinkedList();
		peso = 0;
	}
	
	public void agregarNodo(int nodo, GrafoNPonderados elGrafo) {
		conjuntoSolucion.add(new Integer(nodo));
		peso += elGrafo.pesoNodo(nodo); 
	}
	
	public void sacarNodo(int nodo, GrafoNPonderados elGrafo) {
		ListIterator iter = conjuntoSolucion.listIterator();
		boolean encontro = false;
		while(iter.hasNext() && !encontro) {
			if (((Integer)iter.next()).intValue() == nodo) {
				//System.out.println("Saque el "+nodo);
				iter.remove();
				encontro = true;
			}
		}
		peso -= elGrafo.pesoNodo(nodo);
	}
	
	public void asignarSolucion(LinkedList pConjuntoSolucion, int pPesoMaximo) {
		conjuntoSolucion = pConjuntoSolucion;
		peso = pPesoMaximo;
	}
	
	public int peso() {
		return peso;
	}
	
	public int tamSolucion() {
		return conjuntoSolucion.size();
	}
	
	public ListIterator iterSolucion() {
		return conjuntoSolucion.listIterator();
	}
	
	public void mostrarSolucion(GrafoNPonderados elGrafo) {
		System.out.println("-------------");
		System.out.println("Peso: "+peso);
		System.out.println("Nodos: ");
		
		ListIterator iter = iterSolucion();
		while(iter.hasNext()) {
			int nodo = ((Integer)iter.next()).intValue();
			System.out.print(nodo+"(G:"+elGrafo.gradoNodo(nodo)+", P:"+elGrafo.pesoNodo(nodo)+")"+" ");
		}
		System.out.println();
		System.out.println("-------------");
	}

	private LinkedList conjuntoSolucion;
	private int peso;

}