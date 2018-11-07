
public class ch7inclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] scores = {10,9,7,4,5};
		int[] values = scores;
		
		
		scores[3] = 10;
		values[1] = 5;
		
		for (int i=0; i<scores.length; i++){
			System.out.print(scores[i] + " ");
		}
		System.out.println();
		
		for (int i=0; i<values.length; i++){
			System.out.print(values[i] + " ");
		}
		System.out.println();
		}
	}


