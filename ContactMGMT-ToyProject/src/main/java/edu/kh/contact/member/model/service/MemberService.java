package edu.kh.contact.member.model.service;

import java.sql.Connection;

import static edu.kh.contact.common.JDBCTemplate.*;

import edu.kh.contact.member.model.dao.MemberDAO;
import edu.kh.contact.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	
	
	/** sign up service
	 * @param member
	 * @return result 
	 */	
	public int signup(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.signup(conn, member);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 로그인 서비스
	 * @param inputEmail
	 * @param inputPw
	 * @return loginMember
	 */
	public Member login(String inputEmail, String inputPw) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, inputEmail, inputPw);
		
		close(conn);
		
		
		return loginMember;
	}
	







	
	
	
}
