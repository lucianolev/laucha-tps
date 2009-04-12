#include <stdlib.h>
#include "Fichas.h"

Fichas::Fichas() {
	m_primero = NULL;
	m_actual = NULL;
	m_quedanfichasporver = false;
}

Fichas::~Fichas() {
	Nodo* cursor = m_primero;
	Nodo* borrar;
	while (cursor != NULL) {
		borrar = cursor;
		cursor = cursor->siguiente;
		delete borrar;
	}
}

void Fichas::agregarFicha(Ficha ficha) {
	Nodo* nuevo = new Nodo();
	nuevo->f = ficha;
	nuevo->siguiente = m_primero;
	m_primero = nuevo;
	m_actual = nuevo;
	m_anterior = NULL;
	m_quedanfichasporver = true;
}

void Fichas::siguienteFicha() {
	if (m_actual->siguiente != 0) {
		m_anterior = m_actual;
		m_actual = m_actual->siguiente;
	} 
	else {
		m_quedanfichasporver = false;
	}
}

bool Fichas::quedanFichasPorVer() const {
		return m_quedanfichasporver;
}

Ficha Fichas::fichaActual() const {
		return m_actual->f;
}

void Fichas::eliminarFichaActual() {

	//Si no hay nodos vuelvo
	if(m_actual == NULL) {
		return;
	}

	Nodo* borrar = m_actual;

	//Si quiero eliminar el primer nodo
	if (m_primero == m_actual) {
		m_primero = m_actual->siguiente;
		m_actual = m_primero;
	}

	//Si es el ultimo nodo la lista se invalida! ES TURBIO
	if (m_actual->siguiente == NULL) {
		m_actual = NULL;
		m_anterior->siguiente = NULL;
		m_quedanfichasporver = false;
	}
	else {
		m_anterior->siguiente = m_actual->siguiente;
		m_actual = m_actual->siguiente;
	}

	delete borrar;
}

void Fichas::irAlInicio() {
	m_actual = m_primero;
	m_anterior = NULL;
	m_quedanfichasporver = true;
}

bool Fichas::algunaEncaja(Fichas* otrasfichas) const {
	if(m_primero == NULL || otrasfichas->m_primero == NULL) {
	  return false;
	}

	Nodo* cursor1 = m_primero;
	Nodo* cursor2 = otrasfichas->m_primero;
	while(cursor1 != NULL) {
		while(cursor2 != NULL) {
			if((cursor1->f).der == (cursor2->f).izq) {
				return true;
			}
			cursor2 = cursor2->siguiente;
		}
		cursor1 = cursor1->siguiente;
		cursor2 = otrasfichas->m_primero;
	}
	return false;
}

Fichas* Fichas::generarJardinero(Ficha ficha) const {
	Fichas* nuevojardinero = new Fichas();
	Nodo* cursor1 = m_primero;
	while(cursor1 != NULL) {
		if((cursor1->f).arriba == (ficha.abajo)) {
			nuevojardinero->agregarFicha(ficha);
		}
		cursor1 = cursor1->siguiente;
	}
	return nuevojardinero;
}

