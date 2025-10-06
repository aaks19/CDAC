package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

//Display all student details from the student map.

public class Test1 {
	public static void main(String[] args) {
		Map<String,Student> studentMap = populateMap(populateList());
		System.out.println("All Student Details");
		studentMap.forEach((k,v) -> System.out.println(v));
		
		System.out.println("-----------------------------------");
		System.out.println("Details of student of specified subject");
		Scanner sc = new Scanner(System.in);
		Subject sub = Subject.valueOf(sc.next());
		studentMap.values().stream()
							.filter(p->p.getSubject() == sub)
							.forEach(p->System.out.println(p));
	}
}
