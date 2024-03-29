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

@WebServlet("/insert")
public class InsertCtr extends HttpServlet {
	
	private ContactService ctService = new ContactService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			
			String name = req.getParameter("name");
			String info = req.getParameter("info");
			String phone = req.getParameter("phone");
			
			Member member = (Member)session.getAttribute("loginMember");
			
			int result = ctService.insert(name, info, phone, member.getMemberNo());
			
			if(result > 0) {
				
				session.setAttribute("msg", "연락처가 등록되었습니다!");
				
				List<Contact> ctList = ctService.selectAll(member.getMemberNo());
				session.setAttribute("ctList", ctList);
				
				resp.sendRedirect("/");
			} else {
				
				session.setAttribute("msg", "연락처 등록에 실패했어요ㅠㅠ");
				resp.sendRedirect(req.getHeader("referer"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
