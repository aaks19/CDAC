package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(value="/patient_dashboard",loadOnStartup = 2)
public class PatientDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appointmentDao;
	

	@Override
	public void init() throws ServletException {
		
		try {
			appointmentDao=new AppointmentDaoImpl();
		} catch (Exception e) {
			//to inform WC - about init's failure
			throw new ServletException("err in init "+getClass(), e);
		}
	}
	@Override
	public void destroy() {
		try {
		appointmentDao.cleanUp();
		} catch (Exception e) {
			throw new RuntimeException("err in destroy "+getClass(), e);
		}
	}


	


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
			
			System.out.println("session is new "+hs.isNew());//f
			System.out.println("Session ID "+hs.getId());//same id for the same clnt

			// 2. get patient details from HttpSession
			Patient patient = (Patient) hs.getAttribute("patient_details");
			if (patient != null) {
				String message=(String) hs.getAttribute("mesg");
				if(message != null)
				{
					pw.print("<h4> "+message+"</h4>");
					hs.removeAttribute("mesg");
				}
				pw.print("<h5>Hello ,  " + patient.getName() + "</h5>");
				//3. invoke dao's method - to get the list
				List<AppointmentDTO> allUpcomingAppoints = appointmentDao.listAllUpcomingAppoints(patient.getId());
				//render the list to the clnt
				allUpcomingAppoints.forEach(dto -> {
					pw.print("<h5> "+dto.getAppointmentId()+"  "+dto.getAppointmentTS()+" "+dto.getDocName()+"   ");
					//add cancel button/link
					pw.print("<a href='appointments?action=cancel&id="+dto.getAppointmentId()+"'><button>Cancel</button></a></h5>");
				});
				pw.print("<h5> <a href='book_appointment.html'>Book Appointment</a> ");
				pw.print(" <a href='logout'>Log Out</a></h5>");

			} else {
				pw.print("<h5> You will have to accept the cookies , to continue....</h5>");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-get "+getClass(), e);
		}
	}

}
