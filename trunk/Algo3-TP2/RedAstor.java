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

	public static void mostrarListaAristas(LinkedList lista) {
		ListIterator iter = lista.listIterator();
		while(iter.hasNext()) {
			Arista actual = (Arista)iter.next();
			System.out.println("("+Integer.toString(actual.nodo1)+","+Integer.toString(actual.nodo2)+","+Integer.toString(actual.peso)+")");
		}
	}

	public void armarRed(InstanciaRedAstor instancia) {
		instancia.crearListaAristas();
		instancia.crearListaAstor();
		//DEBUG
		System.out.println("Sin ordenar:");
		mostrarListaAristas(instancia.aristasPorAgregar);
				
		Arista[] arrayTemp = new Arista[instancia.aristasPorAgregar.size()];
		instancia.aristasPorAgregar.toArray(arrayTemp);
		Arrays.sort(arrayTemp, new AristaComparator());
		
		instancia.aristasPorAgregar = new LinkedList(Arrays.asList(arrayTemp));
		//DEBUG
		System.out.println("Ordenada:");
		mostrarListaAristas(instancia.aristasPorAgregar);

		instancia.componentePorNodo = new int[instancia.cantLocales+1];
		instancia.magia = new int[instancia.cantLocales+1];
		for(int i = 0; i < instancia.cantLocales+1; i++) {
			instancia.componentePorNodo[i] = 0;
			instancia.magia[i] = 0;
		}

		//Meto las aristas de astor
		//DEBUG
		System.out.println("Las de astor:");
		mostrarListaAristas(instancia.aristasAstor);
		ListIterator iter = instancia.aristasAstor.listIterator();
		instancia.red = new LinkedList();
		while (iter.hasNext()) {
			Arista aristaActual = ((Arista)iter.next());
			instancia.costoProduccion += aristaActual.peso;
			Dupla dupla = new Dupla(aristaActual.nodo1, aristaActual.nodo2);
			instancia.meterArista(dupla);
		}

		//Empiezo Kruskal
		int cantAristasMetidas = instancia.cantParesAstor;
		iter = instancia.aristasPorAgregar.listIterator();
		while(iter.hasNext() && cantAristasMetidas < instancia.cantLocales) {
			Arista aristaActual = (Arista)iter.next();
			Dupla dupla = new Dupla(aristaActual.nodo1, aristaActual.nodo2);
			if(instancia.sePuedeMeter(dupla)) {
				instancia.costoProduccion += aristaActual.peso; 
				instancia.meterArista(dupla);
			}
		}

		Dupla[] arrayDuplaTemp = new Dupla[instancia.red.size()];
		instancia.red.toArray(arrayDuplaTemp);
		Arrays.sort(arrayDuplaTemp, new DuplaComparator());

		instancia.red = new LinkedList(Arrays.asList(arrayDuplaTemp));
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
				ListIterator iterRed = instanciaResuelta.red.listIterator();
				while(iterRed.hasNext()) {
					Dupla duplaActual = ((Dupla)iterRed.next());
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

	public void cargarInstanciasRandomConParesFijos(int limite, int rangoRandom) {
		System.out.println("Generando instancias aleatorias...");

		int j = 4;
		int c = 0;
		while (j <= limite) {
			InstanciaRedAstor instancia = new InstanciaRedAstor();
			instancia.generarInstanciaRandom(j, rangoRandom);
			listaDeInstancias.add(instancia);
			j++;
			c++;
		}

		System.out.println("Se han generado "+c+" instancias aleatorias!");
	}

}