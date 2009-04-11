#include <cmath>

using namespace std;

bool esPrimo(int n) {
	if (n % 2 == 0) {
		return false;
	}

	int i = 3;
	int m = sqrt((double)n);
	while (i < m + 1) {
		if (n % i == 0) {
			return false;
		}
		i = i + 2;
	}

	return true;
}

pair< int, int > encontrarPrimos(int n) {
	
	if (n == 4) {
		pair< int, int > tupla(2,2);
		return tupla;
	}

	int i = 3;

	while (i <= n / 2) {
		if (esPrimo(i) && esPrimo(n - i)) {
			pair< int, int > tupla(i,n-i);
			return tupla;
		}
		i = i + 2;
	}
	// si golbach la pifio devuelvo (0,0)
	pair< int, int > tupla(0,0);
	return tupla;	
}