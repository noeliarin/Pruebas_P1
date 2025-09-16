package calc.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import calc.Division;
import calc.Producto;


public class DivisionTest {
	
	@Test
	public void testGetDivision() {
		Division division = new Division();
		double resultado = division.getDivision(4.0, 2.0);
		assertEquals(2, resultado, "Al dividir 4 entre 2 debería de dar 2");
	}
	
	@Test
	public void testDivide() {
		Division division = new Division(6.0);
		double valor = division.divide(2.0);
		assertEquals(3,valor,"Al dividir 6 entre 2 debería dar 3");
	}
	
}
