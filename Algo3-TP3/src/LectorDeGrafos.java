import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ListIterator;

public class LectorDeGrafos {

	public LectorDeGrafos(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			StringTokenizer tokens = null;
			
			String line = inputStream.readLine();
			int cantNodos = Integer.parseInt(line);
			while(cantNodos != 0) {
				int[] pesoNodos = new int[cantNodos+1];
				LinkedList[] adyacencias = new LinkedList[cantNodos+1];
				for(int i = 1; i <= cantNodos; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					LinkedList listaAdyacencia = new LinkedList();
					pesoNodos[i] = (Integer.valueOf(tokens.nextToken())).intValue();
					while(tokens.hasMoreTokens()) {
						listaAdyacencia.add(Integer.valueOf(tokens.nextToken()));
					}
					adyacencias[i] = listaAdyacencia;
				}
				GrafoNPonderados unGrafo = new GrafoNPonderados(cantNodos, adyacencias, pesoNodos);
				listaDeGrafos.add(unGrafo);
				line = inputStream.readLine();
				cantNodos = Integer.parseInt(line);
			}
		}
		finally {
			if (inputStream != null) {
				System.out.println("Se han leido "+listaDeGrafos.size()+" grafo/s del archivo "+nombreDelArchivo);
				inputStream.close();
			}
		}
		iter = listaDeGrafos.listIterator();
	}

	public GrafoNPonderados dameProximoGrafo() {
		return (GrafoNPonderados)iter.next();
	}

	public boolean quedanGrafos() {
		return iter.hasNext();
	}

	private LinkedList listaDeGrafos;
	private ListIterator iter;
}