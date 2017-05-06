package tests;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import asignatura.*;
import sistema.*;
import ejercicio.*;

public class SistemaTest {
	private final boolean PROFESOR = true;
	private final boolean ALUMNO = false;
	private final boolean VISIBLE = true;
	private Sistema sistema = null;
	private Asignatura asignatura1;
	private Tema tema1;
	private Opcion opcion1;
	private Opcion opcion2;
	private PreguntaBooleana pregunta1;
	private PreguntaBooleana pregunta2;
	private PreguntaBooleana pregunta3;
	private List<Opcion> opciones1;
	private List<Opcion> opciones2;
	private Tema tema2;
	private Ejercicio ejercicio1;
	private Ejercicio ejercicio2;
	private Alumno alumno1;
	private List<Alumno> alumnos;

	
	@Before
	public void setUp() throws Exception {
		sistema = Sistema.getInstance();
		alumnos = new ArrayList<Alumno>();
		asignatura1 = new Asignatura("Cirel", true);
		tema1 = new Tema("Tema1", VISIBLE);
		tema2 = new Tema("Tema2", VISIBLE);
		pregunta1 = new PreguntaBooleana("¿Es el cielo azul?", 5, false, 5, "booleana");
		pregunta2 = new PreguntaBooleana("¿Es el cielo verde?", 5, false, 0.25f, "booleana");
		pregunta3 = new PreguntaBooleana("¿Es el cielo rojo?", 10, false, 0.25f, "booleana");
		opcion1 = new Opcion("Si");
		opcion2 = new Opcion("No");
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		ejercicio1 = new Ejercicio("Ejercicio1", 10, 2017, 3, 10, 12, 0, 2017, 10, 10, 12, 0, true, false);
		ejercicio2 = new Ejercicio("Ejercicio2", 10, 2017, 3, 10, 12, 0, 2017, 10, 10, 12, 0, true, false);
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		sistema.log_in("Profesor", "profeduudle");		
	}
	
	@After
	public void tearDown() throws Exception{
		Sistema.destroyInstance();
	}

	@Test
	public void testSistema() {		
		assertNotNull(sistema);
	}

	@Test
	public void testGetloginProf() {
		assertSame("Profesor", sistema.getloginProf());
	}

	@Test
	public void testGetpasswordProf() {
		assertSame("profeduudle", sistema.getpasswordProf());
	}

