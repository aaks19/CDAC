package com.LMS.core;

import java.util.Objects;

public class Books {
	private int bookId;
	private String title;
	private String author;
	private double price;
	private boolean isAvailable;
	
	private static int idCounter;

	public Books(String title, String author, double price, boolean isAvailable) {
		super();
		this.bookId = ++idCounter;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, bookId, isAvailable, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Books other = (Books) obj;
		return Objects.equals(author, other.author) && bookId == other.bookId && isAvailable == other.isAvailable
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price
				+ ", isAvailable=" + isAvailable + "]";
	}
	
	
	
	
}
