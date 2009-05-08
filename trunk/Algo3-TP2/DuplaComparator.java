import java.util.Comparator;

class DuplaComparator implements Comparator {
	public int compare(Object dupla1, Object dupla2) {

		if(((Dupla)dupla1).prim < ((Dupla)dupla2).prim) {
			return -1;
		}

		if(((Dupla)dupla1).prim > ((Dupla)dupla2).prim) {
			return 1;
		}

		if(((Dupla)dupla1).prim == ((Dupla)dupla2).prim) {
			if(((Dupla)dupla1).seg < ((Dupla)dupla2).seg) {
				return -1;
			} else if (((Dupla)dupla1).seg > ((Dupla)dupla2).seg) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
}
