import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Dengue {

	private LinkedList listaDeInstancias;

	public Dengue() {
		listaDeInstancias = new LinkedList();
	}

	//Genero limite^2 instancias random, desde zonas = 1, litros = 1 hasta zonas = limite, litros = limite y las cargo en la clase
	public void cargarInstanciasRandomHasta(int limite, int esparcimiento, int rangoRandom) {
		System.out.println("Generando instancias aleatorias...");

		int i = 1;
		int j = 1;
		int c = 0;
		while (j <= limite) {
			InstanciaDengue instancia = new InstanciaDengue();
			instancia.generarInstanciaRandom(i, j, rangoRandom);
			listaDeInstancias.add(instancia);
			if (i >= limite) {
				j += esparcimiento;
				i = 1;
			} else {
				i += esparcimiento;
			}
			c++;
		}

		System.out.println("Se han generado "+c+" instancias aleatorias!");
	}

	public void cargarInstanciasRandomConZonasFijoHasta(int zonas, int limiteLitros, int esparcimiento, int rangoRandom) {
		System.out.println("Generando instancias aleatorias...");

		int j = 1;
		int c = 0;
		while (j <= limiteLitros) {
			InstanciaDengue instancia = new InstanciaDengue();
			instancia.generarInstanciaRandom(zonas, j, rangoRandom);
			listaDeInstancias.add(instancia);
			j += esparcimiento;
			c++;
		}

		System.out.println("Se han generado "+c+" instancias aleatorias!");
	}

	public void cargarInstanciasRandomConLitrosFijoHasta(int litros, int limiteZonas, int esparcimiento, int rangoRandom) {
		System.out.println("Generando instancias aleatorias...");

		int i = 1;
		int c = 0;
		while (i <= limiteZonas) {
			InstanciaDengue instancia = new InstanciaDengue();
			instancia.generarInstanciaRandom(i, litros, rangoRandom);
			listaDeInstancias.add(instancia);
			i += esparcimiento;
			c++;
		}

		System.out.println("Se han generado "+c+" instancias aleatorias!");
	}

	//Leo de un archivo una lista de instancias y la cargo en el private
	public void cargarInstanciasDeArchivo(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			String line = null;
			StringTokenizer tokens = null;
			InstanciaDengue instancia = null;

			line = inputStream.readLine();
			tokens = new StringTokenizer(line, " ");
			int zonas = Integer.parseInt(tokens.nextToken());
			while(zonas != 0) {
				instancia = new InstanciaDengue();
				instancia.zonas = zonas;
				instancia.litros = Integer.parseInt(tokens.nextToken());
				instancia.mosquitosMuertos = new int[instancia.zonas][instancia.litros+1];
				for(int i = 0; i < instancia.zonas; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					instancia.mosquitosMuertos[i][0] = 0; //el litro 0 es 0 para cada zona
					for(int j = 1; j <= instancia.litros; j++) {
						instancia.mosquitosMuertos[i][j] = Integer.parseInt(tokens.nextToken());
					}
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

	//Resuelve una lista de instancias
	public void resolverInstanciasCargadas() {
		ListIterator iter = listaDeInstancias.listIterator();
		while(iter.hasNext()) {
			fumigar((InstanciaDengue)iter.next()); //Resuelvo la instancia
		}

		System.out.println("Se han resuelto todas las instancias ingresadas! ("+listaDeInstancias.size()+" instancia/s)");
	}

	//Guardo en un archivo las instancias resultas (listaDeInstancias)
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

	public void guardarTiemposDeFumigacion(String nombreDelArchivo) throws IOException {
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
				line = Integer.toString(instanciaResuelta.zonas);
				line += " ";
				line += Integer.toString(instanciaResuelta.litros);
				line += " ";
				line += Long.toString(instanciaResuelta.tiempoDeFumigacion);
				line += " ";
				line += Long.toString(instanciaResuelta.tiempoDeLitrosPorZona);
				outputStream.write(line);
				outputStream.newLine();
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado los tiempos de fumigacion de "+listaDeInstancias.size()+" instancia/s resueltas en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
		
	}

	//Resuelve una instancia, guardando en la misma el resultado obtenido
	private void fumigar(InstanciaDengue instancia) {
		long tiempoInicial = System.nanoTime();

		int[][] maxMMParcial = new int[instancia.zonas+1][instancia.litros+1];

		for(int j = 0; j <= instancia.litros; j++) {
			maxMMParcial[0][j] = 0;
		}

		for(int i = 1; i <= instancia.zonas; i++) {
			for(int j = 0; j <= instancia.litros; j++) {
				instancia.maxMosquitosMuertos = maxMMParcial[i-1][j];
				for(int k = 1; k <= j; k++) {
					instancia.maxMosquitosMuertos = Math.max(instancia.maxMosquitosMuertos, 
						maxMMParcial[i-1][j-k]+instancia.mosquitosMuertos[i-1][k]);
				}
				maxMMParcial[i][j] = instancia.maxMosquitosMuertos;
			}
		}

		instancia.tiempoDeFumigacion = (System.nanoTime() - tiempoInicial)/1000;

		tiempoInicial = System.nanoTime();

		instancia.litrosPorZona = new int[instancia.zonas];
		int litrosRestantes = instancia.litros;
		int j = litrosRestantes;
		for(int i = instancia.zonas; i >= 1; i--) {
			while(j >= 0 && 
				(maxMMParcial[i][litrosRestantes] - instancia.mosquitosMuertos[i-1][litrosRestantes - j]) !=
				maxMMParcial[i-1][j])
			{	
				j--;
			}
			instancia.litrosPorZona[i-1] = litrosRestantes - j;
			litrosRestantes = j;
		}
		instancia.resuelta = true;

		instancia.tiempoDeLitrosPorZona = (System.nanoTime() - tiempoInicial)/1000;

		System.out.println("->Zonas: "+instancia.zonas+". Litros: "+instancia.litros+". Mosquitos muertos: "+instancia.maxMosquitosMuertos+". Tiempo fumigacion: "+instancia.tiempoDeFumigacion+" us. Tiempo lts por zona: "+instancia.tiempoDeLitrosPorZona+" us");

	}

}