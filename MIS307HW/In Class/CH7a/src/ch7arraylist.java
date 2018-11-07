import java.util.ArrayList;

public class ch7arraylist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Task1: convert the following array into an array list "prog2" and print
		String[] prog = {"Java", "C", "C#", "C++", "Python", "R"};
		
		ArrayList<String> prog2 = new ArrayList<String>();
		for (int i=0; i<prog.length; i++) {
			prog2.add(prog[i]);
		}
		
		for (int i=0; i<prog2.size(); i++) {
			System.out.println(prog2.get(i));
		}
		
		/* for (String word: prog2) {
			System.out.println(word);
		} */
		
		
		
		// Task2: copy the array list in Task 1 and create an array list "prog3";
		
		ArrayList<String> prog3 = new ArrayList<String>(prog2);
		
		
		
		
		
		// Task3: create an array list of integers 0,1,4,9,16,25 using for loop and print
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int x=0; x<=5; x++) {
			numbers.add(x*x);
		}
		
		

		
		// Task4: from prog3, remove strings with length <=2. Use for loop.
		
		int i=0;
		while (i<prog3.size()) {
			String word = prog3.get(i);
			if(word.length()<=2) {
				prog3.remove(i);
			} else {
				i++;
			}
		}
		
		
		
	}

}
