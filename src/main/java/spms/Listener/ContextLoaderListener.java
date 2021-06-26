package spms.Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();

			InitialContext initialContext = new InitialContext(); //톰캣 서버에서 자원찾기, web.xml에 자원을 참조한다고 선언필수!
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb"); // java:comp/env/jdbc/ > 서버자원의 이름호출
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds); //Dao객체에 주입 Datasource 주입
			
			sc.setAttribute("memberDao", memberDao); //servletcontext에 보관
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	
}
