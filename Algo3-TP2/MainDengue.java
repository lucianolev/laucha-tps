import Dengue;

import java.util.LinkedList;

class MainDengue {

	public static void main(String argv[]) {
		Dengue dengue = new Dengue();
		LinkedList listaDeInstancias = dengue.leerDatosDeArchivo("Tp2Ej1.in");
		dengue.resolverInstancias(listaDeInstancias);
		dengue.guardarResultados("Tp2Ej1.out");
	}

}