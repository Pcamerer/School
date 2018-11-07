import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * CH11 Input/Output and Exceptions
 * @author YWP
 */
public class Total_CommandLine
{
   public static void main(String[] args) throws FileNotFoundException
   {
      // Modify the following five lines to receive command line arguments
	   
	      
	      String inputFileName = args[0];
	      String outputFileName = args[1];
	      
	      
      // Construct the Scanner and PrintWriter objects for reading and writing

      File inputFile = new File(inputFileName);
      Scanner in = new Scanner(inputFile);
      PrintWriter out = new PrintWriter(outputFileName);

      // Read the input and write the output

      double total = 0;
      
      while (in.hasNextDouble())
      {
         double value = in.nextDouble();
         out.printf("%15.2f\n", value);
         total = total + value;
      }

      out.printf("Total: %8.2f\n", total);

      in.close();
      out.close();
   }
}
