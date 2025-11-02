package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;

import com.app.core.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Result Page
 */
@WebServlet("/result")
public class AdmissionResultPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			System.out.println("in do-post of " + getClass());
			// 1. get student details from request scope
			Student s = (Student) request.getAttribute("student_details");
			pw.print("<h5>Hello ," + s.getFirstName() + " " + s.getLastName() + "</h5>");
			if (s.isAdmissionStatus()) {
				pw.print("<h5> Congratulations ! , You are admitted in the Course " + s.getSelectedCourse() + "</h5>");
			} else {
				pw.print("<h5>Sorry ! , You can't be admitted in the Course " + s.getSelectedCourse() + "</h5>");
			}
		}
	}

}
