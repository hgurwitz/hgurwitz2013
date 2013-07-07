package gurwitz.net;

import java.io.IOException;

public class SayMessage extends Message {

	public SayMessage(ChatGUI gui) {
		super(gui, MessageType.SAY);
	}

	@Override
	public void handle(String s) throws IOException {
		String name = getName(s);
		int beginIndex = type.toString().length() + 1 + name.length();
		String theChat = s.substring(beginIndex);
		String message = name + ": " + theChat;
		gui.addMessageToChatWindow(message);

	}

}
