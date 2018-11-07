import java.util.*;

public class RPSmachine {
	
	Random rnd = new Random();
	private int numPlayed;
	
	public RPSmachine(){
		numPlayed =0;
	}
	
	// update numPlayed
	public String Throw() {
		numPlayed++;
		int num = rnd.nextInt(3);
		if(num == 0){
			return "rock";
		}else if(num == 1){
			return "paper";
		}else{
			return "scissors";
		}
	}
	
	// a method to reset numbPlayed = 0
	public void clear(){
		numPlayed = 0;
	}
	
	// a method to return numPlaed value
	public int get_numPlayed(){
		return numPlayed;
	}
}
