import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class EscritorDeSoluciones {

	public EscritorDeSoluciones() {
		listaDeSoluciones = new LinkedList();
	}

	public void agregarSolucion(Solucion unaSolucion) {
		listaDeSoluciones.add(unaSolucion);
	}

	public void guardarSoluciones(String nombreDelArchivo) throws IOException {

		if (listaDeSoluciones.size() == 0) {
			System.out.println("Error: No hay soluciones para guardar!");
			return;
		}

		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			String line = null;
			ListIterator iter = listaDeSoluciones.listIterator();

			while(iter.hasNext()) {
				Solucion unaSolucion = (Solucion)iter.next();
				line = Integer.toString(unaSolucion.peso());
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
				ListIterator iterConj = unaSolucion.iterSolucion();
				line = new String();
				while(iterConj.hasNext()) {
					line += ((Integer)iterConj.next()).toString()+" ";
				}
				outputStream.write(line, 0, line.length()-1);
				outputStream.newLine();
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado "+listaDeSoluciones.size()+" solucion/es en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}

	}

	private LinkedList listaDeSoluciones;

}