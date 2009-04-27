import java.lang.Math;

public class InstanciaDengue {

	public InstanciaDengue() {
		zonas = 0;
		litros = 0;
		mosquitosMuertos = null;
		resuelta = false;
		litrosPorZona = null;
		tiempoDeFumigacion = 0;
		tiempoDeLitrosPorZona = 0;
		maxMosquitosMuertos = 0;
	}

	public void generarInstanciaRandom(int paramZonas, int paramLitros, int rangoRandom) {
		zonas = paramZonas;
		litros = paramLitros;
		mosquitosMuertos = new int[zonas][litros+1];
		for (int i = 0; i < paramZonas; i++) {
			mosquitosMuertos[i][0] = 0; //el litro 0 es 0 para cada zona
			for (int j = 1; j <= paramLitros; j++) {
				mosquitosMuertos[i][j] = (int)(Math.random()*rangoRandom);
			}
		}
	}

	public int zonas;
	public int litros;
	public int[][] mosquitosMuertos;
	public boolean resuelta;
	public int maxMosquitosMuertos;
	public int[] litrosPorZona;
	public long tiempoDeFumigacion;
	public long tiempoDeLitrosPorZona;

}