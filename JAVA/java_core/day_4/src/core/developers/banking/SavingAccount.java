package core.developers.banking;

public class SavingAccount extends BankAccount {
	
	private double interestRate;
	
	public SavingAccount(int accountNumber, double balance, String name, String phoneNum) {
		super(accountNumber, balance, name, phoneNum);
		this.interestRate = interestRate;
	}

	
	public void applyInterest() {
		System.out.println("Saving account interest rate class.");
		 double newBalance = super.getBalance() + interestRate;
		 super.setBalance(newBalance);
		 System.out.println("new balance after interest = " + newBalance);
	}
	
}
