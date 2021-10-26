<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록창</title>
</head>
<body>
	<h1>8번입니다.</h1>
	<jsp:useBean id="m" class="com.kosta.controller.pro13.MemberBean"
		scope="request" />
	<jsp:getProperty name="m" property="id" /> <br>
	<jsp:getProperty name="m" property="pwd" /> <br>
	<jsp:getProperty name="m" property="name" /> <br>
	<jsp:getProperty name="m" property="email" />
</body>
</html>
