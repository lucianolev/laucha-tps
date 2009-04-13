#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "mediana.cpp"
#include "Archivo.h"
#include <sys/time.h>

using namespace std;

int main() {

	srand ( time(NULL) );
	timeval inicio;
	timeval fin;
	int tiempo;
	int mediana;
	Archivo salida("Ej3-Complejidad.txt");

	for(int i = 1; i <= 200000000; i = i*2) {
		int* x = new int[i];
		int* y = new int[i];
		
		x[0] = rand()%20;
		y[0] = rand()%20;

		for(int j = 1; j < i; j++) {
			x[j] = x[j-1] + rand()%10;
			y[j] = y[j-1] + rand()%10;
		}

		gettimeofday(&inicio, NULL);
		mediana = medianaRecursiva(x,y,i);
		gettimeofday(&fin, NULL);
		tiempo = fin.tv_usec - inicio.tv_usec;

		salida.escribirDato(i);
		salida.escribirDato(" ");
		salida.escribirDato(tiempo);
		salida.nuevaLinea();
		delete x;
		delete y;
	}
	
}
