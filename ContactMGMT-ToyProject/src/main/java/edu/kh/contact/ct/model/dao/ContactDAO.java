package edu.kh.contact.ct.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.contact.common.JDBCTemplate.*;
import edu.kh.contact.ct.model.dto.Contact;


public class ContactDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public ContactDAO() {
		try {
			prop = new Properties();
			
			String filePath 
				= ContactDAO.class.getResource("/edu/kh/contact/sql/contact-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그인 회원의 연락처 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return ctList
	 */
	public List<Contact> selectAll(Connection conn, int memberNo) throws Exception {
		
		List<Contact> ctList = new ArrayList<Contact>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Contact ct = new Contact();
				
				ct.setContactNo(rs.getInt(1));
				ct.setContactName(rs.getString(2));
				ct.setContactInfo(rs.getString(3));
				ct.setContactDate(rs.getString(4));
				ct.setStar(rs.getString(5));
				
				ctList.add(ct);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return ctList;
	}
	
	
	
	/** 편집할 연락처 조회 DAO
	 * @param conn
	 * @param contactNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public Contact selectOne(Connection conn, String contactNo, int memberNo) throws Exception {
		
		Contact contact = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, contactNo);
			pstmt.setInt(2, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				contact = new Contact();
				
				contact.setContactNo(rs.getInt(1));
				contact.setContactName(rs.getString(2));
				contact.setContactInfo(rs.getString(3));
				contact.setContactPhone(rs.getString(4));
				contact.setContactDate(rs.getString(5));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return contact;
	}

	public int update(Connection conn, String name, String info, String phone, int memberNo, String contactNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, info);
			pstmt.setString(3, phone);
			pstmt.setString(4, contactNo);
			pstmt.setInt(5, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 연락처 등록 DAO
	 * @param conn
	 * @param name
	 * @param info
	 * @param phone
	 * @param memberNo
	 * @return result
	 */
	public int insert(Connection conn, String name, String info, String phone, int memberNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insert");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, info);
			pstmt.setInt(3, memberNo);
			pstmt.setString(4, phone);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 연락처 삭제
	 * @param conn
	 * @param contactNo
	 * @return
	 */
	public int delete(Connection conn, String contactNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, contactNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
}
