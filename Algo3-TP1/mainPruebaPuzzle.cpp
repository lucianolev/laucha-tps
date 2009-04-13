#include <stdio.h>
#include <stdlib.h>
#include <iostream>
<<<<<<< .mine
//#include "PuzzleConPodaMati.cpp"
#include "puzzleConPodaFede.cpp"
=======
#include "puzzleConPodaFede.cpp"
#include "puzzleSinPoda.cpp"
>>>>>>> .r42
#include "Archivo.h"
#include <sys/time.h>

using namespace std;

void generarTableroConSolucion (Ficha* fichas, int n) {
	srand ( time(NULL) );

	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			fichas[(i*n)+j].der = rand()%200;
			fichas[(i*n)+j].abajo = rand()%200;

			if(j == 0) {
				fichas[(i*n)+j].izq = rand()%200;
			} else {
				fichas[(i*n)+j].izq = fichas[(i*n)+j-1].der;
			}

			if(i == 0) {
				fichas[(i*n)+j].arriba = rand()%200;
			} else {
				fichas[(i*n)+j].arriba = fichas[((i-1)*n)+j].abajo;
			}
		}
	}

	//Ordeno las fichas por su coordenada abajo para mezclarlas
	int maximo;
	for(int i = 0; i < n*n; i++) {
		maximo = i;
		for(int j = i; j < n*n; j++) {
			if(fichas[j].abajo > fichas[maximo].abajo) {
				maximo = j;
			}
		}

		Ficha aux;
		aux.arriba = fichas[i].arriba;
		aux.abajo = fichas[i].abajo;
		aux.der = fichas[i].der;
		aux.izq = fichas[i].izq;
		
		fichas[i].arriba = fichas[maximo].arriba;
		fichas[i].abajo = fichas[maximo].abajo;
		fichas[i].der = fichas[maximo].der;
		fichas[i].izq = fichas[maximo].izq;

		fichas[maximo].arriba = aux.arriba;
		fichas[maximo].abajo = aux.abajo;
		fichas[maximo].der = aux.der;
		fichas[maximo].izq = aux.izq;
	}
}

int main() {
	timeval inicio;
	timeval fin;
	long long int tiempo_microSP;
	long int tiempo_segSP;
	long long int tiempo_microCP;
	long int tiempo_segCP;
	Archivo salida("Ej2-Complejidad.txt");

	for(int n = 1; n <= 15; n++) {
		Ficha x[n*n];
		
		generarTableroConSolucion(x,n);

		gettimeofday(&inicio, NULL);
		Ficha** resultadoSP = resolverPuzzleSinPoda(x,n);
		gettimeofday(&fin, NULL);
		tiempo_microSP = fin.tv_usec - inicio.tv_usec;
		tiempo_segSP = fin.tv_sec - inicio.tv_sec;

		cout << "RESULTADO SIN PODA PARA TABLERO " << n << "x" << n <<endl;
		cout << "SU TIEMPO DE EJECUCION EN MICROSEGUNDOS FUE: " << tiempo_microSP <<endl;
		cout << "SU TIEMPO DE EJECUCION EN SEGUNDOS FUE: " << tiempo_segSP <<endl;
		cout << "--------------------------" << endl;
		
		gettimeofday(&inicio, NULL);
		Ficha** resultadoCP = resolverPuzzleConPoda(x,n);
		gettimeofday(&fin, NULL);
		tiempo_microCP = fin.tv_usec - inicio.tv_usec;
		tiempo_segCP = fin.tv_sec - inicio.tv_sec;

		cout << "RESULTADO CON PODA PARA TABLERO " << n << "x" << n <<endl;
		cout << "SU TIEMPO DE EJECUCION EN MICROSEGUNDOS FUE: " << tiempo_microCP <<endl;
		cout << "SU TIEMPO DE EJECUCION EN SEGUNDOS FUE: " << tiempo_segCP <<endl;
		cout << "--------------------------" << endl;

// 		for(int i = 0; i < n; i++) {
// 			for(int j = 0; j < n; j++) {
// 				cout << resultado[i][j].arriba << " ";
// 				cout << resultado[i][j].abajo << " ";
// 				cout << resultado[i][j].izq << " ";
// 				cout << resultado[i][j].der << " ";
// 				cout << endl;
// 			}
// 		}
		
		salida.escribirDato(n);
		salida.escribirDato(" ");
		if(tiempo_microSP >= 0)
			salida.escribirDato(tiempo_microSP);
		else
			salida.escribirDato(tiempo_segSP*1000000.0);
		salida.escribirDato(" ");
		if(tiempo_microCP >= 0)
			salida.escribirDato(tiempo_microCP);
		else
			salida.escribirDato(tiempo_segCP*1000000.0);			
		salida.nuevaLinea();

		for(int i = 0; i < n; i++)
<<<<<<< .mine
			delete [] resultado[i];
=======
			delete [] resultadoSP[i];
>>>>>>> .r42
		
<<<<<<< .mine
		delete [] resultado;
=======
		delete [] resultadoSP;
>>>>>>> .r42
		
		for(int i = 0; i < n; i++)
			delete [] resultadoCP[i];
		
		delete [] resultadoCP;
		
	}
	return 0;
}
