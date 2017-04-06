package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import asignatura.Asignatura;
import asignatura.Tema;
import sistema.Alumno;
import sistema.Sistema;
import ejercicio.*;

public class PreguntaLibreTest {
	private Alumno alumno1;
	private Alumno alumno2;
	private Alumno alumno3;
	private Asignatura asignatura1;
	private Tema tema1;
	private Ejercicio ejercicio1;
	private PreguntaLibre preguntaLibre1;
	private Respuesta respuestaProf;
	private Respuesta respuesta1;
	private Respuesta respuesta2;
	private List<Opcion> opciones1;
	private List<Opcion> opciones2;
	private Opcion opcion1;
	private Opcion opcion2;
	private Opcion opcion3;

	@Before
	public void setUp() throws Exception {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		asignatura1 = new Asignatura("PADSOF", true);
		tema1 = new Tema("Tema1", true);
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		alumno2 = new Alumno("Miguel", "Garcia", "miggui@gmail.es", "1235", "contrasena2");
		alumno3 = new Alumno("Pepe", "Garcia", "peps@gmail.es", "1255", "contrasena3");
		ejercicio1 = new Ejercicio("Ejercicio1", 0.5f, 2017, 8, 10, 12, 0, 2017, 10, 10, 12, 0, false);
		preguntaLibre1 = new PreguntaLibre("¿Es el cielo azul?", 0.5f, false, 0.25f, "libre");
		respuestaProf = new RespuestaLibre(null);
		respuesta1 = new RespuestaLibre(alumno1);
		respuesta2 = new RespuestaLibre(alumno2);
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		opcion3 = new Opcion("Esta es la opcion3");
	}

	@Test
	public void testPregunta() {
		assertNotNull(preguntaLibre1);
	}
	
	@Test
	public void testPreguntaLibre() {
		assertNotNull(preguntaLibre1);
	}
	
	@Test
	public void testGetEnunciado() {
		assertSame("¿Es el cielo azul?", preguntaLibre1.getEnunciado());
	}

	@Test
	public void testGetPuntuacion() {
		assertEquals(0.5f, preguntaLibre1.getPuntuacion(), 0.1);
	}

	@Test
	public void testGetFalloResta() {
		assertSame(false, preguntaLibre1.getFalloResta());
	}

	@Test
	public void testGetResta() {
		assertEquals(0.25f, preguntaLibre1.getResta(), 0.1);
	}

	@Test
	public void testGetTipo(){
		assertSame("libre", preguntaLibre1.getTipo());
	}
	
	@Test
	public void testGetEjercicioSuperior(){
		ejercicio1.aniadirPregunta(preguntaLibre1);
		assertSame(ejercicio1 , preguntaLibre1.getEjercicioSuperior());
	}
	
	@Test
	public void testSetEjercicioSuperior(){
		preguntaLibre1.setEjercicioSuperior(ejercicio1);
		assertSame(ejercicio1, preguntaLibre1.getEjercicioSuperior());
	}
	
	@Test
	public void testGetRespuestaProf(){
		preguntaLibre1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntaLibre1.getRespuestaProf());
	}
	
	@Test
	public void testSetRespuestaProf(){
		preguntaLibre1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntaLibre1.getRespuestaProf());
	}
	
	@Test
	public void testGetOpciones() {
		int i = 0;
		preguntaLibre1.pregAniadirOpcion(opcion1);
		assertSame(opcion1, preguntaLibre1.getOpciones().get(i));
		assertSame(1, preguntaLibre1.getOpciones().size());
	}
	
	@Test
	public void testGetRespuestas() {
		int i = 0;
		opciones1.add(opcion1);
		preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1);
		assertSame(opcion1, preguntaLibre1.getRespuestas().get(i).getOpciones().get(i));
		assertSame(1, preguntaLibre1.getRespuestas().size());
	}
	
	@Test
	public void testPregAniadirOpcion() {
		int i = 0;
		assertSame(preguntaLibre1.pregAniadirOpcion(opcion1), true);
		assertSame(preguntaLibre1.getOpciones().get(i), opcion1);
		assertSame(1, preguntaLibre1.getOpciones().size());
		
		assertSame(preguntaLibre1.pregAniadirOpcion(opcion2), true);
		i++;
		assertSame(preguntaLibre1.getOpciones().get(i), opcion2);
		assertSame(2, preguntaLibre1.getOpciones().size());
		
		assertSame(preguntaLibre1.pregAniadirOpcion(opcion3), true);
		i++;
		assertSame(preguntaLibre1.getOpciones().get(i), opcion3);
		assertSame(3, preguntaLibre1.getOpciones().size());
	}
	
	@Test
	public void testPregAniadirRespuesta() {
		int i = 0;
		opciones1.add(opcion1);
		assertSame(preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1), true);
		assertSame(preguntaLibre1.getRespuestas().get(i).getOpciones().get(i), opcion1);
		assertSame(1, preguntaLibre1.getRespuestas().size());
	}

	
	@Test
	public void testAlumnosContestados() {
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntaLibre1);
		
		opciones1.add(opcion1);
		
		preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1);
		assertEquals(preguntaLibre1.alumnosContestados(), 50, 0.1);
		
		opciones2.add(opcion2);
		
		preguntaLibre1.pregAniadirRespuesta(alumno2, opciones2);
		assertEquals(preguntaLibre1.alumnosContestados(), 100, 0.1);
	
	}
	
	
	@Test
	public void testAlumnosContestadosCorrecto() {
		
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntaLibre1);
		
		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntaLibre1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1);

		assertEquals(preguntaLibre1.alumnosContestadosCorrecto(), 100, 0.1);
		
		opciones2.add(opcion2);
		preguntaLibre1.pregAniadirRespuesta(alumno2, opciones2);
		
		assertEquals(preguntaLibre1.alumnosContestadosCorrecto(), 50, 0.1);
		
		opciones1.add(opcion1);
		preguntaLibre1.pregAniadirRespuesta(alumno3, opciones1);
		
		assertEquals(preguntaLibre1.alumnosContestadosCorrecto(), 66.66, 0.1);
	}
	

	public void testObtenerNotaAlumno(Alumno alumno){

		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntaLibre1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertEquals(preguntaLibre1.obtenerNotaAlumno(alumno1), 0.5f, 0.1);
		
		respuesta2.resAniadirOpcion(opcion2);
		preguntaLibre1.setRespuestaProf(respuesta2);

		assertEquals(preguntaLibre1.obtenerNotaAlumno(alumno2),0.5f, 0.1);
	}
	
	
	@Test
	public void testCorregirRespuesta() {
		respuesta1.resAniadirOpcion(opcion1);
		respuestaProf.resAniadirOpcion(opcion1);
		preguntaLibre1.setRespuestaProf(respuestaProf);
		opciones1.add(opcion1);
		preguntaLibre1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertSame(true, preguntaLibre1.corregirRespuesta(respuesta1));
	}


}
