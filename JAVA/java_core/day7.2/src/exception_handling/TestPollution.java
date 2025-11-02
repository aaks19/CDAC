package exception_handling;

import static validation.PollutionLimitCheck.CheckPollution;
import java.util.Scanner;

public class TestPollution {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			boolean flag = false;
			while(!flag) {
				System.out.println("Enter choice:\n1. Check Pollution \n0.Exit");
				try {
					switch (sc.nextInt()) {
					case 1: 
						System.out.println("Enter Pollution : ");
						CheckPollution(sc.nextInt());
						
						break;
					
					
					case 0 : 
						flag = true;
						break;
					}
				}
		
				catch(Exception e) {
					sc.nextLine();
					e.printStackTrace();
				}
			}
		}
	}
}
