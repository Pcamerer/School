import java.util.*;

/**
 * CH8 Modeling Objects with Distinct States
 * @author YWP
 */
public class Fish {

	private int hungry;

	public static final int NOT_HUNGRY = 0;  
	public static final int SOMEWHAT_HUNGRY = 1;  
	public static final int VERY_HUNGRY = 2;
	Random rnd = new Random();
	
	public void eat()
	{
		System.out.println("Eating");
		hungry = NOT_HUNGRY;
	}
	
	public void move()
	{
		System.out.println("Moving");
		if (hungry < VERY_HUNGRY) {
			hungry++; 
		}else if (hungry == VERY_HUNGRY)
		{
			search_food();
		}
	}
	
	public void search_food(){
		System.out.println("Searching Food");
		if(rnd.nextDouble() < 0.7){
			eat();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num_moves = 10;
		int i=0;
		Fish nemo = new Fish();
		while(i<num_moves){
			nemo.move();
			i++;
		}
	}

}
