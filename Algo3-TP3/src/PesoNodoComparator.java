import java.util.Comparator;

public class PesoNodoComparator implements Comparator {
	
	public PesoNodoComparator(int[] pPesoNodos) {
		pesoNodos = pPesoNodos;
	}
	
	public int compare(Object nodo1, Object nodo2) {
		if(pesoNodos[((Integer)nodo1).intValue()] < pesoNodos[((Integer)nodo2).intValue()]) {
			return -1;
		}
		if(pesoNodos[((Integer)nodo1).intValue()] > pesoNodos[((Integer)nodo2).intValue()]) {
			return 1;
		}
		return 0;
	}
	
	private int[] pesoNodos;
}
