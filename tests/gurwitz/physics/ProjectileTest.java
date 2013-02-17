package gurwitz.physics;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class ProjectileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetX() {
		Projectile p = new Projectile(-323, 73, Color.BLACK, 10, 500, 0.0);
		assertEquals(291.5, p.getX(5), .1);
	}

	@Test
	public void testGetY() {
		Projectile p = new Projectile(-323, 73, Color.BLACK, 10, 500, 0.0);
		assertEquals(97.1, p.getY(5), .1);
	}

}
