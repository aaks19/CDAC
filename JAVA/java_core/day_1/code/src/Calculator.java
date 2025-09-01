/*Write Java program for the following - 
It should  run till user enters any other option than add or sub or multiply or divide
Prompt user to enter the input operation : (add|subtract|multiply|divide) & 2 numbers(double)
Display the result of the operation.
Use Scanner for accepting all inputs from user. 
Hint : use switch-case
*/
import java.util.Scanner;
class Calculator
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		
		int num1,num2;
		

		boolean flag = false;
		while(flag!=true)
		{
			
			System.out.println("Enter 1. add 2. subtract 3. multiply 4. divide 5. exit");
			switch(sc.nextInt())
			{
				case 1 : System.out.println("Enter 2 numbers:");
						num1= sc.nextInt();
						num2=sc.nextInt();
							System.out.println("Addition = " +((num1) + (num2)));
				break;
				
				case 2 :System.out.println("Enter 2 numbers:");
						num1= sc.nextInt();
						num2=sc.nextInt();
							System.out.println("Subtraction = " + ((num1) - (num2)));
				break;
				
				case 3 :System.out.println("Enter 2 numbers:");
							num1= sc.nextInt();
							num2=sc.nextInt();
							System.out.println("Multipication = " + ((num1) * (num2)));
				break;
				
				case 4 : System.out.println("Enter 2 numbers:");
							num1= sc.nextInt();
							num2=sc.nextInt();
							System.out.println("Division = " + ((num1) / (num2)));
				break;
				
				case 5 : flag= true;break;
				
				default: System.out.println("Invalid output"); break;
				
				
			}
		}
			
		
	}
}