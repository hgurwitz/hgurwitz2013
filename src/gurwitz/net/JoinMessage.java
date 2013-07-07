package gurwitz.net;

import java.io.IOException;
import java.util.ArrayList;

public class JoinMessage extends Message {

	public JoinMessage(ChatGUI gui) {
		super(gui, MessageType.JOIN);
	}

	@Override
	public void handle(String s) throws IOException {
		String name = getName(s);
		String message = name + " has joined";
		gui.getCurrentChatters().add(name);
		gui.setCurrentChatterText();
		String send = "ANNOUNCE " + gui.getMyName();
		gui.getReaderThread().send(send);
		gui.addMessageToChatWindow(message);
	}

}
