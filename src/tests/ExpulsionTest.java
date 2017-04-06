package tests;

import asignatura.*;
import sistema.Alumno;
import sistema.Expulsion;
import sistema.Sistema;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class ExpulsionTest {
	private Alumno alumno1;
	private Asignatura asignatura1;
	private Expulsion expulsion;
	
	@Before
	public void setUp() throws Exception{
		alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		asignatura1 = new Asignatura("Cirel", true);
		expulsion = new Expulsion(alumno1, asignatura1);
	}
	
	@Test
	public void testExpulsion() {
		assertNotNull(expulsion);
	}

	@Test
	public void testGetAsignatura() {
		Asignatura asignatura2 = expulsion.getAsignatura();
		assertSame(asignatura1, asignatura2);
	}

	@Test
	public void testGetAlumno() {
		Alumno alumno2 = expulsion.getAlumno();
		assertSame(alumno1, alumno2);
	}

	@Test
	public void testRevocarExpulsion() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		expulsion.revocarExpulsion();
		assertSame(alumno1, asignatura1.getAlumnos().get(0));		//Verificar que se ha introducido bien el alumno en el Array de la asignatura
		assertSame(asignatura1.getAlumnos().size(), 1);				//Verificar que el array solo tiene un elemento
		assertSame(asignatura1, alumno1.getAsignaturas().get(0));	//Verificar que se ha introducido bien la asignatura en el Array del alumno
		assertSame(alumno1.getAsignaturas().size(), 1);				//Verificar que el array solo tiene un elemento
	}

}
