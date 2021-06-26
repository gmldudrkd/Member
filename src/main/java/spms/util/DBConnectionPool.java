package spms.util;

import java.sql.*;
import java.util.ArrayList;

public class DBConnectionPool {
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	//constructor > 객체초기화위해 사용됨
	public DBConnectionPool(String dirver, String url, String username, String password) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(dirver);
	}
	
	public Connection getConnection() throws Exception{
		if( connList.size() > 0 ) {
			Connection conn = connList.get(0); //array안의 내용을 꺼내줌
			if (conn.isValid(10)) {  //끊어짐 방지 유효성체크
				return conn;
			}
		}
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception{
		//커넥션 객체가 쓰여지고 난이후에는 해당 메서드를 호출함 > 그래야 이후에 사용가능
		connList.add(conn);
	}
	
	public void CloseAll() {
		for( Connection conn : connList ) {
			try { conn.close(); } catch(Exception e) {}
		}
	}
	
}
