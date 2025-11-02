package com.healthcare.pages;

import java.io.IOException;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatientLogin
 */
@WebServlet(value="/authenticate",loadOnStartup = 1)
public class PatientLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//create patient dao instance
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		//patient dao instance clean up
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set content type , get pw
		// get request parameter: email, password
		//invoke dao's sign-in method -> not null=> success->login success message
		//send patient details
		//null -> retry Link -> login form.
		
	}

}
