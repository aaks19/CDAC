package com.MovieManagementSystem.services;

import java.time.LocalDate;
import java.util.List;

import com.MovieManagementSystem.Exception.MovieManagementException;
import com.MovieManagementSystem.core.Movie;
import com.MovieManagementSystem.core.MovieType;

public class MovieManagementValidation {

	public static void checkDuplicateTitle(String title, List<Movie> movieList) throws MovieManagementException {
		boolean exist = movieList.stream()
								 .anyMatch(m->m.getTitle().equals(title));
		if(exist) {
			throw new MovieManagementException("Duplicate title");
		}
	}

	public static MovieType checkMovieType(String movietype) throws IllegalArgumentException {
		MovieType mType = MovieType.valueOf(movietype.toUpperCase());

		return mType;
	}

	public static LocalDate checkReleaseDate(String releasedate)
			throws MovieManagementException, IllegalArgumentException {
		LocalDate releaseDate = LocalDate.parse(releasedate);

		if (!releaseDate.isBefore(LocalDate.now())) {
			throw new MovieManagementException("Movie Release date should be before the current date");
		}

		return releaseDate;
	}

	public static Movie validateAll(int id, String title, String releaseDate, String movietype, double rating,
			List<Movie> movieList) throws MovieManagementException {
		checkDuplicateTitle(title, movieList);
		MovieType m_type = checkMovieType(movietype);
		LocalDate r_date = checkReleaseDate(releaseDate);

		return new Movie(id, title, r_date, m_type, rating);
	}

}
