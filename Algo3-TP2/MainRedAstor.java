import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class MainRedAstor {

	public static void main(String[] args) throws IOException {
		System.out.println("Modo de Uso: java MainRedAstor (-in <archivoentrada>) || (-random <limite> <esparcimiento> <rangorandom> [-fijarparametro <cantidad> (litros || zonas)]) [-out <archivosalida>] [-guardartiempos <archivosalida>]");
		System.out.println();

		RedAstor red = new RedAstor();
		ArrayList argslist = new ArrayList(args.length);
		for(int i = 0; i < args.length; i++) { 
			argslist.add(args[i]);
		}

		int indexIn;
// 		int indexRandom;
// 		int indexFijarParametro;
		int indexOut;
// 		int indexGuardarTiempos;
		try {
			if((indexIn = argslist.indexOf("-in")) >= 0) {
				red.cargarInstanciasDeArchivo(args[indexIn+1]);
				red.resolverInstanciasCargadas();
			} /*else if((indexRandom = argslist.indexOf("-random")) >= 0) {
				int limite = Integer.parseInt(args[indexRandom+1]);
				int esparcimiento = Integer.parseInt(args[indexRandom+2]);
				int rangoRandom = Integer.parseInt(args[indexRandom+3]);
				if((indexFijarParametro = argslist.indexOf("-fijarparametro")) >= 0) {
					int cantidad = Integer.parseInt(args[indexFijarParametro+1]);
					if (args[indexFijarParametro+2].equals("litros")) {
						dengue.cargarInstanciasRandomConLitrosFijoHasta(cantidad, limite, esparcimiento, rangoRandom);
					}
					else if (args[indexFijarParametro+2].equals("zonas")) {
						dengue.cargarInstanciasRandomConZonasFijoHasta(cantidad, limite, esparcimiento, rangoRandom);
					} else {
						ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
						throw e;
					}
				} else {
					dengue.cargarInstanciasRandomHasta(limite, esparcimiento, rangoRandom);
				}
				dengue.resolverInstanciasCargadas();
			}*/

			if((indexOut = argslist.indexOf("-out")) >= 0) {
				red.guardarResultados(args[indexOut+1]);
			}

// 			if((indexGuardarTiempos = argslist.indexOf("-guardartiempos")) >= 0) {
// 				red.guardarTiemposDeFumigacion(args[indexGuardarTiempos+1]);
// 			}
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