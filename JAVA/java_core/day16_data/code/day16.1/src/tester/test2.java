package tester;

import static utils.utils.displayTaste;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fruits.Apple;
import fruits.Orange;

public class test2 {
	public static void main(String[] args) {

		//Fruits
		
		ArrayList<Apple> apples = new ArrayList<>(List.of(new Apple(),new Apple(),new Apple()));
		apples.remove(0);
		apples.add(new Apple());
		displayTaste(apples);
		
		LinkedList<Orange> oranges = new LinkedList<>(List.of(new Orange(),new Orange(),new Orange()));
		displayTaste(oranges);
		
	}
}
