#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "mediana.cpp"
#include "Archivo.h"

using namespace std;

int main() {
	
	Archivo entrada("Tp1Ej3.in", true);
	Archivo salida("Tp1Ej3.out");
	int n;
	entrada.leerDato(n);
		
	while (n != 0) {
		int x[n];
		int y[n];
		for (int i = 0; i < n; i++) {
			entrada.leerDato(x[i]);
		}
		for (int i = 0; i < n; i++) {
			entrada.leerDato(y[i]);
		}
		int mediana = medianaRecursiva(x, y, n);
		salida.escribirDato(mediana);
		salida.nuevaLinea();
		entrada.leerDato(n);
	}
	
	return 0;
}
