package practice;

import java.util.regex.Pattern;

public class Patterns {

	public static void main(String[] args) {
		
		
		String date = "12/05/2023";
		
		Pattern p = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
		
		System.out.println(p.matcher(date).matches());
		
		
		String date2 = "Upload Date: 2023-06-26 12:00 GMT ";
		
		Pattern x = Pattern.compile(".*([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2}) GMT.*");
		System.out.println(x.matcher(date2).matches());

	}

}
