package calc.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import calc.Producto;

public class ProductoTest {

	@Test
	public void testProducto() {
		Producto producto = new Producto();
		double resultado = producto.getProducto(2.0, 3.0);
		assertEquals(6, resultado, "Al multiplicar 2 por 3 debería dar 6");
	}
	
	@Test
	public void testMultiplica() {
		Producto producto = new Producto(6.0);
		double valor = producto.multiplica(2.0);
		assertEquals(12,valor,"Al multiplicar 6 por 2 debería dar 12");
	}
}
