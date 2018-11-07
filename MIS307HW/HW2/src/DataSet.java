import java.lang.reflect.Array;

/**
 * Code for E7.9
 * This class computes various statistics from a set of data values in an array.
 * @author Preston Camerer
 */
public class DataSet
{
   private double[] data;
   private int dataSize;
   private int maximumNumberOfValues;
   private int size;

   /**
      Constructs an empty data set.
      @param maximumNumberOfValues the maximum this data set can hold
   */
   public DataSet(int maximumNumberOfValues)
   {
	   // Complete this constructor
	   size = 0;
	   data = new double[maximumNumberOfValues];
    }

   /**
      Adds a data value to the data set if there is a room in the array.
      @param value a data value
   */
   public void add(double value)
   {
	   if (size<data.length)
		   data[size++] = value;
   }

   /**
      Gets the sum of the added data.
      @return sum of the data or 0 if no data has been added
   */
   public double getSum()
   {
	   // Complete this method
      double sum = 0;
      for (int i=0; i<size; i++) {
    	  sum += data[i];
      }
      return sum;
   }

   /**
      Gets the average of the added data.
      @return average of the data or 0 if no data has been added
   */
   public double getAverage()
   {
	   // Complete this method
      double total = 0;
      for(double element : data) {
    	  total = total + element;
      }
      return total/size;
   }

   /**
      Gets the maximum value entered.
      @return maximum value of the data
      NOTE: returns -Double.MAX_VALUE if no values are entered.
   */
   public double getMaximum()
   {
	   // Complete this method
      double largest = Double.MIN_VALUE;
      for(int i=0; i<size; i++) {
    	  largest = Math.max(largest, data[i]);
      }
      return largest;
   }

   /**
      Gets the minimum value entered.
      @return minimum value of the data
      NOTE: returns Double.MAX_VALUE if no values are entered.
   */
   public double getMinimum()
   {
	   // Complete this method
      double smallest = Double.MAX_VALUE;
      for(int i=0; i<size; i++) {
    	  smallest = Math.min(smallest, data[i]);
      }
      return smallest;
   }
}
