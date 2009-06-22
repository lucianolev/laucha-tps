import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ResolvedorTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

//		generarGrafosDePrueba(3);
//		int tamGrafo = 40;
//		double densidad = 0.8;
//		
//		GrafoNPonderados elgrafo = new GrafoNPonderados(tamGrafo, densidad);
//		
//		ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
//		
//		//resuelvo el problema mediante el metodo exacto
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
		
		compararHCBL("tablaPrueba.txt");
		
		return;
	}
	
	public static void compararHCBL(String archivoSalida) throws IOException  {
		
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(archivoSalida));
			String line = null;
			
			String[] nombres = new String[9];
			nombres[0] = "grafos50bd.in";
			nombres[1] = "grafos50md.in";
			nombres[2] = "grafos50ad.in";
			nombres[3] = "grafos300bd.in";
			nombres[4] = "grafos300md.in";
			nombres[5] = "grafos300ad.in";
			nombres[6] = "grafos700bd.in";
			nombres[7] = "grafos700md.in";
			nombres[8] = "grafos700ad.in";
			
			int cantIteraciones = 1000;
			
			for(int i = 0; i < 9; i++) {
			
				LectorDeGrafos lector = new LectorDeGrafos(nombres[i]);
				
				while(lector.quedanGrafos()) {
					GrafoNPonderados elgrafo = lector.dameProximoGrafo();
					ResolvedorCIPM resolvedor = new ResolvedorCIPM(elgrafo);
					Solucion solucion = null;
			
//					if(i < 3) {
//						//Solucion Exacta
//						solucion = resolvedor.resolverExacto();
//						line = Integer.toString(solucion.peso());
//					} else {
						line = ";";
//					}
					
					//Heuristica PesoGrado
					solucion = resolvedor.heuristicaConstructivaPesoGrado();
					line += ";"+Integer.toString(solucion.peso());
					Solucion bl1 = resolvedor.busquedaLocal(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl1.peso())+" ("+cantIteraciones+")";
					cantIteraciones = 1000;
					Solucion bl2 = resolvedor.busquedaLocal2(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl2.peso())+" ("+cantIteraciones+")";
					
					//Heuristica Peso			
					solucion = resolvedor.heuristicaConstructivaConPeso();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl1.peso())+" ("+cantIteraciones+")";
					cantIteraciones = 1000;
					bl2 = resolvedor.busquedaLocal2(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl2.peso())+" ("+cantIteraciones+")";
					
					//Heuristica Grado
					solucion = resolvedor.heuristicaConstructivaConGrado();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl1.peso())+" ("+cantIteraciones+")";
					cantIteraciones = 1000;
					bl2 = resolvedor.busquedaLocal2(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl2.peso())+" ("+cantIteraciones+")";
					
					//Heuristica PesoVecindad
					solucion = resolvedor.heuristicaConstructivaPesoVecindad();
					line += ";"+Integer.toString(solucion.peso());
					bl1 = resolvedor.busquedaLocal(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl1.peso())+" ("+cantIteraciones+")";
					cantIteraciones = 1000;
					bl2 = resolvedor.busquedaLocal2(solucion, cantIteraciones);
					line += ";"+Integer.toString(bl2.peso())+" ("+cantIteraciones+")";
					
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
			GrafoNPonderados grafo50bd = new GrafoNPonderados(50, 0.2);
			escritor.agregarGrafo(grafo50bd);
		}
		escritor.guardarGrafos("grafos50bd.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo50md = new GrafoNPonderados(50, 0.5);
			escritor.agregarGrafo(grafo50md);
		}
		escritor.guardarGrafos("grafos50md.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo50ad = new GrafoNPonderados(50, 0.8);
			escritor.agregarGrafo(grafo50ad);
		}
		escritor.guardarGrafos("grafos50ad.in");

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
			GrafoNPonderados grafo700bd = new GrafoNPonderados(700, 0.2);
			escritor.agregarGrafo(grafo700bd);
		}
		escritor.guardarGrafos("grafos700bd.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo700md = new GrafoNPonderados(700, 0.5);
			escritor.agregarGrafo(grafo700md);
		}
		escritor.guardarGrafos("grafos700md.in");
		
		escritor = new EscritorDeGrafos();
		for(int i = 0; i < cantGrafosPorTipo; i++) {
			GrafoNPonderados grafo700ad = new GrafoNPonderados(700, 0.8);
			escritor.agregarGrafo(grafo700ad);
		}
		escritor.guardarGrafos("grafos700ad.in");
		
		return;
	}
	
	public static void probarConAlfas(GrafoNPonderados elGrafo, ResolvedorCIPM resolvedor) {
		
		//encuentro una solucion mediante una heuristica constructiva
		Solucion solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0.25);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.25");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0.5);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.5");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0.75);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.75");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
		//encuentro una solucion mediante una heuristica constructiva
		solucionHConstructiva = resolvedor.heuristicaConstructivaGrasp(0.99);
		//escritor.agregarSolucion(solucionHConstructiva);
		//DEBUG
		System.out.println("Heuristica constructiva con alfa = 0.99");
		solucionHConstructiva.mostrarSolucion(elGrafo);
		
	}
	
	public static void probarGrasps(GrafoNPonderados elGrafo, ResolvedorCIPM resolvedor, double alfaRCL, int cantIteracionesGrasp, int cantIteracionesLocal) {
		
		//grasp
		Solucion solucionGrasp = resolvedor.grasp(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
		//escritor.agregarSolucion(solucionExacta);
		//DEBUG
		System.out.println("Solucion GRASP con peso/grado");
		solucionGrasp.mostrarSolucion(elGrafo);
		
//		//grasp
//		solucionGrasp = resolvedor.graspPeso(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con peso");
//		solucionGrasp.mostrarSolucion(elGrafo);
//		
//		//grasp
//		solucionGrasp = resolvedor.graspGrado(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con grado");
//		solucionGrasp.mostrarSolucion(elGrafo);
//		
//		//grasp
//		solucionGrasp = resolvedor.graspPesoVecindad(cantIteracionesGrasp, alfaRCL, cantIteracionesLocal);
//		//escritor.agregarSolucion(solucionExacta);
//		//DEBUG
//		System.out.println("Solucion GRASP con peso/vecindad");
//		solucionGrasp.mostrarSolucion(elGrafo);
		
	}

}
