package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Comparator;
import java.util.Map;

import com.app.core.Student;

//Display  student details for specified subject , sorted as per DoB

public class Test7 {
	public static void main(String[] args) {
		Comparator<Student> comp = (s1, s2) -> s1.getDob().compareTo(s2.getDob());

		Map<String, Student> studentMap = populateMap(populateList());
		studentMap.values().stream().sorted(comp).forEach(p -> System.out.println(p));
	}
}