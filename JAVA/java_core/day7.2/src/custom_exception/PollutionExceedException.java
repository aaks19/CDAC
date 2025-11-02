package custom_exception;

@SuppressWarnings("serial")
public class PollutionExceedException extends Exception{
	public PollutionExceedException(String errmsg) {
		super(errmsg);
	}
}
