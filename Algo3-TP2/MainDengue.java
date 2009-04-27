import java.io.IOException;
import java.util.Arrays;

class MainDengue {

	public static void main(String[] args) throws IOException {
		Dengue dengue = new Dengue();

		int index;
		if((index = Arrays.binarySearch(args, "-test")) >= 0) {
			try {
				int limite = Integer.parseInt(args[index+1]);
				int esparcimiento = Integer.parseInt(args[index+2]);
				if((index = Arrays.binarySearch(args, "-fijarzonas")) >= 0) {
					int zonas = Integer.parseInt(args[index+1]);
					dengue.cargarInstanciasRandomConZonasFijoHasta(zonas, limite, esparcimiento);
				} else if ((index = Arrays.binarySearch(args, "-fijarlitros")) >= 0) {
					int litros = Integer.parseInt(args[index+1]);
					dengue.cargarInstanciasRandomConLitrosFijoHasta(litros, limite, esparcimiento);
				} else {
					dengue.cargarInstanciasRandomHasta(limite, esparcimiento);
				}
				dengue.resolverInstanciasCargadas();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Parametros incorrectos!");
				System.out.println("Modo de Uso:");
				System.out.println("java MainDengue [-noguardarresultados] [-guardartiempos] [-test <limite> <esparcimiento> [-fijarzonas <zonas>] [-fijarlitros <litros>] ]  ");
				return;
			}
		} else {
			dengue.cargarInstanciasDeArchivo("Tp2Ej1.in");
			dengue.resolverInstanciasCargadas();
		}

		if(!(Arrays.binarySearch(args, "-noguardarresultados") >= 0)) {
			dengue.guardarResultados("Tp2Ej1.out");
		}

		if(Arrays.binarySearch(args, "-guardartiempos") >= 0) {
			dengue.guardarTiemposDeFumigacion("Tp2Ej1-TiemposFumigacion.out");
		}

	}
}
