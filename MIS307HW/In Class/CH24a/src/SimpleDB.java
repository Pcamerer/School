import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class SimpleDB 
{
   public static void main(String[] args) throws Exception
   {   
	   if (args.length == 0)
	      {   
	         System.out.println(
	               "Usage: java -classpath driver_class_path" 
	               + File.pathSeparator 
	               + ". TestDB propertiesFile");
	         return;
	      }

	      SimpleDataSource.init(args[0]);
	      
	      try (Connection conn = SimpleDataSource.getConnection())
	      {
	         Statement stat = conn.createStatement();
	         
	         // Construct the Scanner and PrintWriter objects for reading and writing
	         String inputFileName = "products_data.txt"; 
	         File inputFile = new File(inputFileName);
	         Scanner in = new Scanner(inputFile);

	         
	         
	         // PART0: Creating the table given Arraylists
	         
	         ArrayList<String> productCodes = new ArrayList<String>(); 
	         ArrayList<String> descriptions = new ArrayList<String>(); 
	         ArrayList<Double> prices = new ArrayList<Double>(); 
	         
	         while (in.hasNextLine())
	         {
	        	String code = in.nextLine(); 
	        	String name = in.nextLine();
	        	double price = Double.parseDouble(in.nextLine());
	        	
	        	productCodes.add(code);
	        	descriptions.add(name);
	        	prices.add(price);
	         }
	         
	         in.close();
	         
	         stat.execute("CREATE TABLE Product (Product_Code CHAR(7), Description VARCHAR(40), Price DECIMAL(10, 2))");
	         for(int i=0; i<descriptions.size(); i++){
	        	 // INSERT INTO Product VALUES ('116-064','Toaster',24.95)
	        	 stat.execute("INSERT INTO Product VALUES ('" + productCodes.get(i) +"', '" + descriptions.get(i) + "'," + prices.get(i).toString() +")");
	         }

	         double price = 25;
	         String query = "SELECT * FROM Product WHERE Price <= " + Double.toString(price);
	    	  ResultSet result = stat.executeQuery(query);

	          while(result.next()){ //Use the next method to move to the next row. If there is a new row to read, true.
	         	 for(int i=1; i<=3; i++){
	         		 System.out.print(result.getString(i) + "\t");
	         	 }
	         	 System.out.println();
	          }
	          result.close(); // Close the current ResultSet before issuing a new query on the same  statement
	         
	         stat.execute("DROP TABLE Product"); 
	      }
   }
}
