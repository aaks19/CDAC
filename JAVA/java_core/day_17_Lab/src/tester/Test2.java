package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

//Display  details of the students from  specified subject

public class Test2 {
	public static void main(String[] args) {
		Map<String,Student> studentMap = populateMap(populateList());
		System.out.println("Details of student of specified subject");
		Scanner sc = new Scanner(System.in);
		Subject sub = Subject.valueOf(sc.next().toUpperCase());
		studentMap.values()
				  .stream()
				  .filter(p->p.getSubject() == sub)
				  .forEach(p->System.out.println(p));
		
	}
}
