package spms.servlet;

import spms.dao.MemberDao;
import spms.vo.Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/member/add")
public class MemberAddservlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/MembeAdd.jsp");
		rd.include(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");    

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

			request.setAttribute("member", memberDao.insert(new Member().setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password")).setName(request.getParameter("name"))));

			response.sendRedirect("list");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/member/Error.jsp");
			rd.forward(request, response);
		}
		
	}
}
