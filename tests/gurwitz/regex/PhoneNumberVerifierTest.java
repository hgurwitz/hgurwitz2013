package gurwitz.regex;

import static org.junit.Assert.*;

import org.junit.*;

public class PhoneNumberVerifierTest {

	@Test
	public void testVerify() {
		PhoneNumberVerifier v = new PhoneNumberVerifier();
		assertTrue(v.verify("17186924498"));
		assertTrue(v.verify("6924498"));
		assertTrue(v.verify("1-7186924498"));
		assertTrue(v.verify("1 7186924498"));
		assertTrue(v.verify("1 718 692 4498"));
		assertTrue(v.verify("1-718-692-4498"));
		assertTrue(v.verify("1-718692-4498"));
		assertTrue(v.verify("1718 692-4498"));
		assertTrue(v.verify("7186924498"));
		assertFalse(v.verify("23456789"));
		assertFalse(v.verify("12345678"));
		assertFalse(v.verify("112345678"));
	}

}
