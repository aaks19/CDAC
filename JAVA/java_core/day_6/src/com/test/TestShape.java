package com.test;

import com.shape.core.BoundedShape;
import com.shape.core.Circle;
import com.shape.core.Rectangle;

public class TestShape {
	public static void main(String[] args) {
		BoundedShape[] shapes = {
				new Circle(10,20,5.2),
				new Rectangle(20,30,15.2,12.6)
		};
		
		for(BoundedShape shape:shapes) {
			System.out.println(shape);
			
			
			//when abastraction not used
//			if(shape instanceof Circle) {
//				System.out.println("Area = "+ ((Circle)shape).computeArea());
//				System.out.println("Perimeter = "+ ((Circle)shape).computePerimeter());;
//			}else if(shape instanceof Rectangle) {
//				System.out.println("Area = "+ ((Rectangle)shape).computeArea());
//				System.out.println("Perimeter = "+ ((Rectangle)shape).computePerimeter());;
//			}else {
//				System.out.println("Invalid!!");
//			}
			
			
			
			//using Abstraction
			
			System.out.println("Area = "+ shape.computeArea());
			System.out.println("Perimeter = "+ shape.computePerimeter());
		}
	}
}
