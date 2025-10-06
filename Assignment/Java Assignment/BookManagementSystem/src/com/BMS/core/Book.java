package com.BMS.core;

import java.util.Objects;

public class Book {
	private int id;

	private String title;

	private String author;

	private String isbn;

	private double price;

	private double rating;
	
	
	private static int idcounter = 0;

	public Book(String title, String author, String isbn, double price, double rating) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, isbn, price, rating, title);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			return this.id == ((Book)obj).getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", price=" + price
				+ ", rating=" + rating + "]";
	}
	
	
	
	
	
	
	

}
