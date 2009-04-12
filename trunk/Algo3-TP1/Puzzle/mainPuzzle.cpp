#include <iostream>
#include "Archivo.h"
#include "Tablero.h"

using namespace std;

int main() {	

	Archivo entrada("Tp1Ej2.in", true);
	Archivo salida("Tp1Ej2.out");
	int n;
	entrada.leerDato(n);
		
	while (n != 0) {
		Fichas* fichas = new Fichas();
		for (int i = 0; i < n*n; i++) {
			Ficha ficha;
			entrada.leerDato(ficha.arriba);
			entrada.leerDato(ficha.abajo);
			entrada.leerDato(ficha.izq);
			entrada.leerDato(ficha.der);
			fichas->agregarFicha(ficha);
		}
		Tablero* tablero = new Tablero(n, fichas);
		tablero->resolverPuzzleSinPoda();

		if(tablero->estaSolucionado()){
		  for (int i = 0; i < n; i++) {
			  for (int j = 0; j < n; j++) {
				  salida.escribirDato(tablero->ficha(i,j).arriba);
				  salida.escribirDato(" ");
				  salida.escribirDato(tablero->ficha(i,j).abajo);
				  salida.escribirDato(" ");
				  salida.escribirDato(tablero->ficha(i,j).izq);
				  salida.escribirDato(" ");
				  salida.escribirDato(tablero->ficha(i,j).der);
				  salida.nuevaLinea();
			  }
		  }
		}
		else {
		  cout << "No hay solucion!" << endl;
		}
		delete tablero;
		delete fichas;
		entrada.leerDato(n);
	}
	
	return 0;

}