<%@page import="com.bit.phonebook.dao.PhoneBookDAOImpl"%>
<%@page import="com.bit.phonebook.dao.PhoneBookDAO"%>
<%@page import="com.bit.phonebook.vo.PhoneBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	// DAO 를 이영해서 insert 실행
	// 인코딩 실행
	request.setCharacterEncoding("UTF-8");
	// 서블릿 초기 화 파라미터에서 db 정보 확인
	ServletContext context = getServletContext();
	String dbuser = context.getInitParameter("dbuser");
	String dbpass = context.getInitParameter("dbpass");
	// 폼 데이터 확인
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String tel = request.getParameter("tel");
	// VO 생성
	PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
	PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser, dbpass);
	boolean isSuccess = dao.insert(vo);
	
	if (isSuccess) response.sendRedirect(request.getContextPath());
	else response.sendRedirect(request.getContextPath()+"/form.jsp");
	%>