package com.healthcare.pages;

import java.io.IOException;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
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
 * Servlet implementation class DoctorLoginServlet
 */
@WebServlet(value="/authenticateDoctor", loadOnStartup = 1)
public class DoctorLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DoctorDao doctorDao;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			System.out.println("in init of "+getClass());
			//create doctor dao instance
			doctorDao = new DoctorDaoImpl();
		}catch(Exception e) {
			throw new ServletException("errr in init of "+getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			doctorDao.cleanUp();
		}catch(Exception e) {
			throw new RuntimeException("error in destroy of "+getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//get request parameters
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			//invoke doctors signin method
			Doctor doctor = doctorDao.signin(email, password);
			
			//check if doctor is null or not->if it is not null success-> login success message
			if(doctor == null) {
				request.setAttribute("err_mesg", "invalid email or password, please retry...");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/doctor_login.jsp");
				rd.forward(request, response);
			}else {
				HttpSession session = request.getSession();
				System.out.println("Session is new "+ session.isNew());
				System.out.println("Session id "+session.getId());
				System.out.println("Doctor details - "+ session.getAttribute("doctor_details"));
				session.setAttribute("doctor_details", doctor);
				
				//redirect client to doctor dashboard page
				response.sendRedirect("doctor_dashboard");
			}
		}catch(Exception e) {
			throw new ServletException("error in do-post of "+getClass(), e);
		}
	}

}
