package gurwitz.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class WriterThread extends Thread {

	private LinkedBlockingQueue<String> waitingQueue;
	private LinkedList<OutputStream> outs;

	public WriterThread() {
		waitingQueue = new LinkedBlockingQueue<String>();
		outs = new LinkedList<OutputStream>();

	}

	public void addToQueue(String s) {
		waitingQueue.add(s);
	}

	public void addToOuts(OutputStream os) {
		outs.add(os);
	}

	public void addToOuts(Socket s) throws IOException {
		outs.add(s.getOutputStream());
	}

	public void run() {

		while (true) {
			String s;
			try {
				s = waitingQueue.take();

				writeMessageToStreams(s);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void writeMessageToStreams(String s) {
		Iterator<OutputStream> iter = outs.iterator();
		while (iter.hasNext()) {
			OutputStream os = iter.next();
			try {
				writeToStream(os, s);
			} catch (Exception e) {
				iter.remove();
			}

		}

	}

	private void writeToStream(OutputStream os, String s) throws IOException {
		os.write(s.getBytes());
		os.write("\n".getBytes());
		os.flush();

	}

}
