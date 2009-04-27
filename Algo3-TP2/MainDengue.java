import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class MainDengue {

	public static void main(String[] args) throws IOException {
		System.out.println("Modo de Uso: java MainDengue (-in <archivoentrada>) || (-random <limite> <esparcimiento> [-fijarparametro <cantidad> (litros || zonas)]) [-out <archivosalida>] [-guardartiempos <archivosalida>]");
		System.out.println();

		Dengue dengue = new Dengue();
		ArrayList argslist = new ArrayList(args.length);
		for(int i = 0; i < args.length; i++) { 
			argslist.add(args[i]);
		}

		int indexIn;
		int indexRandom;
		int indexFijarParametro;
		int indexOut;
		int indexGuardarTiempos;
		try {
			if((indexIn = argslist.indexOf("-in")) >= 0) {
				dengue.cargarInstanciasDeArchivo(args[indexIn+1]);
				dengue.resolverInstanciasCargadas();
			} else if((indexRandom = argslist.indexOf("-random")) >= 0) {
				int limite = Integer.parseInt(args[indexRandom+1]);
				int esparcimiento = Integer.parseInt(args[indexRandom+2]);
				if((indexFijarParametro = argslist.indexOf("-fijarparametro")) >= 0) {
					int cantidad = Integer.parseInt(args[indexFijarParametro+1]);
					if (args[indexFijarParametro+2].equals("litros")) {
						dengue.cargarInstanciasRandomConLitrosFijoHasta(cantidad, limite, esparcimiento);
					}
					else if (args[indexFijarParametro+2].equals("zonas")) {
						dengue.cargarInstanciasRandomConZonasFijoHasta(cantidad, limite, esparcimiento);
					} else {
						ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
						throw e;
					}
				} else {
					dengue.cargarInstanciasRandomHasta(limite, esparcimiento);
				}
				dengue.resolverInstanciasCargadas();
			}

			if((indexOut = argslist.indexOf("-out")) >= 0) {
				dengue.guardarResultados(args[indexOut+1]);
			}

			if((indexGuardarTiempos = argslist.indexOf("-guardartiempos")) >= 0) {
				dengue.guardarTiemposDeFumigacion(args[indexGuardarTiempos+1]);
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
