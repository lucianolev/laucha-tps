class Dengue {
	public static void main(String argv[]) {
		int litros = 4;
		int zonas = 3;
		int maximo;
		int[][] mosquitos_muertos = new int[zonas][litros];
		int[][] matriz = new int[zonas+1][litros+1];
		
		mosquitos_muertos[0][0] = 8;
		mosquitos_muertos[0][1] = 7;
		mosquitos_muertos[0][2] = 4;
		mosquitos_muertos[0][3] = 1;
		mosquitos_muertos[1][0] = 2;
		mosquitos_muertos[1][1] = 9;
		mosquitos_muertos[1][2] = 3;
		mosquitos_muertos[1][2] = 4;
		mosquitos_muertos[2][0] = 20;
		mosquitos_muertos[2][1] = 1;
		mosquitos_muertos[2][2] = 4;
		mosquitos_muertos[2][3] = 10;
		
		for(int j = 0; j <= litros; j++) {
			matriz[0][j] = 0;
		}

		for(int i = 1; i <= zonas; i++) {
			for(int j = 0; j <= litros; j++) {
				maximo = matriz[i-1][j];
				for(int k = 1; k <= j; k++) {
					maximo = Math.max(maximo, matriz[i-1][j-k]+mosquitos_muertos[i-1][k-1]);
				}
				matriz[i][j] = maximo;
			}
		}

		System.out.println("El resultado optimo es "+matriz[zonas][litros]);
	}
}