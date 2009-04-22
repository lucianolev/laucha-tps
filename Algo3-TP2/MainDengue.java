import java.io.IOException;

class MainDengue {

	public static void main(String argv[]) throws IOException {
		Dengue dengue = new Dengue();
		dengue.cargarDatosDeArchivo("Tp2Ej1.in");
 		dengue.resolverInstanciasCargadas();
 		dengue.guardarResultados("Tp2Ej1.out");
	}

}