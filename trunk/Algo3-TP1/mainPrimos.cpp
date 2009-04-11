#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "encontrarPrimos.cpp"
#include "Archivo.h"

using namespace std;

int main() {
	
	Archivo entrada("Tp1Ej1.in", true);
	Archivo salida("Tp1Ej1.out");
	int n;
	entrada.leerDato(n);
	
	while (n != 0) {
		pair< int, int > res = encontrarPrimos(n);
		if (res.first != 0) {
			salida.escribirDato(res.first);
			salida.escribirDato(" ");
			salida.escribirDato(res.second);
		}
		else {
			salida.escribirDato("Goldbach la pifió!");
		}
		salida.nuevaLinea();
		entrada.leerDato(n);
	}
	
	return 0;
}
