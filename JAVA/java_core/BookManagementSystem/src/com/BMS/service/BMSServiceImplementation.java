package com.BMS.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.BMS.service.BMSValidation.checkDuplicate;

import com.BMS.Exception.BMSException;
import com.BMS.core.Book;

public class BMSServiceImplementation implements BMSService {

	private List<Book> bookList;
	public BMSServiceImplementation() {
		this.bookList = new ArrayList<>();
	}
	@Override
	public String addBook(String title, String author, String isbn, double price, double rating) throws BMSException {
		checkDuplicate(isbn, bookList);
		Book b1 = new Book(title,author,isbn,price,rating);
		bookList.add(b1);
		
		return null;
	}

	@Override
	public void updatePrice(String isbn, double price) throws BMSException {
		Book book = bookList.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BMSException("Invalid ISBN: " + isbn));

        book.setPrice(price);
	}

	@Override
	public void searchByTitle(String title) throws BMSException {
		bookList.stream()
				.filter(p->p.getTitle().equals(title))
				.findFirst()
				.ifPresentOrElse(p->System.out.println(p), ()->{new BMSException("Title not found");});

	}

	@Override
	public void displayAllBooks() {
		for(Book b : bookList) {
			System.out.println(b);
		}

	}

	@Override
	public void displayBooksSorted() {
		Comparator<Book> comp = (b1,b2)->((Double)b1.getRating()).compareTo(b2.getRating());
		
		bookList.stream()
				.sorted(comp)
				.forEach(i->System.out.println(i));
		
	}

}
