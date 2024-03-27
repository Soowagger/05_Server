package edu.kh.todoList.todo.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todoList.member.model.dto.Member;
import edu.kh.todoList.todo.model.dto.Todo;
import edu.kh.todoList.todo.model.service.TodoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insert")
public class InsertController extends HttpServlet {
	
	// Todo 등록 화면 전환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(req, resp);
	}
	
	// Todo 등록할 서비스 호출 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 로그인한 멤버가 누군지 알아야하기 때문에 세션 필요
			HttpSession session = req.getSession();
			
			String title = req.getParameter("title");
			String memo = req.getParameter("memo");
			
			// 로그인 컨트롤러에 로그인멤버 세팅 해놓은 상태
			Member member = (Member) session.getAttribute("loginMember");
								// 오브젝트로 얻어오기 때문에 강제 형변환
			
			TodoService service = new TodoService();
			
			int result = service.insert(title, memo, member.getMemberNo());
			
			if(result > 0) {
				session.setAttribute("message", "할 일이 등록되었습니다.");
				
				// todoList 갱신된 것 구해서 속성값으로 재등록
				List<Todo> todoList = service.selectAll(member.getMemberNo());
				session.setAttribute("todoList", todoList);
				
				
				resp.sendRedirect("/");
			} else {
				session.setAttribute("message", "등록 실패하였습니다!");
				resp.sendRedirect(req.getHeader("referer"));
			}
			
			
		} catch (Exception e) {
			System.out.println("[Todo 등록 중 예외 발생]");
			e.printStackTrace();
		}
		
	}
}
