import java.util.Scanner;

/**
 * Code for P5.13
 * @author Preston Camerer
 */
public class TipCalculator
{
   private static Scanner in;

public static void main(String[] args)
   {
      in = new Scanner(System.in);

      System.out.print("Enter bill amount: ");
      double bill = in.nextDouble();
      double tip = 0;
      System.out.println("Enter your satisfaction level using these ratings: "
            + "1 = Totally satisfied, " + "2 = Satisfied, "
            + "3 = Dissatisfied. ");

      int level = in.nextInt();

	  if (level == 1) {
		  tip = bill * .20;
		  System.out.print("You are totally satisfied! You're leaving a 20% tip. Your total is $" + (tip + bill));
	  }
	  
	  else if (level == 2) {
		  tip = bill * .15;
		  System.out.print("You are satisfied! You're leaving a 15% tip. Your total is $" + (tip + bill));
	  }
	  
	  else if (level == 3) {
		  tip = bill * .10;
		  System.out.print("You are dissatisfied You're leaving a 10% tip. Your total is $" + (tip + bill));
	  }
	  
	  
	  
   }
}
