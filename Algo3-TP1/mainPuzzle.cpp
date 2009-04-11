#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "puzzleConPoda.cpp"
#include "Archivo.h"

using namespace std;

int main() {	

	Archivo entrada("Tp1Ej2.in", true);
	Archivo salida("Tp1Ej2.out");
	int n;
	entrada.leerDato(n);
		
	while (n != 0) {
		Ficha fichas[n*n];
		for (int i = 0; i < n*n; i++) {
			entrada.leerDato(fichas[i].arriba);
			entrada.leerDato(fichas[i].abajo);
			entrada.leerDato(fichas[i].izq);
			entrada.leerDato(fichas[i].der);
		}
		Ficha** matriz = resolverPuzzle(fichas, n);
		if(matriz != 0){
		  for (int i = 0; i < n; i++) {
			  for (int j = 0; j < n; j++) {
				  salida.escribirDato(matriz[i][j].arriba);
				  salida.escribirDato(" ");
				  salida.escribirDato(matriz[i][j].abajo);
				  salida.escribirDato(" ");
				  salida.escribirDato(matriz[i][j].izq);
				  salida.escribirDato(" ");
				  salida.escribirDato(matriz[i][j].der);
				  salida.nuevaLinea();
			  }
			  delete matriz[i];
		  }
		}
		else {
		  cout << "ouch" << endl;
		}

		delete matriz;
		entrada.leerDato(n);
	}
	
	return 0;

}
