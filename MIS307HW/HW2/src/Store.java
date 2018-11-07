import java.util.*;


/**
 * Code for P7.13
 * @author Preston Camerer
 */
public class Store
{
   public String nameOfBestCustomer(ArrayList<Double> sales,
         ArrayList<String> customers)
   {
      
	  // task: store the name of the best customer into String variable "top"
	  // Your work starts here
	  double max = 0;
	  int maxIndex = 0;
	  for(int i=0; i<sales.size(); i++) {
		  if(max<sales.get(i)) {
			  max = sales.get(i);
			  maxIndex = i;
		  }
	  }
	  return customers.get(maxIndex);
	  
	  
	  
	  // Your work ends here
	  
      
   } 

   public static void main(String[] args)
   {
      ArrayList<Double> sales = new ArrayList<Double>();
      ArrayList<String> customers = new ArrayList<String>();

      Scanner in = new Scanner(System.in);
	  
	  // task: use while loop to receive and store prices and last names from the console until the sentinel price 0 is recieved
	  // Your work starts here
      	  while(true){
      		  System.out.println("Enter customer name: ");
      		  String name = in.nextLine();
      		  System.out.println("Enter customer sales: ");
      		  double price = in.nextDouble();
      		  in.nextLine();
      		  if(price == 0){
      			  break;
      		  }
      		  sales.add(price);
      		  customers.add(name);
      	  }
	  // Your work ends here

      Store top = new Store();
      System.out.println("Best customer's name "
            + top.nameOfBestCustomer(sales, customers));
   } 
}
