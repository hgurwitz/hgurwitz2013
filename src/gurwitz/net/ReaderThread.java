package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ReaderThread extends Thread {

	protected Socket socket;
	protected ChatGUI gui;
	protected String str;
	protected OutputStream output;
	protected InputStream in;
	protected Scanner inputStreamReader;
	protected ServerSocket server;

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
