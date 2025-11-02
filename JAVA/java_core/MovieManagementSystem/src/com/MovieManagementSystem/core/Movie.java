package com.MovieManagementSystem.core;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
	private int id;
	private String title;
	private LocalDate releaseDate;
	private MovieType movietype;
	private double rating;
	public Movie(int id, String title, LocalDate releaseDate, MovieType movietype, double rating) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.movietype = movietype;
		this.rating = rating;
	}
	public Movie(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public MovieType getMovietype() {
		return movietype;
	}
	public void setMovietype(MovieType movietype) {
		this.movietype = movietype;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, movietype, rating, releaseDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return id == other.id && movietype == other.movietype
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(releaseDate, other.releaseDate) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", movietype=" + movietype
				+ ", rating=" + rating + "]";
	}
	
	
}
