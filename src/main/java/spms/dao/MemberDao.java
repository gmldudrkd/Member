package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import spms.util.DBConnectionPool;
import spms.vo.Member;

public class MemberDao {
	DataSource ds; // DataSource는 BasicDataSource 의 구현제, 유연성을위해,,
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception{ //회원정보를 값 객체members에 담아서 컨트롤러에게 반환
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO, MNAME, EMAIL, CRE_DATE FROM MEMBERS ORDER BY MNO ASC");
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE")));
			}
			return members;
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(rs != null) rs.close(); } catch(Exception e){ }
			try { if(stmt != null) stmt.close(); } catch(Exception e){ }
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		}	
	}
	
	public int insert(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		 try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
						"INSERT into MEMBERS (EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE) "+
						"VALUES (?,?,?,NOW(),NOW())"
					);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			
			return stmt.executeUpdate();
		 }catch(Exception e) {
			 throw e;
		 }finally {
			 try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	public int delete(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("delete from  MEMBERS where MNO="+no);
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	public Member selectOne(int no) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS where MNO='"+no+"'");
			
			if(rs.next()) {
				return new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE"));
			}else {
		        throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
		      }
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	public int update(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("update MEMBERS set MNAME=?, EMAIL=?, MOD_DATE=now() where MNO=?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS where EMAIL='"+email+"' and PWD='"+password+"'");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Member().setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME")).setCreatedDate(rs.getDate("CRE_DATE"));
			}else {
				return null;
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	

}
