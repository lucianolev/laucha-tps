public class LectorDeGrafo {

	public void LectorDeGrafo(String nombreDelArchivo) throws IOException {
		BufferedReader inputStream = null;

		try {
 			inputStream = new BufferedReader(new FileReader(nombreDelArchivo));
			StringTokenizer tokens = null;
			
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

}