#include "Jardinero.h"

Jardinero::Jardinero() {
	m_primero = NULL;
	m_longitud = 0;
}

Jardinero::~Jardinero() {
	Nodo* cursor = m_primero;
	Nodo* borrar; 
	while (cursor != NULL) {
		borrar = cursor;
		cursor = cursor->siguiente;
		delete borrar;
	}
}

void Jardinero::agregarFicha(Ficha unaFicha) {
	Nodo* nuevo = new Nodo();
	nuevo->f.arriba = unaFicha.arriba;
	nuevo->f.abajo = unaFicha.abajo;
	nuevo->f.der = unaFicha.der;
	nuevo->f.izq = unaFicha.izq;
	nuevo->siguiente = m_primero;
	m_primero = nuevo;
	m_longitud++;
}

bool Jardinero::puedoPodar(Jardinero* otroJardinero) {
	if(m_primero == NULL || otroJardinero->m_primero == NULL) {
	  return false;
	}
	Nodo* cursor1 = m_primero;
	Nodo* cursor2 = otroJardinero->m_primero;
	while(cursor1 != NULL) {
		while(cursor2 != NULL) {
			if((cursor1->f).der == (cursor2->f).izq) {
				return false;
			}
			cursor2 = cursor2->siguiente;
		}
		cursor1 = cursor1->siguiente;
		cursor2 = otroJardinero->m_primero;
	}
	return true;
}
