/**
   Run some tests for the DataSet class
*/
public class DataSetTester
{
   public static void main(String[] args)
   {
      DataSet data = new DataSet(5);
      data.add(3.5);
      data.add(7.9);
      data.add(15.2);
      data.add(-7.3);
      data.add(1.4);
      data.add(-2.7);

      System.out.println("Sum: " + data.getSum());
      System.out.println("Expected: 20.7");
      System.out.println("Average: " + data.getAverage());
      System.out.println("Expected: 4.14");
      System.out.println("Maximum: " + data.getMaximum());
      System.out.println("Expected: 15.2");
      System.out.println("Minimum: " + data.getMinimum());
      System.out.println("Expected: -7.3");
   }
}
