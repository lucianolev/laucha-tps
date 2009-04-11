#include <iostream>

struct Ficha {
	int arriba;
	int abajo;
	int izq;
	int der;
};

struct Nodo {
	Ficha f;
	Nodo* siguiente;
};

class Jardinero {
	public:
		Jardinero();
		~Jardinero();
		void agregarFicha(Ficha f);
		bool puedoPodar(Jardinero* j);

	private: 
	 	Nodo* m_primero;
		unsigned int m_longitud;
};
