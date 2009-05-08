import java.io.IOException;
import java.io.FileNotFoundException;

class MainDengue {

	public static void main(String[] args) throws IOException {
		try {
			Dengue dengue = new Dengue();
			dengue.cargarInstanciasDeArchivo("Tp2Ej1.in");
			dengue.resolverInstanciasCargadas();
			dengue.guardarResultados("Tp2Ej1.out");

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo de entrada!");
			return;
		}

	}
}
