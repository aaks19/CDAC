package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant,Long> {
//add a finder method to list all AVAILABLE (status=true) restaurants
	List<Restaurant> findByStatusTrue();
	//check if restaurant with same name already exists ! - derived query
	boolean existsByName(String restaurantName);
	
}
