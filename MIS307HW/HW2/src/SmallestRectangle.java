/**
 * Code for R7.25
 * This class calculates the x and y coordinates of the smallest rectangle covering points.
 * @author Preston Camerer
 */
public class SmallestRectangle {
	
	/**
    Calculates and prints the x and y coordinates of the smallest rectangle covering points in datapoints
    */
	public void coordinates_of_rectangle(double[][] datapoints) {
		
		double[] UpperRight = new double[2]; // UpperRight[0] = x coordinate, UpperRight[1] = y coordinate
		double[] UpperLeft = new double[2];  // UpperLeft[0] = x coordinate, UpperLeft[1] = y coordinate
		double[] LowerRight = new double[2]; // LowerRight[0] = x coordinate, LowerRight[1] = y coordinate
		double[] LowerLeft = new double[2];  // LowerLeft[0] = x coordinate, LowerLeft[1] = y coordinate
		
		// Complete this method
		// task: fill out the four arrays UpperRight, UpperLeft, LowerRight, LowerLeft
		// your work starts here
		
		UpperRight[0] = 3;
		UpperRight[1] = 7;
		UpperLeft[0] = -5;
		UpperLeft[1] = 7;
		LowerRight[0] = 3;
		LowerRight[1] = 1;
		LowerLeft[0] = -5;
		LowerLeft[1] = 1;
		
		// your work ends here
		
		System.out.println("UpperRight: (" + UpperRight[0] + "," + UpperRight[1]+")");
		System.out.println("UpperLeft: (" + UpperLeft[0] + "," + UpperLeft[1]+")");
		System.out.println("LowerRight: (" + LowerRight[0] + "," + LowerRight[1]+")");
		System.out.println("LowerLeft: (" + LowerLeft[0] + "," + LowerLeft[1]+")");
		
		
		System.out.println("\nExpeted Output");
		System.out.println("UpperRight: (3,7)");
		System.out.println("UpperLeft: (-5,7)");
		System.out.println("LowerRight: (3,1)");
		System.out.println("LowerLeft: (-5,1)");
	}
	
	public static void main(String[] args) {
		// you do not need to alter this method
		
		// Points[i] = i-th point
		// Points[i][0] = x coordinate of i-th point
		// Points[i][1] = y coordinate of i-th point
		double[][] Points = {{0,1},{-1,3},{3,5},{2,7},{-4,1},{-5,6}};
		SmallestRectangle smallrec = new SmallestRectangle();
		smallrec.coordinates_of_rectangle(Points);
	}

}
