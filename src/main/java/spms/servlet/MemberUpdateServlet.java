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


@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
					
			//JSP로 출력을 위임
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.include(request, response);
			
		}catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/Error.jsp");
			rd.include(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");    
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			request.setAttribute("member", memberDao.update(new Member().setName(request.getParameter("name"))
					.setEmail(request.getParameter("email"))
					.setNo(Integer.parseInt(request.getParameter("no")) )));
			
			response.sendRedirect("list");
		}catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/Error.jsp");
			rd.include(request, response);
		}
		
	}
}
