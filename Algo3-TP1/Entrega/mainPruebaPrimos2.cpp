#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "encontrarPrimos.cpp"
#include "Archivo.h"

using namespace std;

int main(int argc, char* argv[]) {

	Archivo salida("Ej1-MayoresPrimos.txt");
	int peor_caso;
	int peor_caso_first;
	int peor_caso_second;
	srand ( time(NULL) );
	
	for(int j = 0; j < 20; j++) {
		peor_caso_first = 0;
		for(int i = 100000 + (rand()%50000)*2; i <= 2147483644 && i > 0; i =  i + 100000 + (rand()%50000)*2) {

			pair< int, int > res = encontrarPrimos(i);

			if(res.first > peor_caso_first) {
				peor_caso = i;
				peor_caso_first = res.first;
				peor_caso_second = res.second;
			}

		}

		salida.escribirDato(peor_caso);
		salida.escribirDato(" ");
		salida.escribirDato(peor_caso_first);
		salida.escribirDato(" ");
		salida.escribirDato(peor_caso_second);
		salida.nuevaLinea();
	}
	
}