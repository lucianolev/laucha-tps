import InstanciaDengue;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;

class Dengue {

	private LinkedList listaDeInstanciasParaResolver;

	public Dengue() {
		listaDeInstanciasParaResolver = null;
	}

	//Resuelve una lista de instancias
	public void resolverInstancias(LinkedList listaDeInstancias) {
		listaDeInstanciasParaResolver = listaDeInstancias;
		ListIterator inter = listaDeInstanciasParaResolver.iterator();
		while(iter.hasNext()) {
			fumigar(iter.next()); //Resuelvo la instancia
		}

		System.out.println("Se han resuelto todas las instancias ingresadas! ("+listaDeInstancias.size()+" instancia/s)");
	}

	//Resuelve una instancia, guardando en la misma el resultado obtenido
	private void fumigar(InstanciaDengue instancia) {
		int[][] matriz = new int[zonas+1][litros+1];

		for(int j = 0; j <= litros; j++) {
			matriz[0][j] = 0;
		}

		for(int i = 1; i <= zonas; i++) {
			for(int j = 0; j <= litros; j++) {
				instancia.maxMosquitosMuertos = matriz[i-1][j];
				for(int k = 1; k <= j; k++) {
					instancia.maxMosquitosMuertos = Math.max(instancia.maxMosquitosMuertos, 
						matriz[i-1][j-k]+mosquitosMuertos[i-1][k-1]);
				}
				matriz[i][j] = instancia.maxMosquitosMuertos;
			}
		}

		//Falta guardar litrosPorZona

		System.out.println("El resultado optimo es "+matriz[zonas][litros]);
	}

	//Leo de un archivo una lista de instancias y la retorno
	public LinkedList leerDatosDeArchivo(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;
		listaDeInstancias = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			String line = null;
			StringTokenizer tokens = null;
			InstanciaDengue instancia = null;

			line = inputStream.readLine();
			tokens = new StringTokenizer(line, " ");
			int zonas = Integer.parseInt(tokens.nextToken());
			if (zonas != 0) {
				listaDeInstancias = new LinkedList();
			}
			while(zonas != 0) {
				instancia = new InstanciaDengue();
				instancia.zonas = zonas;
				instancia.litros = Integer.parseInt(tokens.nextToken());
				instancia.mosquitosMuertos = new int[zonas][litros];
				for(int i = 0; i < zonas; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					for(int j = 0; j < litros; j++) {
						instancia.mosquitosMuertos[i][j] = Integer.parseInt(tokens.nextToken());
					}
				}
				listaDeInstancias.maxMosquitosMuertos = 0; //Por ahora no hay solucion
				listaDeInstancias.litrosPorZona = null; //Por ahora no hay solucion
				listaDeInstancias.add(instancia);
				zonas = Integer.parseInt(tokens.nextToken());
			}
		}
		finally {
			if (inputStream != null) {
				System.out.println("Se han leido "+listaDeInstancias.size();+"instancia/s del archivo "+nombreDelArchivo);
				inputStream.close();
			}
			return listaDeInstancias;
		}
	}

	//Guardo en un archivo las instancias resultas (listaDeInstanciasParaResolver)
	public void guardarResultados(String nombreDelArchivo) throws IOException {
		if (listaDeInstanciasParaResolver == null) {
			System.out.println("Error: No hay instancias resueltas para guardar!");
			return;
		}

		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			ListIterator iter = listaDeInstanciasParaResolver.iterator();
			while(iter.hasNext()) {
				InstanciaDengue instanciaResuelta = iter.next();
				line = Integer.toString(instanciaResuelta.maxMosquitosMuertos);
				outputStream.write(line);
				outputStream.newLine();
				line = Arrays.toString(instanciaResuelta.litrosPorZona).replace(", ", " ");
				outputStream.write(line, 1, line.length()-2);
				outputStream.newLine();
				line = inputStream.readLine();
				tokens = new StringTokenizer(line, " ");
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado "+listaDeInstancias.size();+"instancia/s resueltas en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
	}

}

// 		int litros = 4;
// 		int zonas = 3;
		
// 		mosquitos_muertos[0][0] = 8;
// 		mosquitos_muertos[0][1] = 7;
// 		mosquitos_muertos[0][2] = 4;
// 		mosquitos_muertos[0][3] = 1;
// 		mosquitos_muertos[1][0] = 2;
// 		mosquitos_muertos[1][1] = 9;
// 		mosquitos_muertos[1][2] = 3;
// 		mosquitos_muertos[1][2] = 4;
// 		mosquitos_muertos[2][0] = 20;
// 		mosquitos_muertos[2][1] = 1;
// 		mosquitos_muertos[2][2] = 4;
// 		mosquitos_muertos[2][3] = 10;