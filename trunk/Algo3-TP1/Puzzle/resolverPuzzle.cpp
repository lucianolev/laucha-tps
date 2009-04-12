#include "Tablero.h"

void resolverPuzzleConPoda(Tablero* tablero, Fichas* fichas) {
	Fichas* jardineroActual;

	while (!tablero->estaSolucionado())
	{
		if(fichas->quedanFichasPorVer() && tablero->encaja(fichas->fichaActual())) {

			//Genero siempre un jardinero para la ficha q estoy por intentar poner excepto en el borde de abajo
			if(!tablero->bordeAbajo()) {
				jardineroActual = fichas->generarJardinero(fichas->fichaActual()); //Pido memoria!
			}
			
			//Si estoy en el borde izquierdo o en el de abajo o si alguna ficha del jardinero que acabo de generar encaja con el del jardinero de la ultima ficha puesta
			if(tablero->bordeIzquierdo() || tablero->bordeAbajo() ||
				tablero->jardinero()->algunaEncaja(jardineroActual)) {

				//Si el tablero tiene un jardinero lo borro
				tablero->borrarJardinero(); //Libero memoria!

				//Si no estoy en el borde de abajo, agrego el nuevo jardinero al tablero
				if (!tablero->bordeAbajo()) {
					tablero->asignarJardinero(jardineroActual);
				}

				tablero->ponerFicha(fichas->fichaActual()); //Pongo la nueva ficha
				fichas->irAlInicio();
			} else {
				fichas->siguienteFicha();
			}

		}
		else 
		{
			if(fichas->quedanFichasPorVer()) {
				fichas->siguienteFicha();
			}
			else {
				if(tablero->estaVacio()) {
						return;
				}
				else {
					tablero->sacarFicha();
					fichas->irAlInicio();
				}
			}
		}
	}

}

void resolverPuzzleSinPoda(Tablero* tablero, Fichas* fichas) {

	while (!tablero->estaSolucionado())
	{
		if(fichas->quedanFichasPorVer() && tablero->encaja(fichas->fichaActual())) {
				tablero->ponerFicha(fichas->fichaActual()); //Pongo la nueva ficha
				fichas->irAlInicio();
		}
		else 
		{
			if(fichas->quedanFichasPorVer()) {
				fichas->siguienteFicha();
			}
			else {
				if(tablero->estaVacio()) {
						return;
				}
				else {
					tablero->sacarFicha();
					fichas->irAlInicio();
				}
			}
		}
	}

}
