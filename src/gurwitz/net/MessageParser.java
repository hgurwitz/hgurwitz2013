package gurwitz.net;

import java.util.StringTokenizer;

public class MessageParser {

	private static StringTokenizer tokenizer;

	public static MessageType getMessageType(String message) {

		MessageType[] messageTypes = MessageType.values();
		for (MessageType m : messageTypes) {
			String substring = message.substring(0, m.toString().length());
			if (substring.equals(m.toString())) {

				return m;
			}
		}
		return MessageType.DEFAULT;
	}

	public static String getName(String message) {
		tokenizer = new StringTokenizer(message, " ");
		MessageType type = getMessageType(message);
		String token = tokenizer.nextToken(); // returns type name
		token = tokenizer.nextToken(); // returns name
		return token;
		// +1 for the space
	}

	public static String getMessage(String message) {
		tokenizer = new StringTokenizer(message, " ");
		MessageType type = getMessageType(message);
		switch (type) {
		case JOIN:
			return " has joined";
		case LEAVE:
			return " has left";
		case SAY:
			String name = getName(message);
			int beginIndex = MessageType.SAY.toString().length() + 1
					+ name.length();
			return message.substring(beginIndex);
		case ANNOUNCE:
			return " is chatting";
		}
		return "";
	}

}
