#include "Jardinero.h"

using namespace std;

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
	Jardinero* jardineroViejo;
	Jardinero* jardineroNuevo;

	while( i < n ) {
		metio = false;
		if( (!fichasPuestas[fichaActualEn[i][j]]) &&
			(i == 0 || (tablero[i-1][j]).abajo == (fichas[fichaActualEn[i][j]]).arriba) &&
			(j == 0 || (tablero[i][j-1]).der == (fichas[fichaActualEn[i][j]]).izq))
		{ //Si puedo poner la ficha...
			
			//Si estoy en un borde, asesino al los jardineros porque empiezo nueva jardineria
			if (j == 0) {
				delete jardineroViejo;
				delete jardineroNuevo;
			}
			//Si no estoy en un borde entonces el jardinero nuevo se hizo viejo, entonces el nuevo no existe
			else {
				jardineroViejo = jardineroNuevo;
				delete jardineroNuevo;
			}
			
			//Creo un jardinero nuevo y lo lleno con fichas
			jardineroNuevo = new Jardinero();
			for(int k = 0; k < n*n; k++) {
				if(fichas[k].arriba == tablero[i][j].abajo && !fichasPuestas[k]){
					jardineroNuevo->agregarFicha(fichas[k]);
				}
			}

			//Si estoy en un borde o si no puedo poder entonces meto la ficha y avanzo
			if (j == 0 || !jardineroViejo->puedoPodar(jardineroNuevo)) {
				tablero[i][j] = fichas[fichaActualEn[i][j]];
				fichasPuestas[fichaActualEn[i][j]] = true;
				bool metio = true;
				//Avanzo
				if (j == n-1) {
					i++;
					j = 0;
				}
				else {
					j++;
				}
			}

			//Asesino al jardinero viejo porque ya no me sirve
			delete jardineroViejo;
		}
		if (!metio) {
			delete jardineroNuevo;
			jardineroNuevo = jardineroViejo;

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
			}
			fichaActualEn[i][j]++;
		}
	}

	return tablero;
}
