package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

	private InputStream in;
	private Scanner scanner;
	private WriterThread writer;

	public ClientHandler(Socket socket, WriterThread writer) throws IOException {

		in = socket.getInputStream();
		scanner = new Scanner(in);
		this.writer = writer;
		writer.addToOuts(socket);

	}

	public void run() {
		while (true) {
			writer.addToQueue((scanner.nextLine()));
		}
	}

}
