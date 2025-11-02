package tester;

import static utils.GenericUtils.*;

import java.util.Arrays;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {

		Integer[] ints = { 10, 20, 30, 40, 50 };// auto boxing
		printArray2(ints);
		Double[] doubles = { 10.0, 20.1, 30.4, 40.67, 50.78 };
		printArray2(doubles);
		String[] strings = { "aa", "bb", "adgdf" };
		printArray2(strings);
		/*
		 * Use Arrays.asList - to return fixed size list of double values
		 * { 10.0, 20.1, 30.4, 40.67, 50.78 }
		 */
		List<Double> doubleList=Arrays.asList(10.0, 20.1, 30.4, 40.67, 50.78);
		//try adding | removing elem - exc - UnSupportedOperationException
		//set , contains , indexOf - works !
		System.out.println(doubleList);
//		doubleList.add(52.2);
//		doubleList.remove(10.0);
		
		doubleList.set(0, 99.9);
//		System.out.println(doubleList.set(0, 300.4));
		System.out.println(doubleList);
		System.out.println(doubleList.contains(20.1));
	}

}
