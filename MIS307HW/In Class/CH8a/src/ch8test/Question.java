package ch8test;
import java.util.*;

/**
 * CH8 collecting values
 * @author YWP
 */
public class Question {

	private ArrayList<String> choices;
	
	public Question() {
		choices = new ArrayList<String>();
	}
	
	public void add_option(String option){
		choices.add(option);
	}
	
	public ArrayList<String> get_options(){
		return choices;
	}
	
	public static void main(String[] args) {
		
		// Question class maintains an arraylist (choices) to store options 
		
		// Creating an object of Question class
		Question q1 = new Question();
		q1.add_option("(A) Des Moines");
		q1.add_option("(B) Ames");
		q1.add_option("(C) Davenport");
		q1.add_option("(D) Urbandale");
		q1.add_option("(E) Dubuque");
		ArrayList<String> q1_options = q1.get_options();
		
		System.out.println("Printing q1");
		System.out.println(q1_options);
		System.out.println();
		
		// Creating an object of Question class
		Question q2 = new Question();
		q2.add_option("(A) Information Systems");
		q2.add_option("(B) Supply Chian");
		q2.add_option("(C) Marketing");
		q2.add_option("(D) Management");
		q2.add_option("(E) Accounting");
		q2.add_option("(F) Finance");
		ArrayList<String> q2_options = q2.get_options();
		System.out.println("Printing q2");
		System.out.println(q2_options);
		System.out.println();
		
		
		// We can create an arraylist of Question objects
		// Task: create an arraylist of Question objects and add q1 and q2. Print them

		ArrayList<Question> exam = new ArrayList<Question>();
		exam.add(q1);
		exam.add(q2);
		
		for(int i=0; i<exam.size(); i++){
			System.out.println(exam.get(i).get_options());
			
		}
		
		
	}

}
