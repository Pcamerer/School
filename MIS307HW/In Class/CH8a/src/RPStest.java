
public class RPStest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RPSmachine m1 = new RPSmachine();
		RPSmachine m2 = new RPSmachine();
		for(int i=0; i<10; i++){
			System.out.println(m1.Throw() + " vs. " + m2.Throw());
		}
		
		System.out.print(m1.get_numPlayed());
	}

}