	@Test
	public void testIsProf() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		sistema.aniadirAlumno(alumno1);
		sistema.log_in("8721312", "pedro9");				//Cuando logueamos como alumnos, isProf sera "false"
		assertSame(sistema.isProf(), ALUMNO);
		sistema.log_in("Profesor", "profeduudle");
		assertSame(sistema.isProf(), PROFESOR);				//Cuando logueamos como profesores, isProf sera "true"
	}

	@Test
	public void testGetAlumnos() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		assertSame(sistema.getAlumnos().size(), 0);						//Verificar que el tamaño es 0 antes de introducir el alumno
		sistema.aniadirAlumno(alumno1);
		alumnos.add(alumno1);
		assertSame(sistema.getAlumnos().size(), 1);						//Verificar que el tamaño es 1 despues de introducir el alumno
		assertSame(sistema.getAlumnos().get(0), alumno1);				//Verificar que el alumno se ha introducido correctamente
		assertSame(sistema.getAlumnos().get(0), alumnos.get(0));		//Verificar de nuevo que el alumno esta adecuadamente introducido
	}

	@Test
	public void testLog_in() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		sistema.aniadirAlumno(alumno1);
		assertSame(sistema.log_in("8721312", "pedro9"), true);		//Logueamos como un alumno existente
		assertSame(sistema.getAlumnoLogueado(), alumno1);	
		assertSame(sistema.isProf(), ALUMNO);
		assertSame(sistema.log_in("Profesor", "profeduudle"), true);//Logueamos como profesor
		assertSame(sistema.isProf(), PROFESOR);
		assertSame(sistema.log_in("8721312", "pedr"), false);		//Logueamos con una contraseña incorrecta, por lo que la funcion login devuelve "false" 
		
	}

	@Test
	public void testCrearAsignatura() {
		String nombreAsignatura = "PADSOF";
		boolean esVisible = VISIBLE;
		assertSame(sistema.getAsignaturas().size(), 0);				//Verificar que el array esta vacio antes de introducir una asignatura
		sistema.crearAsignatura(nombreAsignatura, esVisible);
		assertSame(sistema.getAsignaturas().size(), 1);				//Verificar que el tamaño del array ahora es 1
	}

	@Test
	public void testCrearAlumno() {
		String nombreAlumno = "Pedro";
		String apellidoAlumno = "Martinez";
		String correoAlumno = "pedro_crack@gmail.com";
		String NumA = "8742122";
		String contrasenia = "pedro_cr";
		assertSame(sistema.getAlumnos().size(), 0);					//Verificar que el array esta vacio antes de introducir una asignatura
		sistema.crearAlumno(nombreAlumno, apellidoAlumno, correoAlumno, NumA, contrasenia);
		assertSame(sistema.getAlumnos().size(), 1);					//Verificar que el tamaño del array ahora es 1
	}

	@Test
	public void testCrearTema() {
		String nombreAsignatura = "PADSOF";
		boolean esVisible = VISIBLE;
		sistema.crearAsignatura(nombreAsignatura, esVisible);
		String nombreTema = "Analisis y disenio";
		assertSame(sistema.getAsignaturas().get(0).getTemas().size(), 0);			//Verificar que el tamaño del array es 0 antes de aniadir el tema
		sistema.crearTema(sistema.getAsignaturas().get(0), nombreTema, esVisible);
		assertSame(sistema.getAsignaturas().get(0).getTemas().size(), 1);			//Verificar que el tamaño del array es 1 ahora
	}

	@Test
	public void testCrearEjercicio() {
		String nombreAsignatura = "PADSOF";
		boolean esVisible = VISIBLE;
		sistema.crearAsignatura(nombreAsignatura, esVisible);
		String nombreTema = "Analisis y Diseño";
		sistema.crearTema(sistema.getAsignaturas().get(0), nombreTema, VISIBLE);
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
		boolean aleatorio = false;
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getEjercicios().size(), 0);	//Verificar que el tamaño del array es  0
		sistema.crearEjercicio(sistema.getAsignaturas().get(0).getTemas().get(0), nombreEjercicio, pesoEjercicio, 
				anyoFin, mesFin, diaFin, horaFin, minFin, anyoIni, mesIni, diaIni, horaIni, minIni, VISIBLE, aleatorio);
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getEjercicios().size(), 1);	//Verificar que el tamaño del array es  1 ahora
	}

	@Test
	public void testCrearApunte() {
		String nombreAsignatura = "PADSOF";
		boolean esVisible = VISIBLE;
		sistema.crearAsignatura(nombreAsignatura, esVisible);
		String nombreTema = "Analisis y Diseño";
		sistema.crearTema(sistema.getAsignaturas().get(0), nombreTema, VISIBLE);
		String tituloApunte = "Apuntes Tema 1";
		String contenidoApunte = "...";											
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getApuntes().size(), 0);		//Verificar que el tamaño del array es  0
		sistema.crearApunte(sistema.getAsignaturas().get(0).getTemas().get(0), tituloApunte, VISIBLE, contenidoApunte);
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getApuntes().size(), 1);		//Verificar que el tamaño del array es 1 ahora
	}

	@Test
	public void testCrearPregunta() {
		String nombreAsignatura = "PADSOF";
		boolean esVisible = VISIBLE;
		sistema.crearAsignatura(nombreAsignatura, esVisible);
		String nombreTema = "Analisis y Diseño";
		sistema.crearTema(sistema.getAsignaturas().get(0), nombreTema, VISIBLE);
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
		boolean aleatorio = false;
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getEjercicios().size(), 0);		//Verificar que el tamaño del array es  0
		sistema.crearEjercicio(sistema.getAsignaturas().get(0).getTemas().get(0), nombreEjercicio, pesoEjercicio, 
				anyoFin, mesFin, diaFin, horaFin, minFin, anyoIni, mesIni, diaIni, horaIni, minIni, VISIBLE, aleatorio);
		assertSame(sistema.getAsignaturas().get(0).getTemas().get(0).getEjercicios().size(), 1);		//Verificar que el tamaño del array es 1 ahora
	}

	@Test
	public void testExpulsarAlumno() {
		assertSame(sistema.getAsignaturas().size(),0);
		sistema.crearAsignatura("PADSOF", true);
		assertSame(sistema.getAsignaturas().size(),1);
		
		sistema.getAsignaturas().get(0).aniadirAlumno(alumno1);
		alumno1.aniadirAsignatura(sistema.getAsignaturas().get(0));
		assertSame(sistema.getAsignaturas().get(0).getAlumnos().get(0), alumno1);
		assertSame(sistema.getAsignaturas().get(0).getAlumnos().size(), 1);
		sistema.expulsarAlumno(alumno1, sistema.getAsignaturas().get(0));
		assertSame(sistema.getExpulsiones().size(), 1);
		assertSame(sistema.getAsignaturas().get(0).getAlumnos().size(), 0);
	}

	@Test
	public void testRevocarExpulsion() {
		asignatura1.aniadirAlumno(alumno1);
		alumno1.aniadirAsignatura(asignatura1);
		sistema.expulsarAlumno(alumno1, asignatura1);
		assertSame(sistema.getExpulsiones().size(), 1);
		sistema.revocarExpulsion(sistema.getExpulsiones().get(0));
		assertSame(asignatura1.getAlumnos().get(0), alumno1);
		assertSame(alumno1.getAsignaturas().get(0), asignatura1);
		assertSame(sistema.getExpulsiones().size(), 0);
	}

	@Test
	public void testCrearSolicitud() {
		sistema.aniadirAlumno(alumno1);
		sistema.log_in("1234", "contrasena1");
		assertSame(sistema.getSolicitudes().size(), 0);
		sistema.crearSolicitud(asignatura1);
		assertSame(sistema.getSolicitudes().size(), 1);
		assertSame(alumno1.getSolicitudes().get(0), sistema.getSolicitudes().get(0));
	}

	@Test
	public void testConsultarCalificacionesAlumno() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		RespuestaBooleana respuestaProf2 = new RespuestaBooleana(null);
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		
		ejercicio1.aniadirPregunta(pregunta1);
		ejercicio1.aniadirPregunta(pregunta2);
		
		respuestaProf.resAniadirOpcion(opcion1);
		respuestaProf2.resAniadirOpcion(opcion2);
		
		pregunta1.setRespuestaProf(respuestaProf);
		pregunta2.setRespuestaProf(respuestaProf2);
		
		tema1.aniadirSubtema(tema2);
		tema2.aniadirEjercicio(ejercicio2);
		
		ejercicio2.aniadirPregunta(pregunta3);
		pregunta3.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		opciones2.add(opcion2);
		Sistema.getInstance().log_in(alumno1.getNumA(), alumno1.getPassword());
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta1, opciones1);
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta2, opciones2);
		ejercicio2.registrarRespuestaAlumno(alumno1, pregunta3, opciones2);
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		assertEquals(sistema.consultarCalificacionesAlumno(alumno1, asignatura1), 5, 0.1);
	}

	@Test
	public void testConsultarCalificacionesPropias() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		sistema.aniadirAlumno(alumno1);
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		RespuestaBooleana respuestaProf2 = new RespuestaBooleana(null);
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		
		ejercicio1.aniadirPregunta(pregunta1);
		ejercicio1.aniadirPregunta(pregunta2);
		
		respuestaProf.resAniadirOpcion(opcion1);
		respuestaProf2.resAniadirOpcion(opcion2);
		
		pregunta1.setRespuestaProf(respuestaProf);
		pregunta2.setRespuestaProf(respuestaProf2);
		
		tema1.aniadirSubtema(tema2);
		tema2.aniadirEjercicio(ejercicio2);
		
		ejercicio2.aniadirPregunta(pregunta3);
		pregunta3.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		opciones2.add(opcion2);
		
		Sistema.getInstance().log_in(alumno1.getNumA(), alumno1.getPassword());
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta1, opciones1);
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta2, opciones2);
		ejercicio2.registrarRespuestaAlumno(alumno1, pregunta3, opciones2);
		Sistema.getInstance().log_in(alumno1.getNumA(), alumno1.getPassword());
		assertEquals(sistema.consultarCalificacionesPropias(asignatura1), 5, 0.1);
	}
	
	@Test
	public void testGetAlumnoLogueado() {
		sistema.aniadirAlumno(alumno1);
		sistema.log_in("1234", "contrasena1");
		assertSame(sistema.getAlumnoLogueado(), alumno1);
	}

	@Test
	public void testSetAlumnoLogueado() {
		sistema.setAlumnoLogueado(alumno1);
		assertSame(sistema.getAlumnoLogueado(), alumno1);
	}

	@Test
	public void testAceptarSolicitud() {
		sistema.aniadirAlumno(alumno1);
		sistema.log_in("1234", "contrasena1");
		assertSame(sistema.getSolicitudes().size(), 0);
		sistema.crearSolicitud(asignatura1);
		assertSame(sistema.getSolicitudes().size(), 1);
		assertSame(alumno1.getSolicitudes().get(0), sistema.getSolicitudes().get(0));
		sistema.log_in("Profesor", "profeduudle");
		sistema.aceptarSolicitud(sistema.getSolicitudes().get(0));
		assertSame(asignatura1.getAlumnos().get(0), alumno1);
		assertSame(alumno1.getAsignaturas().get(0), asignatura1);
		assertSame(sistema.getSolicitudes().size(), 0);
	}

	@Test
	public void testDenegarSolicitud() {
		sistema.aniadirAlumno(alumno1);
		sistema.log_in("1234", "contrasena1");
		assertSame(sistema.getSolicitudes().size(), 0);
		sistema.crearSolicitud(asignatura1);
		assertSame(sistema.getSolicitudes().size(), 1);
		assertSame(alumno1.getSolicitudes().get(0), sistema.getSolicitudes().get(0));
		sistema.log_in("Profesor", "profeduudle");
		sistema.denegarSolicitud(sistema.getSolicitudes().get(0));
		assertSame(asignatura1.getAlumnos().size(), 0);
		assertSame(alumno1.getAsignaturas().size(), 0);
		assertSame(sistema.getSolicitudes().size(), 0);
	}

	@Test
	public void testGuardarYCargarDatosSistema() {
		Sistema sistema = Sistema.getInstance();
		try{
			//En el primer sistema introducimos datos de alumnos
			sistema.leerDatosAlumno("src/alumnos.txt");
			//Guardamos el sistema en un archivo
			sistema.guardarDatosSistema("src/Sistema.obj");
			
			//Cargamos el sistema anteriormente guardado en un nuevo Sistema
			sistema.cargarDatosSistema("src/Sistema.obj");
			assertSame(sistema, sistema);
		}catch (Exception e){
			System.err.println("Error");
		}
	}

}
