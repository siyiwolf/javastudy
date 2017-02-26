package com.find.usr.pg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	UsrManager usrManager = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uName = req.getParameter("uname");
		String uPassword = req.getParameter("password");
		System.out.println(uName + "+" + uPassword);
		
		if (usrManager == null)
		{
			return;
		}
		
		if (usrManager.logVerity(uName, uPassword))
		{
			resp.sendRedirect("loginSuccess.html");
		}
		else
		{
			resp.sendRedirect("loginFail.html");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	public void init() throws ServletException {
		usrManager = UsrManager.getInstance();
		System.out.println("Login servlet get the data manage tool!");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Login Servlet Destory");
		super.destroy();
	}

	
	
}
