package com.ibm.migration.samples.document.web;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9]*");
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logIn(request, response);
	}

	private void logIn(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = (String) req.getParameter("username");
		if(username == null || username.trim().length() == 0 || !usernamePattern.matcher(username).matches()){
			res.sendRedirect("jsp/loginError.jsp");
			return;
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("username", username); 
		req.getRequestDispatcher("jsp/main.jsp").forward(req, res);
		return;
	}
}
