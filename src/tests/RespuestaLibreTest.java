package tests;

import static org.junit.Assert.*;
import ejercicio.*;
import sistema.Alumno;
import org.junit.Before;
import org.junit.Test;

public class RespuestaLibreTest {
private RespuestaLibre respuestalibre1;
private Alumno alumno1;
private Opcion opcion1;
private Opcion opcion2;

	@Before
	public void setUp() throws Exception {
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		respuestalibre1 = new RespuestaLibre(alumno1);
	}

	@Test
	public void testRespuesta() {
		assertNotNull(respuestalibre1);
	}
	
	@Test
	public void testRespuestaLibre() {
		assertNotNull(respuestalibre1);
	}

	@Test
	public void testGetOpciones() {
		int i = 0;
		respuestalibre1.resAniadirOpcion(opcion1);
		assertSame(respuestalibre1.getOpciones().get(i), opcion1);
		assertSame(respuestalibre1.getOpciones().size(), 1);
	}

	@Test
	public void testResAniadirOpcion() {
		int i = 0;
		respuestalibre1.resAniadirOpcion(opcion1);
		assertSame(respuestalibre1.getOpciones().get(i), opcion1);
		assertSame(respuestalibre1.getOpciones().size(), 1);
		
		assertSame(respuestalibre1.resAniadirOpcion(opcion2), false);
	}

	@Test
	public void testGetAlumno() {
		assertSame(respuestalibre1.getAlumno(), alumno1);
	}
	
}
