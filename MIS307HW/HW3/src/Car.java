
public class Car {
	
	int year;
	String make;
	double speed;
	
	public Car(int y, String m, double s){
		this.year = y;
		this.make = m;
		this.speed = s;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	
	public String getMake(){
		return this.make;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void accelerate(){
		this.speed += 1;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Car c = new Car(2014, "Audi", 190.0);
		System.out.println("Car year: " + c.getYear());
		System.out.println("Car Make: " + c.getMake());
		System.out.println("Car Speed: " + c.getSpeed());
		
		c.accelerate();
		c.accelerate();
		
		System.out.println("Car year: " + c.getYear());
		System.out.println("Car Make: " + c.getMake());
		System.out.println("Car Speed: " + c.getSpeed());
	}
		
		

}
