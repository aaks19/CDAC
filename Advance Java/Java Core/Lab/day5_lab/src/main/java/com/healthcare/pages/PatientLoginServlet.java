package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;
import com.healthcare.pojos.Patient;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PatientLoginServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class PatientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// state - final | effectively final - immutable state => inherently thread safe
	private PatientDao patientDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			System.out.println("in init " + getClass());
			// create patient dao instance
			patientDao = new PatientDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		try {
			// patient dao instance - clean up
			patientDao.cleanUp();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException("err in destroy " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type ,
		response.setContentType("text/html");
		// get pw
		try (PrintWriter pw = response.getWriter()) {
			// get rq params - email , password
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// invoke dao's sign in method
			Patient patient = patientDao.signIn(email, password);
			// -> not null=> success -> login success mesg
			if (patient == null) {
				// invalid login --> retry link -> login.html
				pw.print("<h5> Invalid Login , Please <a href='login.html'>Retry</a></h5>");

			} else {
				// 1. Get HttpSession from WC
				HttpSession session = request.getSession();
				System.out.println("session is new " + session.isNew());// t
				System.out.println("Session ID " + session.getId());// unique id
				System.out.println("patient details - " 
				+ session.getAttribute("patient_details"));//null 
				// 2. save patient details under HttpSession
				session.setAttribute("patient_details", patient);
				// redirect client -> dashboard page
				response.sendRedirect("patient_dashboard");
				/*
				 * WC -> sends temp redirect resp (clrs resp buffer)				 * 
				 * SC 302 , 
				 * Headers 
				 *  - Location - patient_dashboard
				 *  - Set-Cookie - JSESSIONID : ASDSFDFG13485748SDF
				 *  Body
				 *   - empty
				 */

			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}

	}

}
