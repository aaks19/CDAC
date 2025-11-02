package com.sms.services;

import static com.sms.services.SMSValidation.validateAll;

import java.util.ArrayList;
import java.util.Comparator;

import com.sms.StudentManagementException.StudentManagementException;
import com.sms.core.Student;

public class SMSServiceImpl implements SMSService {

	ArrayList<Student> students = new ArrayList<>();

	@Override
	public String takeAdmission(String name, String email, int marks, String course, String admissionDate)
			throws StudentManagementException {
		Student stud = validateAll(name, email, marks, course, admissionDate, students);
		students.add(stud);
		return "Registeration successful";
	}

	@Override
	public void displayAllStudnets() {
//		for(Student s : students) {
//			System.out.println(s);
//		}

		students.stream().forEach(p -> System.out.println(p));
	}

	// cancle admission
	@Override
	public void cancleAdmission(String email) {
//		Student stud = new Student(email);
//		Iterator<Student> itr = students.iterator();
//		while(itr.hasNext()) {
//			if(itr.next().getEmail().equals(email)) {
//				Student stud = itr.next();
//				stud.getCourses().setMinSeat(stud.getCourses().getMinSeat()+1);
//				itr.remove();
//			}
//		}

		// using removeif method
		students.removeIf(p -> p.getEmail().equals(email));
		students.stream().forEach(p -> p.getCourses().setMinSeat(p.getCourses().getMinSeat() + 1));
		System.out.println("Successfully canceled admission");

	}

	// search student by email
	@Override
	public void searchStudentByEmail(String email) throws StudentManagementException {
//		ListIterator<Student> itr = students.listIterator();
//		while(itr.hasNext()) {
//			if(itr.next().getEmail().equals(email)) {
//				System.out.println("Student found");
//				Student stud = itr.previous();
//				System.out.println(stud);
//				return;
//			}
//			System.out.println("Student not found...");
//		}
		
		
		Student s = students.stream()
							.filter(p->p.getEmail().equals(email))
							.findAny()
							.orElseThrow(()->new StudentManagementException("Email not found"));
		System.out.println("found\n"+s);
	}

	// sort By course;
	@Override
	public void listStudentByCourse() throws StudentManagementException {
		Comparator<Student> comp = (s1,s2)->s1.getCourses().compareTo(s2.getCourses());
		students.stream()
				.sorted(comp)
				.forEach(p->System.out.println(p));
	}
}
