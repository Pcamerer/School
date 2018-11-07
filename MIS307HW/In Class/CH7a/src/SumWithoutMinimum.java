// MIS 307
// CH7 in-class task

public class SumWithoutMinimum {
	
	public static void main(String[] args) {
		
		// In this main method, four fundamental algorithms are given.
		// By combining the fundamental algorithms,
		// write an algorithm to calculate the sum without the minimum
		
		double[] numbers = {-30,95,96,20,92,37,-27,-62,72,-8};
		
		// (1) Finding the minimum
		double minValue = numbers[0];
		for(int i=1; i<numbers.length; i++) {
			if(numbers[i]<minValue) {
				minValue = numbers[i];
			}
		}
		System.out.println("minimum value is " + minValue);

		// (2) Finding the location of the minimum value
		int smallestPosition = 0;
		for(int i=1; i<numbers.length; i++) {
			if(numbers[i]<numbers[smallestPosition]) {
				smallestPosition = i;
			}
		}
		System.out.println("minimum value " + minValue + " is at index " + smallestPosition);
		
		// (3) Calculating the sum
		double total = 0;
		for(int i=0; i<numbers.length; i++) {
			total += numbers[i];
		}
		System.out.println("total is " + total);
		
		
		// (4) Removing an element
		double[] values = {-30,95,96,20,92,37,-27,-62,72,-8};
		int currentsize = values.length;
		int index_to_be_removed = 5;
		values[index_to_be_removed] = values[values.length-1];
		currentsize--;
		
		
		// Your implementation
		// write an algorithm to calculate the sum without the minimum
		double[] random_numbers = {66,39,96,55,52,33,-57,-4,66,-65};
		int size = random_numbers.length;
		
		// find the location of the min value 
		
		int minPosition = 0;
		for(int i=1; i<numbers.length; i++) {
			if(numbers[i]<numbers[minPosition]) {
				minPosition = i;
			}
		}
		
		//remove the min value from random_numbers
		
		random_numbers[minPosition] = random_numbers[random_numbers.length-1];
		size--;
		
		//calculate the sum
		
		double total2 = 0;
		for(int i=0; i<size.length; i++) {
			total2 += random_numbers[i];
		}
		System.out.println("total is " + total2);
		
	}

}
