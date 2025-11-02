package com.healthcare.dao;

import com.healthcare.entities.User;

public interface UserDao {
	//add a method to register new user
	String signup(User newUser);
}
