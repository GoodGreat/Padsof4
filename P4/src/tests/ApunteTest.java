package tests;
import asignatura.*;
import sistema.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
	
	
public class ApunteTest {
	private Apunte apunte1;
	
	@Before
	public void setUp() throws Exception {
		apunte1 = new Apunte("Apunte1", false, "Este es un apunte");
	}

	@Test
	public void testApunte() {
		assertNotNull(apunte1);
	}

	@Test
	public void testGetTitulo() {
		String nombreApunte = apunte1.getTitulo();
		assertSame("Apunte1", nombreApunte);
	}

	@Test
	public void testSetTitulo() {
		apunte1.setTitulo("Apunte2");
		assertSame("Apunte2", apunte1.getTitulo());
	}

	@Test
	public void testGetVisible() {
		assertSame(false, apunte1.getVisible());
	}

	@Test
	public void testPublicarApunte() {
		Asignatura asignatura = new Asignatura("PADSOF", false);
		Tema tema1 = new Tema("Tema1", false);
		asignatura.aniadirTema(tema1);
		tema1.aniadirApunte(apunte1);
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		apunte1.publicarApunte();
		assertSame(true, apunte1.getVisible());
	}

	@Test
	public void testOcultarApunte() {
		apunte1.ocultarApunte();
		assertSame(false, apunte1.getVisible());
	}

	@Test
	public void testGetContenido() {
		assertSame("Este es un apunte", apunte1.getContenido());
	}

	@Test
	public void testSetContenido() {
		Sistema.getInstance().log_in("Profesor", "profeduudle");
		apunte1.setTitulo("Este es el apunte1");
		assertSame("Este es el apunte1", apunte1.getTitulo());
	}

}
