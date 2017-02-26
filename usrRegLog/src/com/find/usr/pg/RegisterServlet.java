package com.find.usr.pg;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	private UsrManager usrManager = null;
	
	@Override
	public void init() throws ServletException {
		usrManager = UsrManager.getInstance();
		System.out.println("Register get data manage tool!!");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uName = req.getParameter("uname");
		String uPassword = req.getParameter("password");
		System.out.println(uName + "+" + uPassword);
		if (usrManager == null)
		{
			System.out.println("Data initalization failed!");
		}
		
		if (usrManager.addUsr(uName, uPassword))
		{
			UsrManager.serializeAccount();
	 		System.out.println("RegisterServlet add success!");
			resp.sendRedirect("login.html");
		}
		else
		{
			resp.sendRedirect("registerFail.html");
		}
	}

	@Override
	public void destroy() {
		System.out.println("Register Servlet Destory");
		super.destroy();
	}
	
	

}
