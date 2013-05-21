package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WritingThread extends Thread {

	protected Socket socket;
	protected ChatGUI gui;
	protected OutputStream output;
	protected InputStream in;

	public WritingThread(Socket socket, ChatGUI gui) throws IOException {
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
		while (scanner.hasNext()) {
			// while (true) {
			// if (scanner.hasNext()) {
			try {
				gui.receiveChatMessage((scanner.nextLine()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// }

		}
	}

}
