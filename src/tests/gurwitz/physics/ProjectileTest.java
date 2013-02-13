package tests.gurwitz.physics;

import static org.junit.Assert.*;
import gurwitz.physics.Projectile;

import org.junit.Before;
import org.junit.Test;


public class ProjectileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetX() {
		Projectile p=new Projectile(-323, 73);
		assertEquals(291.5, p.getX(5), .1);
	}
	
	@Test
	public void testGetY() {
		Projectile p=new Projectile(-323, 73);
		assertEquals(97.1, p.getY(5), .1);
	}

}
