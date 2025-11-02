package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

import custom_exception.StudentCollectionException;

//Print name of specified subject topper
//i/p : subject name

public class Test4 {
	public static void main(String[] args) throws StudentCollectionException {
		Map<String,Student> studentMap = populateMap(populateList());
		System.out.println("Details of student of specified subject");
		Scanner sc = new Scanner(System.in);
		Subject sub = Subject.valueOf(sc.next().toUpperCase());
		Student name = studentMap.values()
				  .stream()
				  .filter(p->p.getSubject() == sub)
				  .max((o1, o2) -> ((Double)o1.getGpa()).compareTo(o2.getGpa()))
				  .orElseThrow(() -> new StudentCollectionException("No student of the following subject"));
//				  .mapToDouble(p->p.getGpa())
//				  .forEach(p->System.out.println(p));
		System.out.println("Topper is :"+ name);
	}
}
