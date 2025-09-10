package custom_exception;

public class OverdraftLimitZero extends Exception{
	public OverdraftLimitZero(String errmsg) {
		super(errmsg);
	}
}
