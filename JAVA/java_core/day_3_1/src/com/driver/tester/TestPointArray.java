//package com.driver.tester;
//
//import java.util.Scanner;
//
//import com.developers.geometry.Point2D;
//
//public class TestPointArray {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter number of point to plot : ");
//		int num = sc.nextInt(); 
//		Point2D[] pts = new Point2D[num];
//		
//		int counter = 0; // Boundary condition checking
//		
//		if(counter<pts.length) {
//			for(int i=0; i<pts.length;i++) {
//				// loop to insert elements in array.
//				System.out.println("Enter x-coordinate and y-coordinate : ");
//				pts[i] = new Point2D(sc.nextInt(),sc.nextInt());
//			}
//			counter++;
//		}else {
//			System.out.println("Array is full!");
//		}
//		
//		//display the coordinates
//		for(Point2D p : pts) {
//			System.out.println("x-coordinate and y-coordinates are : ");
//			p.show();
//		}
//		
//		
//		//add new points
//		System.out.println("Enter number of points to add: ");
//		int n = sc.nextInt();
//		
//		Point2D[] ptnew = new Point2D[num + n];
//      	for (int i = 0; i < num; i++) {
//          	ptnew[i] = pts[i];
//      	}
//		
//		
//		System.out.println("Enter new points to plot : ");
//        int insertIndex = num; // start inserting after old points
//
//        for (int i = 0; i < n; i++) {
//            Point2D pt2 = new Point2D(sc.nextInt(), sc.nextInt());
//
//            // check for duplicate with already stored points
//            boolean exists = false;
//            for (int j = 0; j < insertIndex; j++) {
//                if (ptnew[j].Equal(pt2)) {
//                    System.out.println("The element is already present");
//                    exists = true;
//                    break;
//                }
//            }
//
//            // only insert if not duplicate
//            if (!exists) {
//                ptnew[insertIndex] = pt2;
//                insertIndex++;
//            }
//        }
//		
//		System.out.println("new points");
//		for(Point2D p : ptnew) {
//			System.out.println("x-coordinate and y-coordinates are : ");
//			p.show();
//		}
//	
//		
//		
//	}
//}






package com.driver.tester;

import java.util.Scanner;
import com.developers.geometry.Point2D;

public class TestPointArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of point to plot : ");
        int num = sc.nextInt();
        Point2D[] pts = new Point2D[num];

        for (int i = 0; i < pts.length; i++) {
            System.out.println("Enter x-coordinate and y-coordinate : ");
            pts[i] = new Point2D(sc.nextInt(), sc.nextInt());
        }

        System.out.println("Initial Points:");
        for (Point2D p : pts) {
            p.show();
        }

        System.out.println("Enter number of points to add: ");
        int n = sc.nextInt();

        Point2D[] ptnew = new Point2D[num + n];
        for (int i = 0; i < num; i++) {
            ptnew[i] = pts[i];
        }

        System.out.println("Enter new points to plot : ");
        int insertIndex = num; 

        for (int i = 0; i < n; i++) {
            Point2D pt2 = new Point2D(sc.nextInt(), sc.nextInt());

            boolean exists = false;
            for (int j = 0; j < insertIndex; j++) {
                if (ptnew[j].Equal(pt2)) {
                    System.out.println("The element is already present");
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                ptnew[insertIndex] = pt2;
                insertIndex++;
            }
        }

        // Display final points
        System.out.println("All Points after insertion:");
        for (int i = 0; i < insertIndex; i++) {
            ptnew[i].show();
        }

        sc.close();
    }
}
