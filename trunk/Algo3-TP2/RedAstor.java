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
			InstanciaRedAstor instancia = null;

			line = inputStream.readLine();
			tokens = new StringTokenizer(line, " ");
			int cantLocales = Integer.parseInt(tokens.nextToken());
			while(cantLocales != 0) {
				instancia = new InstanciaRedAstor();
				instancia.cantLocales = cantLocales;
				instancia.aristasAstor = new LinkedList();
				instancia.cantParesAstor = Integer.parseInt(tokens.nextToken());
				instancia.matrizPesos = new int[instancia.cantLocales][instancia.cantLocales];
				for(int i = 0; i < instancia.cantLocales; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					for(int j = 0; j < instancia.cantLocales; j++) {
						instancia.matrizPesos[i][j] = Integer.parseInt(tokens.nextToken());
					}
				}

				for(int i = 0; i < instancia.cantParesAstor; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					Arista nuevaArista = new Arista();
					nuevaArista.nodo1 = Integer.parseInt(tokens.nextToken());
					nuevaArista.nodo2 = Integer.parseInt(tokens.nextToken());
					instancia.aristasAstor.add(nuevaArista);
				}
				
				listaDeInstancias.add(instancia);
				line = inputStream.readLine();
				tokens = new StringTokenizer(line, " ");
				cantLocales = Integer.parseInt(tokens.nextToken());
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
			armarRed((InstanciaRedAstor)iter.next()); //Resuelvo la instancia
		}

		System.out.println("Se han resuelto todas las instancias ingresadas! ("+listaDeInstancias.size()+" instancia/s)");
	}

	public void armarRed(InstanciaRedAstor instancia) {
		instancia.crearListaAristas();
		instancia.crearListaAstor();

		Arista[] arrayTemp = new Arista[instancia.aristasPorAgregar.size()];
		instancia.aristasPorAgregar.toArray(arrayTemp);
		Arrays.sort(arrayTemp, new AristaComparator());
		
		instancia.aristasPorAgregar = (LinkedList)Arrays.asList(arrayTemp);

		instancia.componentePorNodo = new int[instancia.cantLocales];
		instancia.magia = new int[instancia.cantLocales];
		for(int i = 0; i < instancia.cantLocales; i++) {
			instancia.componentePorNodo[i] = 0;
			instancia.magia[i] = 0;
		}

		ListIterator iter = instancia.aristasAstor.listIterator();
		while (iter.hasNext()) {
			instancia.red = new LinkedList();
			Arista aristaActual = ((Arista)iter.next());
			instancia.costoProduccion =+ aristaActual.peso;
			Dupla dupla = new Dupla(aristaActual.nodo1, aristaActual.nodo2);
			instancia.meterArista(dupla);
		}

		int cantAristasMetidas = instancia.cantParesAstor;
		iter = instancia.aristasPorAgregar.listIterator();
		while(iter.hasNext() && cantAristasMetidas < instancia.cantLocales) {
			Arista aristaActual = (Arista)iter.next();
			Dupla dupla = new Dupla(aristaActual.nodo1, aristaActual.nodo2);
			if(instancia.sePuedeMeter(dupla)) {
				instancia.costoProduccion =+ aristaActual.peso; 
				instancia.meterArista(dupla);
			}
		}

		Dupla[] arrayDuplaTemp = new Dupla[instancia.red.size()];
		instancia.red.toArray(arrayDuplaTemp);
		Arrays.sort(arrayDuplaTemp, new DuplaComparator());

		instancia.red = (LinkedList)Arrays.asList(arrayTemp);
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
				InstanciaRedAstor instanciaResuelta = (InstanciaRedAstor)iter.next();
				line = Integer.toString(instanciaResuelta.costoProduccion);
				outputStream.write(line);
				outputStream.newLine();
				iter = instanciaResuelta.red.listIterator();
				while(iter.hasNext()) {
					Dupla duplaActual = (Dupla)iter.next();
					line = Integer.toString(duplaActual.prim)+" "+Integer.toString(duplaActual.seg);
					outputStream.write(line, 0, line.length());
					outputStream.newLine();
				}
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