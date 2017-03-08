import static org.junit.Assert.*;
import java.io.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class testJuego {
	static Tablero juego;
	
	@BeforeClass
	public static void begin(){
		juego = new Tablero(new File("input.txt"),new File("output.txt"));
	}

	@Test
	public void testGetxSize() {
		assertTrue(juego.getxSize() == 15);
	}

	@Test
	public void testGetySize() {
		assertTrue(juego.getySize() == 15);
	}

	@Test
	public void testGetTablero() {
		assertNotNull(juego.getTablero());
	}

	@Test
	public void testSetxSize() {
		juego.setxSize(10);
		assertTrue(juego.getxSize() == 10);
		juego.setxSize(15);
	}

	@Test
	public void testSetySize() {
		juego.setySize(10);
		assertTrue(juego.getySize() == 10);
		juego.setySize(15);
	}

	@Test
	public void testGetCell() {
		assertNotNull(juego.getCell(0, 0));
	}

	@Test
	public void testSetCell() {
		Celula cell = new Celula(0,0,true);
		juego.setCell(cell);
		assertEquals(juego.getCell(0, 0), cell);
	}

	@Test
	public void testSetOutFile() {
		File t = new File("papito");
		juego.setOutFile(t);
		assertEquals(juego.getOutFile(), t);
	}

	@Test
	public void testGetOutFile() {
		assertNotNull(juego.getOutFile());
	}

}
