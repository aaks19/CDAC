package validaion;
import custom_exception.*;

public class AccountValidation{

	public static void validateAccount() throws AccountAlreadyExistException
	{
		throw new AccountAlreadyExistException("Account No. Already Existed, please try different Account Number");
	}
	
}
