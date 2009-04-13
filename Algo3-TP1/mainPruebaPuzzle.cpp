#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "PuzzleConPodaMati.cpp"
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
	int tiempo;
	Archivo salida("Ej2-Complejidad.txt");

	for(int n = 1; n <= 10; n++) {
		Ficha x[n*n];
		
		generarTableroConSolucion(x,n);

		gettimeofday(&inicio, NULL);
		Ficha** resultado = resolverPuzzle(x,n);
		gettimeofday(&fin, NULL);
		tiempo = fin.tv_sec - inicio.tv_sec;

		cout << "RESULTADO PARA TABLERO " << n << "x" << n <<endl;
		cout << "--------------------------" << endl;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cout << resultado[i][j].arriba << " ";
				cout << resultado[i][j].abajo << " ";
				cout << resultado[i][j].izq << " ";
				cout << resultado[i][j].der << " ";
				cout << endl;
			}
		}
		salida.escribirDato(n);
		salida.escribirDato(" ");
		salida.escribirDato(tiempo);
		salida.nuevaLinea();

		for(int i = 0; i < n; i++)
			delete resultado[i];
		
		delete resultado;
		
	}
	return 0;
}
