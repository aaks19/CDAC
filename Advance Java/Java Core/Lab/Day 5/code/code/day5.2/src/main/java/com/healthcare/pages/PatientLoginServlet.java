package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;
import com.healthcare.pojos.Patient;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatientLoginServlet
 */

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
			//get servlet config
			ServletConfig config = getServletConfig();
			//get init params
			String dbURL=config.getInitParameter("url");
			String userName=config.getInitParameter("user_name");
			String pwd=config.getInitParameter("password");
			// create patient dao instance
			patientDao = new PatientDaoImpl(dbURL,userName,pwd);
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
				// successful login -> send patient details -> clnt
				pw.print("<h5> Login Successful </h5>");
				pw.print("<h5> Patient Details " + patient + "</h5>");

			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}

	}

}
