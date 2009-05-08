import java.util.Comparator;

class DuplaComparator implements Comparator {
	public int compare(Object dupla1, Object dupla2) {

		if(((Dupla)dupla1).prim < ((Dupla)dupla2).prim) {
			return -1;
		}

		if(((Dupla)dupla1).prim > ((Dupla)dupla2).prim) {
			return 1;
		}

		if(((Arista)arista1).prim == ((Arista)arista2).prim) {
			if(((Arista)arista1).seg < ((Arista)arista2).seg) {
				return -1;
			} else if (((Arista)arista1).seg > ((Arista)arista2).seg) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
