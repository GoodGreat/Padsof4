package tests;

import asignatura.*;
import sistema.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class AlumnoTest {
	private Alumno alumno1;
	private List<Asignatura> asignaturas;
	private List<Solicitud> solicitudes;
	
	
	@Before
	public void setUp() throws Exception {
		alumno1 = new Alumno("Pedro", "Martinez", "pedro.32@gmail.com", "8721312", "pedro9");
		asignaturas = new ArrayList<Asignatura>();
		solicitudes = new ArrayList<Solicitud>();
	}

	@Test
	public void testAlumno() {
		assertNotNull(alumno1);
	}

	@Test
	public void testCrearSolicitud() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		Solicitud solicitud = new Solicitud(alumno1, asignatura1);
		assertSame(alumno1.getSolicitudes().size(), 0);			//Verificar que el tamaño es 0 antes de crear la solicitud
		alumno1.crearSolicitud(solicitud);
		assertSame(alumno1.getSolicitudes().size(), 1);			//Verificar que el tamaño es 1 despues de crear la solicitud
		assertSame(alumno1.getSolicitudes().get(0), solicitud);	//Verificar que es la misma solicitud que hemos introducido
	}

	@Test
	public void testExpulsarAlumno() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		assertSame(alumno1.getAsignaturas().size(), 0);			//Verificar que el tamaño es 0 antes de aniadir la asignatura
		alumno1.aniadirAsignatura(asignatura1);
		assertSame(alumno1.getAsignaturas().size(), 1);			//Verificar que el tamaño es 1 despues de aniadir la asignatura
		alumno1.expulsarAlumno(asignatura1);
		assertSame(alumno1.getAsignaturas().size(), 0);			//Verificar que el tamaño vuelve a ser 0, tras la llamada a expulsar alumno	
	}

	@Test
	public void testAniadirAsignatura() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		assertSame(alumno1.getAsignaturas().size(), 0);				//Verificar que el tamaño es 0 antes de aniadir la asignatura
		alumno1.aniadirAsignatura(asignatura1);
		assertSame(alumno1.getAsignaturas().size(), 1);				//Verificar que el tamaño es 1 despues de aniadir la asignatura
		assertSame(alumno1.getAsignaturas().get(0), asignatura1);	//Verificar que la asignatura aniadida es la misma
	}

	@Test
	public void testGetNombre() {
		assertSame("Pedro", alumno1.getNombre());
	}

	@Test
	public void testSetNombre() {
		assertSame("Pedro", alumno1.getNombre());					//Verificar que el nombre es el introducido en @Before: "Pedro"
		alumno1.setNombre("Miguel");
		assertNotSame("Pedro", alumno1.getNombre());				//Verificar que el nombre ya no es "Pedro"
		assertSame("Miguel", alumno1.getNombre());					//Verificar que el nombre es "Miguel"
	}

	@Test
	public void testGetApellido() {
		assertSame("Martinez", alumno1.getApellido());
	}

	@Test
	public void testSetApellido() {
		assertSame("Martinez", alumno1.getApellido());				//Verificar que el apellido es el introducido en @Before: "Martinez"
		alumno1.setApellido("Martin");
		assertNotSame("Martinez", alumno1.getApellido());			//Verificar que el apellido ya no es "Martinez"
		assertSame("Martin", alumno1.getApellido());				//Verificar que el apellido es "Martin"
	}

	@Test
	public void testGetCorreo() {
		assertSame("pedro.32@gmail.com", alumno1.getCorreo());
	}

	@Test
	public void testSetCorreo() {
		assertSame("pedro.32@gmail.com", alumno1.getCorreo());		//Verificar que el correo es el introducido en @Before: "pedro.32@gmail.com"
		alumno1.setCorreo("pedro_crack@gmail.com");
		assertNotSame("pedro.32@gmail.com", alumno1.getCorreo());	//Verificar que el apellido ya no es "pedro.32@gmail.com"
		assertSame("pedro_crack@gmail.com", alumno1.getCorreo());	//Verificar que el apellido es "pedro_crack@gmail.com"
	}

	@Test
	public void testGetNumA() {
		assertSame("8721312", alumno1.getNumA());
	}

	@Test
	public void testSetNumA() {
		assertSame("8721312", alumno1.getNumA());					//Verificar que el NumA es el introducido en @Before: "8721312"
		alumno1.setNumA("8777799");
		assertNotSame("8721312", alumno1.getNumA());				//Verificar que el NumA ya no es "8721312"
		assertSame("8777799", alumno1.getNumA());					//Verificar que el NumA es "8777799"
	}
	
	
	@Test
	public void testGetPassword() {
		assertSame("pedro9", alumno1.getPassword());
	}

	@Test
	public void testSetPassword() {
		assertSame("pedro9", alumno1.getPassword());				//Verificar que la contrasenia es la introducida en @Before: "pedro9"
		alumno1.setPassword("pedrito_cr");
		assertNotSame("pedro9", alumno1.getPassword());				//Verificar que la contraseña ya no es "pedro9"
		assertSame("pedrito_cr", alumno1.getPassword());			//Verificar que la contraseña es "pedrito_cr"
	}
	
	@Test
	public void testGetAsignaturas() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		assertSame(alumno1.getAsignaturas().size(), 0);					//Verificar que el tamaño es 0 antes de introducir la asignatura
		alumno1.aniadirAsignatura(asignatura1);
		asignaturas.add(asignatura1);
		assertSame(alumno1.getAsignaturas().size(), 1);					//Verificar que el tamaño es 1 despues de introducir la asignatura
		assertSame(alumno1.getAsignaturas().get(0), asignatura1);		//Verificar que la asignatura se ha introducido correctamente
		assertSame(alumno1.getAsignaturas().get(0), asignaturas.get(0));//Verificar de nuevo que la asignatura esta adecuadamente introducida
	}

	@Test
	public void testSetAsignaturas() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		assertSame(alumno1.getAsignaturas().size(), 0);					//Verificar que el tamaño es 0 antes de asignarle un array de Asignaturas
		asignaturas.add(asignatura1);
		alumno1.setAsignaturas((ArrayList<Asignatura>) asignaturas);	//Asignar al array de asignaturas de alumno otro array anteriormente creado
		assertSame(alumno1.getAsignaturas().size(), 1);					//Verificar que el tamaño es 1 despues de asignarle un array de Asignaturas
		assertSame(alumno1.getAsignaturas().get(0), asignatura1);		//Verificar que la asignacion es correcta		
	}

	@Test
	public void testGetSolicitudes() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		Solicitud solicitud = new Solicitud(alumno1, asignatura1);
		assertSame(alumno1.getSolicitudes().size(), 0);					//Verificar que el tamaño es 0 antes de crear la solicitud
		alumno1.crearSolicitud(solicitud);
		solicitudes.add(solicitud);
		assertSame(alumno1.getSolicitudes().size(), 1);					//Verificar que el tamaño es 1 despues de crear la solicitud
		assertSame(alumno1.getSolicitudes().get(0), solicitud);			//Verificar que la solicitud se ha introducido correctamente
		assertSame(alumno1.getSolicitudes().get(0), solicitudes.get(0));//Verificar de nuevo que la solicitud esta adecuadamente introducida
	}

	@Test
	public void testSetSolicitudes() {
		Asignatura asignatura1 = new Asignatura("PADSOF", true);
		Solicitud solicitud = new Solicitud(alumno1, asignatura1);
		assertSame(alumno1.getSolicitudes().size(), 0);					//Verificar que el tamaño es 0 antes de crear la solicitud
		solicitudes.add(solicitud);
		alumno1.setSolicitudes((ArrayList<Solicitud>) solicitudes);		//Asignar al array de solicitudes de alumno otro array anteriormente creado
		assertSame(alumno1.getSolicitudes().size(), 1);					//Verificar que el tamaño es 1 despues de asignarle un array de Asignaturas
		assertSame(alumno1.getSolicitudes().get(0), solicitud);			//Verificar que la asignacion es correcta	
	}
}
