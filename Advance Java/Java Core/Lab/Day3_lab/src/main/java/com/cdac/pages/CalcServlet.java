package com.cdac.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/calculate")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			String op = request.getParameter("action");
			double result = 0;
			
			switch (op) {
			case "add": {
				result = num1+num2;
				break;
			}
			
			case "subtract": {
				result = num1-num2;
				break;
			}
			
			case "multiply": {
				result = num1*num2;
				break;
			}
			
			case "divide": {
				result = num1/num2;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + op);
			}
			
			pw.print("<h5> result of " + op + " is = "+result+"</h5>");
		}
	}

}
