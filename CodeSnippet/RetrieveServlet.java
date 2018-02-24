package com.ibm.migration.samples.document.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.migration.samples.document.ejb.FileManagerBean;
import com.ibm.migration.samples.document.util.Document;

public class RetrieveServlet extends HttpServlet{

	private static final long serialVersionUID = -2773446199481416101L;
	
	@EJB
	private FileManagerBean docManager;
	
	
	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		String userid = (String) req.getSession().getAttribute("username");
		
		if(userid == null){
			req.getRequestDispatcher("/").forward(req, res);
		}
		List<Document> docs = null;
		if(userid.equals("editor")){
			docs = docManager.getAllFiles();
		}
		else{
			docs = docManager.getFilesByUserid(userid);
		}
		
		if(docs == null || docs.size() == 0){
			out.append("<TR>");
			out.append("<TD colspan='4' align='center'>You have no files!</TD>");
			out.append("</TR>");
		}
		if (docs != null) {
			int i = 0;
			for (Document doc : docs) {
				
				out.append("<TR>");
				out.append("<TD>" + doc.getOwner() + "</TD>");
				out.append("<TD>" + doc.getFilename() + "</TD>");
				out.append("<TD>" + doc.getTimestamp().toString() + "</TD>");
				out.append("</TR>");
			}
		}
		out.append("</TBODY>");
		out.append("</TABLE>");

		req.setAttribute("Doclist", out);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
