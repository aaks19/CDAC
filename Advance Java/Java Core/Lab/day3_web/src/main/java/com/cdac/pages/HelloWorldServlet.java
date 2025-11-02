package com.cdac.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value="/hello",loadOnStartup = 2)
/*
 * Add class level run time annotation.
 * Who reads it & when - WC @ web app deployment time
 * Meaning - To map incoming request ending in url-pattern /hello with HelloWorldServlet.
 * URL - http://host:port/day3_web/hello
 * WC creates an empty  HashMap - at the app startup
 * key - url -pattern (eg /hello) : value of annotation
 * value - F.Q servlet class name (com.cdac.pages.HelloWorldServlet)
  */
public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-get");
		//1. set response content type
		/*
		 * Method of ServletResponse
		 * public void setContentType(String type)
		 * 
		 */
		resp.setContentType("text/html");
		//2. Get Writer stream , to write text(HTML) resp to the client
		/*
		 * Method of ServletResponse
		 * public PrintWriter getWriter() throws IOException
		 * To send binary data
		 * Method of ServletResponse
		 * public ServletOutputStream getOutputStream() throws IOException
		 */
		try(PrintWriter pw=resp.getWriter()) {
			//3. send resp - print / write
			pw.print("<h5> Welcome 2 Servlets, Server TS "+LocalDateTime.now()+"</h5>");
		}//JVM - pw.close() -> resp is rendered (i.e sent) to clnt.
		
	}

	@Override
	public void destroy() {
		System.out.println("in destroy");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("in init of  "+getClass());
	}

}
