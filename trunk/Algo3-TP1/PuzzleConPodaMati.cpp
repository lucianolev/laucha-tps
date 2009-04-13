#include <list>
struct Ficha {
	int arriba;
	int abajo;
	int izq;
	int der;
};

using namespace std;

bool puedoPodar(list<Ficha> j1,list<Ficha> j2) {
	list<Ficha>::iterator it1;
	list<Ficha>::iterator it2;

	it1 = j1.begin();

	while(it1 != j1.end()) {
		it2 = j2.begin();
		while(it2 != j2.end()) {
			if(it1->der == it2->izq) {
				return false;
			}
			it2++;
		}
		it1++;
	}
	return true;

}

Ficha** resolverPuzzle(Ficha* fichas, int n) {

	Ficha** tablero = new Ficha*[n];
	for(int i = 0; i < n; i++) {
		tablero[i] = new Ficha[n];
	}

	// offset de posicion de ficha actual segun la casilla de el tablero
	int fichaActualEn[n][n];
	// al principio todas las casillas tienen su ficha actual en 0
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
		fichaActualEn[i][j] = 0;
		}
	}

	// offset que me dice que fichas estan puestas en el tablero
	bool fichasPuestas[n*n];
	// al principio ninguna ficha esta puesta en el tablero
	for(int i = 0; i < n*n; i++) {
		fichasPuestas[i] = false;
	}
	
	int i = 0;
	int j = 0;
	bool metio;
	list<Ficha> jardineroViejo;
	list<Ficha> jardineroNuevo;
	
	while( i < n ) {
		metio = false;
		if(j != 0) {
			jardineroViejo.clear();
			jardineroViejo = list<Ficha>(jardineroNuevo);
			jardineroNuevo.clear();
		}
		else {
			jardineroViejo.clear();
			jardineroNuevo.clear();
		}

		if( (!fichasPuestas[fichaActualEn[i][j]]) &&
			(i == 0 || (tablero[i-1][j]).abajo == (fichas[fichaActualEn[i][j]]).arriba) &&
			(j == 0 || (tablero[i][j-1]).der == (fichas[fichaActualEn[i][j]]).izq) )
		{ //Si puedo poner la ficha...

			//A jardinero nuevo le pongo las fichas
			if(i != n-1) {
				for(int k = 0; k < n*n; k++) {
					if(fichas[k].arriba == (fichas[fichaActualEn[i][j]]).abajo && !fichasPuestas[k]){
						jardineroNuevo.push_back(fichas[k]);
					}
				}
			}

			if (j == 0 || i == n-1 || !puedoPodar(jardineroViejo,jardineroNuevo)) {
				tablero[i][j] = fichas[fichaActualEn[i][j]];
				metio = true;
				fichasPuestas[fichaActualEn[i][j]] = true;
				if (j == n-1) {
					i++;
					j = 0;
				}
				else {
					j++;
				}
			}
		}

		if(!metio) {
// 			if(jardineroNuevo != jardineroViejo){
// 			 // delete jardineroNuevo;
// 			}
			jardineroNuevo.clear();
			jardineroNuevo = list<Ficha>(jardineroViejo);

			while( (fichaActualEn[i][j] == (n*n - 1))) {
				if (i == 0 && j == 0) { //No hay solucion
					return 0;
				}
				fichaActualEn[i][j] = 0;
				if (j == 0) {
					i--;
					j = n-1;
				}
				else {
					j--;
				}
				fichasPuestas[fichaActualEn[i][j]] = false;
				
				if (j != 0 && i != n-1 ){
					jardineroNuevo.clear();
					for(int k = 0; k < n*n; k++) {
						if(fichas[k].arriba == (fichas[fichaActualEn[i][j-1]]).abajo && !fichasPuestas[k]){
							jardineroNuevo.push_back(fichas[k]);
						}
					}
				}
			}
			fichaActualEn[i][j]++;
		}
	}

	return tablero;
}
