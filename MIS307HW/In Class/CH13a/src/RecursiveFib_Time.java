import java.util.Scanner;

/**
   This program computes Fibonacci numbers using a recursive method.
*/ 
public class RecursiveFib_Time
{  
   public static void main(String[] args)
   {  
	  StopWatch sw = new StopWatch();
      Scanner in = new Scanner(System.in);
      System.out.print("Enter n: ");
      int n = in.nextInt();
      sw.start();
      for (int i = 1; i <= n; i++)
      {
         long f = fib(i);
         System.out.println("fib(" + i + ") = " + f);
      }
      sw.stop();
      System.out.println(sw.getElapsedTime());
      in.close();
   }

   /**
      Computes a Fibonacci number.
      @param n an integer
      @return the nth Fibonacci number
   */
   public static long fib(int n)
   {  
      if (n <= 2) { return 1; }
      else { return fib(n - 1) + fib(n - 2); }
   }
}
