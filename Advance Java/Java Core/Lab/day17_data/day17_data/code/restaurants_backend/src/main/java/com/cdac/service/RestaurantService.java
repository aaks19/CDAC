package com.cdac.service;

import java.util.List;

import com.cdac.dto.ApiResponse;
import com.cdac.entities.Restaurant;

public interface RestaurantService {
	List<Restaurant> getAllAvailableRestaurants();

	String addRestaurant(Restaurant newRestaurant);

	String deleteDetails(Long restaurantId);

	Restaurant getRestaurantDetails(Long restaurantId);

	ApiResponse updateDetails(Long restaurantId, Restaurant restaurant);
}
