/*4.1 Write Java program - 
Display food menu to user. User will select items from menu along with the quantity. (eg 1. Dosa 2. Samosa 3. Idli ... 0 . Generate Bill ) Assign fixed prices to food items(hard code the prices)
When user enters 'Generate Bill' option(0) , display total bill & exit.
*/
import java.util.Scanner;
class FoodMenu
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		boolean flag = false;
		int total =0;
		int quantity=0;
		while(flag!=true)
		{
			System.out.println("Enter options: 1. Dosa:10$ 2. Idli:5$  3. Samosa:20$ 4. vadaPav:30$ 0. Generate Bill");
			switch(sc.next())
			{
				case "1": System.out.println("Enter Quantity Dosa:");
						 quantity = sc.nextInt();
						total+= quantity* 10;
						break;
				
				case "2": System.out.println("Enter Quantity Idli:");
						 quantity = sc.nextInt();
						total+= quantity* 5;
						break;
						
				case "3": System.out.println("Enter Quantity Samosa:");
						 quantity = sc.nextInt();
						total+= quantity* 20;
						break;
						
				case "4": System.out.println("Enter Quantity VandaPav:");
						 quantity = sc.nextInt();
						total+= quantity* 30;
						break;
						
				case "0": System.out.println("Generating total bill:");
						System.out.println(total);
						flag= true; break;
						
				default: System.out.println("Choice Not Available..");
				break;
						
			}
		}
		
		
	}
}
