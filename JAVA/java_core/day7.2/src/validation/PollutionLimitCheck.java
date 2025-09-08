package validation;

import custom_exception.PollutionExceedException;

public class PollutionLimitCheck {
	public static final int MAX_POLLUTION;
	static {
		MAX_POLLUTION = 20;
	}
	
	public static void CheckPollution(int pollution) throws PollutionExceedException{
		if(pollution > MAX_POLLUTION) {
			throw new PollutionExceedException("The pollution is increased");
		}
		System.out.println("Normal pollution rnage");
	}
}
