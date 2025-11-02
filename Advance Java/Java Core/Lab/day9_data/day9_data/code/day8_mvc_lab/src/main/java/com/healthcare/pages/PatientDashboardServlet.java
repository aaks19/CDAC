package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.pojos.Patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PatientDashboardServlet
 */
@WebServlet(value = "/patient_dashboard", loadOnStartup = 2)
public class PatientDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appointmentDao;

	@Override
	public void init() throws ServletException {

		try {
			appointmentDao = new AppointmentDaoImpl();
		} catch (Exception e) {
			// to inform WC - about init's failure
			throw new ServletException("err in init " + getClass(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			appointmentDao.cleanUp();
		} catch (Exception e) {
			throw new RuntimeException("err in destroy " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			/*
			 * 1. get HttpSession from WC - WC returns null - in case of no HttpSession
			 * founds otherwise rets EXISTING HttpSession .
			 */
			HttpSession hs = request.getSession(false);
			if (hs != null) {
				System.out.println("session is new " + hs.isNew());// false
				System.out.println("Session ID " + hs.getId());// same id for the same clnt
				// 2. get patient details from HttpSession
				Patient patient = (Patient) hs.getAttribute("patient_details");
				// 3. invoke dao's method - to get the list
				List<AppointmentDTO> allUpcomingAppoints = appointmentDao.listAllUpcomingAppoints(patient.getId());
				// add appoinment list under request scope
				request.setAttribute("appointment_list", allUpcomingAppoints);
				// forward the client to view layer - patient_dashboard.jsp
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/patient_dashboard.jsp");
				rd.forward(request, response);
			} else {
				// => no session found -> redirect the client to the login page, with error
				// message

				response.sendRedirect(request.getContextPath()+"?message=Login again , after accepting the cookies....");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-get " + getClass(), e);
		}
	}

}
