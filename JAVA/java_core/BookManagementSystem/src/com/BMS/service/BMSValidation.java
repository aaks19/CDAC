package com.BMS.service;

import java.util.List;

import com.BMS.Exception.BMSException;
import com.BMS.core.Book;

public class BMSValidation {
	public static void checkDuplicate(String isbn, List<Book> bookList) throws BMSException {
        boolean exists = bookList.stream()
                                 .anyMatch(b -> b.getIsbn().equals(isbn));
        if (exists) {
            throw new BMSException("Book with ISBN " + isbn + " already exists!");
        }
    }
}
