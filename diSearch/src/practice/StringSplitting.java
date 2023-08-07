package practice;

public class StringSplitting {

	public static void main(String[] args) {


		String completeString = "Upload Date: 2023-07-03";
		
		String myString = completeString.substring(completeString.lastIndexOf(" "), completeString.length()).trim();
		
		System.out.println(myString);

	}

}
