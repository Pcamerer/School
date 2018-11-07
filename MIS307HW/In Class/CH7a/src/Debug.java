
public class Debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a = 2;
		double b = 4;
		
		double sum = calculate_sum(a,b);
		System.out.println(sum);
		double power = calculate_power(a,b);
		System.out.println(power);
		
		
		System.out.println("end");
	}
	
	public static double calculate_sum(double num1, double num2){
		double sum = num1+num2;
		return sum;
	}

	public static double calculate_power(double num1, double num2){
		double power = Math.pow(num1, num2);
		return power;
	}
	
}
