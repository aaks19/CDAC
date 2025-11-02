package custom_exception;

public class AccountAlreadyExistException extends Exception {
	
	public AccountAlreadyExistException(String errmsg) {
		super(errmsg);
	}

}
