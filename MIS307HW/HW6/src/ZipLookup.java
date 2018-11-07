import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

/* The input file has the format

50001
ACKWORTH
50002
ADAIR
50003
ADEL
50005
ALBION
50006
ALDEN
50007
ALLEMAN
50008
. . .

*/

public class ZipLookup
{
   public static void main(String[] args) throws IOException
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of the zipcode file: ");
      String fileName = in.nextLine();
 
      LookupTable table = new LookupTable();
      FileReader reader = new FileReader(fileName);
      table.read(new Scanner(reader));
      
      boolean more = true;
      while (more)
      {  
         System.out.println("Lookup Z)ip, C)ity name, Q)uit?");
         String cmd = in.nextLine();
           
         if (cmd.equalsIgnoreCase("Q")) 
            more = false;
         else if (cmd.equalsIgnoreCase("Z"))
         { 
            System.out.println("Enter Zipcode:");
            String n = in.nextLine();
            System.out.println("City name: " + table.lookup(n) + "\n");
         }
         else if (cmd.equalsIgnoreCase("C"))
         { 
            System.out.println("Enter city name:");
            String n = in.nextLine();
            System.out.println("Zip code: " + table.reverseLookup(n)+ "\n");
         }
      }
   }
}
