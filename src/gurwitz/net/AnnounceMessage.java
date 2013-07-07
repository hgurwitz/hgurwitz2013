package gurwitz.net;

import java.io.IOException;
import java.util.ArrayList;

public class AnnounceMessage extends Message {

	public AnnounceMessage(ChatGUI gui) {
		super(gui, MessageType.ANNOUNCE);
	}

	@Override
	public void handle(String s) throws IOException {
		String name = getName(s);
		String message = name + " is chatting";
		ArrayList<String> currentChatters = gui.getCurrentChatters();
		if (!currentChatters.contains(name)) {
			currentChatters.add(name);
			gui.setCurrentChatterText();
			gui.addMessageToChatWindow(message);
		}

	}

}
