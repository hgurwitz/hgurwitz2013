package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Echo {

	public static void main(String[] args) {
		Socket socket;
		try {
			socket = new Socket("localhost", 8080);
			InputStream in = socket.getInputStream();
			System.out.println(in.read());
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
