
/**
 * CH13: This class implements recursive function for checking palindromes
 * @author YWP
 */
public class RecursivePalindromes {

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
	
	public RecursivePalindromes(String text) {
		sentence = text;
	}
	
	public boolean isPalindrome()
	{
		int length = sentence.length();
		
		// Separate case for shortest strings.  
		if (length <= 1) { return true; }
	
		// Get first and last characters, converted to lowercase.  
		char first = Character.toLowerCase(sentence.charAt(0));
		char last = Character.toLowerCase(sentence.charAt(length - 1));  
		
		if (Character.isLetter(first) && Character.isLetter(last)){ // Both are letters.
			if (first == last){
				
				// Remove both first and last character.
				String shorter = sentence.substring(1, length - 1);
				RecursivePalindromes rp = new RecursivePalindromes(shorter);
				return rp.isPalindrome();
			}
			else {
				return false;
			}
		}
		else if (!Character.isLetter(last))	{
			// Remove last character.
			String shorter = sentence.substring(0, length - 1);  
			RecursivePalindromes rp = new RecursivePalindromes(shorter);
			return rp.isPalindrome();
		}
		else {
			// Remove first character.
			String shorter = sentence.substring(1);  // equivalent to text.substring(1, length);  
			RecursivePalindromes rp = new RecursivePalindromes(shorter);
			return rp.isPalindrome();
		}
	}

}
