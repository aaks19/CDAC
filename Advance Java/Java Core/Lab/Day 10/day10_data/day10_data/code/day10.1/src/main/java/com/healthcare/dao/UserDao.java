package com.healthcare.dao;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public interface UserDao {
//add a method to register(sign up) new user
	String signUp(User newUser);

	// add a method to get user details by id
	User getUserDetailsById(Long userId);

	List<User> getAllUsers();

	List<User> getSelectedUsers(UserRole role, LocalDate date);

	List<String> getSelectedUsersLastName(UserRole role);

	List<User> getSelectedDetailsByRole(UserRole role);

	String changePassword(String email, String oldPassword, String newPass);

	String applyDiscount(LocalDate localDate, UserRole role, int discountAmount);

	String deleteUserDetailsById(Long userId);
}
