package com.shape.core;

public abstract class BoundedShape {
	private int x;
	private int y;
	
	public BoundedShape(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public String toString() {
		return "(x :" + x + ",y:" + y+")";
	}
	
	
	//when abstraction is not used
//	public double computeArea() {
//		return 0;
//	}
//	
//	public double computePerimeter() {
//		return 0;
//	}
	
	
	//using Abstraction
	public abstract double computeArea() ;
	
	public abstract double computePerimeter();
}
