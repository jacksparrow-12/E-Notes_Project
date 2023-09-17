package com.Servlet;

import java.io.IOException;

import com.DAO.PostDAO;
import com.Db.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer noteid=Integer.parseInt(request.getParameter("note_id"));
		PostDAO dao=new PostDAO(DBConnect.getConn());
		
		boolean f=dao.DeleteNotes(noteid);
		HttpSession session=null;
		if(f)
		{
			session=request.getSession();
			session.setAttribute("updateMsg","Notes Deleted Successfully..");
			response.sendRedirect("showNotes.jsp");
			
		}
		else {
			session=request.getSession();
			session.setAttribute("wrongMsg", "Sometthing went wrong on the server");
			response.sendRedirect("showNotes.jsp");
			
		}
	}
}
