package com.bit.phonebook.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.phonebook.dao.PhoneBookDAO;
import com.bit.phonebook.dao.PhoneBookDAOImpl;
import com.bit.phonebook.vo.PhoneBookVO;

@WebServlet(name="PhoneBookServlet", urlPatterns="/")
public class PhoneBookServlet extends BaseServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if("form".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp");
			rd.forward(req, resp);
		} else {

			PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser, dbpass);
			List<PhoneBookVO> list = dao.getList();
		
			req.setAttribute("list", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");  

			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		if("insert".equals(action)) {
			// a hidden field가 insert인 경우의 처리
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
			PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser, dbpass);
			
			boolean isSuccess = dao.insert(vo);
			if(isSuccess) System.out.println("INSERT SUCCESS");
			else System.err.println("INSERT FAILED");
			
			resp.sendRedirect(req.getContextPath()+"/");
		} 
		else if("delete".equals(action)) {
			// a hidden 필드가 delete 일 경우를 처리
			String id = req.getParameter("id");
			PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser, dbpass);
			
			boolean isSuccess = dao.delete(Long.valueOf(id));
			if (isSuccess) System.out.println("DELETE SUCCESS");
			else System.err.println("DELETE FAILED");
			
			resp.sendRedirect(req.getContextPath()+"/");
		}
		else if("search".equals(action)) {
			String name = req.getParameter("name");
			PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser, dbpass);
			
			List<PhoneBookVO> list = dao.get(name);
			req.setAttribute("list", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");  

			rd.forward(req, resp);
			
		}
		else {
			resp.sendError(405);
		}
	}
	
}
