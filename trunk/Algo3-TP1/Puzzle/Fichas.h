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

class Fichas {

	public:
		Fichas();
		~Fichas();
		void agregarFicha(Ficha ficha);
		Ficha fichaActual() const;
		void eliminarFichaActual();
		void siguienteFicha();
		void irAlInicio();
		bool quedanFichasPorVer() const;
		bool algunaEncaja(Fichas* otrasfichas) const;

		Fichas* generarJardinero(Ficha ficha) const;

	private:
		Nodo* m_primero;
		Nodo* m_actual;
		Nodo* m_anterior;
		bool m_quedanfichasporver;

};