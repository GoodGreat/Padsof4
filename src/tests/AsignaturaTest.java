package tests;

import static org.junit.Assert.*;
import org.junit.*;
import asignatura.*;
import ejercicio.*;
import sistema.*;
import java.util.*;

public class AsignaturaTest {	
	private static final boolean VISIBLE = true;
	private static final boolean NO_VISIBLE = false;
	private Asignatura asignatura;
	private Tema tema1;
	private Tema tema2;
	private Opcion opcion1;
	private Opcion opcion2;
	private List<Opcion> opciones1;
	private List<Opcion> opciones2;
	private PreguntaBooleana pregunta1;
	private PreguntaBooleana pregunta2;
	private PreguntaBooleana pregunta3;
	private Ejercicio ejercicio1;
	private Ejercicio ejercicio2;
	private List<Alumno> alumnos;
	private List<Tema> temas;
	
	
	@Before
	public void setUp() throws Exception {
		asignatura = new Asignatura("PADSOF", VISIBLE);
		tema1 = new Tema("Tema1", VISIBLE);
		tema2 = new Tema("Tema2", VISIBLE);
		opcion1 = new Opcion("Si");
		opcion2 = new Opcion("No");
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		ejercicio1 = new Ejercicio("Ejercicio1", 10, 2016, 1, 10, 12, 0, 2018, 10, 10, 12, 0, true, false);
		ejercicio2 = new Ejercicio("Ejercicio2", 10, 2016, 1, 10, 12, 0, 2018, 10, 10, 12, 0, true, false);
		pregunta1 = new PreguntaBooleana("¿Es el cielo azul?", 5, false, 5, "booleana");
		pregunta2 = new PreguntaBooleana("¿Es el cielo verde?", 5, false, 0.25f, "booleana");
		pregunta3 = new PreguntaBooleana("¿Es el cielo rojo?", 10, false, 0.25f, "booleana");
		alumnos = new ArrayList<Alumno>();
		temas = new ArrayList<Tema>();
		Sistema.getInstance().log_in("Profesor", "profeduudle");
	}

	@After
	public void tearDown() throws Exception{
		Sistema.destroyInstance();
	}
	
	@Test
	public void testAsignatura() {
		assertNotNull(asignatura);
	}

	@Test
	public void testPublicarAsignatura() {
		assertSame(asignatura.getVisible(), true);			//Verificar que la asignatura esta publica para todos los usuarios (tal como se inicializo)
		asignatura.ocultarAsignatura();
		assertSame(asignatura.getVisible(), false);			//Verificar que el atributo de "visible" se ha cambiado a "NO_VISIBLE"
		asignatura.publicarAsignatura();
		assertSame(asignatura.getVisible(), true);			//Verificar que la asignatura ha vuelto a ser publicada
	}

	@Test
	public void testPrivatizarAsignatura() {
		assertSame(asignatura.getVisible(), true);			//Verificar que la asignatura esta publica para todos los usuarios (tal como se inicializo)
		asignatura.ocultarAsignatura();
		assertSame(asignatura.getVisible(), false);			//Verificar que la asignatura se ha ocultado para los alumnos
	}

	@Test
	public void testPosibleCambiarAsignatura() {
		int i = 0;
		
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		assertSame(asignatura.posibleCambiarAsignatura(), true);
		tema1.getEjercicios().get(i).aumentarRealizaciones();
		assertSame(asignatura.posibleCambiarAsignatura(), false);
	}

	@Test
	public void testAniadirTema() {
		Tema tema1 = new Tema("Diseño y analisis", VISIBLE);
		assertSame(asignatura.getTemas().size(), 0);				//Verificar que el tamaño es 0 antes de aniadir el tema
		asignatura.aniadirTema(tema1);
		assertSame(asignatura.getTemas().size(), 1);				//Verificar que el tamaño es 1 despues de aniadir el tema
		assertSame(asignatura.getTemas().get(0), tema1);			//Verificar que el tema aniadido es el mismo
	}

