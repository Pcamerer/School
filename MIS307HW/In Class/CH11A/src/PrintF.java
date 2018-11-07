import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * CH11 Input/Output and Exceptions
 * @author YWP
 */
public class PrintF {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		String inputFileName = "grocery.txt";
		 File inputFile = new File(inputFileName);
	     Scanner in = new Scanner(inputFile);
	     
	     ArrayList<String> items = new ArrayList<String>();
	     ArrayList<Integer> quantity = new ArrayList<Integer>();
	     ArrayList<Double> UnitPrice = new ArrayList<Double>();
	     ArrayList<Double> ItemPrice = new ArrayList<Double>();
	     
	     while (in.hasNextLine())
	     {
	    	 String line = in.nextLine();
	    	 Scanner lineScanner = new Scanner(line);
	    	 
	    	 items.add(lineScanner.next());
	    	 quantity.add(lineScanner.nextInt());
	    	 UnitPrice.add(lineScanner.nextDouble());
	    	 ItemPrice.add(lineScanner.nextDouble());
	     }
	     
	     System.out.println(items);
	     System.out.println(quantity);
	     System.out.println(UnitPrice);
	     System.out.println(ItemPrice);
	     
	     // from here, use printf to print the table in the matrix format
	     // All values have length = 8
	     // Items must be left-aligned
	     // quantities must be left-aligned
	     // UnitPrice must be right-aligned and has one digit after the decimal point
	     // ItemPrice must be right-aligned and has one digit after the decimal point
	     
	     for(int i=0; i<items.size(); i++){
	    	 System.out.printf("%-8s%-8d%8.1f%8.1f\n", items.get(i), quantity.get(i), UnitPrice.get(i), ItemPrice.get(i));
	     }
	     
	     in.close();
	}

}
