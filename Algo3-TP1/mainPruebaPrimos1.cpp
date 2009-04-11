#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "encontrarPrimos.cpp"
#include "Archivo.h"
#include <sys/time.h>

using namespace std;

int main(int argc, char* argv[]) {

	timeval inicio;
	timeval fin;
	int diferencia;
	Archivo salida("Ej1-Complejidad.txt");

	for(int i = 4; i <= 2147483644 && i > 0; i =  i + 10000 + (rand()%5000)*2) {

		gettimeofday(&inicio, NULL);
		pair< int, int > res = encontrarPrimos(i);
		gettimeofday(&fin, NULL);
		diferencia = fin.tv_usec - inicio.tv_usec;

		if(diferencia > 0) {
			salida.escribirDato(i);
			salida.escribirDato(" ");
			salida.escribirDato(diferencia);
			salida.nuevaLinea();
		}

	}

}
