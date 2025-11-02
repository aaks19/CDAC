package com.healthcare.pages;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.pojos.Doctor;
import com.healthcare.pojos.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AppointmentsServlet
 */
@WebServlet(value = "/patient_appointments", loadOnStartup = 3)
public class AppointmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appointmentDao;
	private DoctorDao doctorDao;

	@Override
	public void init() throws ServletException {
		try {
			appointmentDao = new AppointmentDaoImpl();
			doctorDao = new DoctorDaoImpl();
		} catch (Exception e) {
			// to inform WC - about init's failure
			throw new ServletException("err in init " + getClass(), e);
		}
	}

	@Override
	public void destroy() {
		try {
			appointmentDao.cleanUp();
			doctorDao.cleanUp();
		} catch (Exception e) {
			throw new RuntimeException("err in destroy " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// control comes here after submitting book appointment form.
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// check action
			String action = req.getParameter("action");
			if (action.equals("show_book_form")) {
				// get doctor list & add it as request scoped attribute
				List<Doctor> doctors = doctorDao.findAll();
				req.setAttribute("doctors", doctors);
				req.getRequestDispatcher("/WEB-INF/views/book_appointment.jsp").forward(req, resp);
				System.out.println("show book appointment form");
			} else if (action.equals("cancel")) {
				cancelAppointment(req, resp);
				System.out.println("cancel appointment");
			} else if (action.equals("book")) {
				bookAppointment(req, resp);
				System.out.println("book appointment");
			}
		} catch (Exception e) {
			throw new ServletException("err in do post " + getClass(), e);
		}

	}

	private void cancelAppointment(HttpServletRequest request, HttpServletResponse resp)
			throws SQLException, IOException {
		// read req params
		int appointmentId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession(false);
		if (session != null) {
			int patientId = ((Patient) session.getAttribute("patient_details")).getId();
			// call dao's method
			String mesg = appointmentDao.cancelAppointment(appointmentId, patientId);
			session.setAttribute("mesg", mesg);
			resp.sendRedirect("patient_dashboard");
		} else {
			// => no session found -> redirect the client to the login page, with error mesg
			resp.sendRedirect(request.getContextPath() + "?message=Login again , after accepting the cookies....");
		}
	}

	private void bookAppointment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		// get session from WC
		HttpSession session = request.getSession(false);
		if (session != null) {
			Patient patient = (Patient) session.getAttribute("patient_details");
			int doctorId = Integer.parseInt(request.getParameter("doc_id"));
			String timeStamp = request.getParameter("appointment_ts");
			Timestamp ts = Timestamp.valueOf(LocalDateTime.parse(timeStamp));
			// book appointment- call dao's method
			String message = appointmentDao.bookAppointment(doctorId, patient.getId(), ts);
			if (message.contains("un available")) {
				// doctor un available -> redirect the client to book appoint form again , with
				// error message
				response.sendRedirect(request.getContextPath()
						+ "/appointments?action=show_book_form&err_mesg=Appoinment un available , Try another Date/Time");

			} else {
				// add session scoped attribute
				session.setAttribute("mesg", message);
				response.sendRedirect("patient_dashboard");
			}

		} else {
			// => no session found -> redirect the client to the login page, with error mesg
			response.sendRedirect(request.getContextPath() + "?message=Login again , after accepting the cookies....");
		}
	}

}
