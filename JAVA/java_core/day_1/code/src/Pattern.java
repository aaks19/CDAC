import java.util.Scanner;
class Pattern
{
	public static void main(String[] args)
	{
		System.out.println("enter dimension");
		Scanner sc = new Scanner(System.in);
		int dim = sc.nextInt();
		
		System.out.println("Enter Any char");
		
		char ch = sc.next().charAt(0);
		
		
		for(int i=1; i<= dim ; i++)
		{
			for(int j=1; j<=i; j++)
			{
				System.out.print(ch+" ");
			}
			System.out.println("");
		}
	}
}