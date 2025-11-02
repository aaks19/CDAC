package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import com.healthcare.Dao.DoctorDao;
import com.healthcare.Dao.DoctorDaoImpl;
import com.healthcare.pojos.Doctors;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DoctorRegServlet
 */
@WebServlet("/register")
public class DoctorRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DoctorDao doctorDao;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			System.out.println("in init mthod of "+ getClass());
			doctorDao = new DoctorDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("error in init ", e);
			
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			doctorDao.cleanUp();
		} catch (SQLException e) {
			throw new RuntimeException("error in destroy "+getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			String doc_name = request.getParameter("name");
			String speciality = request.getParameter("speciality");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Date dob = Date.valueOf(request.getParameter("dob"));
			
			Doctors doc = new Doctors(doc_name,speciality,email,password,dob);
			
			String res = doctorDao.doctorRegister(doc);
			if(res.equals("duplicate")) {
				pw.print("<h5> duplicate email , Please <a href='index.html'>Retry</a></h5>");
			}else {
				HttpSession session = request.getSession();
				System.out.println("session is new " + session.isNew());// t
				System.out.println("Session ID " + session.getId());// unique id
				System.out.println("Doctor details - " 
				+ session.getAttribute("patient_details"));//null 
				// 2. save patient details under HttpSession
				session.setAttribute("patient_details", doc);
				// redirect client -> dashboard page
				System.out.println("<h2> registered success</h2>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
