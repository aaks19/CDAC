package com.developers.geometry;
//import com.tester.TestPoint;
import static java.lang.Math.*;
public class Point2D{
    private int x;
	private int y;
	public Point2D(int x,int y){   
	   this.x=x;
	   this.y=y;
	}
	public void show(){
	   System.out.println("x:: "+x+" y::"+y);
	}
	public boolean Equal(Point2D p1){
		if(p1.x == x && p1.y == y){
			return true;
		}
		else
		{
			return false;
		}
	}
	public double distance(Point2D p1){
		double base  = (Math.pow(p1.x-x,2))+(Math.pow(p1.y-y,2));
		double result = Math.pow(base, 0.5);
		return result;
	}

}

