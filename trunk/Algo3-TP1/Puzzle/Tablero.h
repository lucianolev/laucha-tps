#include "Fichas.h"

class Tablero {

	public:
		Tablero(unsigned int n, Fichas* fichas_sueltas);
		~Tablero();
		void resolverPuzzleSinPoda();
		void resolverPuzzleConPoda();
		bool estaSolucionado() const { return m_solucionado; };
		Ficha ficha(unsigned int i, unsigned int j) const;

	private:
		void ponerFicha(Ficha ficha);
		void sacarUltimaFichaPuesta();

		bool encaja(Ficha ficha) const;

		bool estaVacio() { return (m_pos_i == 0 && m_pos_j == 0); };
		bool bordeIzquierdo() { return (m_pos_j == 0); };
		bool bordeDerecho() { return (m_pos_j == m_n-1); };
		bool bordeAbajo() { return (m_pos_i == m_n-1); };

	private:
		Ficha** m_matriz;
		unsigned int m_n;
		bool m_solucionado;
		unsigned int m_pos_i;
		unsigned int m_pos_j;
		Fichas* m_fichas_sueltas;
		Fichas* m_jardinero;

};