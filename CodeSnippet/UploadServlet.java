package com.ibm.migration.samples.document.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.migration.samples.document.ejb.FileManagerBean;

@WebServlet({"/upload"})
public class UploadServlet extends HttpServlet{

	private static final long serialVersionUID = -2773446199481416101L;
	
	@EJB
	private FileManagerBean docManager;
	
	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String filename = req.getParameter("file");
		String userID = (String) req.getSession().getAttribute("username");
		req.setAttribute("result", docManager.upload(userID, filename));
		req.getRequestDispatcher("jsp/main.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
