package kr.co.itcen.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.web.WebUtils;

public class GuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("a");
		
		if("guestbook".equals(action)) {
			List<GuestbookVo> list = new GuestbookDao().getList();
			request.setAttribute("list", list);
			
			WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		} else if("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(content);
			
			new GuestbookDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/guest?a=guestbook");
		} else if("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);
		} else if("delete".equals(action)) {
			String para_no = request.getParameter("no");
			int no = Integer.parseInt(para_no);
			String password = request.getParameter("password");
			
			new GuestbookDao().set_delete(no, password);
			
			response.sendRedirect(request.getContextPath() + "/guest?a=guestbook");
		} else {
			WebUtils.redirect(request, response, request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
