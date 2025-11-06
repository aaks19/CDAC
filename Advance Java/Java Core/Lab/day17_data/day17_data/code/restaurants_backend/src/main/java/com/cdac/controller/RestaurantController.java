package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.entities.Restaurant;
import com.cdac.service.RestaurantService;

@RestController // @Controller + @ResponseBody
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	public RestaurantController() {
		System.out.println("in ctor of " + getClass());
	}

	/*
	 * URL - http://host:port/restaurants , method=GET payload - none Resp - List of
	 * Restaurants -> Json[] -> clnt
	 */
	@GetMapping
	public ResponseEntity<?> getAvailableRestaurants() {
		System.out.println("in get avlable restaurants");
		List<Restaurant> list = restaurantService.getAllAvailableRestaurants();
		if (list.isEmpty()) {
			// sts code SC 204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			// SC 204
		}
		// non empty
		return ResponseEntity.ok(list);
		// SC 200 , body - list
	}

	/*
	 * Desc - add new restaurant URL - http://host:port/restaurants Method - POST
	 * Resp -success - SC 201 + success mesg 
	 * SC 400 + err mesg
	 */
	@PostMapping
	public ResponseEntity<?> addNewRestaurant(@RequestBody Restaurant newRestaurant) {
		System.out.println("in add new restaurant " + newRestaurant);
		try {
			// invoke service layer method
			String message = restaurantService.addRestaurant(newRestaurant);
			//=> success
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(message);
		} catch (RuntimeException e) {
			System.out.println("err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
	}
	/*
	 * Desc -Soft delete  existing  restaurant .
	 * URL - http://host:port/restaurants/{restaurantId}    Method - DELETE
	 * Resp - success - SC 200 + success mesg 
	 * Failure - SC 404 
	 */
	@DeleteMapping("/{restaurantId}")
	public ResponseEntity<?> deleteRestaurantDetails(@PathVariable Long restaurantId)
	{
		System.out.println("in delete details "+restaurantId);
		try {
		return ResponseEntity.ok(restaurantService.deleteDetails(restaurantId));
		} catch (RuntimeException e) {
			System.out.println("err "+e);
			return ResponseEntity.notFound().build();
		}
		
	}
	/*
	 *
	 * 1. Get Existing restaurant details
	 * URL - http://host:port/restaurants/{restaurantId}    
	 * Method - GET
	 * Resp - success - SC 200 + Restaurant -> JSON
	 * Failure - SC 404 + API Response DTO
	 */
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getRestaurantDetails(@PathVariable Long restaurantId)
	{
		System.out.println("in get restaurant details "+restaurantId);
		try {
			return ResponseEntity.ok(restaurantService.getRestaurantDetails(restaurantId));
		} catch (RuntimeException e) {
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), "failed"));
		}
	}
	/*
	 *
	 * 2. Update Existing restaurant details
	 * URL - http://host:port/restaurants/{restaurantId}    
	 * Method - PUT
	 * path var - restaurantId (validation)
	 * Payload -updated restaurant details
	 * Resp - success - SC 200 +  API Response DTO - success
	 * Failure - SC 404 | 400  + API Response DTO
	 */
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> updateRestaurantDetails(@PathVariable Long restaurantId,@RequestBody Restaurant restaurant)
	{
		System.out.println("in update "+restaurantId+" "+restaurant);
		try {
			return ResponseEntity.ok(restaurantService.updateDetails(restaurantId,restaurant));
		} catch (RuntimeException e) {
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), "failed"));
		}
	}

}
