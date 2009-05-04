import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Diamante {

	private LinkedList listaDeInstancias;

	public Diamante() {
		listaDeInstancias = new LinkedList();
	}

	public void cargarInstanciasDeArchivo(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			StringTokenizer tokens = null;
			InstanciaDengue instancia = null;
			
			tokens = new StringTokenizer(inputStream.readLine(), " ");
			int cantNodos = Integer.parseInt(tokens.nextToken());
			while(cantNodos != 0) {
				instancia = new InstanciaDiamante(cantNodos);
				nodosDeGradoUno = new int[cantNodos];
				for(int i = 1; i <= instancia.cantNodos; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					LinkedList listaAdyacencia = new LinkedList();
					while(tokens.hasMoreTokens()) {
						listaAdyacencia.add(Integer.parseInt(tokens.nextToken()));
					}
/*					if (listaAdyacencia.size() > 0) {
						if (listaAdyacencia.size() == 1)
							nodosDeGradoUno[listaAdyacencia[0]] = i;
						instancia.adyacencias[i] = listaAdyacencia;
					} else {
						instancia.adyacencias[i] = null;
					}*/
				}
				
				tokens = new StringTokenizer(inputStream.readLine(), " ");
				cantNodos = Integer.parseInt(tokens.nextToken());
			}
		}
		finally {
			if (inputStream != null) {
				System.out.println("Se han leido "+listaDeInstancias.size()+" instancia/s del archivo "+nombreDelArchivo);
				inputStream.close();
			}
		}
	}

	public void resolverInstanciasCargadas() {
		ListIterator iter = listaDeInstancias.listIterator();
		while(iter.hasNext()) {
			buscarDiamante((InstanciaDiamante)iter.next()); //Resuelvo la instancia
		}

		System.out.println("Se han resuelto todas las instancias ingresadas! ("+listaDeInstancias.size()+" instancia/s)");
	}

	public void guardarResultados(String nombreDelArchivo) throws IOException {
		if (listaDeInstancias.size() == 0) {
			System.out.println("Error: No hay instancias resueltas para guardar!");
			return;
		}

		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			String line = null;
			ListIterator iter = listaDeInstancias.listIterator();

			while(iter.hasNext()) {
				InstanciaDiamante instanciaResuelta = (InstanciaDiamante)iter.next();
				if(instanciaResuelta.hayDiamante) {
					line = Arrays.toString(instanciaResuelta.diamanteMinimo).replace(", ", " ");
					outputStream.write(line, 1, line.length()-2);
				} else {
					line = "No hay diamante";
					outputStream.write(line, 0, line.length()-1);
				}
				outputStream.newLine();
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado "+listaDeInstancias.size()+" instancia/s resueltas en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
	}

}