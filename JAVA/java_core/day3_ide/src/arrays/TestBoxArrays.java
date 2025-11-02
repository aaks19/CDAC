package arrays;

import java.util.Scanner;
import com.cdac.core.Box;

/*
 * Ask user(client) , how many boxes to make ?
Accept Box dimensions.
Store these details suitably.
Display box details n volume using for-each loop

 */

public class TestBoxArrays {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of boxes to make");
		Box[] boxes = new Box[sc.nextInt()];
		System.out.println("Default array contents");

		for (int i = 0; i < boxes.length; i++) {
			System.out.println("Enter w d h");
			boxes[i] = 
					new Box(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
		}
		for (Box b : boxes)
			System.out.println(b);
		System.out.println("Box dims and volume - for each loop");
		
		for (Box b : boxes)
		{
			System.out.println(b.getBoxDimensions());
			System.out.println("Volume "+b.getVolume());
		}
		
		
		for(Box b : boxes) {
			if(b.getVolume()<100) {
				System.out.println("Box having volume < 100 there width gets doubled = "+b.getWidth()*2);
			}
		}
		sc.close();

	}

}
