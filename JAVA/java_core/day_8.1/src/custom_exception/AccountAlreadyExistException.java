package custom_exception;

@SuppressWarnings("serial")
public class AccountAlreadyExistException extends Exception {
	
	public AccountAlreadyExistException(String errmsg) {
		super(errmsg);
	}

}
