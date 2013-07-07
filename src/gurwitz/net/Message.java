package gurwitz.net;

import java.io.IOException;
import java.util.StringTokenizer;

public abstract class Message {

	protected MessageType type;
	protected ChatGUI gui;
	protected StringTokenizer tokenizer;

	public abstract void handle(String s) throws IOException;

	public MessageType getType() {
		return type;
	}

	public Message(ChatGUI gui, MessageType type) {
		this.gui = gui;
		this.type = type;
	}

	public String getName(String s) {
		tokenizer = new StringTokenizer(s, " ");
		tokenizer.nextToken(); // returns type name
		return tokenizer.nextToken(); // returns name

	}

	public boolean isMessage(String s) {
		int length = type.toString().length();
		return (s.substring(0, length).equals(type.toString()));
	}

}
