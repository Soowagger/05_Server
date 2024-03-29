package edu.kh.contact.ct.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.contact.ct.model.dto.Contact;
import edu.kh.contact.ct.model.service.ContactService;
import edu.kh.contact.member.model.dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateCtr extends HttpServlet {
	
	private ContactService ctService = new ContactService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			Member member = (Member)session.getAttribute("loginMember");
			
			String contactNo = req.getParameter("contactNo");
			
			Contact contact = ctService.selectOne(contactNo, member.getMemberNo());
				
			req.setAttribute("contact", contact);
			
			req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			
			Member member = (Member)session.getAttribute("loginMember");
			
			String name = req.getParameter("name");
			String info = req.getParameter("info");
			String phone = req.getParameter("phone");
			String contactNo = req.getParameter("contactNo"); // 히든으로 값 얻어오기
			
			int result = ctService.update(name, info, phone, member.getMemberNo(), contactNo);
			
			if(result > 0) {
				
				session.setAttribute("msg", "연락처 편집 성공!!");
				
				List<Contact> ctList = ctService.selectAll(member.getMemberNo());
				
				session.setAttribute("ctList", ctList);
				
				resp.sendRedirect("/");
				
			} else {
				
				session.setAttribute("msg", "연락처 편집 실패ㅠㅠ");
				
				resp.sendRedirect(req.getHeader("referer"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println("업데이트중예외발생");
			e.printStackTrace();
		}
		
		
	}
	
}
