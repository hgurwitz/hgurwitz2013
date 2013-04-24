package gurwitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class Downloader {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("www.amazon.com", 80);
			OutputStream out = socket.getOutputStream();
			out.write("GET /index.html\n\n".getBytes());
			out.flush();
			InputStream in = socket.getInputStream();
			String s = IOUtils.toString(in);
			System.out.println(s);

			// one way
			byte byteReturned = 0;
			while (byteReturned != -1) {
				byteReturned = (byte) in.read();
				System.out.print((char) byteReturned);
			}
			// another way?
			byte[] byteArray = new byte[10000];
			// String s2 = new String(byteArray);
			// int read = in.read(byteArray); //
			System.out.println(Arrays.toString(byteArray));

			// best way
			String str = IOUtils.toString(in);
			System.out.println(str);

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
