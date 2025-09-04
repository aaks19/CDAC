package com.tester;
import com.developers.geometry.Point2D;
import java.util.Scanner;
class TestPoint{
   
  public static void main(String[] args)
  {
	 Scanner sc = new Scanner(System.in);
     System.out.println("Enter X & Y coordinates");
	 Point2D p1 = new Point2D(sc.nextInt(),sc.nextInt());
	 System.out.println("Enter X & Y coordinates");
	 Point2D p2 = new Point2D(sc.nextInt(),sc.nextInt());
	 p1.show();
	 System.out.println("ISEQUAL " +p2.Equal(p1));
	 if(p2.Equal(p1)){
	  System.out.println("SAME");
	 }else
	 {
		 System.out.println("NOTSAME");
	 }
	 if(!p2.Equal(p1)){
	  System.out.println("Distance between two points " +p2.distance(p1));
	 }
	 sc.close();
  }
}