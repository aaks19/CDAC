package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;

import com.app.core.Course;
import com.app.core.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessAdmissionForm
 */
@WebServlet("/process_admission")
public class ProcessAdmissionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// 1. get request parameter n parse
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			int score = Integer.parseInt(request.getParameter("score"));
			Course chosenCourse = Course.valueOf(request.getParameter("course").toUpperCase());
			// 2. Encapsulate it in student object
			Student s1 = new Student(firstName, lastName, score, chosenCourse);
			// 3 B.L
			if (score >= chosenCourse.getMinScore())
				s1.setAdmissionStatus(true);
			// 4. save student details under (min) request scope
			request.setAttribute("student_details", s1);
			pw.print("from 1st page....");
			pw.flush();
			// 5 Get RequestDispatcher - to chain the same req it to next page
			RequestDispatcher rd = request.getRequestDispatcher("result");
			rd.include(request, response);
			System.out.println("control comes back....");
		}
	}

}
