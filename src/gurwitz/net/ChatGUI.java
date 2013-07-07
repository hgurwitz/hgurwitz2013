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

	private static final long serialVersionUID = 1L;
	private JButton sendBtn;
	private JTextField compose;
	private JTextArea chat;
	private JScrollPane scroll;
	private WritingThread readerThread;
	private Socket socket;
	private JTextArea currentlyChatting;
	private ArrayList<String> currentChatters;
	private String myName;
	private Message[] messageTypes;

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
		currentlyChatting.setEditable(false);
		add(currentlyChatting, BorderLayout.EAST);
		// currentChatters.add(myName);
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
		initializeMessageTypes();
		readerThread.start();

	}

	public void addMessageToChatWindow(String message) {
		String oldChats = chat.getText();
		chat.setText(oldChats + "\n" + message);
	}

	private void initializeMessageTypes() {
		messageTypes = new Message[4];
		messageTypes[0] = new JoinMessage(this);
		messageTypes[1] = new LeaveMessage(this);
		messageTypes[2] = new AnnounceMessage(this);
		messageTypes[3] = new SayMessage(this);

	}

	public void setCurrentChatterText() {
		currentlyChatting.setText("Currently chatting:");
		for (String name : currentChatters) {
			currentlyChatting
					.setText(currentlyChatting.getText() + "\n" + name);
		}

	}

	private void initializeClient() throws UnknownHostException, IOException {
		// socket = new Socket("192.168.117.105", 8080);
		socket = new Socket("localhost", 8080);
		readerThread = new Client(socket, this);
		readerThread.send("JOIN " + myName);
	}

	private void initializeServer() throws IOException {
		ServerSocket server = new ServerSocket(1025);
		readerThread = new Server(server, this);
	}

	public void receiveChatMessage(String s) throws IOException {

		for (Message m : messageTypes) {
			if (m.isMessage(s)) {
				m.handle(s);
			}
		}

	}

	public void sendTheChat() throws IOException {
		String s = compose.getText();
		readerThread.send("SAY " + myName + " " + s);
		compose.setText("");

	}

	public static void main(String[] args) {
		try {
			new ChatGUI();
		} catch (IOException e) {
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
				e.printStackTrace();
			}

		}

	}

	private class EnterListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					sendTheChat();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

	}

	private class ChatGUIWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				readerThread.send("LEAVE " + myName);
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowOpened(WindowEvent e) {

		}

	}

	public JTextField getCompose() {
		return compose;
	}

	public JTextArea getChat() {
		return chat;
	}

	public WritingThread getReaderThread() {
		return readerThread;
	}

	public JTextArea getCurrentlyChatting() {
		return currentlyChatting;
	}

	public ArrayList<String> getCurrentChatters() {
		return currentChatters;
	}

	public String getMyName() {
		return myName;
	}

}
