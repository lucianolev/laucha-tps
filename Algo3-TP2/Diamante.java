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
			InstanciaDiamante instancia = null;
			
			String line = inputStream.readLine();
			int cantNodos = Integer.parseInt(line);
			while(cantNodos != 0) {
				instancia = new InstanciaDiamante(cantNodos);
				for(int i = 1; i <= instancia.cantNodos; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					LinkedList listaAdyacencia = new LinkedList();
					while(tokens.hasMoreTokens()) {
						listaAdyacencia.add(Integer.valueOf(tokens.nextToken()));
					}
					instancia.adyacencias[i] = listaAdyacencia;
				}
				listaDeInstancias.add(instancia);
				line = inputStream.readLine();
				cantNodos = Integer.parseInt(line);
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

	public void buscarDiamante(InstanciaDiamante instancia) {
		//DEBUG
/* 		mostrarAdyacencias(instancia.adyacencias,instancia.cantNodos);*/
		long tiempoInicial = System.nanoTime();
		instancia.eliminarNodosChicos();
/* 		mostrarAdyacencias(instancia.adyacencias,instancia.cantNodos);*/
		instancia.armarMatrizDeAdyacencia();

		/*instancia.mostrarMatrizDeAdyacencia();*/ //DEBUG
		
		LinkedList diamantesMinimos = new LinkedList();
		for (int superNodo = 1; superNodo <= instancia.cantNodos; superNodo++) {
			if ((instancia.adyacencias[superNodo] != null) 
				&& (instancia.adyacencias[superNodo].size() >= 3)) 
			{
				LinkedList[] vecindadDeSuperNodo = instancia.crearVecindad(new Integer(superNodo));
				Integer[] diamanteMinimoVecindad = instancia.buscarDiamanteMinimoEnVecindad(new Integer(superNodo), vecindadDeSuperNodo);

				if(diamanteMinimoVecindad != null) {
					diamantesMinimos.add(diamanteMinimoVecindad);
				}

			}
		}
		
		if(diamantesMinimos.size() == 0) {
			instancia.hayDiamante = false;
			//DEBUG
			System.out.println("----------------");
			System.out.println("Cant de nodos: "+instancia.cantNodos);
			System.out.println("No hay diamante!");
			System.out.println("");
		} else {
			instancia.hayDiamante = true;
			ListIterator iter = diamantesMinimos.listIterator();
			instancia.diamanteMinimo = (Integer[])iter.next();
			Integer[] diamante = new Integer[4];
			while(iter.hasNext()) {
				diamante = (Integer[])iter.next();
				if(InstanciaDiamante.compararDiamantes(diamante, instancia.diamanteMinimo)) {
					instancia.diamanteMinimo = diamante;
				}
			}

			//DEBUG
			System.out.println("----------------");
			System.out.println("Cant de nodos: "+instancia.cantNodos);
			System.out.println("diamanteMinimo: "+instancia.diamanteMinimo[0]+" "+instancia.diamanteMinimo[1]+" "+instancia.diamanteMinimo[2]+" "+instancia.diamanteMinimo[3]);
			System.out.println("");
		}
		instancia.tiempoDeBusqueda = (System.nanoTime() - tiempoInicial)/1000;
	}

	public static void mostrarAdyacencias(LinkedList[] adyacencias, int cantNodos) {
		for(int superNodo = 1; superNodo <= cantNodos; superNodo++) {
			if (adyacencias[superNodo] != null) {
				ListIterator listaAdyacencia = adyacencias[superNodo].listIterator();
				System.out.print(superNodo+". ");
				while(listaAdyacencia.hasNext()) {
					System.out.print(((Integer)listaAdyacencia.next()).toString()+" ");
				}
				System.out.println("");
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
				InstanciaDiamante instanciaResuelta = (InstanciaDiamante)iter.next();
				if(instanciaResuelta.hayDiamante) {
					line = Arrays.toString(instanciaResuelta.diamanteMinimo).replace(", ", " ");
					outputStream.write(line, 1, line.length()-2);
				} else {
					line = "No hay diamante";
					outputStream.write(line, 0, line.length());
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

	public void cargarInstanciasRandom(int inicio, int limite) {
		for(int i = inicio; i <= limite; i++) {
			InstanciaDiamante nuevaInstancia = new InstanciaDiamante(i);
			nuevaInstancia.generarInstanciaRandom();
			listaDeInstancias.add(nuevaInstancia);
		}
	}

	public void cargarInstanciaCompleta(int paramCantNodos) {
		InstanciaDiamante nuevaInstancia = new InstanciaDiamante(paramCantNodos);
		nuevaInstancia.generarInstanciaCompleta();
		listaDeInstancias.add(nuevaInstancia);
	}

	public void guardarTiempos(String nombreDelArchivo) throws IOException {
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
				line = Integer.toString(instanciaResuelta.cantNodos);
				line += " ";
				line += Long.toString(instanciaResuelta.tiempoDeBusqueda);
				outputStream.write(line);
				outputStream.newLine();
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado los tiempos de busqueda de diamante de "+listaDeInstancias.size()+" instancia/s resueltas en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
		
	}

}