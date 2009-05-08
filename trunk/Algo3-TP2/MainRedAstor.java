import java.io.IOException;
import java.io.FileNotFoundException;

class MainRedAstor {

	public static void main(String[] args) throws IOException {
		try {
			RedAstor red = new RedAstor();
			red.cargarInstanciasDeArchivo("Tp2Ej3.in");
			red.resolverInstanciasCargadas();
			red.guardarResultados("Tp2Ej3.out");

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo de entrada!");
			return;
		}
	}
}
