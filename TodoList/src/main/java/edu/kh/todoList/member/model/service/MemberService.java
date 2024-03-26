package edu.kh.todoList.member.model.service;

import java.sql.Connection;

import static edu.kh.todoList.common.JDBCTemplate.*;
import edu.kh.todoList.member.model.dao.MemberDAO;
import edu.kh.todoList.member.model.dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/** 로그인 서비스
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 */
	public Member login(String inputId, String inputPw) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, inputId, inputPw);
		
		close(conn);
		
		return loginMember;
	}
}
