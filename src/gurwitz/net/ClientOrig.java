package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientOrig {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket socket = new Socket("192.168.117.119", 1025);
		InputStream in = socket.getInputStream();
		OutputStream output = socket.getOutputStream();

		Scanner scanner = new Scanner(in);
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			System.out.println(scanner.nextLine());

			String message = keyboard.nextLine() + "\n";
			output.write(message.getBytes());
			output.flush();
		}

	}

}