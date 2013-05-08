package hgurwitz2013.tests.net;

import static org.junit.Assert.assertEquals;
import gurwitz.net.MessageParser;
import gurwitz.net.MessageType;

import org.junit.Before;
import org.junit.Test;

public class MessageParserTest {

	MessageParser parser;
	String name;
	String say;
	String leave;
	String join;

	@Before
	public void setUp() {
		parser = new MessageParser();
		name = "Hadassah";
		say = "SAY " + name + " test";
		leave = "LEAVE " + name;
		join = "JOIN " + name;
	}

	@Test
	public void testGetType() {
		assertEquals(MessageType.SAY, parser.getMessageType(say));
		assertEquals(MessageType.LEAVE, parser.getMessageType(leave));
		assertEquals(MessageType.JOIN, parser.getMessageType(join));
	}

	@Test
	public void testGetName() {
		assertEquals(name, parser.getName(leave));
		assertEquals(name, parser.getName(join));
		// assertEquals(name, parser.getName(say));
	}

	@Test
	public void testGetMessage() {
		assertEquals(" has left", parser.getMessage(leave));
		assertEquals(" has joined", parser.getMessage(join));
		assertEquals(name + " test", parser.getMessage(say));
	}

}
