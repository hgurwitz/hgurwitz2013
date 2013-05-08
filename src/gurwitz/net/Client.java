package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends WritingThread {

	public Client(Socket socket, ChatGUI gui) throws IOException {
		super(socket, gui);
	}

}