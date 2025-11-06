package com.cdac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.customException.ResourseAlreadyExists;
import com.cdac.customException.ResourseNotFound;
import com.cdac.dao.RestaurantDao;
import com.cdac.entities.Restaurant;


@Service
@Transactional
public class RestaurantServicesImpl implements RestaurantServices {
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Override
	public List<Restaurant> listAllRestaurants() {	
		return restaurantDao.findByStatusTrue();
	}

	@Override
	public String addRestaurant(Restaurant newRestaurant) {
		// Check ifthe restaurant with the same name already exists or not.
		if(restaurantDao.existsByName(newRestaurant.getName())) {
			//duplicate restaurant name -> throw custom exception : unchecked exception
			throw new ResourseAlreadyExists("Restaurant with the same name already exists");
		}
		// restaurant name is new , set the status to true
		newRestaurant.setStatus(true);
		
		//save the details
		Restaurant persistentRestaurant = restaurantDao.save(newRestaurant);
		
		return "Added new restaurant whit id ="+persistentRestaurant.getId();
	}

	
	@Override
	public String deleteDetails(Long restaurantId) {
		//validate id
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(() -> new ResourseNotFound("Id not found..."));
		
		//restaurant - persistent
		//setter - status = false
		restaurant.setStatus(false);
		return "Soft deleted restaurant details...";
	}//no exception -> tx.commit -> DML  - Update -> session close

	
	
	@Override
	public String updateDetails(Long restaurantId, Restaurant updateRestaurant) {

		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResourseNotFound("Restaurant not found"));
		
		// update the restaurant details
		
		return null;
	}

}
