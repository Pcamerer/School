import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
   Tests a database installation by creating and querying
   a sample table. Call this program as
   java -classpath driver_class_path;. TestDB propertiesFile
*/
public class TestDB2
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
         

         // PART0: Creating the table
         stat.execute("CREATE TABLE Product (Product_Code CHAR(7), Description VARCHAR(40), Price DECIMAL(10, 2))");
         stat.execute("INSERT INTO Product VALUES ('116-064', 'Toaster', 24.95)");
         stat.execute("INSERT INTO Product VALUES ('257-535', 'Hair dryer', 29.95)");
         stat.execute("INSERT INTO Product VALUES ('643-119', 'Car vacuum', 19.95)");

         
         // PART1: Query 1
         ResultSet result = stat.executeQuery("SELECT * FROM Product");
         System.out.println("[PART1: Print by column name]");
         while(result.next()){ //Use the next method to move to the next row. If there is a new row to read, true.
        	 System.out.print(result.getString("Product_Code")+ "\t");
        	 System.out.print(result.getString("Description")+ "\t");
        	 System.out.print(result.getDouble("Price"));
        	 System.out.println();
         }
         result.close(); // Close the current ResultSet before issuing a new query on the same  statement
         
         
         
      // PART2: Query 2
         result = stat.executeQuery("SELECT * FROM Product");
         System.out.println("\n\n[PART2: Print by column index]");
         while(result.next()){ //Use the next method to move to the next row. If there is a new row to read, true.
        	 System.out.println(result.getString(2));
         }
         result.close(); // Close the current ResultSet before issuing a new query on the same  statement
         
         
         
      // PART3: Query 3
         result = stat.executeQuery("SELECT * FROM Product WHERE Price < 25");
         System.out.println("\n\n[PART3: Print all columns by index]");
         while(result.next()){ //Use the next method to move to the next row. If there is a new row to read, true.
        	 for(int i=1; i<=3; i++){
        		 System.out.print(result.getString(i) + "\t");
        	 }
        	 System.out.println();
         }
         result.close(); // Close the current ResultSet before issuing a new query on the same  statement
         
         
         
         // PART4: Query 4
         findProduct_by_Price_Description(conn, 25.5, "C%");
                  
         
         // PART5: MetaData
         result = stat.executeQuery("SELECT Product_code, Price FROM Product");
         ResultSetMetaData metaData = result.getMetaData(); // get the number of columns
         int columnCount = metaData.getColumnCount(); // get the name of each column
         System.out.println("\n\n[PART5: MetaData - Print all selected column names]"); 
         
         System.out.println("Name \t Width");
         for (int i = 1; i <= columnCount; i++)
         {  
            System.out.println(metaData.getColumnLabel(i) + "\t" + metaData.getColumnDisplaySize(i) );
         }
         
         System.out.println();
         
         stat.execute("DROP TABLE Product"); 
         // Need to drop Product table, otherwise, you will get an error message 
         // saying "Product table already exists" 
         // when you rerun the program and try to create Product table
      }
      
     
   }
   
   private static boolean findProduct_by_Price_Description(Connection conn, double price, String description) throws SQLException{
	      boolean found = false;
	      try (PreparedStatement stat = conn.prepareStatement(
	         "SELECT * FROM Product WHERE Price < ? AND Description LIKE ?"))
	      {
	         stat.setDouble(1, price);
	         stat.setString(2, description);
	         ResultSet result = stat.executeQuery();
	         
	         System.out.println("\n\n[PART4: Prepared Statement]");
	         while(result.next()){ //Use the next method to move to the next row. If there is a new row to read, true.
	        	 for(int i=1; i<=3; i++){
	        		 System.out.print(result.getString(i) + "\t");
	        	 }
	        	 System.out.println();
	         }
	         
	         result.close(); // Close the current ResultSet before issuing a new query on the same  statement
	      }
	      return found;
	   }
}
