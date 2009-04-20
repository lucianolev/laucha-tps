import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.IOException;

class Dengue {

	public static void main(String argv[]) throws IOException {
		BufferedReader inputStream = null;
		BufferedWriter outputStream = null;
		try {
 			inputStream = new BufferedReader(new FileReader("Tp2Ej1.in"));
			outputStream = new BufferedWriter(new FileWriter("Tp2Ej1.out"));
			String line = null;
			StringTokenizer tokens = null;
			line = inputStream.readLine();
			tokens = new StringTokenizer(line, " ");
			zonas = Integer.parseInt(tokens.nextToken());
			while(zonas != 0) {
				litros = Integer.parseInt(tokens.nextToken());
				mosquitosMuertos = new int[zonas][litros];
				for(int i = 0; i < zonas; i++) {
					tokens = new StringTokenizer(inputStream.readLine(), " ");
					for(int j = 0; j < litros; j++) {
						mosquitosMuertos[i][j] = Integer.parseInt(tokens.nextToken());
					}
				}
				fumigar();
				line = Integer.toString(maxMosquitosMuertos);
				outputStream.write(line);
				outputStream.newLine();
				line = Arrays.toString(litrosPorZona).replace(", ", " ");
				outputStream.write(line, 1, line.length()-2);
				outputStream.newLine();
				line = inputStream.readLine();
				tokens = new StringTokenizer(line, " ");
				zonas = Integer.parseInt(tokens.nextToken());
			}
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void fumigar() {
		int[][] matriz = new int[zonas+1][litros+1];

		for(int j = 0; j <= litros; j++) {
			matriz[0][j] = 0;
		}

		for(int i = 1; i <= zonas; i++) {
			for(int j = 0; j <= litros; j++) {
				maxMosquitosMuertos = matriz[i-1][j];
				for(int k = 1; k <= j; k++) {
					maxMosquitosMuertos = Math.max(maxMosquitosMuertos, matriz[i-1][j-k]+mosquitosMuertos[i-1][k-1]);
				}
				matriz[i][j] = maxMosquitosMuertos;
			}
		}

		System.out.println("El resultado optimo es "+matriz[zonas][litros]);
	}

	private static int[][] mosquitosMuertos;
	private static int[] litrosPorZona;
	private static int maxMosquitosMuertos;
	private static int zonas;
	private static int litros;
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