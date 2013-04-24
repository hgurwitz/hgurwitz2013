package gurwitz.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberVerifier {
	private static final String regex = "(" + "(1[- ]?)?"
			+ "([2-9][0-9]{2}[- ]?)" + ")?" + "[2-9][0-9]{2}[- ]?" + "[0-9]{4}";

	private static final Pattern pattern = Pattern.compile(regex);

	public boolean verify(String phoneNumber) {

		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

}
