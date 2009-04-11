int medianaRecursiva (int* x, int* y, int n) {
	
	if (n == 1) {
		if (x[0] > y[0]) {
			return y[0];
		}
		else {
			return x[0];
		}
	}
		
	if (n == 2) {
		if (x[0] == y[0]) {
			return x[0];
		}
		if (x[0] > y[0]) {
			if (x[0] > y[1]) {
				return y[1];
			}
			else {
				return x[0];
			}
		}
		else {
			if (x[1] > y[0]) {
				return y[0];
			}
			else {
				return x[1];
			}
		}
	}
	
	if (n > 2) {
	    int medio1;
	    int medio2;
	    if (n % 2 == 1) {
    		medio1 = n / 2;
    		medio2 = medio1;
	    }
	    else {
		medio1 = n / 2 - 1;
		medio2 = n / 2;
	    }
	    if (x[medio1] > y[medio2]) {
		return medianaRecursiva(x, y+medio2, medio1+1);
	    }
	    else {
		return medianaRecursiva(x+medio1, y, medio2+1);
	    }
	}
}

void ordenar(int* a, int n) {
	
	for (int i=0; i < n; i++) {
		int min = i;
		for(int j = i; j<n; j++){
			if(a[j] < a[min]) {
				min = j;
			}
		}
		int aux = a[i];
		a[i] = a[min];
		a[min] = aux;
	}
}