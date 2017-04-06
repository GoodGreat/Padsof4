package tests;

import static org.junit.Assert.*;
import java.util.*;
import asignatura.*;
import ejercicio.*;
import sistema.Alumno;
import sistema.Sistema;

import org.junit.Before;
import org.junit.Test;

public class EjercicioTest {
	private Ejercicio ejercicio1;
	private Tema tema1;
	private Alumno alumno1;
	private Pregunta pregunta1;
	private Pregunta pregunta2;
	private Calendar calendar1;
	private Opcion opcion1;
	private Opcion opcion2;
	private List<Opcion> opciones1;
	private List<Opcion> opciones2;
	
	@Before
	public void setUp() throws Exception {
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		ejercicio1 = new Ejercicio("Ejercicio1", 0.5f, 2017, 8, 10, 12, 0, 2017, 10, 10, 12, 0, false);
		tema1 = new Tema("Introduccion", false);
		pregunta1 = new PreguntaUnica("¿2+2?", 0.5f, false, 0.25f, "Unica");
		pregunta2 = new PreguntaUnica("¿3+3?", 0.5f, false, 0.25f, "Unica");
		calendar1 = Calendar.getInstance();
		opcion1 = new Opcion("Si");
		opcion2 = new Opcion("No");
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
	}

	@Test
	public void testEjercicio() {
		assertNotNull(ejercicio1);
	}

	@Test
	public void testGetnRealizaciones() {
		assertSame(0, ejercicio1.getnRealizaciones());
	}

	@Test
	public void testAumentarRealizaciones() {
		ejercicio1.aumentarRealizaciones();
		assertSame(1, ejercicio1.getnRealizaciones());
	}

	@Test
	public void testGetTemaSuperior() {
		Tema tema2 = ejercicio1.getTemaSuperior();
		assertSame(tema2, ejercicio1.getTemaSuperior());
	}

	@Test
	public void testSetTemaSuperior() {
		ejercicio1.setTemaSuperior(tema1);
		assertSame(tema1, ejercicio1.getTemaSuperior());
	}

	@Test
	public void testOcultarEjercicio() {
		ejercicio1.ocultarEjercicio();
		assertSame(false, ejercicio1.getVisible());
	}

	@Test
	public void testPublicarEjercicio() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);

		ejercicio1.publicarEjercicio();
		assertSame(true, ejercicio1.getVisible());
	}

	@Test
	public void testAniadirPregunta() {
		int i = 0;
		ejercicio1.aniadirPregunta(pregunta1);
		assertSame(pregunta1, ejercicio1.getPreguntas().get(i));
		assertSame(1, ejercicio1.getPreguntas().size());
	}

	@Test
	public void testEliminarPregunta() {
		ejercicio1.aniadirPregunta(pregunta1);
		ejercicio1.eliminarPregunta(pregunta1);
		assertSame(0, ejercicio1.getPreguntas().size());	
	}

	@Test
	public void testGetNombre() {
		assertSame("Ejercicio1", ejercicio1.getNombre());
	}

	@Test
	public void testSetNombre() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		ejercicio1.setNombre("Ejercicio2");
		assertSame("Ejercicio2", ejercicio1.getNombre());
	}

	@Test
	public void testGetPeso() {
		assertEquals(0.5f, ejercicio1.getPeso(),0.1);
	}

	@Test
	public void testSetPeso() {
		ejercicio1.setPeso(1);
		assertEquals(1, ejercicio1.getPeso(), 0.1);
	}
	
	@Test
	public void testGetFechaIni() {
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.setFechaIni(calendar1);
		assertSame(calendar1, ejercicio1.getFechaIni());
	}

	@Test
	public void testSetFechaIni() {
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		calendar1.set(2017, 7, 10, 12, 0);
		ejercicio1.setFechaIni(calendar1);
		assertSame(calendar1, ejercicio1.getFechaIni());
	}
	
	@Test
	public void testGetFechaFin() {
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.setFechaFin(calendar1);
		assertSame(calendar1, ejercicio1.getFechaFin());
	}

	@Test
	public void testSetFechaFin() {
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		calendar1.set(2017, 12, 10, 12, 0);
		ejercicio1.setFechaFin(calendar1);
		assertSame(calendar1, ejercicio1.getFechaFin());
	}

	@Test
	public void testIsExpirado() {
		assertSame(false, ejercicio1.isExpirado());
	}

	@Test
	public void testGetPreguntas() {
		int i = 0;
		ejercicio1.aniadirPregunta(pregunta1);
		assertSame(pregunta1, ejercicio1.getPreguntas().get(i));
		assertSame(1, ejercicio1.getPreguntas().size());
	}

	@Test
	public void testObtenerNotaAlumno() {
		Sistema sistema = null;
		sistema = Sistema.getInstance();
		sistema.log_in("Profesor", "profeduudle");
		sistema.aniadirAlumno(alumno1);
		ejercicio1.aniadirPregunta(pregunta1);
		ejercicio1.aniadirPregunta(pregunta2);
		
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		RespuestaBooleana respuestaProf2 = new RespuestaBooleana(null);
		
		respuestaProf.resAniadirOpcion(opcion1);
		respuestaProf2.resAniadirOpcion(opcion2);
		
		pregunta1.setRespuestaProf(respuestaProf);
		pregunta2.setRespuestaProf(respuestaProf2);
		
		opciones1.add(opcion1);
		opciones2.add(opcion2);
		
		sistema.log_in(alumno1.getNumA(), alumno1.getPassword());
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta1, opciones1);
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta2, opciones2);
		
		assertEquals(ejercicio1.obtenerNotaAlumno(alumno1), 10, 0.1);
		
	}
	
	@Test
	public void testRegistrarRespuestaAlumno() {
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		ejercicio1.aniadirPregunta(pregunta1);
		respuestaProf.resAniadirOpcion(opcion1);
		
		pregunta1.setRespuestaProf(respuestaProf);
		opciones1.add(opcion1);
		
		Sistema.getInstance().log_in(alumno1.getNumA(), alumno1.getPassword());
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta1, opciones1);
		assertEquals(ejercicio1.obtenerNotaAlumno(alumno1), 10, 0.1);
		
		}
	
	
	}

