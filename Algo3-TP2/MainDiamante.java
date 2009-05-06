import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class MainDiamante {

	public static void main(String[] args) throws IOException {
		
		Diamante diamante = new Diamante();

// 		System.out.println("Modo de Uso: java MainDiamante (-in <archivoentrada>) || (-random <limite> <esparcimiento> <rangorandom> [-out <archivosalida>] [-guardartiempos <archivosalida>]");
// 		System.out.println();
		System.out.println("Modo de Uso: java MainDiamante -in <archivoentrada> [-out <archivosalida>]");
		System.out.println();

		ArrayList argslist = new ArrayList(args.length);
		for(int i = 0; i < args.length; i++) { 
			argslist.add(args[i]);
		}

		int indexIn;
		int indexRandom;
		int indexOut;
		int indexGuardarTiempos;
		try {
			if((indexIn = argslist.indexOf("-in")) >= 0) {
				diamante.cargarInstanciasDeArchivo(args[indexIn+1]);
				diamante.resolverInstanciasCargadas();
			} else if((indexRandom = argslist.indexOf("-random")) >= 0) {
				int inicio = Integer.parseInt(args[indexRandom+1]);
				int limite = Integer.parseInt(args[indexRandom+2]);
// 				int rangoRandom = Integer.parseInt(args[indexRandom+3]);
				diamante.cargarInstanciasRandom(inicio, limite);
				diamante.resolverInstanciasCargadas();
			}

			if((indexOut = argslist.indexOf("-out")) >= 0) {
				diamante.guardarResultados(args[indexOut+1]);
			}

			if((indexGuardarTiempos = argslist.indexOf("-guardartiempos")) >= 0) {
				diamante.guardarTiempos(args[indexGuardarTiempos+1]);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Parametros incorrectos!");
			return;
		} catch (NumberFormatException e) {
			System.out.println("Parametros incorrectos!");
			return;
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo de entrada!");
			return;
		}
	}
}