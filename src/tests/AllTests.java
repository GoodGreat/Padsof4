package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlumnoTest.class,
				ApunteTest.class,
				AsignaturaTest.class,
				EjercicioTest.class,
				ExpulsionTest.class,
				MultipreguntaTest.class,
				MultirrespuestaTest.class,
				OpcionTest.class,
				PreguntaBooleanaTest.class,
				PreguntaLibreTest.class,
				PreguntaUnicaTest.class,
				RespuestaBooleanaTest.class,
				RespuestaLibreTest.class,
				RespuestaUnicaTest.class,
				SistemaTest.class,
				SolicitudTest.class,
				TemaTest.class})
/**
 * Clase para realizar todos los tests
 * @author Álvaro Martinez de Navascues y Alejandro Martin Climent
 */
public class AllTests {}
