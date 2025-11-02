package com.LMS.service;

import static com.LMS.service.LMSValidation.validateAllFaculty;
import static com.LMS.service.LMSValidation.validateAllStudent;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LMS.Exception.LMSException;
import com.LMS.core.Books;
import com.LMS.core.Faculty;
import com.LMS.core.Members;
import com.LMS.core.Student;

public class LMSServiceImplementation implements LMSService {

	private List<Members> memberList;
	private List<Books> bookList;
	private Map<Integer,Integer> issuedTo;
	
	public LMSServiceImplementation() {
		super();
		this.memberList = new ArrayList<>();
		this.bookList = new ArrayList<>();
		this.issuedTo = new HashMap<>();
	}

	@Override
	public void addStudent(String name, String dateOfMembership, String phoneNumber, String aadhaarCard,
			String courseName, int yearOfStudy) throws LMSException {
		Student stud = validateAllStudent(name, dateOfMembership, phoneNumber, aadhaarCard, courseName, yearOfStudy, memberList);
		memberList.add(stud);
		
	}

	@Override
	public void addFaculty(String name, String dateOfMembership, String phoneNumber, String aadhaarCard,
			String departmentName, String designation) throws LMSException {
		Faculty fac = validateAllFaculty(name, dateOfMembership, phoneNumber, aadhaarCard, departmentName, designation, memberList);
		memberList.add(fac);

	}

	@Override
	public void addBook(String title, String author, double price, boolean isAvailable) throws LMSException {
		bookList.add(new Books(title, author, price, isAvailable));

	}

	@Override
	public void issueBookToMember(int memberId, int bookId) throws LMSException {
		Books book = bookList.stream()
						 .filter(b->b.getBookId()==bookId)
						 .findFirst()
						 .orElseThrow(()-> new LMSException("Book not found"));
		
		if(!book.isAvailable()) {
			throw new LMSException("Book not available");
		}
		
		Members member = memberList.stream()
								   .filter(m->m.getMemberId() == memberId)
								   .findFirst()
								   .orElseThrow(()->new LMSException("Member is not present"));
		
		book.setAvailable(false);
		issuedTo.put(bookId, memberId);
		System.out.println("Book : "+book.getTitle()+" is issued by : "+ member.getMemberId() + " " + member.getName());
	}

	@Override
	public void returnBook(int bookId) throws LMSException {
		Books book = bookList.stream()
							 .filter(b->b.getBookId() == bookId)
							 .findFirst()
							 .orElseThrow(()->new LMSException("Book id not found"));
		
		book.setAvailable(true);
		issuedTo.remove(bookId);

	}

	@Override
	public void deleteMember(int member_id) throws LMSException {
		// TODO Auto-generated method stub
		memberList.removeIf(m->m.getMemberId() == member_id);

	}

	@Override
	public void searchMemberByAadhaar(String aadhaaeNumber) throws LMSException {
		// TODO Auto-generated method stub
		memberList.stream()
				  .filter(m->m.getAadhaarCard().equals(aadhaaeNumber))
				  .forEach(m->System.out.println(m));

	}

	@Override
	public void displayAllMember() {
		// TODO Auto-generated method stub
		memberList.stream()
				  .forEach(m->System.out.println(m));

	}

	@Override
	public void displayAllBookSortedByTitle() {
		Comparator<Books> comp = (b1,b2)->b1.getTitle().compareTo(b2.getTitle());
		
		bookList.stream()
				.sorted(comp)
				.forEach(b->System.out.println(b));
	}

}
