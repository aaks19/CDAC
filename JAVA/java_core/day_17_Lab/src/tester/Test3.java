package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;

//Print sum of  marks of students of all students from the specified state
//i/p : name of the state

public class Test3 {
	public static void main(String[] args) {
		Map<String, Student> studentMap = populateMap(populateList());
		System.out.println("Details of student of specified subject");
		Scanner sc = new Scanner(System.in);
		String sub = sc.next().toUpperCase();
		double sum = studentMap.values().stream().filter(p -> p.getAddress().getState().equals(sub))
				.mapToDouble(p -> p.getGpa()).sum();
		System.out.println(sum);
//				  .forEach(p->System.out.println(p));

	}
}
