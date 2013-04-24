package gurwitz.net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {

	private JButton sendBtn;
	private JTextField compose;
	private JTextArea chat;
	private JScrollPane scroll;
	private ReaderThread readerThread;
	private Socket socket;

	public ChatGUI() throws IOException {
		super();
		sendBtn = new JButton("Send");
		compose = new JTextField();
		compose.addKeyListener(new EnterListener());
		chat = new JTextArea();
		scroll = new JScrollPane(chat);
		chat.setEditable(false);
		setLayout(new BorderLayout());

		add(scroll, BorderLayout.CENTER);
		add(new ComposePanel(sendBtn, compose), BorderLayout.SOUTH);
		sendBtn.addActionListener(new ClickListener());
		setVisible(true);
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setName("Chat");

		// initializeClient();

		initializeServer();

		readerThread.start();

	}

	private void initializeClient() throws UnknownHostException, IOException {
		socket = new Socket("192.168.117.195", 1025);
		readerThread = new Client(socket, this);
	}

	private void initializeServer() throws IOException {
		ServerSocket server = new ServerSocket(1025);
		readerThread = new Server(server, this);
	}

	public void getChatMessage(String s) {
		String oldChats = chat.getText();
		chat.setText(oldChats + "\n" + s);
	}

	public void sendTheChat() throws IOException {
		String s = compose.getText();
		// String oldChats = chat.getText();
		// chat.setText(oldChats + "\n" + s);
		String oldChats = chat.getText();
		chat.setText(oldChats + "\n" + s);
		readerThread.send(s);
		compose.setText("");

	}

	public static void main(String[] args) {
		try {
			new ChatGUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ComposePanel extends JPanel {
		public ComposePanel(JButton send, JTextField compose) {
			setLayout(new BorderLayout());
			add(compose, BorderLayout.CENTER);
			add(send, BorderLayout.EAST);
		}
	}

	private class ClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				sendTheChat();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private class EnterListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			// TODO Auto-generated method stub
			if (event.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					sendTheChat();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
