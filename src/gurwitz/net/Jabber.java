package gurwitz.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Jabber {

	Socket socket;
	OutputStream out;
	int x;

	public Jabber(Socket s) throws IOException {
		socket = s;
		out = socket.getOutputStream();
	}

	public void jabber() throws IOException, InterruptedException {
		while (socket != null) {
			Thread.sleep(500);
			String message = String.valueOf(x++);
			// System.out.println(message);
			out.write(message.getBytes());
			out.write("\n".getBytes());
		}
		socket.close();
	}

	public static void main(String[] args) {

		try {
			Socket socket;
			socket = new Socket("localhost", 8080);
			Jabber j = new Jabber(socket);
			j.jabber();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
