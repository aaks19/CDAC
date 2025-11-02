package com.MovieManagementSystem.Tester;

import java.util.Scanner;

import com.MovieManagementSystem.services.MovieManagementService;
import com.MovieManagementSystem.services.MovieManagementServiceImplementation;

public class ui {
	public static void main(String[] args) {

		MovieManagementService service = new MovieManagementServiceImplementation();

		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			while (!exit) {
				try {

					System.out.println("1.Add Movie\n2.Display all movie\n3.remove movie whose rating < 3.5\n"
							+ "4.display movie in sorted order of release date\n5.Display movie sorted order of movie type\n"
							+ "6.Update movie name\n7.Display movie whose rating > 5");

					switch (sc.nextInt()) {
					case 1: {
						System.out.println(
								"Enter movie id, movie title, release date, movie type(bollywood , tollywood, hollywood, rating");
						int id = sc.nextInt();
						String title = sc.next();
						String releaseDate = sc.next();
						String movieType = sc.next();
						double rating = sc.nextDouble();

						service.addMovie(id, title, releaseDate, movieType, rating);
						System.out.println("Added...");
						break;
					}
					case 2: {
						service.displayAllMovie();
						break;
					}
					case 3: {
						service.removeMovieByRating();
						System.out.println("movie removed whose rating < 3.5");
						break;
					}
					case 4: {
						service.displaySortedReleaseDate();
						break;
					}
					case 5: {
						service.displaySortedMovieType();
						break;
					}
					case 6: {
						System.out.println("Enter id and  new title");
						int id = sc.nextInt();
						String newTitle = sc.next();
						service.updateMovieTitleUsingId(id, newTitle);
						System.out.println("updated...");
						break;
					}
					case 7: {
						System.out.println("Movie whose rating > 5");
						service.displayMovieRatingGreater();
						break;
					}
					case 8: {
						System.out.println("Exit...");
						exit = true;

						break;
					}
					default:
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
