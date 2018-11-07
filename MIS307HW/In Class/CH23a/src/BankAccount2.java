/**
 * CH11 IO Exception
 * @author YWP
 */
public class BankAccount2 {

	private double balance;  
	private int accountNumber;
	private static int lastAssignedNumber = 1000;
	public static final double OVERDRAFT_FEE = 29.95;
	
	public BankAccount2()
	{
		lastAssignedNumber++;
		accountNumber = lastAssignedNumber;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public void deposit(double amount){
		balance += amount;
		System.out.println("Account number:" + getAccountNumber() + "\t (+)deposit:" + amount + "\tbalance:" + getBalance());
		
	}
	
	public double getBalance(){
		return balance;
	}
	
	
	/*
	 * Rule 1: non-positve amount cannot be withdrawn
	 * Rule 2: amount exceeding balance cannot be withdrawn. Overdraft fee is charged
	 * Rule 3: at most $1000 can be withdraw
	 * Your task: change the following method to have a simpler structure. In detail
	 * (a) Use exceptions 
	 * (b) Instead of nested if statements, 
	 *     use three consecutive if statements checking the rules and throwing exceptions.
	 */
	public void withdraw(double amount){
		if(amount <= 0){
			System.out.println("******* Amount must be positive *******");
		}else{
			if(amount > balance){
				System.out.println("******* Amount exceeds balance *******");
				charge_overdraft_fee();
			}else{
				if(amount > 1000){
					System.out.println("******* Amount must be at most $1,000 *******");
				}else{
					balance -= amount;
					System.out.println("Account number:" + getAccountNumber() + "\t (-)withdraw:" + amount + "\tbalance:" + getBalance());	
				}
				
			}
		}
	}
	
	public void charge_overdraft_fee(){
		System.out.println("******* Overdraft fee " + OVERDRAFT_FEE + " charged. *******");
		balance -= OVERDRAFT_FEE;
		System.out.println("Account number:" + getAccountNumber() + "\t (-)fee:" + OVERDRAFT_FEE + "\tbalance:" + getBalance());
	}
	
	
	
	public static void main(String[] args) {

		BankAccount2 myAccount = new BankAccount2();
		myAccount.deposit(500.00);
		myAccount.withdraw(100);
		myAccount.withdraw(800);
		myAccount.withdraw(-10);
		myAccount.deposit(1500.00);
		myAccount.withdraw(1500.00);
		
	}

}
