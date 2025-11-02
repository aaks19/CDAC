package core.developers.banking;

public class CurrentAccount extends BankAccount{

	private double overdraftLimit;
	public CurrentAccount(int accountNumber, double balance, String name, String phoneNum) {
		super(accountNumber, balance, name, phoneNum);
		this.overdraftLimit = overdraftLimit;
	}

	public void useOverdraftFacility() {
		System.out.println("in current account over draft facility");
	}
	
}
