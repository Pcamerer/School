
/**
 * CH13: This class implements recursive function for checking palindromes
 * This class uses a helper method to improve efficiency
 * @author YWP
 */
public class RecursivePalindromes2 {

	String sentence;
	
	
	public static void main(String[] args) {

		String sentence1 = "Madam, I'm Adam!";
		System.out.println(sentence1);
		RecursivePalindromes rp1 = new RecursivePalindromes(sentence1);
	    System.out.println("Palindrome: " + rp1.isPalindrome());
	    String sentence2 = "Sir, I'm Eve!";    
	    RecursivePalindromes rp2 = new RecursivePalindromes(sentence2);
	    System.out.println(sentence2);
	    System.out.println("Palindrome: " + rp2.isPalindrome());
	}
	
	public RecursivePalindromes2(String text) {
		sentence = text;
	}
	
	public boolean isPalindrome(int start, int end) {
		
		// Separate case for substrings of length 0 and 1.  
		if (start >= end) { return true; }
		
		// Get first and last characters, converted to lowercase.  
		char first = Character.toLowerCase(sentence.charAt(start));  
		char last = Character.toLowerCase(sentence.charAt(end));
		if (Character.isLetter(first) && Character.isLetter(last)){
			if (first == last){
				// Test substring that doesn’t contain the matching letters.  
				return isPalindrome(start + 1, end - 1);
			}
			else{
				return false;
			}
		}
		else if (!Character.isLetter(last)){
		// Test substring that doesn’t contain the last character.  
			return isPalindrome(start, end - 1);
		}
		else {
			// Test substring that doesn’t contain the first character.  
			return isPalindrome(start + 1, end);
		}
	}
	
	public boolean isPalindrome()
	{
		return isPalindrome(0,sentence.length()-1);
	}

}
