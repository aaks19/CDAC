package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fruits.Fruit;

public interface GenericUtils {
/*
 * Write a static method in a non generic Utils class 
 - to display elements of any Set | List
 */
	static List<Fruit> populateFruitList(){
		List<Fruit> fruitList = new ArrayList<>();
		return fruitList;
		
	}
	
	
	 public static void displayElements(Collection<?> anyCollection)
	 {
		 for(Object o : anyCollection)
			 System.out.println(o);
	 }
	 
	
}
