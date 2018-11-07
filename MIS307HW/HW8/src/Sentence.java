/**
 * Code for E13.8(HW8)
 * @author Preston Camerer
 */
public class Sentence
{
   private String phrase;

   /**
      Constructs a Sentence object.
      @param aPhrase a phrase entered by user
   */
   public Sentence(String aPhrase)
   {
      phrase = aPhrase;
   }
   
   /**
      Determines if a string exists in the phrase.
      @param text the string to find
      @return true if the string is found, false otherwise
   */
   public boolean find(String text)
   {
      if (text == null || phrase == null){
    	  return false;
      }
      if(text.length() > phrase.length()){
    	  return false;
      }
      if(phrase.length() == text.length()){
    	  return phrase.equals(text);
      }
      return (phrase.startsWith(text) || new Sentence(phrase.substring(1)).find(text));
  }
}
