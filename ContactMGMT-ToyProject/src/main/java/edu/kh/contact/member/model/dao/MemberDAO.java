package edu.kh.contact.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.contact.common.JDBCTemplate.*;
import edu.kh.contact.member.model.dto.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public MemberDAO() {
		try {
			prop = new Properties();
			
			String filePath 
				= MemberDAO.class.getResource("/edu/kh/contact/sql/member-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** sign up DAO
	 * @param conn
	 * @param member
	 * @return result
	 */
	public int signup(Connection conn, Member member) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("signup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}



	/** 로그인 DAO
	 * @param conn
	 * @param inputEmail
	 * @param inputPw
	 * @return loginMember
	 */
	public Member login(Connection conn, String inputEmail, String inputPw) throws Exception {
		
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, inputPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt(1));
				loginMember.setMemberEmail(rs.getString(2));
				loginMember.setMemberName(rs.getString(3));
				loginMember.setMemberPhone(rs.getString(4));				
				loginMember.setMemberDate(rs.getString(5));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}






	
	
	
	
	
}
