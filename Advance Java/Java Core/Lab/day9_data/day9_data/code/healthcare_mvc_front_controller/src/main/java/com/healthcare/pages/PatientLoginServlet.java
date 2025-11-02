package com.healthcare.pages;

import java.io.IOException;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;
import com.healthcare.pojos.Patient;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PatientLoginServlet
 */
@WebServlet(value = "/auth", loadOnStartup = 1)
public class PatientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		try {
			// get rq params - email , password
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// invoke dao's sign in method
			Patient patient = patientDao.signIn(email, password);
			// -> not null=> success -> login success mesg
			if (patient == null) {
				// invalid login -->error message & forward to JSP with error mesg
				request.setAttribute("err_mesg", "Invalid Email or Password !");
				request.getRequestDispatcher("/WEB-INF/views/patient_login.jsp").forward(request, response);
			} else {
				// 1. Get HttpSession from WC
				HttpSession session = request.getSession();
				// 2. save patient details under HttpSession
				session.setAttribute("patient_details", patient);
				// redirect client -> dash board page
				response.sendRedirect("patient_dashboard");
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}

	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doPost(request, response);
//	}

}
