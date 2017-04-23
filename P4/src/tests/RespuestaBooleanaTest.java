package tests;

import static org.junit.Assert.*;
import ejercicio.*;
import sistema.Alumno;
import org.junit.Before;
import org.junit.Test;

public class RespuestaBooleanaTest {
private RespuestaBooleana respuestabool1;
private Alumno alumno1;
private Opcion opcion1;
private Opcion opcion2;

	@Before
	public void setUp() throws Exception {
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		respuestabool1 = new RespuestaBooleana(alumno1);
		
	}

	@Test
	public void testRespuesta() {
		assertNotNull(respuestabool1);
	}
	
	@Test
	public void testRespuestaBooleana() {
		assertNotNull(respuestabool1);
	}

	@Test
	public void testGetOpciones() {
		int i = 0;
		respuestabool1.resAniadirOpcion(opcion1);
		assertSame(respuestabool1.getOpciones().get(i), opcion1);
		assertSame(respuestabool1.getOpciones().size(), 1);
	}

	@Test
	public void testResAniadirOpcion() {
		int i = 0;
		respuestabool1.resAniadirOpcion(opcion1);
		assertSame(respuestabool1.getOpciones().get(i), opcion1);
		assertSame(respuestabool1.getOpciones().size(), 1);
		
		assertSame(respuestabool1.resAniadirOpcion(opcion2), false);
	}

	@Test
	public void testGetAlumno() {
		assertSame(respuestabool1.getAlumno(), alumno1);
	}
	
}
