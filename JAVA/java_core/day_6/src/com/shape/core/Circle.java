package com.shape.core;
import static java.lang.Math.PI;;
public class Circle extends BoundedShape{
	private double radius;
	
	public Circle(int x, int y, double radius) {
		super(x,y);
		this.radius = radius;
	}
	
	@Override
	public double computeArea() {
		return PI * Math.pow(radius, 2);
	}
	
	@Override
	public double computePerimeter() {
		return 2 * PI * radius;
	}
	
	@Override
	public String toString() {
		return "Circle "+ super.toString() + " radius "+ this.radius;
	}
}

