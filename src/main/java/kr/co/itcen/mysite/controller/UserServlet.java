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

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("a");
		
		if("joinform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		} else if("guestbook".equals(action)) {
			List<GuestbookVo> list = new GuestbookDao().getList();
			request.setAttribute("list", list);
			
			WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		} else {
			WebUtils.redirect(request, response, request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
