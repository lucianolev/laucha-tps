import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ResolvedorTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

//		generarGrafosDePrueba(3);
//		int tamGrafo = 30;
//		double densidad = 0.2;
//		
//		GrafoNPonderados elgrafo = new GrafoNPonderados(tamGrafo, densidad);
//		
//		ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
//		
		//resuelvo el problema mediante el metodo exacto
//		Solucion solucionExacta = resolvedor.resolverExacto();
//		//DEBUG
//		System.out.println("Solucion exacta");
//		solucionExacta.mostrarSolucion(elgrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaPesoGrado();
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso/grado");
//		solucionHConstructiva.mostrarSolucion(elgrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaConPeso();
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso");
//		solucionHConstructiva.mostrarSolucion(elgrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaConGrado();
//		//DEBUG
//		System.out.println("Heuristica constructiva con grado");
//		solucionHConstructiva.mostrarSolucion(elgrafo);
//		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaPesoVecindad();
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso vecindad");
//		solucionHConstructiva.mostrarSolucion(elgrafo);
		
//		//encuentro una solucion mediante una heuristica constructiva
//		solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0);
//		//DEBUG
//		System.out.println("Heuristica constructiva con peso/grado (alfa 0)");
//		solucionHConstructiva.mostrarSolucion(elgrafo);
		
		//compararHCBL("tablaPrueba.txt");
		//pruebasGrasp("testsGrasps.out", 30);
		//pruebasGrasp("testsGrasps100.out", 100);
		//pruebasGrasp("testsGrasps300.out", 300);
		
//		medicionesExacto("tiemposExacto.txt");
//		medicionesHC("tiemposHC.txt");
//		medicionesBL("tiemposBL.txt");
		
		return;
	}
	
	public static void medicionesExacto(String archivoSalida) throws IOException {
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			GrafoNPonderados grafo = null;
			ResolvedorCIPM resolvedor = null;
			
			for(int i = 1; i <= 36; i++) {
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
				long inicio = System.currentTimeMillis();
				resolvedor.heuristicaConstructivaPesoGrado();
				long fin = System.currentTimeMillis();
				long tiempo = (fin - inicio);
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.currentTimeMillis();
				resolvedor.heuristicaConstructivaPesoGrado();
				fin = System.currentTimeMillis();
				tiempo = (fin - inicio);
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.currentTimeMillis();
				resolvedor.heuristicaConstructivaPesoGrado();
				fin = System.currentTimeMillis();
				tiempo = (fin - inicio);
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
			
			for(int i = 1; i <= 600; i += 10) {
				String line = new String();
				grafo = new GrafoNPonderados(i,0.2);
				resolvedor = new ResolvedorCIPM(grafo);
				long inicio = System.currentTimeMillis();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 100);
				long fin = System.currentTimeMillis();
				long tiempo = (fin - inicio);
				line += i+" "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.5);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.currentTimeMillis();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 100);
				fin = System.currentTimeMillis();
				tiempo = (fin - inicio);
				line += " "+tiempo;
				
				grafo = new GrafoNPonderados(i,0.8);
				resolvedor = new ResolvedorCIPM(grafo);
				inicio = System.currentTimeMillis();
				solucion = resolvedor.heuristicaConstructivaConGrado();
				resolvedor.busquedaLocal2(solucion, 100);
				fin = System.currentTimeMillis();
				tiempo = (fin - inicio);
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

	public static void pruebasViejas() {
		
//		LectorDeGrafos lector = new LectorDeGrafos("TP3.in");
//		EscritorDeSoluciones escritor = new EscritorDeSoluciones();
		int tamGrafo = 300;
		double densidad = 1;
		GrafoNPonderados elgrafo = new GrafoNPonderados(tamGrafo, densidad);
		GrafoNPonderados elgrafoOrd = new GrafoNPonderados(elgrafo);
		elgrafoOrd.ordenarAdyacenciasPorPeso();
		
		//elgrafo.mostrarGrafo();
		
//		while(lector.quedanGrafos()) {
//			GrafoNPonderados elgrafo = lector.dameProximoGrafo();
			double alfaRCL = 0.99;
			int cantIteracionesLocal = 100;
			//int cantIteracionesGrasp = 30;
			
			ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
			ResolvedorCIPM resolvedor2 = new ResolvedorCIPM(elgrafoOrd);
		
//			//resuelvo el problema mediante el metodo exacto
//			Solucion solucionExacta = resolvedor.resolverExacto();
//			//escritor.agregarSolucion(solucionExacta);
//			//DEBUG
//			System.out.println("Solucion exacta");
//			solucionExacta.mostrarSolucion(elgrafo);
			
			//encuentro una solucion mediante una heuristica constructiva
			Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(alfaRCL);
			//escritor.agregarSolucion(solucionHConstructiva);
			//DEBUG
			System.out.println("Heuristica constructiva con peso/grado con alfa = "+alfaRCL);
			solucionHConstructiva.mostrarSolucion(elgrafo);
			
			//hago una busqueda local
			Solucion mejorSolucion = resolvedor.busquedaLocal2(solucionHConstructiva, cantIteracionesLocal);
			//escritor.agregarSolucion(mejorSolucion);
			//DEBUG
			System.out.println("Busqueda Local 2");
			mejorSolucion.mostrarSolucion(elgrafo);
			
			//hago una busqueda local
			mejorSolucion = resolvedor2.busquedaLocal2(solucionHConstructiva, cantIteracionesLocal);
			//escritor.agregarSolucion(mejorSolucion);
			//DEBUG
			System.out.println("Busqueda Local 2 (ord por peso)");
			mejorSolucion.mostrarSolucion(elgrafoOrd);
			
//			//grasp
//			Solucion solucionGrasp = resolvedor.graspPesoGrado2(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//			//escritor.agregarSolucion(solucionExacta);
//			//DEBUG
//			System.out.println("Solucion GRASP 2 con peso/grado");
//			solucionGrasp.mostrarSolucion(elgrafo);
//			
//			//grasp
//			solucionGrasp = resolvedor2.graspPesoGrado2(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//			//escritor.agregarSolucion(solucionExacta);
//			//DEBUG
//			System.out.println("Solucion GRASP 2 con peso/grado (ord por peso)");
//			solucionGrasp.mostrarSolucion(elgrafoOrd);
			
			//probarHeuristicasCons(elgrafo, resolvedor, alfaRCL);
			
			//probarGrasps(elgrafo, resolvedor, alfaRCL, cantIteracionesGrasp, cantIteracionesLocal);
//		}
		
//		escritor.guardarSoluciones("TP3.out");		
		
		return;
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
