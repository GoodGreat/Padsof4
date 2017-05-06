package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import asignatura.*;
import sistema.*;
import ejercicio.*;
import org.junit.Before;
import org.junit.Test;

public class TemaTest {
	private Tema tema1;
	private Tema tema2;
	private Ejercicio ejercicio1;
	private Ejercicio ejercicio2;
	private Apunte apunte1;
	private Opcion opcion1;
	private Opcion opcion2;
	private List<Opcion> opciones1;
	private List<Opcion> opciones2;
	private PreguntaBooleana pregunta1;
	private PreguntaBooleana pregunta2;
	
	@Before
	public void setUp() throws Exception {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		tema1 = new Tema("Introduccion", false);
		tema2 = new Tema("Fisica I", false);
		opcion1 = new Opcion("Si");
		opcion2 = new Opcion("No");
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		ejercicio1 = new Ejercicio("Ejercicio1", 10, 2017, 3, 10, 12, 0, 2017, 6, 10, 22, 00, true, false);
		ejercicio2 = new Ejercicio("Ejercicio2", 10, 2017, 3, 10, 12, 0, 2017, 10, 10, 12, 0, true, false);
		apunte1 = new Apunte("Apunte1", false, "Este es un apunte de este tema");
		pregunta1 = new PreguntaBooleana("¿Es el cielo azul?", 5, false, 0.25f, "booleana");
		pregunta2 = new PreguntaBooleana("¿Es el cielo verde?", 5, false, 0.25f, "booleana");
	}

	@Test
	public void testTema() {
		assertNotNull(tema1);
	}

	@Test
	public void testGetNombre() {
		assertSame("Introduccion", tema1.getNombre());
	}

	@Test
	public void testSetNombre() {
		tema1.setNombre("Tema1");
		assertSame("Tema1", tema1.getNombre());
	}
	@Test
	public void testGetVisible() {
		assertSame(false, tema1.getVisible());
	}

	@Test
	public void testPublicarTema() {
		tema1.publicarTema();
		assertSame(true, tema1.getVisible());
	}

	@Test
	public void testOcultarTema() {
		tema1.ocultarTema();
		assertSame(false, tema1.getVisible());
	}

	@Test
	public void testGetSubtemas() {
		int i = 0;
		tema1.aniadirSubtema(tema2);
		assertSame(tema2, tema1.getSubtemas().get(i));
		assertSame(1, tema1.getSubtemas().size());
	}

	@Test
	public void testGetEjercicios() {
		int i = 0;
		tema1.aniadirEjercicio(ejercicio1);
		assertSame(ejercicio1, tema1.getEjercicios().get(i));
		assertSame(1, tema1.getEjercicios().size());
	}

	@Test
	public void testGetApuntes() {
		int i = 0;
		tema1.aniadirApunte(apunte1);
		assertSame(apunte1, tema1.getApuntes().get(i));
		assertSame(1, tema1.getApuntes().size());
	}

	@Test
	public void testAniadirTema() {
		int i = 0;
		tema1.aniadirSubtema(tema2);
		assertSame(tema2, tema1.getSubtemas().get(i));
		assertSame(1, tema1.getSubtemas().size());
	}

	@Test
	public void testEliminarSubtema() {
		tema1.aniadirSubtema(tema2);
		tema1.eliminarSubtema(tema2);
		assertSame(0, tema1.getSubtemas().size());	
	}

	@Test
	public void testPosibleCambiarSubtema() {
		int i = 0;
		tema1.aniadirSubtema(tema2);
		tema1.getSubtemas().get(i).aniadirEjercicio(ejercicio1);
		assertSame(true, tema1.posibleCambiarSubtema(tema1.getSubtemas().get(i)));
	}

	@Test
	public void testAnaidirEjercicio() {
		int i = 0;
		tema1.aniadirEjercicio(ejercicio1);
		assertSame(ejercicio1, tema1.getEjercicios().get(i));
		assertSame(1, tema1.getEjercicios().size());
	}

	@Test
	public void testEliminarEjercicio() {
		tema1.aniadirEjercicio(ejercicio1);
		tema1.eliminarEjercicio(ejercicio1);
		assertSame(0, tema1.getEjercicios().size());
	}

	@Test
	public void testAniadirApunte() {
		int i = 0;
		tema1.aniadirApunte(apunte1);
		assertSame(apunte1, tema1.getApuntes().get(i));
		assertSame(1, tema1.getApuntes().size());
		}

	@Test
	public void testEliminarApunte() {
		tema1.aniadirApunte(apunte1);
		tema1.eliminarApunte(apunte1);
		assertSame(0, tema1.getApuntes().size());
	}
	
	@Test
	public void testConsultarCalificacionesAlumno(){
		Alumno alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		RespuestaBooleana respuestaProf = new RespuestaBooleana(null);
		RespuestaBooleana respuestaProf2 = new RespuestaBooleana(null);
		tema1.aniadirEjercicio(ejercicio1);
		
		ejercicio1.aniadirPregunta(pregunta1);
		ejercicio2.aniadirPregunta(pregunta2);
		
		respuestaProf.resAniadirOpcion(opcion1);
		respuestaProf2.resAniadirOpcion(opcion2);
		
		pregunta1.setRespuestaProf(respuestaProf);
		pregunta2.setRespuestaProf(respuestaProf2);
		
		opciones1.add(opcion1);
		opciones2.add(opcion2);
		Sistema.getInstance().log_in(alumno1.getNumA(), alumno1.getPassword());
		ejercicio1.registrarRespuestaAlumno(alumno1, pregunta1, opciones1);
		ejercicio2.registrarRespuestaAlumno(alumno1, pregunta2, opciones2);
		
		assertEquals(tema1.consultarCalificacionesAlumno(alumno1), 100, 0.1);
	}	

}
