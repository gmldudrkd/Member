package spms.servlet;

import spms.dao.MemberDao;
import spms.vo.Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/member/list") //애노테이션 서블릿 배치정보 설정
public class MemberListServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			ServletContext sc = this.getServletContext(); //웹어플리케이션이 시작할때 읽는객체
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao"); //Servletcontext에서 가져옴 > 객체재생성 필요없음
			
			request.setAttribute("members", memberDao.selectList());
			
			//JSP로 출력을 위임
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
		} 
		catch (Exception e) {
			//예외발생 시 ServletException 객체에 담아 서블릿 컨테이너로 이동, 서블릿 컨테이너가 클라이언트에게 적절한 화면 노출
			throw new ServletException(e);
		} 
		
	}
}
