import java.util.Scanner;

class Tester{
	public static void main(String[] args){
		
		
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter x-coordinate: ");
		int x = sc.nextInt();
		System.out.println("Enter y-coordinate: ");
		int y = sc.nextInt();
		Point2D pt = new Point2D(x, y);
		Point2D pt2 = new Point2D(4,6);
		
		System.out.println("Coordinates are: "+"x: "+pt.getx() + ", y: "+pt.gety()) ;
		
		System.out.println("Is it equal : " + pt.isEqual(pt2));
		
		System.out.println("Calculate distance:");
		System.out.println("Enter new x-coordinate: ");
		int nx = sc.nextInt();
		System.out.println("Enter new y-coordinate: ");
		int ny = sc.nextInt();
		Point2D pt3 = new Point2D(nx,ny);
		
		System.out.println("Distance of " + "(" + x + "," + y + ")" + " from " + "(" + nx + "," + ny + ")" + "is : " + pt.calculateDistance(pt3)); 
		
		
		sc.close();
	}
}