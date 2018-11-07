import java.util.Scanner;

/**
 * Code for E5.25
 * @author Preston Camerer
 */
public class GroceryDiscount
{
   private static Scanner in;

public static void main(String[] args)
   {
      in = new Scanner(System.in);

      System.out.print("Please enter the cost of your groceries: ");
      double bill = in.nextDouble();
      double coupon = 0;
      
      if (bill > 210) {
    	  coupon = bill * .14;
    	  System.out.print("You win a discount of $" + coupon);
    	  System.out.print(" (14% of your total purchase)");
      }
      
      else if (bill > 150) {
    	  coupon = bill * .12;
    	  System.out.print("You win a discount of $" + coupon);
    	  System.out.print(" (12% of your total purchase)");
      }
      
      else if (bill > 60) {
    	  coupon = bill * .10;
    	  System.out.print("You win a discount of $" + coupon);
    	  System.out.print(" (10% of your total purchase)");
      }
      
      else if (bill >= 10) {
    	  coupon = bill * .08;
    	  System.out.print("You win a discount of $" + coupon);
    	  System.out.print(" (8% of your total purchase)");
      }

      else if (bill < 10) {
    	  System.out.print("You win no discount coupon");
      }
      
      
    	  
      
      
      
      
      
      
      
      
      
      
      
      
   }
}
