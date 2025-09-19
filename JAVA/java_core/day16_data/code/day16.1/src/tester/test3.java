package tester;

import java.util.HashSet;
import java.util.TreeSet;

import static utils.sumUtils.*;

import static utils.utils.*;
/*
 *  Write a static method in a non generic Utils interface 
 - to get sum of all numbers(integer | double | float | byte ..), stored in the Set .
Test cases - HashSet<Integer> , LinkedHashSet<Double> , TreeSet<Long>

 */
public class test3 {

	public static void main(String[] args) {
		HashSet<Integer> intSum = new HashSet<>();
		intSum.add(10);
		intSum.add(10);
		intSum.add(10);
		intSum.add(10);
		sumAll(intSum);
		System.out.println(sumAll(intSum));
		
		
		TreeSet<Long> treeSum = new TreeSet<>();
		treeSum.add(100l);
		treeSum.add(200l);
		treeSum.add(300l);
		treeSum.add(400l);
		sumAll(treeSum);
		System.out.println(sumAll(treeSum));

		
	}
}
