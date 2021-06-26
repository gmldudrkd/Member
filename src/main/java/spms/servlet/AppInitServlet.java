package spms.servlet;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

//ServletContext 만들기 : 웹어플리케이션의 시작~종료까지 사용하는 데이터

public class AppInitServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		System.out.println("AppInitServlet 준비...");
		super.init(config); //슈퍼클래스로 부터 상속받은 init을 그대로 사용함
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			sc.setAttribute("conn", conn); //데이터베이스 커넥션을 객체에 저장
		}
		catch (Exception e) {
			//예외발생 시 ServletException 객체에 담아 서블릿 컨테이너로 이동, 서블릿 컨테이너가 클라이언트에게 적절한 화면 노출
			throw new ServletException(e);
		} 
	}
	
	@Override
	public void destroy() {
		System.out.println("AppInitServlet 마무리...");
		super.destroy();
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		
		try {
			if(conn != null && conn.isClosed() == false ) {
				conn.close();
			}
		}catch (Exception e) { }
	}
	
}
