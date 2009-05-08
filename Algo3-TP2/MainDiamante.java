import java.io.IOException;
import java.io.FileNotFoundException;

class MainDiamante {

	public static void main(String[] args) throws IOException {
		try {
			Diamante diamante = new Diamante();
			diamante.cargarInstanciasDeArchivo("Tp2Ej2.in");
			diamante.resolverInstanciasCargadas();
			diamante.guardarResultados("Tp2Ej2.out");

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo de entrada!");
			return;
		}
	}
}