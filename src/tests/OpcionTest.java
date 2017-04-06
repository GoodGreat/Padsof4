package tests;

import static org.junit.Assert.*;
import ejercicio.*;
import org.junit.Before;
import org.junit.Test;


public class OpcionTest {
private Opcion opcion1;

	@Before
	public void setUp() throws Exception {
		opcion1 = new Opcion("Esta es la opcion1");
	}

	@Test
	public void testOpcion() {
		assertNotNull(opcion1);
	}

	@Test
	public void testGetOpcion() {
		assertSame("Esta es la opcion1", opcion1.getOpcion());
	}

}
