package tester;
import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

//Display names of students enrolled in a specified subject , securing marks > specified marks
//i/p : subject name , marks

public class Test6 {
	public static void main(String[] args) {
		Map<String,Student> studentMap = populateMap(populateList());
		System.out.println("Name of student enrolled in a specified subject");
		System.out.println("Enter subject name: ");
		Scanner sc = new Scanner(System.in);
		Subject sub = Subject.valueOf(sc.next().toUpperCase());
		System.out.println("Enter marks: ");
		double marks = sc.nextDouble();
		studentMap.values()
				  .stream()
				  .filter(p->p.getSubject() == sub && p.getGpa()>marks)
				  .forEach(p->System.out.println(p));			  
	}
}
