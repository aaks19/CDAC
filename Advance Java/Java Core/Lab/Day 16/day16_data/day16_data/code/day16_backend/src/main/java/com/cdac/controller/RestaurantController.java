package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entities.Restaurant;
import com.cdac.services.RestaurantServices;

@RestController // @Controller + @ResponseBody
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantServices restaurantServices;
	
	public RestaurantController() {
		System.out.println("in constructor of "+getClass());
	}
	
	// List all the restaurants having status = true
	
	// url - http://locathost:8080/restaurants , method - GET
	@GetMapping
	public ResponseEntity<?> listAllRestaurants(){
		System.out.println("availabe restaurant");
		List<Restaurant> list = restaurantServices.listAllRestaurants();
		if(list.isEmpty()) {
			//statuc code : 204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		//non empty list
		//status code 200, response body - list
		return ResponseEntity.ok(list);
	}
	
	
	/*
	 * Add new Restaurant
	 * URL : http://host:port/restaurants
	 * method : POST
	 * response :
	 * in case of success: status code : 201 and message
	 * in case of failure: status code : 400 and message
	 */
	@PostMapping
	public ResponseEntity<?> addNewRestaurant(@RequestBody Restaurant newRestaurant){
		System.out.println("in add new restaurant"+newRestaurant);
		try {
			// invoke service layer method
			String message = restaurantServices.addRestaurant(newRestaurant);
			// success
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		} catch (RuntimeException e) {
			System.out.println("error "+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	/*
	 * Soft delete existing Restaurant
	 * URL : http://host:port/restaurants/{restaurantId}
	 * method : DELETE
	 * response :
	 * in case of success: status code - 200 , message - deleted successfully
	 * in case of failure: status code : 404
	 */
	
	@DeleteMapping("/{restaurantId}")  // "/{restaurantId}" it is known as template uri variable
	public ResponseEntity<?> deleteExistingRestaurant(@PathVariable Long restaurantId){
		System.out.println("in delete restaurant"+ restaurantId);
		
		try {
			return ResponseEntity.ok(restaurantServices.deleteDetails(restaurantId));
			
		} catch (RuntimeException e) {
			System.out.println("error "+e);
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	/*
	 * Update restaurant details
	 * URL : http://host:port/restaurants/{restaurantId}
	 * method : PUT
	 * response :
	 * in case of success: status code - 200 , message - updated successfully
	 * in case of failure: status code : 404
	 */
	@PutMapping("/{restaurantId")
	public ResponseEntity<?> updateRestaurantDetail(@PathVariable Long restaurantId, @RequestBody Restaurant updateRestaurant){
		System.out.println("in update with resturant id = "+restaurantId);
		
		try {
			return ResponseEntity.ok(restaurantServices.updateDetails(restaurantId,updateRestaurant));
		} catch (RuntimeException e) {
			System.out.println("error : "+e);
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
