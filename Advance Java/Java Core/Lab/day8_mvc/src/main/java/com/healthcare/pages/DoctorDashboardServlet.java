package com.healthcare.pages;

import java.io.IOException;
import java.util.List;

import com.healthcare.dao.DoctorAppointmentDao;
import com.healthcare.dao.DoctorAppointmentDaoImpl;
import com.healthcare.dto.DoctorAppointmentDto;
import com.healthcare.pojos.Doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DoctorDashboardServlet
 */
@WebServlet(value = "/doctor_dashboard", loadOnStartup = 2)
public class DoctorDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DoctorAppointmentDao doctorAppointmentDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			doctorAppointmentDao = new DoctorAppointmentDaoImpl();
		} catch (Exception e) {
			// to inform WC - about init's failure
			throw new ServletException("err in init " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			doctorAppointmentDao.cleanUp();
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
			HttpSession hs = request.getSession(false);
			if (hs != null) {
				System.out.println("session is new " + hs.isNew());// false
				System.out.println("Session ID " + hs.getId());// same id for the same clnt

				// 2. get patient details from HttpSession
				Doctor doctor = (Doctor) hs.getAttribute("doctor_details");

				String message = (String) hs.getAttribute("mesg");
				if (message != null) {
					hs.removeAttribute("mesg");
				}
				// 3. invoke dao's method - to get the list
				List<DoctorAppointmentDto> allUpcomingAppoints = DoctorAppointmentDao.listAllAppointments(doctor.getId());
				// add appoinment list under request scope
				request.setAttribute("appointment_list", allUpcomingAppoints);
				// forward the client to view layer - patient_dashboard.jsp
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/patient_dashboard.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("/day8_mvc/");
			}

		} catch (Exception e) {
			throw new ServletException("error in do-get of " + getClass(), e);
		}
	}

}
