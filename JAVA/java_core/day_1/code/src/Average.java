/*
Accept 5 doubles from user (scanner)
Print it's average.
*/
import java.util.Scanner;
class Average
{	
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);
		double num1, num2, num3, num4, num5;
		
		System.out.println("Enter 5 numbers");
		num1= sc.nextDouble();
		num2= sc.nextDouble();
		num3= sc.nextDouble();
		num4 = sc.nextDouble();
		num5= sc.nextDouble();
		
		double avg = (num1+num2+num3+num4+num5)/5;
		System.out.println("Average:"+avg);
		
	}
}