	@Test
	public void testAniadirAlumno() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		assertSame(asignatura.getAlumnos().size(), 0);					//Verificar que el tamaño es 0 antes de aniadir el alumno
		asignatura.aniadirAlumno(alumno1);
		assertSame(asignatura.getAlumnos().size(), 1);					//Verificar que el tamaño es 1 despues de aniadir el alumno
		assertSame(asignatura.getAlumnos().get(0), alumno1);			//Verificar que el alumno aniadido es el mismo
	}

	@Test
	public void testEliminarTema() {
		Tema tema1 = new Tema("Diseño y analisis", VISIBLE);
		assertSame(asignatura.getTemas().size(), 0);				//Verificar que el tamaño es 0 antes de aniadir el tema
		asignatura.aniadirTema(tema1);
		assertSame(asignatura.getTemas().size(), 1);				//Verificar que el tamaño es 1 despues de aniadir el tema
		assertSame(asignatura.getTemas().get(0), tema1);			//Verificar que el tema aniadido es el mismo
		asignatura.eliminarTema(tema1);
		assertSame(asignatura.getTemas().size(), 0);				//Verificar que el tamaño vuelve a ser 0 tras eliminar el tema
	}

	@Test
	public void testExpulsarAlumno() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		assertSame(asignatura.getAlumnos().size(), 0);					//Verificar que el tamaño es 0 antes de aniadir el alumno
		asignatura.aniadirAlumno(alumno1);
		assertSame(asignatura.getAlumnos().size(), 1);					//Verificar que el tamaño es 1 despues de aniadir el alumno
		assertSame(asignatura.getAlumnos().get(0), alumno1);			//Verificar que el alumno aniadido es el mismo
		asignatura.expulsarAlumno(alumno1);
		assertSame(asignatura.getAlumnos().size(), 0);					//Verificar que el tamaño vuelve a ser 0 tras eliminar el alumno
	}

	@Test
	public void testConsultarCalificacionesAlumno() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		RespuestaBooleana respuestaProf2 = new RespuestaBooleana(null);
		asignatura.aniadirAlumno(alumno1);
		asignatura.aniadirTema(tema1);
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
		assertEquals(asignatura.consultarCalificacionesAlumno(alumno1), 5, 0.1);
	}

	@Test
	public void testGetNombre() {
		assertSame("PADSOF", asignatura.getNombre());
	}

	@Test
	public void testSetNombre() {
		assertSame("PADSOF", asignatura.getNombre());				//Verificar que el nombre al inicio es el mismo que se le dio en @Before: "PADSOF"
		asignatura.setNombre("ADSOF");
		assertNotSame("PADSOF", asignatura.getNombre());			//Verificar que el nombre ya no es el mismo
		assertSame("ADSOF", asignatura.getNombre());				//Verificar que el nombre ha sido cambiado correctamente
	}

	@Test
	public void testGetAlumnos() {
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		assertSame(asignatura.getAlumnos().size(), 0);						//Verificar que el tamaño es 0 antes de introducir el alumno
		asignatura.aniadirAlumno(alumno1);
		alumnos.add(alumno1);
		assertSame(asignatura.getAlumnos().size(), 1);						//Verificar que el tamaño es 1 despues de introducir el alumno
		assertSame(asignatura.getAlumnos().get(0), alumno1);				//Verificar que el alumno se ha introducido correctamente
		assertSame(asignatura.getAlumnos().get(0), alumnos.get(0));			//Verificar de nuevo que el alumno esta adecuadamente introducido
	}

	@Test
	public void testGetVisible() {
		assertSame(VISIBLE, asignatura.getVisible());
		asignatura.ocultarAsignatura();
		assertSame(NO_VISIBLE, asignatura.getVisible());
	}

	@Test
	public void testGetTemas() {
		Tema tema1 = new Tema("Diseño y analisis", VISIBLE);
		assertSame(asignatura.getTemas().size(), 0);						//Verificar que el tamaño es 0 antes de aniadir el tema
		asignatura.aniadirTema(tema1);
		temas.add(tema1);
		assertSame(asignatura.getTemas().size(), 1);						//Verificar que el tamaño es 1 despues de introducir el tema
		assertSame(asignatura.getTemas().get(0), tema1);					//Verificar que el tema se ha introducido correctamente
		assertSame(asignatura.getTemas().get(0), temas.get(0));				//Verificar de nuevo que el tema esta adecuadamente introducido
	}
}
