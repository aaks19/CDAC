package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;

import com.app.core.Student;

import custom_exception.StudentCollectionException;

//Find any student with GPA above 8(Should run as short circuit operation,
//meaning the moment you come across any student with GPA>8,
//the streams should stop iterating&return the result immediately)

public class Test8 {
	public static void main(String[] args) {
		Map<String, Student> studentMap = populateMap(populateList());
		Student result = studentMap.values().stream().filter(p -> p.getGpa() > 8.0).findFirst()
				.orElseThrow(() -> new StudentCollectionException("No student has GPA > 8.0"));
		System.out.println(result);
	}
}
