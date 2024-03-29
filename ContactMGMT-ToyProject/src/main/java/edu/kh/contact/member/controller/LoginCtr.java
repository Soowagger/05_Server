package edu.kh.contact.member.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.contact.ct.model.dto.Contact;
import edu.kh.contact.ct.model.service.ContactService;
import edu.kh.contact.member.model.dto.Member;
import edu.kh.contact.member.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginCtr extends HttpServlet {
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			
			Member loginMember = service.login(inputEmail, inputPw);
			
			// 로그인 성공 시
			if(loginMember != null) { 
				
				// 세션에 로그인 멤버 세팅
				session.setAttribute("loginMember", loginMember);
				
				session.setMaxInactiveInterval(60*30);
			
				ContactService ctService = new ContactService();
				
				// 로그인 회원 연락처 전체 조회
				List<Contact> ctList = ctService.selectAll(loginMember.getMemberNo());
				
				session.setAttribute("ctList", ctList);
				
				resp.sendRedirect("/");
				
			// 실패 시 
			} else {
				
				session.setAttribute("msg", "Email or Password 불일치");
				
				resp.sendRedirect( req.getHeader("referer") );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
