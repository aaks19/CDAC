package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
import com.app.core.Subject;

import custom_exception.StudentCollectionException;


//Print number  of  failures for the specified subject chosen  from user.
//i/p : subject name
//(failure is GPA < 5.0 , out of 1-10)

public class Test5 {
	public static void main(String[] args) throws StudentCollectionException {
		Map<String,Student> studentMap = populateMap(populateList());
		System.out.println("Details of student of specified subject");
		Scanner sc = new Scanner(System.in);
		Subject sub = Subject.valueOf(sc.next().toUpperCase());
		long count = studentMap.values()
				  .stream()
				  .filter(p->p.getSubject() == sub && p.getGpa()<5.0)
				  .peek(p->System.out.println()).count();
//				  .forEach(p->System.out.println(count));
		System.out.println(count);
		
	}
}
