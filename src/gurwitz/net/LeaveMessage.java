package gurwitz.net;

import java.io.IOException;

public class LeaveMessage extends Message {

	public LeaveMessage(ChatGUI gui) {
		super(gui, MessageType.LEAVE);

	}

	@Override
	public void handle(String s) throws IOException {
		String name = getName(s);
		String message = name + " has left";
		gui.getCurrentChatters().remove(name);
		gui.setCurrentChatterText();
		gui.addMessageToChatWindow(message);

	}

}
