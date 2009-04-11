#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "encontrarPrimos.cpp"
#include "Archivo.h"
#include <sys/time.h>

using namespace std;

int main(int argc, char* argv[]) {

	Archivo salida("Ej1-PeoresCasos.txt");
	timeval inicio;
	timeval fin;
	int tiempo;
	int maximo_tiempo = 0;
	int peor_caso;
	int j = 1;
	srand ( time(NULL) );

	for(int i = 4; i <= 2147483644 && i > 0; i = i + 10000 + (rand()%5000)*2) {

		gettimeofday(&inicio, NULL);
		pair< int, int > res = encontrarPrimos(i);
		gettimeofday(&fin, NULL);
		tiempo = fin.tv_usec - inicio.tv_usec;

		if(tiempo > maximo_tiempo) {
			maximo_tiempo = tiempo;
			peor_caso = i;
		}

		if (j == 1000) {
			salida.escribirDato(peor_caso);
			salida.escribirDato(" ");
			salida.escribirDato(maximo_tiempo);
			salida.nuevaLinea();
			maximo_tiempo = 0;
			j = 0;
		}
		j++;

	}

}

4
10001
20501