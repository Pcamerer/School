import java.util.Scanner;

/**
 * Code for P6.12
 * @author Prseton Camerer
 */
public class TicketSeller
{
   public static void main(String[] args)
   {
      final int MAX_TICKETS = 100;
      Scanner input = new Scanner(System.in);
      int availableTickets = 100;
      int numberBuyers = 0;
      
      do {
    	  System.out.print("Number of tickets to buy: ");
    	  int ticketsToBuy = input.nextInt();
    	  if (ticketsToBuy < 5 && ticketsToBuy > 0) {
    		  System.out.println("You bought " + ticketsToBuy + " tickets");
    		  availableTickets -= ticketsToBuy;
    		  numberBuyers += 1;
    	  }
    	  else {
    		  System.out.println("You cannot buy more than 4 tickets.");
    		  
    	  }
      } while (availableTickets > 0);
      input.close();
      System.out.println("We are sold out!");
      System.out.println("Total buyers: " + numberBuyers);
	  
	  
	  
	  
	  
   }
}