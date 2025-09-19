package tester;

import java.util.ArrayList;
import java.util.List;

import fruits.Fruit;
import fruits.Mango;

public class Test5 {

	public static void main(String[] args) {
		//solve
		Fruit f=new Mango();// up casting
		List<Fruit> fruits;
		List<Mango> mangoes = new ArrayList<>();
		//what will happen ?
	//	fruits=mangoes; javac error....in collections subclass --> superclass conversion not allowed.
		List<Object> objects;
	//	objects=mangoes; javac error....Object sabka super type hai, collections mein direct replace nahi kar sakte.
		/*
		 * Conclusion - Java Collections are "invariant" by nature.
		 * Meaning - List<Object> IS NOT a super type of List<Mango>
		 * or List<Fruit>
		 * i.e You can't replace List<Object> by List<Mango>
		 * Can you use T here ?
		 * Try it out !
		 * 
		 */
//		List<T> someList;//javac error : T can't be resolved to a type
		List<?> anyList=mangoes;
	}
	

}
