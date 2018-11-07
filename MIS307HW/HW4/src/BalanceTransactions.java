import java.io.*;
import java.util.*;

/**
 * Code for P11.11.  This program checks the amounts of cash a store owner 
 * has VS the expected amount
 * @author Preston Camerer
 */
public class BalanceTransactions
{
   public static void main(String[] args) throws NumberFormatException, IOException
   {
	   // The following lines ask the user to enter the file name and the balances at the beginning and the end
      Scanner in = new Scanner(System.in);
   
      System.out.print("Please enter the file name for input: ");
      String filename = in.next();
   
      System.out.print("Please enter the cash amount at the start: ");
      double start = in.nextDouble();
   
      System.out.print("Please enter the cash amount at the end: ");
      double end = in.nextDouble();
   
   // Your work starts here
      File file = new File(filename);
      Scanner scan = new Scanner(file);
      while(scan.hasNextLine()){
    	  String line = scan.nextLine();
    	  String tokens[] = line.split("\\s+");
    	  double amount = Double.parseDouble(tokens[1]);
    	  if(tokens[2].equals("P")){
    		  start -= amount;
    	  } else if(tokens[2].equals("R")){
    		  start+= amount;
    	  }
      }
      if(end == start){
    	  System.out.println("Actual amount matches expected amount.");
      } else {
    	  System.out.println("Actual amount doesn't match the expected amount");
    	  System.out.println("Actual: " + start);
    	  System.out.println("Expected: " + end);
      }
      scan.close();
      in.close();
   
   }
}
