package tests;

import static org.junit.Assert.*;
import ejercicio.*;
import sistema.Alumno;
import org.junit.Before;
import org.junit.Test;

public class MultirrespuestaTest {
private Multirrespuesta multirrespuesta1;
private Alumno alumno1;
private Opcion opcion1;
private Opcion opcion2;
	@Before
	public void setUp() throws Exception {
		opcion1 = new Opcion("Esta es la opcion1");
		opcion2 = new Opcion("Esta es la opcion2");
		alumno1 = new Alumno("Juan", "Gonzalez", "juan@gmail.es", "1234", "contrasena1");
		multirrespuesta1 = new Multirrespuesta(alumno1);
	}

	@Test
	public void testRespuesta() {
		assertNotNull(multirrespuesta1);
	}
	
	@Test
	public void testRespuestaLibre() {
		assertNotNull(multirrespuesta1);
	}

	@Test
	public void testGetOpciones() {
		int i = 0;
		multirrespuesta1.resAniadirOpcion(opcion1);
		assertSame(multirrespuesta1.getOpciones().get(i), opcion1);
		assertSame(multirrespuesta1.getOpciones().size(), 1);
	}

	@Test
	public void testResAniadirOpcion() {
		int i = 0;
		multirrespuesta1.resAniadirOpcion(opcion1);
		assertSame(multirrespuesta1.getOpciones().get(i), opcion1);
		assertSame(multirrespuesta1.getOpciones().size(), 1);
		
		i++;
		assertSame(multirrespuesta1.resAniadirOpcion(opcion2), true);
		assertSame(multirrespuesta1.getOpciones().get(i), opcion2);
		assertSame(multirrespuesta1.getOpciones().size(), 2);
	}

	@Test
	public void testGetAlumno() {
		assertSame(multirrespuesta1.getAlumno(), alumno1);
	}
	
}
