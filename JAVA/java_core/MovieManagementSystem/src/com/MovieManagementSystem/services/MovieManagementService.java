package com.MovieManagementSystem.services;

import com.MovieManagementSystem.Exception.MovieManagementException;

public interface MovieManagementService {

	void addMovie(int id, String title, String releaseDate, String movietype, double rating)throws MovieManagementException;
	
	void displayAllMovie();
	
	void removeMovieByRating() throws MovieManagementException;
	
	void displaySortedReleaseDate();
	
	void displaySortedMovieType();
	
	void updateMovieTitleUsingId(int id, String title) throws MovieManagementException;
	
	void displayMovieRatingGreater();
	
}
