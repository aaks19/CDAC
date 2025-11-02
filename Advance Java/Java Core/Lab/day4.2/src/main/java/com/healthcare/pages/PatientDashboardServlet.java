package com.healthcare.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.healthcare.pojos.Patient;

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
			// 1. get HttpSession from WC
			HttpSession hs = request.getSession();
			// 2. get patient details from HttpSession
			Patient patient = (Patient) hs.getAttribute("patient_details");
			if (patient != null) {
				pw.print("<h5> Patient details from HttpSession " + patient + "</h5>");
			} else {
				pw.print("<h5> You will have to accept the cookies , to continue....</h5>");
			}
		}
	}

}
