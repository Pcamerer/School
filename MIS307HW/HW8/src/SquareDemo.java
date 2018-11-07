public class SquareDemo
{
   public static void main(String[] args)
   {
      Square s1 = new Square(8);
      Square s2 = new Square(14);

      System.out.println(s1.getArea());
      System.out.println("Expected: 64");
      System.out.println(s2.getArea());
      System.out.println("Expected: 196");
   }
}
