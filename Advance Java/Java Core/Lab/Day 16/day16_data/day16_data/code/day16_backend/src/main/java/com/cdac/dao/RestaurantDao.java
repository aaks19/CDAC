package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, Long> {
	List<Restaurant> findByStatusTrue();
	
	//check if restaurant with same name exists or not,  derived query
	boolean existsByName(String restaurantName);
}
