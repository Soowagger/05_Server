package edu.kh.contact.member.controller;

import java.io.IOException;

import edu.kh.contact.member.model.dto.Member;
import edu.kh.contact.member.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignupCtr extends HttpServlet{
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputEmail = req.getParameter("inputEmail");
		req.setAttribute("inputEmail", inputEmail);
		req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			String inputName = req.getParameter("inputName");
			String inputPhone = req.getParameter("inputPhone");
			
			Member member = new Member();
			member.setMemberEmail(inputEmail);
			member.setMemberPw(inputPw);
			member.setMemberName(inputName);
			member.setMemberPhone(inputPhone);
			
						
			int result = service.signup(member);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("msg", "회원가입이 정상적으로 처리되었습니다.");
				resp.sendRedirect("/");
			} else {
				session.setAttribute("msg", "회원가입 오류..!");
				resp.sendRedirect( req.getHeader("referer") );
			}
			
			
		} catch (Exception e) {
			System.out.println("* 회원가입 중 예외 발생! *");
			e.printStackTrace();
		}
		
	}
}
