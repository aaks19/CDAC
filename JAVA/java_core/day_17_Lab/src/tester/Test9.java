package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;

import com.app.core.Student;

//Print names of home cities (no dups please !) of all Students 

public class Test9 {
	public static void main(String[] args) {
		Map<String, Student> studentMap = populateMap(populateList());
		studentMap.values().stream().map(p -> p.getAddress().getCity()).distinct().forEach(p -> System.out.println(p));
	}
}