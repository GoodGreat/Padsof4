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

public class PreguntaUnicaTest {
	private Alumno alumno1;
	private Alumno alumno2;
	private Alumno alumno3;
	private Asignatura asignatura1;
	private Tema tema1;
	private Ejercicio ejercicio1;
	private PreguntaUnica preguntaUnica1;
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
		ejercicio1 = new Ejercicio("Ejercicio1", 0.5f, 2017, 8, 10, 12, 0, 2017, 10, 10, 12, 0, false, false);
		preguntaUnica1 = new PreguntaUnica("¿Es el cielo azul?", 0.5f, false, 0.25f, "unica");
		respuestaProf = new RespuestaUnica(null);
		respuesta1 = new RespuestaUnica(alumno1);
		respuesta2 = new RespuestaUnica(alumno2);
		respuesta2 = new RespuestaUnica(alumno3);
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		opcion3 = new Opcion("Esta es la opcion3");
	}

	@Test
	public void testPregunta() {
		assertNotNull(preguntaUnica1);
	}
	
	@Test
	public void testPreguntaUnica() {
		assertNotNull(preguntaUnica1);
	}
	
	@Test
	public void testGetEnunciado() {
		assertSame("¿Es el cielo azul?", preguntaUnica1.getEnunciado());
	}

	@Test
	public void testGetPuntuacion() {
		assertEquals(0.5f, preguntaUnica1.getPuntuacion(), 0.1);
	}

	@Test
	public void testGetFalloResta() {
		assertSame(false, preguntaUnica1.getFalloResta());
	}

	@Test
	public void testGetResta() {
		assertEquals(0.25f, preguntaUnica1.getResta(), 0.1);
	}

	@Test
	public void testGetTipo(){
		assertSame("unica", preguntaUnica1.getTipo());
	}
	
	@Test
	public void testGetEjercicioSuperior(){
		ejercicio1.aniadirPregunta(preguntaUnica1);
		assertSame(ejercicio1 , preguntaUnica1.getEjercicioSuperior());
	}
	
	@Test
	public void testSetEjercicioSuperior(){
		preguntaUnica1.setEjercicioSuperior(ejercicio1);
		assertSame(ejercicio1, preguntaUnica1.getEjercicioSuperior());
	}
	

	@Test
	public void testGetAleatorio() {
		boolean aleatorio = true;
		ejercicio1.setAleatorio(aleatorio);
		ejercicio1.aniadirPregunta(preguntaUnica1);
		assertSame(aleatorio, preguntaUnica1.getAleatorio());
	}

	@Test
	public void testSetAleatorio() {
		boolean aleatorio = true;
		preguntaUnica1.setAleatorio(aleatorio);
		assertSame(aleatorio, preguntaUnica1.getAleatorio());
	}
	
	@Test
	public void testGetRespuestaProf(){
		preguntaUnica1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntaUnica1.getRespuestaProf());
	}
	
	@Test
	public void testSetRespuestaProf(){
		preguntaUnica1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntaUnica1.getRespuestaProf());
	}
	
	@Test
	public void testGetOpciones() {
		int i = 0;
		preguntaUnica1.pregAniadirOpcion(opcion1);
		assertSame(opcion1, preguntaUnica1.getOpciones().get(i));
		assertSame(1, preguntaUnica1.getOpciones().size());
	}
	
	@Test
	public void testGetRespuestas() {
		int i = 0;
		opciones1.add(opcion1);
		preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1);
		assertSame(opcion1, preguntaUnica1.getRespuestas().get(i).getOpciones().get(i));
		assertSame(1, preguntaUnica1.getRespuestas().size());
	}
	
	@Test
	public void testPregAniadirOpcion() {
		int i = 0;
		assertSame(preguntaUnica1.pregAniadirOpcion(opcion1), true);
		assertSame(preguntaUnica1.getOpciones().get(i), opcion1);
		assertSame(1, preguntaUnica1.getOpciones().size());
		
		assertSame(preguntaUnica1.pregAniadirOpcion(opcion2), true);
		i++;
		assertSame(preguntaUnica1.getOpciones().get(i), opcion2);
		assertSame(2, preguntaUnica1.getOpciones().size());
		
		assertSame(preguntaUnica1.pregAniadirOpcion(opcion3), true);
		i++;
		assertSame(preguntaUnica1.getOpciones().get(i), opcion3);
		assertSame(3, preguntaUnica1.getOpciones().size());
	}
	
	@Test
	public void testPregAniadirRespuesta() {
		int i = 0;
		opciones1.add(opcion1);
		assertSame(preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1), true);
		assertSame(preguntaUnica1.getRespuestas().get(i).getOpciones().get(i), opcion1);
		assertSame(1, preguntaUnica1.getRespuestas().size());
	}

	
	@Test
	public void testAlumnosContestados() {
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntaUnica1);
		
		opciones1.add(opcion1);
		
		preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1);
		assertEquals(preguntaUnica1.alumnosContestados(), 50, 0.1);
		
		opciones2.add(opcion2);
		
		preguntaUnica1.pregAniadirRespuesta(alumno2, opciones2);
		assertEquals(preguntaUnica1.alumnosContestados(), 100, 0.1);
	}
	
	
	@Test
	public void testAlumnosContestadosCorrecto() {
	
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntaUnica1);
		
		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntaUnica1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1);

		assertEquals(preguntaUnica1.alumnosContestadosCorrecto(), 100, 0.1);
		
		opciones2.add(opcion2);
		preguntaUnica1.pregAniadirRespuesta(alumno2, opciones2);
		
		assertEquals(preguntaUnica1.alumnosContestadosCorrecto(), 50, 0.1);
		
		opciones1.add(opcion1);
		preguntaUnica1.pregAniadirRespuesta(alumno3, opciones1);
		
		assertEquals(preguntaUnica1.alumnosContestadosCorrecto(), 66.66, 0.1);
	}
	

	public void testObtenerNotaAlumno(Alumno alumno){

		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntaUnica1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertEquals(preguntaUnica1.obtenerNotaAlumno(alumno1), 0.5f, 0.1);
		
		respuesta2.resAniadirOpcion(opcion2);
		preguntaUnica1.setRespuestaProf(respuesta2);

		assertEquals(preguntaUnica1.obtenerNotaAlumno(alumno2),0.5f, 0.1);
	}
	
	
	@Test
	public void testCorregirRespuesta() {
		respuesta1.resAniadirOpcion(opcion1);
		respuestaProf.resAniadirOpcion(opcion1);
		preguntaUnica1.setRespuestaProf(respuestaProf);
		opciones1.add(opcion1);
		preguntaUnica1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertSame(true, preguntaUnica1.corregirRespuesta(respuesta1));
	}
}

