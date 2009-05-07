import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class RedAstor {

	private LinkedList listaDeInstancias;

	public RedAstor() {
		listaDeInstancias = new LinkedList();
	}

	public void cargarInstanciasDeArchivo(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			String line = null;
			StringTokenizer tokens = null;
			InstanciaDengue instancia = null;

			line = inputStream.readLine();
			tokens = new StringTokenizer(line, " ");
			int cantLocales = Integer.parseInt(tokens.nextToken());
			while(cantLocales != 0) {
				instancia = new InstanciaRedAstor();
				instancia.cantLocales = cantLocales;
				instancia.cantParesAstor = Integer.parseInt(tokens.nextToken());
				instancia.aristas = new Arista[(instancia.cantLocales*(instancia.cantLocales-1))/2];
				inputStream.readLine();
				int k = 0;
				for(int i = 1; i < instancia.cantLocales; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					for(int j = 0; j < i; j++) {
						instancia.aristas[k] = Integer.parseInt(tokens.nextToken());
						k++;
					}
					for(int)
				}
				listaDeInstancias.add(instancia);
				line = inputStream.readLine();
				tokens = new StringTokenizer(line, " ");
				zonas = Integer.parseInt(tokens.nextToken());
			}
		}
		finally {
			if (inputStream != null) {
				System.out.println("Se han leido "+listaDeInstancias.size()+" instancia/s del archivo "+nombreDelArchivo);
				inputStream.close();
			}
		}
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
				InstanciaDengue instanciaResuelta = (InstanciaDengue)iter.next();
				line = Integer.toString(instanciaResuelta.maxMosquitosMuertos);
				outputStream.write(line);
				outputStream.newLine();
				line = Arrays.toString(instanciaResuelta.litrosPorZona).replace(", ", " ");
				outputStream.write(line, 1, line.length()-2);
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