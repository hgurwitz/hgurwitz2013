package gurwitz.net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
	private WritingThread readerThread;
	private Socket socket;
	private JTextArea currentlyChatting;
	private ArrayList<String> currentChatters;
	private String myName;
	private MessageParser parser;

	public ChatGUI() throws IOException {
		super();

		sendBtn = new JButton("Send");
		compose = new JTextField();
		compose.addKeyListener(new EnterListener());
		chat = new JTextArea();
		scroll = new JScrollPane(chat);
		chat.setEditable(false);
		setLayout(new BorderLayout());
		myName = "Hadassah";
		currentChatters = new ArrayList<String>();
		currentlyChatting = new JTextArea();
		add(currentlyChatting, BorderLayout.EAST);
		currentChatters.add(myName);
		parser = new MessageParser();
		setCurrentChatterText();
		add(scroll, BorderLayout.CENTER);
		add(new ComposePanel(sendBtn, compose), BorderLayout.SOUTH);
		sendBtn.addActionListener(new ClickListener());
		setVisible(true);
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setName("Chat");
		addWindowListener(new ChatGUIWindowListener());
		initializeClient();

		// initializeServer();

		readerThread.start();

	}

	private void setCurrentChatterText() {
		currentlyChatting.setText("Currently chatting:");
		for (String name : currentChatters) {
			currentlyChatting
					.setText(currentlyChatting.getText() + "\n" + name);
		}

	}

	private void initializeClient() throws UnknownHostException, IOException {
		socket = new Socket("192.168.117.105", 8080);
		// socket = new Socket("localhost", 8080);
		readerThread = new Client(socket, this);
		readerThread.send("JOIN " + myName);
	}

	private void initializeServer() throws IOException {
		ServerSocket server = new ServerSocket(1025);
		readerThread = new Server(server, this);
	}

	public void receiveChatMessage(String s) {
		MessageType type = parser.getMessageType(s);
		String message = "";
		String name = parser.getName(s);
		switch (type) {
		case SAY:
			message = name + ": " + parser.getMessage(s);
			break;
		case JOIN:
			message = name + parser.getMessage(s);
			currentChatters.add(name);
			setCurrentChatterText();
			break;
		case LEAVE:
			message = name + parser.getMessage(s);
			currentChatters.remove(name);
			setCurrentChatterText();
			break;
		case DEFAULT:
			message = "cannot parse message";

		}

		String oldChats = chat.getText();
		chat.setText(oldChats + "\n" + message);
	}

	public void sendTheChat() throws IOException {
		String s = compose.getText();
		String oldChats = chat.getText();
		readerThread.send("SAY " + myName + " " + s);
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

	private class ChatGUIWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				readerThread.send("LEAVE " + myName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
