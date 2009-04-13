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

	Jardinero* jardineroViejo = new Jardinero();
	Jardinero* jardineroNuevo = new Jardinero();
	bool metio;
	

	while( i < n ) {
		
// 		Si estoy en un borde, asesino al los jardineros porque empiezo nueva jardineria
		if (j == 0) {
		
		}
// 		Si no estoy en un borde entonces el jardinero nuevo se hizo viejo, entonces el nuevo no existe
		else {
			if(jardineroNuevo != jardineroViejo){
			}
			
			jardineroViejo = new Jardinero();
			jardineroViejo = jardineroNuevo;

		}	
		
		metio = false;
		
		if( (!fichasPuestas[fichaActualEn[i][j]]) &&
			(i == 0 || (tablero[i-1][j]).abajo == (fichas[fichaActualEn[i][j]]).arriba) &&
			(j == 0 || (tablero[i][j-1]).der == (fichas[fichaActualEn[i][j]]).izq))
		{ //Si puedo poner la ficha...
			
			if (i != n-1) {
// 				if(jardineroNuevo != jardineroViejo){
// 				  //delete jardineroNuevo;
// 				}
				//Creo un jardinero nuevo y lo lleno con fichas
				jardineroNuevo = new Jardinero();
				for(int k = 0; k < n; k++) {
					if(fichas[k].arriba == (fichas[fichaActualEn[i][j]]).abajo && !fichasPuestas[k]){
						jardineroNuevo->agregarFicha(fichas[k]);
					}
				}
			}



			//Si estoy en un borde o si no puedo poder entonces meto la ficha y avanzo
			if (j == 0 || i == n-1 || !jardineroViejo->puedoPodar(jardineroNuevo)) {
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
				//***lo muevo porque sino se rompe cuando poda o no puede meter la ficha por otra razon
				//Asesino al jardinero viejo porque ya no me sirve

			}
			
		}
		if (!metio) {
			
// 			if(jardineroNuevo != jardineroViejo){
// 			 // delete jardineroNuevo;
// 			}
			jardineroNuevo = new Jardinero();
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
				//***cuando se vuelve hay que crear un jardinero que va a ser el viejo
				if (j != 0 and i != n-1 ){
					if(jardineroNuevo != jardineroViejo){
				//	  delete jardineroNuevo;
					}
					
					//Creo un jardinero nuevo y lo lleno con fichas
					jardineroNuevo = new Jardinero();
					for(int k = 0; k < n*n; k++) {
						if(fichas[k].arriba == (fichas[fichaActualEn[i][j-1]]).abajo && !fichasPuestas[k]){
							jardineroNuevo->agregarFicha(fichas[k]);
						}
					}
				}
			}
			fichaActualEn[i][j]++;
		}
	}

// 	delete jardineroNuevo;
// 	delete jardineroViejo;
// 	

	return tablero;
}
