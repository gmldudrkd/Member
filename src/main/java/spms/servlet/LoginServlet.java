package spms.servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(request, response); //다시 돌아올 필요없음 > 재요청할거임
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {		    
			ServletContext sc = this.getServletContext();
			//리스너에선언된 db커넥션 가져옴
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			request.setAttribute("member", memberDao.exist(request.getParameter("email"), request.getParameter("password")));
			
			if(request.getAttribute("member") != null) {
				HttpSession session = request.getSession();
		        session.setAttribute("member", request.getAttribute("member"));
				response.sendRedirect("../member/list");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
}
