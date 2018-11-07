import java.io.*;
import java.util.*;


/**
 * Code for P11.1.  This program takes in a list of baby names and outputs a list of boys and   
   girls names.
 * @author Preston Camerer
 */
public class BabyNames
{

   // In this method, you do not need to ask user the filename through arguments of the main method or console. 
   public static void main(String[] args) throws FileNotFoundException {
   {
	   try (Scanner in = new Scanner(new File("babynames.txt"));
           PrintWriter boyOut = new PrintWriter("boynames.txt");
           PrintWriter girlOut = new PrintWriter("girlnames.txt"))
      {
         // Process the data
		 while(in.hasNextLine()){
			 String line = in.nextLine();
			 String tokens[] = line.split("\\s+");
			 String boyName = tokens[1];
			 String girlName = tokens[4];
			 boyOut.write(boyName + '\n');
			 girlOut.write(girlName + '\n');
		 }
		 

      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
}
