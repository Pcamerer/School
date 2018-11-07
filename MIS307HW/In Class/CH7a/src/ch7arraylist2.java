import java.util.*;

public class ch7arraylist2 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		// Task: using the Scanner object, receive names and store in a String array list.
		// If the user enters 0, then stop receiving names and print the current array list.
		
		// String newName = in.next();
		// By this code, the new input is stored in String variable newName
		
		
		// Step1: declare String array list
		
		ArrayList<String> names = new ArrayList<String>();
		
		// Step2: Use while loop 
		// If the input is equal to "0", then terminate the loop
		// Otherwise, add the input name to the array list
		while(true){
			System.out.print("Enter:");
			String newName = in.next();
			if(newName.equals("0")) {
				break;
			}else{
				names.add(newName);
			}
		}
		
		// Step3: print the array list
		System.out.println(names);
	}

}
