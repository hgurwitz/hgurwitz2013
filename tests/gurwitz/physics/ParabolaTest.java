package gurwitz.physics;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParabolaTest {

	private Parabola p;

	@Before
	public void setUp() throws Exception {
		p = new Parabola(37, 73);
	}

	@Test
	public void testX() {
		System.out.println(p.getX(5));
		assertEquals( 291.50196122, p.getX(5), .5);
	}

	// -232.5

	@Test
	public void testY() {
		System.out.println(p.getY(5));
		assertEquals( 97.1624833811, p.getY(5),.5);
	}

}
