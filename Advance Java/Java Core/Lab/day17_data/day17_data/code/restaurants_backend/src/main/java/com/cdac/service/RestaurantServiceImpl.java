package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.custom_excpetions.ResourceAlreadyExists;
import com.cdac.custom_excpetions.ResourceNotFoundException;
import com.cdac.dao.RestaurantDao;
import com.cdac.dto.ApiResponse;
import com.cdac.entities.Restaurant;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	// depcy - dao i/f
	@Autowired
	private RestaurantDao restaurantDao;

	@Override
	public List<Restaurant> getAllAvailableRestaurants() {
		// TODO Auto-generated method stub
		return restaurantDao.findByStatusTrue();
	}

	@Override
	public String addRestaurant(Restaurant newRestaurant) {
		// check if restaurant with same name already exists !
		if (restaurantDao.existsByName(newRestaurant.getName())) {
			// dup restaurant name -> throw custom exception : unchecked
			throw new ResourceAlreadyExists("Restaurant with same name already exists !!!");
		}
		// => new restaurant name -> set status : true
		newRestaurant.setStatus(true);
		// save the details
		Restaurant persistentRestaurant = restaurantDao.save(newRestaurant);
		return "Added new Restaurant with ID=" + persistentRestaurant.getId();
	}

	@Override
	public String deleteDetails(Long restaurantId) {
		//validate id
		Restaurant restaurant=restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid restaurant id !!!!!"));
		//=> restaurant : persistent
		//setter - status : false
		restaurant.setStatus(false);
		return "Soft deleted restaurant details....";
	} //no exc -> tx.commit-> DML - update -> session close

	@Override
	public Restaurant getRestaurantDetails(Long restaurantId) {
		// TODO Auto-generated method stub
		return restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant id invalid !!!!"));
	}

	@Override
	public ApiResponse updateDetails(Long restaurantId, Restaurant restaurant) {
		Restaurant existingRestuarant=getRestaurantDetails(restaurantId);
		//=> restaurant exists , existingRestuarant : persistent
		//invoke setters
		existingRestuarant.setName(restaurant.getName());
		existingRestuarant.setAddress(restaurant.getAddress());
		existingRestuarant.setCity(restaurant.getCity());
	//	existingRestuarant.setStatus(restaurant.isStatus());
		existingRestuarant.setDescription(restaurant.getDescription());		
		return new ApiResponse("Restaurant Details Updated ...", "Success");
	}
	
	
	

}
