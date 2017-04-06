package tester;

import java.util.*;
import asignatura.*;
import sistema.*;
import ejercicio.*;
public class Tester {
	
	private static final boolean VISIBLE = true;
	private static final boolean NO_VISIBLE = false;
	private static final boolean RESTA = true;
	private static final boolean NO_RESTA = false;
	public static void main(String args[]){
		
		/**
		 * CARGAR Y GUARDAR DATOS
		 */
		System.out.println("\nProbando --> Cargar y Guardar Datos <--\n");
		//Creamos dos sistemas
		Sistema sistema = Sistema.getInstance();
		List<Alumno> alumnosAntes = new ArrayList<Alumno>();
		boolean comprobacion = false;
		try{
			//En el primer sistema introducimos datos de alumnos
			sistema.leerDatosAlumno("src/alumnos.txt");
			//Guardamos el sistema en un archivo
			sistema.guardarDatosSistema("src/Sistema.obj");
			
			//Comprobamos que los alumnos se han cargado correctamente
			for (Alumno alumno: sistema.getAlumnos()){
				alumnosAntes.add(alumno);
			}
			
			//Cargamos el sistema anteriormente guardado en un nuevo Sistema
			sistema.cargarDatosSistema("src/Sistema.obj");
			
			//Comprobamos que en el nuevo Sistema, la informacion no se ha alterado
			for (int i = 0; i < sistema.getAlumnos().size(); i++){
				if (alumnosAntes.get(i).getNombre().equals(sistema.getAlumnos().get(i).getNombre())){
					comprobacion = true;
				}else{
					comprobacion = false;
				}
			}
			
			//Comprobar que cada alumno queda totalmente inalterado
			if (comprobacion == true){
				System.out.print("Los alumnos no se han alterado, este mensaje SI debe aparecer");
			}
			
		}catch (Exception e){
			System.err.println("Error");
		}
		
		/**
		 * CREAR ASIGNATURAS
		 */
		System.out.println("\nProbando --> Crear asignaturas <--\n");
		//Añadiremos ahora asignaturas
		String nombreAsignatura = "PADSOF";
		if (sistema.crearAsignatura(nombreAsignatura, VISIBLE) == false){
			System.out.println("Como no hemos hecho login, este mensaje SI debe aparecer");
		}
		
		//Hacemos login en la aplicacion como profesores
		sistema.log_in("Profesor", "profeduudle");
		if (sistema.crearAsignatura(nombreAsignatura, VISIBLE) == false){
			System.out.println("Ahora hemos hecho login, este mensaje NO debe aparecer");
		}else{
			System.out.println("La asignatura se ha creado correctamente, este mensaje SI debe aparecer");
		}
		
		if (sistema.getAsignaturas().size() == 1){
			System.out.println("El tamaño del array de Asignaturas es 1, este mensaje SI debe aparecer");
		}
		
		//Creamos otra asignatura
		String nombreAsignatura2 = "ADSOF";
		if (sistema.crearAsignatura(nombreAsignatura2, NO_VISIBLE) == true){
			System.out.println("Se ha creado correctamente la segunda asignatura, este mensaje SI debe aparecer");
		}
		
		//Creamos una tercera asignatura
		String nombreAsignatura3 = "SOPER";
		if (sistema.crearAsignatura(nombreAsignatura3, VISIBLE) == true){
			System.out.println("Se ha creado correctamente la tercera asignatura, este mensaje SI debe aparecer");
		}
		
		//Ahora haremos solicitudes de matriculas
		if (sistema.crearSolicitud(sistema.getAsignaturas().get(0)) == true){
			System.out.println("En este momento, estamos logueados como profesor, este mensaje NO debe aparecer");
		}
		
		/**
		 * CREAR UNA SOLICITUD
		 */
		System.out.println("\nProbando --> Crear una solicitud <--\n");
		//Hacemos login con el alumno Jorge Alcazar
		sistema.log_in("1289", "JoA");
		if (sistema.crearSolicitud(sistema.getAsignaturas().get(0)) == true){
			System.out.println("Ahora estamos logueados como alumno, este mensaje SI debe aparecer");
		}
		
		/*Nos aseguramos de que tanto en el array de Solicitudes de Jorge Alcazar como en el de Sistema, 
		  se ha generado la solicitud*/
		
		Alumno jorgeAlcazar = null;
		//Encontrar a Jorge Alcazar
		for (Alumno alumAux: sistema.getAlumnos()){
			if (alumAux.getNumA().equals("1289") && alumAux.getPassword().equals("JoA")){
				jorgeAlcazar = alumAux;
				break;
			}
		}
		
		if (jorgeAlcazar.getSolicitudes().size() == 1){
			System.out.println("Jorge Alcazar tiene una solicitud, este mensaje SI debe aparecer");
		}
		
		if (sistema.getSolicitudes().size() == 1){
			System.out.println("Sistema tiene una solicitud, este mensaje SI debe aparecer");
		}
		
		/**
		 * ACEPTAR SOLICITUD 
		 */
		System.out.println("\nProbando --> Aceptar solicitud <--\n");
		//Ahora haremos que el profesor acepte la solicitud del alumno
		
		//Antes de nada, loguearemos como Profesor
		sistema.log_in("Profesor", "profeduudle");
		
		sistema.aceptarSolicitud(sistema.getSolicitudes().get(0));
		
		//Para comprobar el correcto funcionamiento del metodo anterior, tenemos que hacer varias comprobaciones
		
		//Primero: comprobar que el array de solicitudes de Jorge Alcazar y de Sistema esta vacio
		if (jorgeAlcazar.getSolicitudes().size() == 0){
			System.out.println("Jorge Alcazar ya no tiene solicitudes, este mensaje SI debe aparecer");
		}
		
		if (sistema.getSolicitudes().size() == 0){
			System.out.println("Sistema ya no tiene solicitudes, este mensaje SI debe aparecer");
		}
		
		//Segundo: comprobar que la asignatura de PADSOF tiene un alumno matriculado, y es Jorge Alcazar
		
		Asignatura padsof = null;
		/*Encontrar la asignatura que se llama PADSOF*/
		for (Asignatura asigAux: sistema.getAsignaturas()){
			if (asigAux.getNombre().equals("PADSOF")){
				padsof = asigAux;
			}
		}
		
		if (padsof.getAlumnos().size() == 1){
			System.out.println("Solo hay un alumno y su informacion es: \n" + padsof.getAlumnos().get(0));
		}
		
		//Tercero: comprobar que Jorge Alcazar tiene una nueva asignatura, y que esta asignatura es PADSOF
		if (jorgeAlcazar.getAsignaturas().size() == 1){
			System.out.println("Solo hay una asignatura y su nombre es: " + jorgeAlcazar.getAsignaturas().get(0).getNombre());
		}
		
		/**
		 * CASO ESPECIAL DE CREAR SOLICITUD
		 */
		System.out.println("\nProbando --> Crear solicitud (caso especial) <--\n");
		// Un alumno no podra crear solicitudes de una asignatura en la que ya esta matriculado
		
		//Hacemos login con el alumno Jorge Alcazar
		sistema.log_in("1289", "JoA");
		if (sistema.crearSolicitud(sistema.getAsignaturas().get(0)) == true) {
			System.out.println("Jorge Alcazar ha podido hacer la solicitud, este mensaje NO debe aparecer");
		} else {
			System.out.println("Jorge Alcazar no ha podido hacer la solicitud, este mensaje SI debe aparecer");
		}
		
		/**
		 * DENEGAR SOLICITUD
		 */
		System.out.println("\nProbando --> Denegar una solicitud <--\n");
		//Logueamos como Jorge Alcazar, y pedimos solicitud para la asignatura ADSOF		
		sistema.log_in("1289", "JoA");
		
		Asignatura adsof = null;
		/*Encontrar la asignatura que se llama ADSOF*/
		for (Asignatura asigAux: sistema.getAsignaturas()){
			if (asigAux.getNombre().equals("ADSOF")){
				adsof = asigAux;
			}
		}
		
		if (sistema.crearSolicitud(adsof) == true){
			System.out.println("Hemos creado la solicitud de Jorge Alcazar para ADSOF, este mensaje SI debe aparecer");
		}
		
		//Logueamos ahora como el profesor
		sistema.log_in("Profesor", "profeduudle");
		sistema.denegarSolicitud(sistema.getSolicitudes().get(0));
		
		//Comprobar que la solicitud desaparece de los arrays de Sistema y de Jorge Alcazar
		if (jorgeAlcazar.getAsignaturas().size() == 1 && jorgeAlcazar.getSolicitudes().size() == 0){
			System.out.println("Jorge Alcazar solo tiene una asignatura y ninguna solicitud pendiente");
		}
		
		if (sistema.getSolicitudes().size() == 0){
			System.out.println("No hay ninguna solicitud pendiente en sistema");
		}
		
		/**
		 * EXPULSAR A UN ALUMNO
		 */
		System.out.println("\nProbando --> Expulsar a un alumno <--\n");
		//Expulsaremos ahora a Jorge Alcazar de PADSOF
		
		sistema.expulsarAlumno(jorgeAlcazar, padsof);
		
		//De nuevo, para verificar el correcto funcionamiento del metodo expulsarAlumno() debemos comprobar varias cosas
		
		//Primero: verificar que Jorge Alcazar ya no pertenece a los alumnos de PADSOF
		if (padsof.getAlumnos().size() == 0){
			System.out.println("Jorge Alcazar ya no pertenece a los alumnos de PADSOF, este mensaje SI debe aparecer");
		}
		
		//Segundo: verificar que PADSOF ya no se encuentra entre las asignaturas de Jorge Alcazar
		if (jorgeAlcazar.getAsignaturas().size() == 0){
			System.out.println("PADSOF ya no se encuentra entre las asignaturas de Jorge Alcazar, este mensaje SI debe aparecer");
		}
		
		//Tercero: verificar que sistema ha añadido una expulsion al array de Expulsiones
		
		if (sistema.getExpulsiones().size() == 1){
			System.out.println("Sistema tiene una expulsion registrada, este mensaje SI debe aparecer");
		}
		
		/**
		 * REVOCAR UNA EXPULSION
		 */
		System.out.println("\nProbando --> Revocar una expulsion <--\n");
		//El profesor revoca una expulsion, dando esta por finalizada
		sistema.revocarExpulsion(sistema.getExpulsiones().get(0));
		
		//Verificamos que el alumno vuelve a estar admitido en la asignatura
		if (padsof.getAlumnos().contains(jorgeAlcazar) == true){
			System.out.println("Jorge Alcazar esta readmitido en la asignatura PADSOF, este mensaje SI debe aparecer");
		}
		
		//Verificamos que PADSOF vuelve a estar entre las asignaturas de Jorge Alcazar
		if (jorgeAlcazar.getAsignaturas().contains(padsof) == true){
			System.out.println("PADSOF se encuentra entre las asignaturas de Jorge Alcazar, este mensaje SI debe aparecer");
		}
		
		//Verificamos que la expulsion ya ha sido eliminada del array de Expulsiones de sistema
		if (sistema.getExpulsiones().size() == 0){
			System.out.println("El sistema ya no tiene expulsiones registradas, este mensaje SI debe aparecer");
		}
		
		/**
		 * CREAR UN TEMA
		 */
		System.out.println("\nProbando --> Crear tema <--\n");
		String nombreTema = "Analisis y diseño";
		
		//Creamos el tema en la asignatura PADSOF
		sistema.crearTema(padsof, nombreTema, VISIBLE);
		
		//Verificar que la asignatura PADSOF tiene ahora un tema
		if (padsof.getTemas().size() == 1){
			System.out.println("PADSOF tiene un tema añadido, este mensaje SI debe aparecer");
		}
		
		/**
		 * ELIMINAR TEMA
		 */
		System.out.println("\nProbando --> Eliminar tema <--\n");		
		//Queremos hacer uso mas adelante del tema "Analisis y diseño", por lo que crearemos otro tema para eliminarlo despues
		String nombreTema2 = "Diagramas de clases";
		
		//Creamos el tema en la asignatura PADSOF
		sistema.crearTema(padsof, nombreTema2, VISIBLE);
		
		//Verificar que la asignatura PADSOF tiene ahora dos tema
		if (padsof.getTemas().size() == 2){
			System.out.println("PADSOF tiene ahora dos temas, este mensaje SI debe aparecer");
		}
				
		padsof.eliminarTema(padsof.getTemas().get(1));
		
		//Verificar que la asignatura PADSOF vuelve a tener solo un tema
		if (padsof.getTemas().size() == 1) {
			System.out.println("PADSOF vuelve a tener solo un tema, este mensaje SI debe aparecer");
		}
		
		/**
		 * CREAR APUNTE
		 */
		System.out.println("\nProbando --> Crear apunte <--\n");
		//Encontrar tema cuyo titulo es "Analisis y diseño"
		Tema Analisis = null;
		for (Tema temaAux: padsof.getTemas()){
			if (temaAux.getNombre().equals("Analisis y diseño")){
				Analisis = temaAux;
			}
		}
		
		String nombreApunte = "Apuntes Tema 1";
		String contenidoApunte = "...";
		
		sistema.crearApunte(Analisis, nombreApunte, VISIBLE, contenidoApunte);
		
		//Verificar que el tema de Analisis y diseño tiene ahora un apunte
		if (Analisis.getApuntes().size() == 1){
			System.out.println("Analisis y diseño tiene un nuevo apunte, este mensaje SI debe aparecer");
		}
		
		/**
		 * ELIMINAR APUNTE
		 */
		System.out.println("\nProbando --> Eliminar apunte <--\n");
		 //Crearemos un apunte arbitrario para añadirlo y eliminarlo posteriormente
		String nombreApunte2 = "Apuntes Tema 2";
		String contenidoApunte2 = "...";
		
		sistema.crearApunte(Analisis, nombreApunte2, VISIBLE, contenidoApunte2);
		
		//Verificar que el tema de Analisis y diseño tiene ahora un apunte
		if (Analisis.getApuntes().size() == 2){
			System.out.println("Analisis y diseño tiene ahora dos apuntes, este mensaje SI debe aparecer");
		}
		
		//Eliminamos el apunte
		Analisis.eliminarApunte(Analisis.getApuntes().get(1));
		
		//Verificar que el tema de Analisis y diseño tiene de nuevo un solo apunte
		if (Analisis.getApuntes().size() == 1){
			System.out.println("Analisis y diseño tiene solo un apunte de nuevo, este mensaje SI debe aparecer");
		}
		
		/**
		 * CREAR EJERCICIO
		 */
		System.out.println("\nProbando --> Crear ejercicio <--\n");
		//Preparamos los parametros del Ejercicio
		String nombreEjercicio = "Ejercicio 1";
		float pesoEjercicio = 10;
		int anyoFin = 2017;
		int mesFin = 4;
		int diaFin = 23;
		int horaFin = 9;
		int minFin = 0;
		int anyoIni = 2017;
		int mesIni = 3;
		int diaIni = 23;
		int horaIni = 9;
		int minIni = 0;
		
		//Lo creamos y lo añadimos a un tema
		sistema.crearEjercicio(Analisis, nombreEjercicio, pesoEjercicio, anyoFin, mesFin, diaFin, horaFin, minFin, anyoIni, mesIni, diaIni, horaIni, minIni, VISIBLE);
		//Verificar que el tema de Analisis y Diseño tiene ahora un ejercicio
		if (Analisis.getEjercicios().size() == 1){
			System.out.println("Analisis y diseño tiene un nuevo ejercicio, este mensaje SI debe aparecer");
		}
		
		/**
		 * ELIMINAR EJERCICIO
		 */
		System.out.println("\nProbando --> Eliminar ejercicio <--\n");
		//Crearemos un ejercicio arbitrario para añadirlo y eliminarlo posteriormente
		String nombreEjercicio2 = "Ejercicio 2";
		float pesoEjercicio2 = 10;
		int anyoFin2 = 2017;
		int mesFin2 = 4;
		int diaFin2 = 25;
		int horaFin2 = 9;
		int minFin2 = 0;
		int anyoIni2 = 2017;
		int mesIni2 = 3;
		int diaIni2 = 25;
		int horaIni2 = 9;
		int minIni2 = 0;
		
		//Lo creamos y lo añadimos a un tema
		sistema.crearEjercicio(Analisis, nombreEjercicio2, pesoEjercicio2, anyoFin2, mesFin2, diaFin2, horaFin2, minFin2, anyoIni2, mesIni2, diaIni2, horaIni2, minIni2, VISIBLE);
		
		//Verificar que el tema de Analisis y Diseño tiene ahora dos ejercicios
		if (Analisis.getEjercicios().size() == 2){
			System.out.println("Analisis y diseño tiene ahora dos ejercicios, este mensaje SI debe aparecer");
		}
		
		Analisis.eliminarEjercicio(Analisis.getEjercicios().get(1));
		
		//Verificar que el tema de Analisis y Diseño vuelve a tener solo un ejercicio
		if (Analisis.getEjercicios().size() == 1) {
			System.out.println("Analisis y diseño vuelve a tener solo un ejercicio, este mensaje SI debe aparecer");
		}
		
		/**
		 * CREAR PREGUNTA LIBRE
		 */
		System.out.println("\nProbando --> Crear pregunta libre<--\n");
		// Encontrar el ejercicio de nombre "Ejercicio 1"
		Ejercicio ejercicio1 = null;
		
		for (Ejercicio ejercicioAux: Analisis.getEjercicios()){
			if (ejercicioAux.getNombre().equals("Ejercicio 1")){
				ejercicio1 = ejercicioAux;
			}
		}
		
		//Preparamos los atributos de pregunta
		String enunciadoPregunta = "¿2+2?";
		float puntuacionPregunta = 20;
		boolean falloResta = NO_RESTA;
		float restaPuntuacion = 0;
		String tipo = "Libre";
		sistema.crearPregunta(ejercicio1, enunciadoPregunta, puntuacionPregunta, falloResta, restaPuntuacion, tipo);
		
		//Verificar que ahora ejercicio tiene una pregunta, y que es de tipo libre
		if (ejercicio1.getPreguntas().size() == 1){
			System.out.println("Ejercicio 1 tiene ahora 1 pregunta y es de tipo: " + ejercicio1.getPreguntas().get(0).getTipo());
		}
		
		/**
		 * CREAR MULTIPREGUNTA
		 */
		System.out.println("\nProbando --> Crear multipregunta<--\n");
		
		//Preparamos los atributos de pregunta
		String enunciadoPregunta2 = "¿Primeros dos dias de la semana?";
		float puntuacionPregunta2 = 10;
		boolean falloResta2 = NO_RESTA;
		float restaPuntuacion2 = 0;
		String tipo2 = "Multipregunta";
		sistema.crearPregunta(ejercicio1, enunciadoPregunta2, puntuacionPregunta2, falloResta2, restaPuntuacion2, tipo2);
		
		//Verificar que ahora ejercicio tiene dos preguntas, y que la segunda es de tipo Multipregunta
		if (ejercicio1.getPreguntas().size() == 2){
			System.out.println("Ejercicio 1 tiene ahora 2 preguntas y son de tipo: " + 
					ejercicio1.getPreguntas().get(0).getTipo() + ", " + ejercicio1.getPreguntas().get(1).getTipo());
		}
		
		/**
		 * CREAR PREGUNTA UNICA
		 */
		System.out.println("\nProbando --> Crear pregunta unica<--\n");
		
		//Preparamos los atributos de pregunta
		String enunciadoPregunta3 = "¿1+2?";
		float puntuacionPregunta3 = 10;
		boolean falloResta3 = RESTA;
		float restaPuntuacion3 = 5;
		String tipo3 = "Unica";
		sistema.crearPregunta(ejercicio1, enunciadoPregunta3, puntuacionPregunta3, falloResta3, restaPuntuacion3, tipo3);
		
		//Verificar que ahora ejercicio tiene tres preguntas, y que la tercera es de tipo Unica
		if (ejercicio1.getPreguntas().size() == 3){
			System.out.println("Ejercicio 1 tiene ahora 3 preguntas y son de tipo: " + 
					ejercicio1.getPreguntas().get(0).getTipo() + ", " + ejercicio1.getPreguntas().get(1).getTipo() + 
					", " + ejercicio1.getPreguntas().get(2).getTipo());
		}
		
		/**
		 * CREAR PREGUNTA BOOLEANA
		 */
		System.out.println("\nProbando --> Crear pregunta booleana<--\n");
		
		//Preparamos los atributos de pregunta
		String enunciadoPregunta4 = "FFh se escribe 127 en decimal. ¿Verdadero o false?";
		float puntuacionPregunta4 = 10;
		boolean falloResta4 = NO_RESTA;
		float restaPuntuacion4 = 0;
		String tipo4 = "Booleana";
		sistema.crearPregunta(ejercicio1, enunciadoPregunta4, puntuacionPregunta4, falloResta4, restaPuntuacion4, tipo4);
		
		//Verificar que ahora ejercicio tiene cuatro preguntas, y que la cuarta es de tipo Booleana
		if (ejercicio1.getPreguntas().size() == 4){
			System.out.println("Ejercicio 1 tiene ahora 4 preguntas y son de tipo: " + 
					ejercicio1.getPreguntas().get(0).getTipo() + ", " + ejercicio1.getPreguntas().get(1).getTipo() + 
					", " + ejercicio1.getPreguntas().get(2).getTipo() + ", " + ejercicio1.getPreguntas().get(3).getTipo());
		}
		
		/**
		 * ELIMINAR PREGUNTA
		 */
		
		//Creare una pregunta arbitraria para añadirla y despues eliminarla
		System.out.println("\nProbando --> Eliminar pregunta<--\n");

		// Preparamos los atributos de pregunta
		String enunciadoPregunta5 = "FFh se escribe 128 en decimal. ¿Verdadero o false?";
		float puntuacionPregunta5 = 10;
		boolean falloResta5 = RESTA;
		float restaPuntuacion5 = 5;
		String tipo5 = "Booleana";
		sistema.crearPregunta(ejercicio1, enunciadoPregunta5, puntuacionPregunta5, falloResta5, restaPuntuacion5,
				tipo5);
		
		//Verificar que ahora ejercicio tiene 5 preguntas
		if (ejercicio1.getPreguntas().size() == 5){
			System.out.println("Ejercicio 1 tiene ahora 5 preguntas y son de tipo: " + 
					ejercicio1.getPreguntas().get(0).getTipo() + ", " + ejercicio1.getPreguntas().get(1).getTipo() + 
					", " + ejercicio1.getPreguntas().get(2).getTipo() + ", " + ejercicio1.getPreguntas().get(3).getTipo()
					+ ", " + ejercicio1.getPreguntas().get(4).getTipo());
		}
		
		//Eliminamos la pregunta recien añadida
		ejercicio1.eliminarPregunta(ejercicio1.getPreguntas().get(4));
		
		//Verificar que ahora ejercicio vuelve a tener 4 preguntas
		if (ejercicio1.getPreguntas().size() == 4){
			System.out.println("Ejercicio 1 tiene ahora 4 preguntas y son de tipo: " + 
					ejercicio1.getPreguntas().get(0).getTipo() + ", " + ejercicio1.getPreguntas().get(1).getTipo() + 
					", " + ejercicio1.getPreguntas().get(2).getTipo() + ", " + ejercicio1.getPreguntas().get(3).getTipo());
		}
		
		/**
		 * CREAR RESPUESTAS DE PROFESORES
		 */
		System.out.println("\nProbando --> Crear respuestas de profesores para preguntas <--\n");
		//Crearemos una respuesta del profesor con sus opciones para cada pregunta
		Respuesta respuestaProf1 = new RespuestaLibre(null);
		Respuesta respuestaProf2 = new Multirrespuesta(null);
		Respuesta respuestaProf3 = new RespuestaUnica(null);
		Respuesta respuestaProf4 = new RespuestaBooleana(null);

		//Listas de opciones
		List<Opcion> opciones1 = new ArrayList<Opcion>();
		List<Opcion> opciones2 = new ArrayList<Opcion>();
		List<Opcion> opciones3 = new ArrayList<Opcion>();
		List<Opcion> opciones4 = new ArrayList<Opcion>();
				
		
		//Opciones con las que el profesor y el alumno registrara sus respuestas
		Opcion opcion1 = new Opcion("4");
		Opcion opcion2 = new Opcion("Lunes");
		Opcion opcion3 = new Opcion("Martes");
		Opcion opcion7 = new Opcion("3");
		Opcion opcion4 = new Opcion("5");
		Opcion opcion5 = new Opcion("V");
		Opcion opcion6 = new Opcion("F");
		
		//Introducimos las opciones erroneas en la pregunta 
		ejercicio1.getPreguntas().get(0).pregAniadirOpcion(opcion1);
		ejercicio1.getPreguntas().get(0).pregAniadirOpcion(opcion2);
		
		if (ejercicio1.getPreguntas().get(0).getOpciones().size() == 2){
			System.out.println("La pregunta 1 del ejercicio 1 ahora tiene 2 opciones: " + 
					ejercicio1.getPreguntas().get(0).getOpciones().get(0).getOpcion() + ", " 
					+ ejercicio1.getPreguntas().get(0).getOpciones().get(1).getOpcion());
		}
		
		
		
		//Respuesta preguntaLibre ¿2+2? Son 4, seleccionamos la opcion correcta
		respuestaProf1.resAniadirOpcion(opcion1);
		
		//Respuesta Multipregunta ¿Primeros dos dias de la semana? Lunes y Martes
		respuestaProf2.resAniadirOpcion(opcion2);
		respuestaProf2.resAniadirOpcion(opcion3);
		
		//Respuesta preguntaUnica ¿1+2? Son 3
		respuestaProf3.resAniadirOpcion(opcion7);
		
		//Respuesta preguntaBooleana ¿FFh se escribe 127 en decimal. ¿Verdadero o false? Falso
		respuestaProf4.resAniadirOpcion(opcion6);
		
		//Introducimos la respuesta del profesor donde corresponde
		ejercicio1.getPreguntas().get(0).setRespuestaProf(respuestaProf1);
		ejercicio1.getPreguntas().get(1).setRespuestaProf(respuestaProf2);
		ejercicio1.getPreguntas().get(2).setRespuestaProf(respuestaProf3);
		ejercicio1.getPreguntas().get(3).setRespuestaProf(respuestaProf4);
		
		
		/**
		 * REALIZAR EJERCICIOS CONTESTANDO PREGUNTAS
		 */
		
		sistema.log_in(jorgeAlcazar.getNumA(), jorgeAlcazar.getPassword());
		
		//¿2+2? Son 4, opcion que marca el alumno, luego la tendrá bien y sumara 4 puntos
		opciones1.add(opcion1);
		System.out.println("La pregunta es " + ejercicio1.getPreguntas().get(0).getEnunciado() +
				" y la respuesta correcta, (la del profesor) es: " + ejercicio1.getPreguntas().get(0).getRespuestaProf().getOpciones().get(0).getOpcion() +
				" mientras que la marcada por el alumno es: " + opciones1.get(0).getOpcion());
		ejercicio1.registrarRespuestaAlumno(jorgeAlcazar, ejercicio1.getPreguntas().get(0), opciones1);
		
		System.out.println();
		
		//Los dos primeros dia de la semana son el lunes y el martes, 
		// opciones que aniadimos al alumno, luego la tendrá bien y sumara 2 puntos
		opciones2.add(opcion2);
		opciones2.add(opcion3);
		System.out.println("La pregunta es " + ejercicio1.getPreguntas().get(1).getEnunciado() +
				" y la respuesta correcta (la del profesor) es: " + ejercicio1.getPreguntas().get(1).getRespuestaProf().getOpciones().get(0).getOpcion() +
				" " + ejercicio1.getPreguntas().get(1).getRespuestaProf().getOpciones().get(1).getOpcion() + " " +
			    "mientras que la marcada por el alumno es: " + opciones2.get(0).getOpcion() + " y " + opciones2.get(1).getOpcion());
		ejercicio1.registrarRespuestaAlumno(jorgeAlcazar, ejercicio1.getPreguntas().get(1), opciones2);
		
		System.out.println();
		
		
		//¿1+2? Son 3 (opcion7), y el alumno selecciono 5(opcion4), luego la tendra mal y restara (ya que esta si resta) 1 punto.
		opciones3.add(opcion4);
		System.out.println("La pregunta es " + ejercicio1.getPreguntas().get(2).getEnunciado() +
				" y la respuesta correcta, (la del profesor) es: " + ejercicio1.getPreguntas().get(2).getRespuestaProf().getOpciones().get(0).getOpcion() + " "
				+  " mientras que la marcada por el alumno es: " + opciones3.get(0).getOpcion());
			
		ejercicio1.registrarRespuestaAlumno(jorgeAlcazar, ejercicio1.getPreguntas().get(2), opciones3);
		
		System.out.println();
	
		//FFh en hexadecimal es 127h, ¿F o V? El alumno selecciona V(opcion6), luego esta mal (es F, opcion6) y no sumara nada.
		opciones4.add(opcion5);
		System.out.println("La pregunta es " + ejercicio1.getPreguntas().get(3).getEnunciado() +
		" y la respuesta correcta, (la del profesor) es: " + ejercicio1.getPreguntas().get(3).getRespuestaProf().getOpciones().get(0).getOpcion() +
		" mientras que la marcada por el alumno es: " + opciones4.get(0).getOpcion());
		
		ejercicio1.registrarRespuestaAlumno(jorgeAlcazar, ejercicio1.getPreguntas().get(3), opciones4);
		
		System.out.println();
		
		/**
		 *  CALCULAMOS LA NOTA DEL ALUMNO
		 */
		//Las ponderaciones de las preguntas son 20, 10, 10 y 10 respectivamente.
		//La primera la responde correctamente, por lo que suma (20/50) *10 4 puntos
		//La segunda la responde correctamente, por lo que suma (10/50) *10 2 puntos
		//La tercera la responde erroneamente, por lo que resta (5/50) *10 1 puntos
		//La tercera la responde erroneamente, por lo que suma 0 puntos
		
		System.out.println("\nProbando --> Obtener calificacion media de un alumno en una asignatura (log como alumno)<--\n");
		
		System.out.println("La nota del alumno en esta asignatura es : " + sistema.consultarCalificacionesPropias(padsof));
		
		System.out.println("\nProbando --> Obtener calificacion media de un alumno en una asignatura (log como profesor)<--\n");

		Sistema.getInstance().log_in("Profesor", "profeduudle");
		System.out.println("La nota del alumno en esta asignatura es : " + sistema.consultarCalificacionesAlumno(jorgeAlcazar, padsof));

		/**
		 *  CALCULAMOS LAS ESTADISTICAS DE UNA PREGUNTA, TANTO DE CONTESTADA COMO DE CORRECTA
		 */
		
		System.out.println("\nProbando --> Obtener porcentaje de alumnos que contestaron una pregunta (con respecto al total de matriculados)<--\n");
		System.out.println("\nDeberia salir 100%, ya que solo ha contestado jorge Alcazar y es el unico alumno matriculado en esta asignatura--->");
		System.out.println(ejercicio1.getPreguntas().get(0).alumnosContestados());
		
		//Aniadimos un alumno mas en la asignatura
		System.out.println("\nProbando --> Aniadimos un nuevo Alumno a la asignatura, Manuel Blanco<--\n");
		Alumno manuelBlanco = new Alumno("Manuel","Blanco","Manuel.Blanco@esdu.es","1258","anuel.Bl");
		padsof.aniadirAlumno(manuelBlanco);

		System.out.println("\nDeberia salir 50%, ya que Manuel aun no contesto esa pregunta ---> ");
		System.out.println(ejercicio1.getPreguntas().get(0).alumnosContestados());
		

		System.out.println("\nProbando --> Obtener porcentaje de alumnos que contestaron correctamente a una pregunta (con respecto al total de contestados)<--\n");
		System.out.println("\nDeberia salir 100%, ya que la pregunta1 solo la ha contestado jorge Alcazar y lo hizo correctamente--->");
		System.out.println(ejercicio1.getPreguntas().get(0).alumnosContestadosCorrecto());
		
		System.out.println("\nSin embargo, la pregunta3 la contesto erroneamente, por lo que nadie la ha contestado bien. Deberia salir 0% --->");
		System.out.println(ejercicio1.getPreguntas().get(2).alumnosContestadosCorrecto());
		
		/**
		 * BORRAR TEMA Y EJERCICIO YA HAN SIDO REALIZADOS
		 */
		
		System.out.println("\nProbando --> Eliminar Tema Analisis con ejercicios realizados<--\n");
		padsof.eliminarTema(padsof.getTemas().get(0));
		
		//Comprobamos que no se ha eliminado
		if(padsof.getTemas().get(0).getNombre() == "Analisis y diseño"){
			System.out.println("\nCorrecto --> El tema Analisis y diseño de padsof no ha debido eliminarse<--\n");
		}
		
		System.out.println("\nProbando --> Eliminar Ejercicio realizado<--\n");
		padsof.eliminarTema(padsof.getTemas().get(0));
		
		if(padsof.getTemas().get(0).getEjercicios().get(0).getNombre() == "Ejercicio 1"){
			System.out.println("\nCorrecto --> El ejercicio1 del tema Analisis y diseño de padsof no ha debido eliminarse<--\n");
		}
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
