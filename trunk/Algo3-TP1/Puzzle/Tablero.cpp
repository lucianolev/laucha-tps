#include <iostream>
#include "Tablero.h"

using namespace std;

Tablero::Tablero(unsigned int n, Fichas* fichas_sueltas) {
	m_n = n;

	m_matriz = new Ficha*[m_n];
	for(int i = 0; i < m_n; i++) {
		m_matriz[i] = new Ficha[m_n];
	}

	m_pos_i = 0;
	m_pos_j = 0;

	m_solucionado = false;
	m_fichas_sueltas = fichas_sueltas;
	m_jardinero = 0;
}

Tablero::~Tablero() {
	borrarJardinero();

	for (int i = 0; i < m_n; i++) {
	  delete m_matriz[i];
  }
	delete m_matriz;
}

void Tablero::resolverPuzzleSinPoda() {

	while (!this->estaSolucionado())
	{
		if(m_fichas_sueltas->quedanFichasPorVer() && this->encaja(m_fichas_sueltas->fichaActual())) {

				this->ponerFicha(m_fichas_sueltas->fichaActual());
				m_fichas_sueltas->irAlInicio();
		}
		else 
		{
			if(m_fichas_sueltas->quedanFichasPorVer()) {
				m_fichas_sueltas->siguienteFicha();
			}
			else {
				if(this->estaVacio()) {
						return;
				}
				else {
					this->sacarUltimaFichaPuesta();
					m_fichas_sueltas->irAlInicio();
				}
			}
		}
	}

}

void Tablero::resolverPuzzleConPoda() {
	Fichas* jardineroActual;

	while (!this->estaSolucionado())
	{
		if(m_fichas_sueltas->quedanFichasPorVer() && this->encaja(m_fichas_sueltas->fichaActual())) {

			//Genero siempre un jardinero para la ficha q estoy por intentar poner excepto en el borde de abajo
			if(!this->bordeAbajo()) {
				jardineroActual = m_fichas_sueltas->generarJardinero(m_fichas_sueltas->fichaActual()); //Pido memoria!
			}
			
			if(this->bordeIzquierdo() || this->bordeAbajo() ||
				m_jardinero->algunaEncaja(jardineroActual)) {

				//Si el tablero tiene un jardinero lo borro
				if (m_jardinero != 0) {
					delete m_jardinero;
				}

				//Si no estoy en el borde de abajo, agrego el nuevo jardinero al tablero
				if (!this->bordeAbajo()) {
					m_jardinero = jardineroActual;
				}

				tablero->ponerFicha(m_fichas_sueltas->fichaActual()); //Pongo la nueva ficha
				m_fichas_sueltas->irAlInicio(); //Vuelvo al comienzo de la lista de fichas
			} else {
				m_fichas_sueltas->siguienteFicha();
			}

		}
		else 
		{
			if(m_fichas_sueltas->quedanFichasPorVer()) {
				m_fichas_sueltas->siguienteFicha();
			}
			else {
				if(this->estaVacio()) {
						return;
				}
				else {
					this->sacarFicha();
					m_fichas_sueltas->irAlInicio();
				}
			}
		}
	}

}

void Tablero::ponerFicha(Ficha ficha) {
	m_matriz[m_pos_i][m_pos_j] = ficha;
	m_fichas_sueltas->eliminarFichaActual();

	if (m_pos_j == m_n-1) {
		m_pos_j = 0;
		m_pos_i++;
	} 
	else {
		m_pos_j++;
	}

	if(m_pos_i == m_n) {
		m_solucionado = true;
	}

}

void Tablero::sacarUltimaFichaPuesta() {
	m_fichas_sueltas->agregarFicha(m_matriz[m_pos_i][m_pos_j]);

	if (m_pos_j == 0) {
		m_pos_j = m_n-1;
		m_pos_i--;
	}
	else {
		m_pos_j--;
	}

}

bool Tablero::encaja(Ficha ficha) const {
	return ((m_pos_i == 0 || (m_matriz[m_pos_i-1][m_pos_j]).abajo == ficha.arriba) &&
			(m_pos_j == 0 || (m_matriz[m_pos_i][m_pos_j-1]).der == ficha.izq));
}

Ficha Tablero::ficha(unsigned int i, unsigned int j) const {
	return m_matriz[i][j];
}

