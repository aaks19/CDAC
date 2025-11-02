package com.healthcare.Dao;

import java.sql.SQLException;

import com.healthcare.pojos.Patient;

public interface PatientDao extends BaseDao{
	Patient signIn(String email, String password) throws SQLException;

	

}
