#include <iostream>
#include "Archivo.h"
#include "Fichas.h"

using namespace std;

int main() {

	Archivo entrada("Tp1Ej2.in", true);
	int n;
	entrada.leerDato(n);

	Fichas* fichas = new Fichas();
	Fichas* fichas2 = new Fichas();
	for (int i = 0; i < n*n; i++) {
		Ficha ficha;
		entrada.leerDato(ficha.arriba);
		entrada.leerDato(ficha.abajo);
		entrada.leerDato(ficha.izq);
		entrada.leerDato(ficha.der);
		fichas->agregarFicha(ficha);
		fichas2->agregarFicha(ficha);
	}

	while (fichas->quedanFichasPorVer()) {
		cout << "Ficha " << fichas->fichaActual().arriba << " " << fichas->fichaActual().abajo << " " << fichas->fichaActual().izq << " " << fichas->fichaActual().der << endl;
		fichas->siguienteFicha();
	}
	fichas->eliminarFichaActual();
	fichas->irAlInicio();
	while (fichas->quedanFichasPorVer()) {
		cout << "Ficha " << fichas->fichaActual().arriba << " " << fichas->fichaActual().abajo << " " << fichas->fichaActual().izq << " " << fichas->fichaActual().der << endl;
		fichas->siguienteFicha();
	}

	return 0;
}