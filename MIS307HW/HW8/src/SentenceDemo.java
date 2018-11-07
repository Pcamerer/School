/**
   A tester class for Sentence.
*/
public class SentenceDemo
{
   public static void main(String[] args)
   {
      Sentence s = new Sentence("Mississippi!");
      boolean b = s.find("sip");
      System.out.println(b);
      System.out.println("Expected: true");
      b = s.find("tip");
      System.out.println(b);
      System.out.println("Expected: false");
   }
}
