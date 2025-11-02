package com.MovieManagementSystem.services;

import static com.MovieManagementSystem.services.MovieManagementValidation.validateAll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.MovieManagementSystem.Exception.MovieManagementException;
import com.MovieManagementSystem.core.Movie;

public class MovieManagementServiceImplementation implements MovieManagementService {

	private List<Movie> movieList;
	
	public MovieManagementServiceImplementation() {
		this.movieList = new ArrayList<>();
	}
	@Override
	public void addMovie(int id, String title, String releaseDate, String movietype, double rating)
			throws MovieManagementException {
		Movie m = validateAll(id, title, releaseDate, movietype, rating, movieList);
		
		movieList.add(m);

	}

	@Override
	public void displayAllMovie() {
		movieList.stream()
				 .forEach(m->System.out.println(m));

	}

	@Override
	public void removeMovieByRating() throws MovieManagementException {
		movieList.removeIf(m->m.getRating()<3.5);

	}

	@Override
	public void displaySortedReleaseDate() {
		Comparator<Movie> comp = (m1,m2)->m2.getReleaseDate().compareTo(m1.getReleaseDate());
		movieList.stream()
				 .sorted(comp)
				 .forEach(m->System.out.println(m));
	}

	@Override
	public void displaySortedMovieType() {
		Comparator<Movie> comp = (m1,m2)-> m1.getMovietype().compareTo(m2.getMovietype());
		
		movieList.stream()
				 .sorted(comp)
				 .forEach(m->System.out.println(m));

	}

	@Override
	public void updateMovieTitleUsingId(int id, String title) throws MovieManagementException {
		movieList.stream()
				 .filter(m->m.getId() == id)
				 .forEach(m->m.setTitle(title));

	}
	@Override
	public void displayMovieRatingGreater() {
		movieList.stream()
				 .filter(m->m.getRating()>5)
				 .forEach(m->System.out.println(m));
		
	}

}
