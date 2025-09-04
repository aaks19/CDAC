package core.developers.banking;

public class BankAccount {
	private int accountNumber;
	private double balance;
	private String name;
	private String phoneNum;
	
	public BankAccount(int accountNumber, double balance, String name, String phoneNum) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
		this.phoneNum = phoneNum;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double newbalance) {
		this.balance = newbalance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void deposit(double amount) {
		this.balance = this.balance + amount;
		
	}
	
	public void withdraw(double amount) {
		if((this.balance - amount) < 0 ) {
			System.out.println("Insufficient balance!");
		}else {
			System.out.println("Amount withdrawn successfully");
			this.balance -= amount ;
		}
	}
	
	public void getAccountSummary() {
		System.out.println("Account Details:-");
		System.out.println("Account number :   " + accountNumber);
		System.out.println("Name :    " + name);
		System.out.println("Balance :    " + balance);
		System.out.println("Phone Number :    " + phoneNum);
	}
	
	
	
}
