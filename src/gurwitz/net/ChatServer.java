package gurwitz.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer extends ReaderThread {

	private WriterThread writer;
	public final static Logger LOGGER = Logger.getLogger(ChatServer.class
			.getName());

	public ChatServer() throws IOException {
		server = new ServerSocket(8080);
		writer = new WriterThread();
		writer.start();
	}

	public void listen() throws IOException {
		socket = server.accept();

		while (socket != null) {
			ClientHandler ch = new ClientHandler(socket, writer);
			ch.start();
			socket = server.accept();
			LOGGER.log(Level.INFO, "Socket accepted");

		}
	}

	public static void main(String[] args) {
		try {
			ChatServer server = new ChatServer();
			server.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
