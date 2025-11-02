package com.healthcare.pages;

import java.io.IOException;
import java.util.List;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.pojos.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PatientDashboardServlet
 */
@WebServlet(value = "/patient_dash", loadOnStartup = 2)
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
			// 1. get HttpSession from WC, returns null in case of no existing session
			HttpSession hs = request.getSession(false);
			if (hs != null) {
				// 2. get patient details from HttpSession
				Patient patient = (Patient) hs.getAttribute("patient_details");
				// 3. invoke dao's method - to get the list
				List<AppointmentDTO> allUpcomingAppoints = appointmentDao.listAllUpcomingAppoints(patient.getId());
				request.setAttribute("appointments", allUpcomingAppoints);
				request.getRequestDispatcher("/WEB-INF/views/patient_dashboard.jsp").forward(request, response);

			} else {
				// => no session found -> redirect the client to the login page, with error mesg
				response.sendRedirect(
						request.getContextPath() + "?message=Login again , after accepting the cookies....");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-get " + getClass(), e);
		}
	}

}
