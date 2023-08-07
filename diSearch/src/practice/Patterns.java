package practice;

import java.util.regex.Pattern;

public class Patterns {

	public static void main(String[] args) {
		
		
		String date = "12/05/2023";
		
		Pattern p = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
		
		System.out.println(p.matcher(date).matches());

	}

}
