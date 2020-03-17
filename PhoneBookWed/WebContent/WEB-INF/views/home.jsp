<%@page import="com.bit.phonebook.vo.PhoneBookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// Context 초기화 파라미터 불러오기
ServletContext context = getServletContext();
String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 Servlet</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	<h3>목록 
		<% if(request.getParameter("name")!=null) { %>
		( 검색어 : <%= request.getParameter("name") %> )
		<% } %>
	</h3> 
	<form method="POST" action="<%= request.getContextPath() %>/">
		<input type="hidden" name="a" value="search">
		<tr>검색어</tr>
		<input type="text" name="name" placeholder="이름">			
		<button type="submit">검색</button>
	</form>
	<table border = "1">
		<thead>
			<tr>
				<th>이름</th>
				<th>휴대전화</th>
				<th>전화번호</th>
				<th>도구</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<PhoneBookVO> list = (List<PhoneBookVO>)request.getAttribute("list");
			for(PhoneBookVO vo : list) {
			%>
			<tr>
				<td><%= vo.getPBname() %></td>
				<td><%= vo.getPBhp() %></td>
				<td><%= vo.getPBtel() %></td>
				<td>
					<form method="POST" action="<%= request.getContextPath() %>/">
					<input type="hidden" name="a" value="delete">
					<input type="hidden" name="id" value="<%= vo.getPBid() %>">
					<button type="submit">삭제</button>
					</form>
				</td>
			</tr>
			<% } %>	
		</tbody>	
	</table>
	<!-- 작성 폼으로 링크 -->
	<a href="<%= request.getContextPath() %>/?a=form">새주소작성</a>
</body>
</html>