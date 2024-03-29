import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ResolvedorTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
	
		//medicionesBL("hasta2000.out");

		//generarGrafosDePrueba(3);
		
		//compararHCBL("tablaPrueba.txt");
		//pruebasGrasp("testsGrasps.out", 30);
		//pruebasGrasp("testsGrasps100.out", 100);
		//pruebasGrasp("testsGrasps300.out", 300);
		
		//medicionesExacto("tiemposExacto.txt");
		//medicionesHC("tiemposHC.txt");
		//medicionesBL("tiemposBL.txt");

		//optimizarCantIterParaAlfa("pesosCantIter.txt", 0.2);
		//medicionesGrasp("tiemposGrasp.txt", 0.2, 70);
		//pruebaTodos("pruebaTodos.txt");

		return;
	}
	
	public static void medicionesExacto(String archivoSalida) throws IOException {
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = null;
			ResolvedorCIPM resolvedor = null;
			
			for(int i = 1; i <= 37; i++) {
				String line = new String();
				grafo = new GrafoNPonderados(i,0.2);
				resolvedor = new ResolvedorCIPM(grafo);
				long inicio = System.nanoTime();
				resolvedor.resolverExacto();
				long fin = System.nanoTime();
				long tiempo = (fin - inicio)/1000;
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.resolverExacto();
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.resolverExacto();
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
			}
			
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se guardaron los tiempos para el exacto en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}
	
	public static void medicionesHC (String archivoSalida)  throws IOException  {
		
		BufferedWriter outputStream = null;
		try {
			
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = null;
			ResolvedorCIPM resolvedor = null;
			
			for(int i = 1; i <= 1000; i += 10) {
				String line = new String();
				grafo = new GrafoNPonderados(i,0.2);
				resolvedor = new ResolvedorCIPM(grafo);
				long inicio = System.nanoTime();
				resolvedor.heuristicaConstructivaPesoGrado();
				long fin = System.nanoTime();
				long tiempo = (fin - inicio)/1000;
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.heuristicaConstructivaPesoGrado();
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.heuristicaConstructivaPesoGrado();
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
			}
			
		}
		
		finally {
			if (outputStream != null) {
				System.out.println("Se guardaron los tiempos para la HC en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}
	
	public static void medicionesBL (String archivoSalida)  throws IOException  {
		
		BufferedWriter outputStream = null;
		try {
			
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = null;
			ResolvedorCIPM resolvedor = null;
			Solucion solucion = null;
			
			
			for(int i = 20; i <= 4000; i += 20) {
				String line = new String();
				grafo = new GrafoNPonderados(i,0.2);
				resolvedor = new ResolvedorCIPM(grafo);
				long inicio = System.nanoTime();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 300);
				long fin = System.nanoTime();
				long tiempo = (fin - inicio)/1000;
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 300);
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 300);
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
			}
			
		}
		
		finally {
			if (outputStream != null) {
				System.out.println("Se guardaron los tiempos para la BL en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}

	public static void optimizarCantIterParaAlfa(String archivoSalida, double alfa) throws IOException {
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = new GrafoNPonderados(500, 0.5);
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(grafo);
			
			Solucion solucion = null;
			String line = null;
			for(int i = 1; i <= 200 ; i += 1) {	
				solucion = resolvedor.grasp(i, alfa, 1000);
				line = i+" "+solucion.peso();
				solucion = resolvedor.graspConGrado(i, alfa, 1000);
				line += " "+solucion.peso();
				System.out.println("Resolvi grafo para iteraciones = "+i);
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado los resultados en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}

	public static void medicionesGrasp(String archivoSalida, double alfa, int cantIterGrasp) throws IOException {
		BufferedWriter outputStream = null;
		try {
			
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = null;
			ResolvedorCIPM resolvedor = null;
			
			for(int i = 10; i <= 800; i += 10) {
				String line = new String();
				grafo = new GrafoNPonderados(i,0.2);
				resolvedor = new ResolvedorCIPM(grafo);
				long inicio = System.nanoTime();
				resolvedor.grasp(cantIterGrasp, alfa, 1000);
				long fin = System.nanoTime();
				long tiempo = (fin - inicio)/1000;
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.grasp(cantIterGrasp, alfa, 1000);
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.nanoTime();
				resolvedor.grasp(cantIterGrasp, alfa, 1000);
				fin = System.nanoTime();
				tiempo = (fin - inicio)/1000;
				line += " "+tiempo;
				
				outputStream.write(line, 0, line.length());
				outputStream.newLine();
				
				System.out.println("hice grasp con grafos de tamaño "+i);
			}
			
		}
		
		finally {
			if (outputStream != null) {
				System.out.println("Se guardaron los tiempos para la BL en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}

	public static void compararHCBL(String archivoSalida) throws IOException  {
		
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			String line = null;
			
			String[] nombres = new String[9];
			nombres[0] = "grafos30bd.in";
			nombres[1] = "grafos30md.in";
			nombres[2] = "grafos30ad.in";
			nombres[3] = "grafos300bd.in";
			nombres[4] = "grafos300md.in";
			nombres[5] = "grafos300ad.in";
			nombres[6] = "grafos600bd.in";
			nombres[7] = "grafos600md.in";
			nombres[8] = "grafos600ad.in";
			
			for(int i = 0; i < 9; i++) {
			
				LectorDeGrafos lector = new LectorDeGrafos("../grafos/"+nombres[i]);
				
				while(lector.quedanGrafos()) {
					GrafoNPonderados elgrafo = lector.dameProximoGrafo();
					ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
					Solucion solucion = null;
			
					if(i < 3) {
						//Solucion Exacta
						solucion = resolvedor.resolverExacto();
						line = Integer.toString(solucion.peso());
						System.out.println("Termine un exacto");
					}
					
					//Heuristica PesoGrado
					solucion = resolvedor.heuristicaConstructivaPesoGrado();
					line += ";"+Integer.toString(solucion.peso());
					Solucion bl1 = resolvedor.busquedaLocal(solucion, 1000);
					line += ";"+Integer.toString(bl1.peso())+" ("+resolvedor.cantIteracionesBL+")";
					Solucion bl2 = resolvedor.busquedaLocal2(solucion, 1000);
					line += ";"+Integer.toString(bl2.peso())+" ("+resolvedor.cantIteracionesBL+")";
					
					//Heuristica Peso			
					solucion = resolvedor.heuristicaConstructivaConPeso();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, 1000);
					line += ";"+Integer.toString(bl1.peso())+" ("+resolvedor.cantIteracionesBL+")";
					bl2 = resolvedor.busquedaLocal2(solucion, 1000);
					line += ";"+Integer.toString(bl2.peso())+" ("+resolvedor.cantIteracionesBL+")";
					
					//Heuristica Grado
					solucion = resolvedor.heuristicaConstructivaConGrado();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, 1000);
					line += ";"+Integer.toString(bl1.peso())+" ("+resolvedor.cantIteracionesBL+")";
					bl2 = resolvedor.busquedaLocal2(solucion, 1000);
					line += ";"+Integer.toString(bl2.peso())+" ("+resolvedor.cantIteracionesBL+")";
					
					//Heuristica PesoVecindad
					solucion = resolvedor.heuristicaConstructivaPesoVecindad();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, 1000);
					line += ";"+Integer.toString(bl1.peso())+" ("+resolvedor.cantIteracionesBL+")";
					bl2 = resolvedor.busquedaLocal2(solucion, 1000);
					line += ";"+Integer.toString(bl2.peso())+" ("+resolvedor.cantIteracionesBL+")";
					
					outputStream.write(line, 0, line.length());
					outputStream.newLine();
				}
			}
		}
		
		finally {
			if (outputStream != null) {
				System.out.println("Se creo la tabla de prueba en el archivo "+archivoSalida);
				outputStream.close();
			}
		}
	}
	
	public static void pruebasGrasp(String nombreDelArchivo, int cantIteracionesGrasp) throws IOException {
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			int cantIteracionesLocal = 200;
			
			String[] archivos = new String[9];
			archivos[0] = "grafos30bd.in";
			archivos[1] = "grafos30md.in";
			archivos[2] = "grafos30ad.in";
			archivos[3] = "grafos300bd.in";
			archivos[4] = "grafos300md.in";
			archivos[5] = "grafos300ad.in";
			archivos[6] = "grafos600bd.in";
			archivos[7] = "grafos600md.in";
			archivos[8] = "grafos600ad.in";

			for(int i = 0; i < 9 ; i++) { 
				LectorDeGrafos lector = new LectorDeGrafos("../grafos/"+archivos[i]);
				while(lector.quedanGrafos()) {
					 GrafoNPonderados elgrafo = lector.dameProximoGrafo();
					 ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
					
					 String line = new String();
					 for(double alfaRCL = 0.1; alfaRCL < 1; alfaRCL+=0.1) {
						 Solucion solucionGrasp = resolvedor.grasp(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
						 line += solucionGrasp.peso()+";";
					 }
					 outputStream.write(line, 0, line.length());
					 outputStream.newLine();
				}
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado los resultados en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
	}
	
	public static void pruebaTodos(String nombreDelArchivo) throws IOException {
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(nombreDelArchivo));
			int cantIteracionesLocal = 200;
			int cantIteracionesGrasp = 70;
			double alfaRCL = 0.2;
			
			String[] archivos = new String[3];
			archivos[0] = "grafos600bd.in";
			archivos[1] = "grafos600md.in";
			archivos[2] = "grafos600ad.in";

			long inicio;
			long fin;
			long tiempoHC;
			long tiempoBL;
			long tiempoGrasp;
			
			for(int i = 0; i < 3 ; i++) { 
				LectorDeGrafos lector = new LectorDeGrafos("../grafos/"+archivos[i]);
				while(lector.quedanGrafos()) {
					 GrafoNPonderados elgrafo = lector.dameProximoGrafo();
					 ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
								
					 inicio = System.nanoTime();
					 Solucion solucionHCPG = resolvedor.heuristicaConstructivaPesoGrado();
					 fin = System.nanoTime();
					 tiempoHC = (fin - inicio)/(1000*1000);
					 
					 inicio = System.nanoTime();
					 Solucion solucionBL = resolvedor.heuristicaConstructivaConGrado();
					 solucionBL = resolvedor.busquedaLocal2(solucionBL, cantIteracionesLocal);
					 fin = System.nanoTime();
					 tiempoBL = (fin - inicio)/(1000*1000);
					 
					 inicio = System.nanoTime();
					 Solucion solucionGrasp = resolvedor.grasp(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
					 fin = System.nanoTime();
					 tiempoGrasp = (fin - inicio)/(1000*1000);					 
					 
					 String line = new String();
					 line += solucionHCPG.peso()+" ("+tiempoHC+" ms.);"+solucionBL.peso()+" ("+tiempoBL+" ms.);"+solucionGrasp.peso()+" ("+tiempoGrasp+" ms.);";
					 
					 System.out.println("Se han realizado todas las pruebas para un grafo");
					 
					 outputStream.write(line, 0, line.length());
					 outputStream.newLine();
				}
			}
		}
		finally {
			if (outputStream != null) {
				System.out.println("Se han guardado los datos totales en el archivo "+nombreDelArchivo);
				outputStream.close();
			}
		}
	}
	
	public static void generarGrafosDePrueba(int cantGrafosPorTipo) throws IOException {
		
		EscritorDeGrafos escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo30bd = new GrafoNPonderados(30, 0.2);
			escritor.agregarGrafo(grafo30bd);
		}
		escritor.guardarGrafos("grafos30bd.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo30md = new GrafoNPonderados(30, 0.5);
			escritor.agregarGrafo(grafo30md);
		}
		escritor.guardarGrafos("grafos30md.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo30ad = new GrafoNPonderados(30, 0.8);
			escritor.agregarGrafo(grafo30ad);
		}
		escritor.guardarGrafos("grafos30ad.in");

		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo300bd = new GrafoNPonderados(300, 0.2);
			escritor.agregarGrafo(grafo300bd);
		}
		escritor.guardarGrafos("grafos300bd.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo300md = new GrafoNPonderados(300, 0.5);
			escritor.agregarGrafo(grafo300md);
		}
		escritor.guardarGrafos("grafos300md.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo300ad = new GrafoNPonderados(300, 0.8);
			escritor.agregarGrafo(grafo300ad);
		}
		escritor.guardarGrafos("grafos300ad.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo600bd = new GrafoNPonderados(600, 0.2);
			escritor.agregarGrafo(grafo600bd);
		}
		escritor.guardarGrafos("grafos600bd.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo600md = new GrafoNPonderados(600, 0.5);
			escritor.agregarGrafo(grafo600md);
		}
		escritor.guardarGrafos("grafos600md.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo600ad = new GrafoNPonderados(600, 0.8);
			escritor.agregarGrafo(grafo600ad);
		}
		escritor.guardarGrafos("grafos600ad.in");
		
		return;
	}

}
