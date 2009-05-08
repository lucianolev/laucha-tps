import java.util.Comparator;

class AristaComparator implements Comparator {
	public int compare(Object arista1, Object arista2) {

		if(((Arista)arista1).peso < ((Arista)arista2).peso) {
			return (-1);
		}

		if(((Arista)arista1).peso > ((Arista)arista2).peso) {
			return 1;
		}

		if(((Arista)arista1).peso == ((Arista)arista2).peso) {
			return 0;
		}
		return 0;
	}
}