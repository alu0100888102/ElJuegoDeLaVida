import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;


public class testCell {
	static Tablero juego;
	
	@BeforeClass
	public static void begin(){
		juego = new Tablero(new File("input.txt"),new File("output.txt"));
	}
	
	@Test
	public void testGetXcord() {
		assertTrue(juego.getCell(10, 0).getXcord() == 10);
	}

	@Test
	public void testGetYcord() {
		assertTrue(juego.getCell(0, 10).getYcord() == 10);
	}

	@Test
	public void testGetViva() {
		assertFalse(juego.getCell(0, 0).getViva());
		assertTrue(juego.getCell(6,4).getViva());
	}
	

}
