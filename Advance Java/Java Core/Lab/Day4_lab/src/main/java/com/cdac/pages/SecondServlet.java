package com.cdac.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
/*
 * Servlet implementation class FirstServlet WC creates a map of req mapping
 * key: /first value: com.cdac.pages.FirstServlet
 */
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init " + getClass());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy " + getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-get of "+getClass());
		// 1. set response content type
		response.setContentType("text/html");

		// 2. get writer to send text respond
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h2> Hello from " + getClass() + "at " + LocalTime.now() + "</h2>");
		} // JVM - out.close() - response is sent to client
	}

}
