import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.derby.iapi.sql.PreparedStatement;


/**
 * Code for HW5
 * @author
 */
/**
   Creates the Car table, insert some data into the table,
   and drop the table from a database.
*/
//ARGS database.properties
public class CarDB
{
   public static void main(String[] args) throws IOException, SQLException,
         ClassNotFoundException
   {
	// Construct the Scanner and PrintWriter objects for reading and writing
       
   
       if (args.length == 0)
       {
          System.out.println("Usage: java CarDB propertiesFile");
          System.exit(0);
       }

      SimpleDataSource.init(args[0]);
  
      try (Connection conn = SimpleDataSource.getConnection())
      {
         Statement stat = conn.createStatement();
         
         String inputFileName = "src\\carmpg.txt"; 
         File inputFile = new File(inputFileName);
         Scanner in = new Scanner(inputFile);
  	   
  	   
  	   	 ArrayList<String> Manufacturer = new ArrayList<String>(); 
         ArrayList<String> Model = new ArrayList<String>(); 
         ArrayList<Double> Mpg = new ArrayList<Double>(); 
         ArrayList<Double> Price = new ArrayList<Double>(); 
         
         while(in.hasNextLine()){
      	   String manufacturer = in.next();
      	   String model = in.next();
      	   double mpg = Double.parseDouble(in.next());
      	   double price = Double.parseDouble(in.next());
      	   Manufacturer.add(manufacturer);
      	   Model.add(model);
      	   Mpg.add(mpg);
      	   Price.add(price);
         
        stat.execute("CREATE TABLE Car (manufacturer VARCHAR(20), Model VARCHAR(20), MPG int, Price int)");
        for(int i=0; i<Model.size(); i++){
        	stat.execute("INSERT INTO Car VALUES ('" + Manufacturer.get(i) + "','" + Model.get(i) + "'," + Mpg.get(i) + ","+ Price.get(i) +")");        }
        	
        
        Scanner input = new Scanner(System.in);
        boolean ask = true;
        while(ask){
        	System.out.println("Welcome! Please select from the following options");
        	System.out.println("(Q) Quit: quit the program");
        	System.out.println("(A) Add a car: insert a car to the table");
        	System.out.println("(C) Calculate avg: calculate the average MPG");
        	System.out.println("(W) Write the entire table to a text file");
        	System.out.println("(P) Print the entire table");
        	System.out.println("(M) Print a subset of the cars based on the price");
        	String choice = input.next().toUpperCase();
        	
        	switch(choice){
        	case "A":
        		if(choice.equals("A")){
        			System.out.println("Entering a car into the table!");
        			System.out.print("Enter Manufacturer: ");
        			String ManufacturerA = input.next();
        			System.out.print("Enter Model: ");
        			String ModelA = input.next();
        			System.out.print("Enter MPG: ");
        			int MpgA = input.nextInt();
        			System.out.print("Enter price: ");
        			String PriceA = input.next().replaceAll(",", "");
        			stat.executeQuery("INSERT INTO Car VALUES ('" + ManufacturerA + "','" + ModelA +"'," + MpgA +"," + PriceA +")");
        		}break;
        	case "C":
        		if(choice.equals("C")){
        			ResultSet result = stat.executeQuery("SELECT avg(mpg) FROM Car");
        			while(result.next()){
        				String token = result.getString(1);
        				System.out.println("Average miles per gallon: " + token);
        			}result.close();
        		}break;
        	case "W":
        		if(choice.equals("W")){
        			System.out.println("Writing table to a text file!");
        			System.out.println("Enter a name for text file: ");
        			String outputFile = input.next();
        			PrintWriter output = new PrintWriter(outputFile);
        			output.close();
        		}break;
        	case "P":
        		if(choice.equals("P")){
        			System.out.println("Printing the entire table: ");
        			ResultSet results = stat.executeQuery("SELECT * FROM Car");
        			System.out.printf("%18.12s %11.10s %14.10s %14.10s \n" , "Manufacturer","Model","MPG","Price");
        			for(int i=1; i<=4; i++){
        				System.out.printf("%15.10s", results.getString(i));
        			}System.out.println();
        			results.close();
        		}break;
        	case "M":
        		if(choice.equals("M")){
        			System.out.println("Printing cars based on a price: ");
        			System.out.println("Maximum amount: ");
        			int amounts = Integer.parseInt(input.next());
        			ResultSet result = stat.executeQuery("SELECT * FROM Car WHERE Price < " + amounts + "ORDER BY Price DESC");
        			System.out.printf("18.12s %11.10s %14.10s %14.10s \n", "Manufacturer","Model","MPG","Price");
        			while(result.next()){
        				for(int i=1; i<4; i++){
        					System.out.printf("15.10s", result.getString(i));
        				}System.out.println();
        			}result.close();
        		}break;
        	case "Q":
        		stat.execute("DROP TABLE Car");
        		System.out.println();
        		System.out.println("Exiting the program!");
        		return;
        		default:
        		System.out.println();
        		System.out.println("Select a different option");
        	}
        	}System.out.println();
        
        }
     }
   }
}
