package custom_exception;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String errmsg) {
		super(errmsg);
	}
}
