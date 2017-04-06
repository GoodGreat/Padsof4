package tests;

import asignatura.*;
import sistema.*;
import static org.junit.Assert.*;
import org.junit.*;

public class SolicitudTest {
	private Alumno alumno1;
	private Asignatura asignatura1;
	private Solicitud solicitud;
	
	@Before
	public void setUp() throws Exception{
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		asignatura1 = new Asignatura("Cirel", true);
		solicitud = new Solicitud(alumno1, asignatura1);
	}
	
	@Test
	public void testSolicitud() {
		assertNotNull(solicitud);
	}

	@Test
	public void testAceptarSolicitud() {
		
		solicitud.aceptarSolicitud();
		assertSame(alumno1, asignatura1.getAlumnos().get(0));		//Verificar que se ha introducido bien el alumno en el Array de la asignatura
		assertSame(asignatura1.getAlumnos().size(), 1);				//Verificar que el array solo tiene un elemento
		assertSame(asignatura1, alumno1.getAsignaturas().get(0));	//Verificar que se ha introducido bien la asignatura en el Array del alumno
		assertSame(alumno1.getAsignaturas().size(), 1);				//Verificar que el array solo tiene un elemento
	}
	
	@Test
	public void testDenegarSolicitud() {
		solicitud.denegarSolicitud();
		assertSame(asignatura1.getAlumnos().size(), 0);		//Verificar que el array no tiene ningun elemento
		assertSame(alumno1.getAsignaturas().size(), 0);		//Verificar que el array no tiene ningun elemento
	}

	@Test
	public void testGetAlumno() {
		Alumno alumno2 = solicitud.getAlumno();
		assertSame(alumno1, alumno2);
	}

	@Test
	public void testGetAsignatura() {
		Asignatura asignatura2 = solicitud.getAsignatura();
		assertSame(asignatura1, asignatura2);
	}
}
