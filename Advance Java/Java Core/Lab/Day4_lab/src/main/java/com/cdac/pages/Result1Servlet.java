package com.cdac.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Result1Servlet
 */
@WebServlet("/result1")
public class Result1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				pw.write("<h2>Result>0</h2>");
				pw.write("<h3> result = "+cookies[0].getValue()+"</h3>");
			}else {
				pw.write("<h5> cookies not found</h5>");
			}
//			pw.write("<h2> hello from "+ getClass()+"</h2>");
		}
	}

}
