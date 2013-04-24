package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends ReaderThread {

	public Client(Socket socket, ChatGUI gui) throws IOException {
		this.socket = socket;
		this.gui = gui;

		in = socket.getInputStream();
		output = socket.getOutputStream();
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