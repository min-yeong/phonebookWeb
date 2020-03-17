<%@page import="com.bit.phonebook.vo.PhoneBookVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.bit.phonebook.dao.PhoneBookDAOImpl"%>
<%@page import="com.bit.phonebook.dao.PhoneBookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String name = request.getParameter("name");
// db정보 불러오기
ServletContext context = getServletContext();
String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");

PhoneBookDAO dao = new PhoneBookDAOImpl(dbuser,dbpass);
List<PhoneBookVO> list = new ArrayList<>();
list = dao.get(name);

response.sendRedirect(request.getContextPath());
%>