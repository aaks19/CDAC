package com.healthcare.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class PatientDashboardServlet
 */
@WebServlet("/patient_dashboard")
public class PatientDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4> Welcome to Patient Dashboard....</h4>");
			// get cookies from client , req header
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				
				pw.print("<h5> patient Details accessed from cookie ," +cookies[0].getValue()+ "</h5>");
			} else
			{
				pw.print("<h5> You will have to accept the cookies , to continue....</h5>");
			}
		}
	}

}
