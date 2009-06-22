import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class EscritorDeGrafos {

	public EscritorDeGrafos() {
		listaDeGrafos = new LinkedList();
	}
	
	public void agregarGrafo(GrafoNPonderados unGrafo) {
		listaDeGrafos.add(unGrafo);
	}
	
	public void guardarGrafos(String nombreDelArchivo) throws IOException {
		
		if (listaDeGrafos.size() == 0) {
			System.out.println("Error: No hay grafos para guardar!");
			return;
		}

		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			String line = null;
			ListIterator iter = listaDeGrafos.listIterator();

			while(iter.hasNext()) {
				GrafoNPonderados unGrafo = (GrafoNPonderados)iter.next();
				line = Integer.toString(unGrafo.cantNodos());
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
				LinkedList[] adyacencias = unGrafo.adyacencias();
				for (int i = 1; i <= unGrafo.cantNodos(); i++) {
					line = Integer.toString(unGrafo.pesoNodo(i))+" ";
					ListIterator iterAdy = adyacencias[i].listIterator();
					while(iterAdy.hasNext()) {
						line += ((Integer)iterAdy.next()).toString()+" ";
					}
					outputStream.write(line, 0, line.length()-1);
					outputStream.newLine();
				}
			}
			line = "0";
			outputStream.write(line, 0, line.length());
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado "+listaDeGrafos.size()+" grafo/s en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
	}
	
	private LinkedList listaDeGrafos;
	
}
