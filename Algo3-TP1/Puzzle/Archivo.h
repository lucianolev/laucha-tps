#include <stdlib.h>
#include <fstream>

using namespace std;

class Archivo {

public:
	Archivo(char* nombre, bool sololectura);
	~Archivo();
	void abrir(char* nombre, bool sololectura);
	template< class T > void escribirDato(T dato);
	void nuevaLinea();
	template< class T > void leerDato(T& dato);
// 	template< class T > void leerLinea(T* linea);
	void cerrar();
	
private:
	fstream m_datos;

};

Archivo::Archivo(char* nombre, bool sololectura = false) {
    abrir(nombre, sololectura);
}

void Archivo::abrir(char* nombre, bool sololectura = false) {
    
    if (sololectura) {
    	m_datos.open(nombre, fstream::in);
    } else {
    	m_datos.open(nombre, fstream::in | fstream::out | fstream::app);
    }
    
    if (!m_datos.is_open()) {
    	cout << "No se puede abrir el archivo!" << endl;
    	exit(0);
    }
  
}

Archivo::~Archivo() {
	if (m_datos.is_open()) {
		m_datos.close();
	}
}

template< class T > void Archivo::escribirDato(T dato) {
	m_datos << dato;
}

void Archivo::nuevaLinea() {
	m_datos << endl;
}

template< class T > void Archivo::leerDato(T& dato) {
	m_datos >> skipws >> dato;
}

// template< class T > void Archivo::leerLinea(T* datos, int cantdatos) {
// 	stringstream dato;
// 	m_datos >> noskipws >> dato;
// 	int i = 0;
// 	while(dato != '\n' && i < cantdatos) {
// 		if (dato != ' ' && dato != '\t') {
// 			T[i] = dato;
// 			i++;
// 		}
// 		m_datos >> noskipws >> dato;
// 	}
// }

void Archivo::cerrar() {
	m_datos.close();
}
