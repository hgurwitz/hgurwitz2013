package hgurwitz2013.tests.net;

import static org.mockito.Mockito.times;
import gurwitz.net.ChatGUI;
import gurwitz.net.WritingThread;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ReaderThreadTest {

	@Before
	public void setUp() {

	}

	@Test
	public void testReadAndAppend() {

		try {
			Socket socket = Mockito.mock(Socket.class);
			JTextArea area = Mockito.mock(JTextArea.class);

			ByteArrayInputStream in = new ByteArrayInputStream(
					"JOIN test\nJOIN gurwitz\n".getBytes());

			ChatGUI gui = Mockito.mock(ChatGUI.class);
			WritingThread reader;
			Mockito.when(socket.getInputStream()).thenReturn(in);
			reader = new WritingThread(socket, gui);
			reader.run();
			Mockito.verify(gui, times(1)).receiveChatMessage("JOIN test");
			Mockito.verify(gui, times(1)).receiveChatMessage("JOIN gurwitz");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
