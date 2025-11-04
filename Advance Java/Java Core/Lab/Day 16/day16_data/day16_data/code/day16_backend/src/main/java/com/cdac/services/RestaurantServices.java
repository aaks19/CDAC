package com.cdac.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.entities.Restaurant;

public interface RestaurantServices{
	List<Restaurant> listAllRestaurants();

	String addRestaurant(Restaurant newRestaurant);

	String deleteDetails(Long restaurantId);
}
