import java.util.Arrays;
import java.util.*;

// This class aims to calculate sum of the elements in an array using a recursive function.
// Fill out the missing part to complete the method. 
public class RecursiveSum {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] randomNums = {4,5,1,6,9,11,13,15,11,4,75,21,76,23,77,33,92,23,15,23,
				30,70,100,90,88,14,50,90,21,61,72,73,69,9,35,73,42,10,85,
				74,13,37,38,25,25,66,87,21,87,11,63,21,72,23,4,76,54,47,
				64,89,10,33,65,23,93,50,29,8,98,58,11,40,81,89,15,44,6
		};
		int result = RecursiveSum.sum(randomNums);
		System.out.println(result);
	}
	
	// Recursive method to calculate sum of the elements of an array
	public static int sum(int[] numbers) {
		if(numbers.length == 1) { // Base case: when array length is 1, the element is equal to the sum
			return numbers[0];
		}else {
			// sum of the elements in "numbers" array
			// = the last element + sum of the elements of the shorter array (excluding the last element of "numbers" array)
			// Hint: Use Arrays.copyOf method to create a shorter array.
			return numbers[numbers.length-1] + sum(Arrays.copyOf(numbers, numbers.length-1));
		}
	}
}
