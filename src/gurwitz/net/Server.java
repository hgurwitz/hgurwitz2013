package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends WritingThread {

	// private Scanner inputStreamReader;

	public Server(ServerSocket server, ChatGUI chatGUI) throws IOException {
		// server = new ServerSocket(1025);
		super(server.accept(), chatGUI);
		// inputStreamReader = new Scanner(in);

	}

}