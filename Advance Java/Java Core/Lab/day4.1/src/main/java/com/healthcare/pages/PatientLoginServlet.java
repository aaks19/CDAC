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

/**
 * Servlet implementation class PatientLoginServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class PatientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//state - final | effectively final - immutable state => inherently thread safe
	private PatientDao patientDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			System.out.println("in init "+getClass());
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
			Patient patient= patientDao.signIn(email, password);
			// -> not null=> success -> login success mesg
			if (patient == null) {
				// invalid login --> retry link -> login.html
				pw.print("<h5> Invalid Login , Please <a href='login.html'>Retry</a></h5>");

			} else {
				// successful login -> send patient details -> clnt
//				pw.print("<h5> Login Successful </h5>");
//				pw.print("<h5> Patient Details "+patient+"</h5>");
//				pw.flush();
				//redirect client -> dashboard page
				//create a cookie to store patient details
				Cookie c1=new Cookie("patient_details", patient.toString());			
				//add the cookie to resp header
				response.addCookie(c1);
				response.sendRedirect("patient_dashboard");
				/*
				 * WC sends temp redirect resp
				 * SC 302 , Location - patient_dashboard, Set-Cookie - patient_details , toString, body - empty
				 * -> cookie accepted -> web browser sends a redirect req
				 * URL - http://host:port/ctx_path/patient_dashboard
				 * Method - GET
				 * Header - Cookie - patient_details , toString
				 * -> WC PatientDashboardServlet's  class's service -> doGet ->  method
				 */
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}

	}

}
