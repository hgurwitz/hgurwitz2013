package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends ReaderThread {

	public Server(ServerSocket server, ChatGUI chatGUI) throws IOException {
		// server = new ServerSocket(1025);
		socket = server.accept();
		output = socket.getOutputStream();
		in = socket.getInputStream();
		inputStreamReader = new Scanner(in);
		this.gui = chatGUI;
		// out.flush();
	}

	public void send(String message) throws IOException {
		output.write(message.getBytes());
		output.write("\n".getBytes());
		output.flush();
	}

	public void run() {

		Scanner scanner = new Scanner(in);
		while (true) {
			if (scanner.hasNext()) {
				gui.getChatMessage((scanner.nextLine()));
			}

		}
	}

}