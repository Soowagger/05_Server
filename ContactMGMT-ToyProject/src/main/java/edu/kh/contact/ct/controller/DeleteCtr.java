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

@WebServlet("/delete")
public class DeleteCtr extends HttpServlet {
	
	private ContactService ctService = new ContactService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String contactNo = req.getParameter("contactNo");
			int result = ctService.delete(contactNo);
			
			HttpSession session = req.getSession();
			
			Member member = (Member)session.getAttribute("loginMember");
			
			if(result > 0) {
				
				session.setAttribute("msg", "삭제가 완료되었습니다.");
				
				List<Contact> ctList = ctService.selectAll(member.getMemberNo());
				session.setAttribute("ctList", ctList);
				
			} else {
				session.setAttribute("msg", "삭제 실패!");
			}
			
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
