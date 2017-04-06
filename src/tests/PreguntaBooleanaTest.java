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

public class PreguntaBooleanaTest {
	private Alumno alumno1;
	private Alumno alumno2;
	private Alumno alumno3;
	private Asignatura asignatura1;
	private Tema tema1;
	private Ejercicio ejercicio1;
	private PreguntaBooleana preguntabool1;
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
		preguntabool1 = new PreguntaBooleana("¿Es el cielo azul?", 0.5f, false, 0.25f, "booleana");
		respuestaProf = new RespuestaBooleana(null);
		respuesta1 = new RespuestaBooleana(alumno1);
		respuesta2 = new RespuestaBooleana(alumno2);
		opciones1 = new ArrayList<Opcion>();
		opciones2 = new ArrayList<Opcion>();
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		opcion3 = new Opcion("Esta es la opcion3");
	}

	@Test
	public void testPregunta() {
		assertNotNull(preguntabool1);
	}
	
	@Test
	public void testPreguntaBooleana() {
		assertNotNull(preguntabool1);
	}
	
	@Test
	public void testGetEnunciado() {
		assertSame("¿Es el cielo azul?", preguntabool1.getEnunciado());
	}

	@Test
	public void testGetPuntuacion() {
		assertEquals(0.5f, preguntabool1.getPuntuacion(), 0.1);
	}

	@Test
	public void testGetFalloResta() {
		assertSame(false, preguntabool1.getFalloResta());
	}

	@Test
	public void testGetResta() {
		assertEquals(0.25f, preguntabool1.getResta(), 0.1);
	}

	@Test
	public void testGetTipo(){
		assertSame("booleana", preguntabool1.getTipo());
	}
	
	@Test
	public void testGetEjercicioSuperior(){
		ejercicio1.aniadirPregunta(preguntabool1);
		assertSame(ejercicio1 , preguntabool1.getEjercicioSuperior());
	}
	
	@Test
	public void testSetEjercicioSuperior(){
		preguntabool1.setEjercicioSuperior(ejercicio1);
		assertSame(ejercicio1, preguntabool1.getEjercicioSuperior());
	}
	
	@Test
	public void testGetRespuestaProf(){
		preguntabool1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntabool1.getRespuestaProf());
	}
	
	@Test
	public void testSetRespuestaProf(){
		preguntabool1.setRespuestaProf(respuestaProf);
		assertSame(respuestaProf, preguntabool1.getRespuestaProf());
	}
	
	@Test
	public void testGetOpciones() {
		int i = 0;
		preguntabool1.pregAniadirOpcion(opcion1);
		assertSame(opcion1, preguntabool1.getOpciones().get(i));
		assertSame(1, preguntabool1.getOpciones().size());
	}
	
	@Test
	public void testGetRespuestas() {
		int i = 0;
		opciones1.add(opcion1);
		preguntabool1.pregAniadirRespuesta(alumno1, opciones1);
		assertSame(opcion1, preguntabool1.getRespuestas().get(i).getOpciones().get(i));
		assertSame(1, preguntabool1.getRespuestas().size());
	}
	
	@Test
	public void testPregAniadirOpcion() {
		int i = 0;
		assertSame(preguntabool1.pregAniadirOpcion(opcion1), true);
		assertSame(preguntabool1.getOpciones().get(i), opcion1);
		assertSame(1, preguntabool1.getOpciones().size());
		
		assertSame(preguntabool1.pregAniadirOpcion(opcion2), true);
		i++;
		assertSame(preguntabool1.getOpciones().get(i), opcion2);
		assertSame(2, preguntabool1.getOpciones().size());
		
		assertSame(preguntabool1.pregAniadirOpcion(opcion3), false);
		assertSame(preguntabool1.getOpciones().get(i), opcion2);
		assertSame(2, preguntabool1.getOpciones().size());
	}
	
	@Test
	public void testPregAniadirRespuesta() {
		int i = 0;
		opciones1.add(opcion1);
		assertSame(preguntabool1.pregAniadirRespuesta(alumno1, opciones1), true);
		assertSame(preguntabool1.getRespuestas().get(i).getOpciones().get(i), opcion1);
		assertSame(1, preguntabool1.getRespuestas().size());
	}

	
	@Test
	public void testAlumnosContestados() {
		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntabool1);
		
		opciones1.add(opcion1);
		
		preguntabool1.pregAniadirRespuesta(alumno1, opciones1);
		assertEquals(preguntabool1.alumnosContestados(), 50, 0.1);
		
		opciones2.add(opcion2);
		
		preguntabool1.pregAniadirRespuesta(alumno2, opciones2);
		assertEquals(preguntabool1.alumnosContestados(), 100, 0.1);
	
	}
	
	
	@Test
	public void testAlumnosContestadosCorrecto() {

		asignatura1.aniadirAlumno(alumno1);
		asignatura1.aniadirAlumno(alumno2);
		asignatura1.aniadirTema(tema1);
		tema1.aniadirEjercicio(ejercicio1);
		ejercicio1.aniadirPregunta(preguntabool1);
		
		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntabool1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntabool1.pregAniadirRespuesta(alumno1, opciones1);

		assertEquals(preguntabool1.alumnosContestadosCorrecto(), 100, 0.1);
		
		opciones2.add(opcion2);
		preguntabool1.pregAniadirRespuesta(alumno2, opciones2);
		
		assertEquals(preguntabool1.alumnosContestadosCorrecto(), 50, 0.1);
		
		opciones1.add(opcion1);
		preguntabool1.pregAniadirRespuesta(alumno3, opciones1);
		
		assertEquals(preguntabool1.alumnosContestadosCorrecto(), 66.66, 0.1);
	}
	

	public void testObtenerNotaAlumno(Alumno alumno){

		respuestaProf.resAniadirOpcion(opcion1);
		
		preguntabool1.setRespuestaProf(respuestaProf);
		
		opciones1.add(opcion1);
		preguntabool1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertEquals(preguntabool1.obtenerNotaAlumno(alumno1), 0.5f, 0.1);
		
		respuesta2.resAniadirOpcion(opcion2);
		preguntabool1.setRespuestaProf(respuesta2);

		assertEquals(preguntabool1.obtenerNotaAlumno(alumno2),0.5f, 0.1);
	}
	
	
	@Test
	public void testCorregirRespuesta() {
		respuesta1.resAniadirOpcion(opcion1);
		respuestaProf.resAniadirOpcion(opcion1);
		preguntabool1.setRespuestaProf(respuestaProf);
		opciones1.add(opcion1);
		preguntabool1.pregAniadirRespuesta(alumno1, opciones1);
		
		assertSame(true, preguntabool1.corregirRespuesta(respuesta1));
		
	}


}
