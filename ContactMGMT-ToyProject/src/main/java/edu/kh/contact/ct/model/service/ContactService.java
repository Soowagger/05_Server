package edu.kh.contact.ct.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.contact.common.JDBCTemplate.*;

import edu.kh.contact.ct.model.dao.ContactDAO;
import edu.kh.contact.ct.model.dto.Contact;

public class ContactService {
	
	private ContactDAO dao = new ContactDAO();
	
	
	
	/** 로그인 회원의 연락처 조회 서비스
	 * @param memberNo
	 * @return ctList
	 */
	public List<Contact> selectAll(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Contact> ctList = dao.selectAll(conn, memberNo);
		
		
		close(conn);
		
		
		return ctList;
	}


	
	/** 편집할 연락처 조회 서비스
	 * @param contactNo
	 * @param memberNo
	 * @return contact
	 */
	public Contact selectOne(String contactNo, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		Contact contact = dao.selectOne(conn, contactNo, memberNo);
		
		close(conn);
		
		return contact;
	}



	/** 연락처 편집 서비스
	 * @param name
	 * @param info
	 * @param phone
	 * @param memberNo
	 * @param contactNo
	 * @return result
	 */
	public int update(String name, String info, String phone, int memberNo, String contactNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.update(conn, name, info, phone, memberNo, contactNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 연락처 등록 서비스
	 * @param name
	 * @param info
	 * @param phone
	 * @param memberNo
	 * @return result
	 */
	public int insert(String name, String info, String phone, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.insert(conn, name, info, phone, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 연락처 삭제 서비스
	 * @param contactNo
	 * @return result
	 */
	public int delete(String contactNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.delete(conn, contactNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
