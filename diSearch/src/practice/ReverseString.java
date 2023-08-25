package practice;

public class ReverseString {

	public static void main(String[] args) {
		System.out.println("Reversed String: " + reverseString("DEMO"));

	}

	private static String reverseString(String stringForReversal) {
		// Create a StringBuilder to build the reversed string
		StringBuilder reversedStringBuilder = new StringBuilder();

		// Iterate through the input string in reverse order
		for (int i = stringForReversal.length() - 1; i >= 0; i--) {
			char c = stringForReversal.charAt(i);
			reversedStringBuilder.append(c);
		}

		// Convert the StringBuilder to a string
		String reversedString = reversedStringBuilder.toString();

		return reversedString;
	}
}
