#include "Jardinero.h"

using namespace std;

Ficha** resolverPuzzleConPoda(Ficha* fichas, int n) {

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
	bool metio;
	bool volviolgunavez;
	int i_ant;
	int j_ant;

	while( i < n ) {
		
		metio = false;
		if( (!fichasPuestas[fichaActualEn[i][j]]) &&
			(i == 0 || (tablero[i-1][j]).abajo == (fichas[fichaActualEn[i][j]]).arriba) &&
			(j == 0 || (tablero[i][j-1]).der == (fichas[fichaActualEn[i][j]]).izq))
		{ //Si puedo poner la ficha...

			//Si no estoy en el borde de abajo creo un jardinero nuevo
			if (i != n-1)
			{
				jardineroNuevo = new Jardinero();
				for(int k = 0; k < n*n; k++) {
					if(fichas[k].arriba == (fichas[fichaActualEn[i][j]]).abajo && !fichasPuestas[k]) {
						jardineroNuevo->agregarFicha(fichas[k]);
					}
				}
			}

			//Si estoy en borde izquierdo/abajo o si no puedo podar -> meto la ficha y avanzo
			if (j == 0 || i == n-1 || !jardineroViejo->puedoPodar(jardineroNuevo)) 
			{
				//Si estoy en la primera posicion entonces el jardinero viejo es el nuevo q cree
				if (j == 0 && i == 0) {
					jardineroViejo = jardineroNuevo;
				}
				else {
					if (i != n-1) { //Si no estoy en el borde de abajo borro el jardinero viejo y ahora el viejo es el que acabo de crear
						delete jardineroViejo;
						jardineroViejo = jardineroNuevo;
					}
				}

				tablero[i][j] = fichas[fichaActualEn[i][j]];
				fichasPuestas[fichaActualEn[i][j]] = true;
				metio = true;
				//Avanzo
				if (j == n-1) {
					i++;
					j = 0;
				}
				else {
					j++;
				}
			}
			else {
				delete jardineroNuevo; //Si no meti nada tengo q borrar el jardinero nuevo que cree.
			}
		}

		if (!metio) {
			
			volviolgunavez = false;
			while (fichaActualEn[i][j] == (n*n - 1)) 
			{
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
				volviolgunavez = true;
			}

			if(volviolgunavez) {
				if (j == 0) {
					i_ant = i-1;
					j_ant = n-1;
				}
				else {
					i_ant = i;
					j_ant = j-1;
				}
// 				if ((i_ant != n-1) && !(i_ant == n-2 && j_ant == n-1))
// 					delete jardineroViejo;
				if (i_ant != n-1)
					delete jardineroViejo;
				if ((i != 0 || j != 0) && (i_ant != n-1)) {
					//Creo un jardinero viejo y lo lleno con fichas
					jardineroViejo = new Jardinero();
					for(int k = 0; k < n*n; k++) {
						if(fichas[k].arriba == (fichas[fichaActualEn[i_ant][j_ant]]).abajo && !fichasPuestas[k]) {
							jardineroViejo->agregarFicha(fichas[k]);
						}
					}
				}
			}

			fichaActualEn[i][j]++;
		}
	}
// 	delete jardineroViejo; //Borro el jardinero de [n-2][n-1] que nunca borre

	return tablero;
}
