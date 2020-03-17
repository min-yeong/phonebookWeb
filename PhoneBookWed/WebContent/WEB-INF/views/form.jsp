<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 Servlet</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	<h3>새 주소 등록</h3>
	<form action="<%= request.getContextPath() %>/" method="POST">
	<input type="hidden" name="a" value="insert">
	<label for="name">이름</label>
	<input type="text" name="name">
	<br>
	<label for="hp">휴대전화</label>
	<input type="text" name="hp">
	<br>
	<label for="tel">전화번호</label>
	<input type="text" name="tel">
	<br>
	<input type="submit" value="주소등록">
	<br>
	<a href="<%= request.getContextPath() %>">목록보기</a>
	</form>
</body>
</html>