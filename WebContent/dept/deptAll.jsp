<%@page import="com.kosta.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width: 80%;
	margin: 0 auto
}
;
</style>
</head>
<body>
	<h1>부서목록</h1>

	<a href="deptinsert">부서입력</a>
	<br>
	<br>
	<table border="1">
		<tr>
			<td>부서코드</td>
			<td>부서이름</td>
			<td>매니저</td>
			<td>지역코드</td>
			<td>삭제하기</td>
		</tr>

		<%
		List<DeptDTO> deptlist = (List<DeptDTO>) request.getAttribute("deptlistinfo");
		for (DeptDTO dept : deptlist) {
		%>
		<tr>
			<td><a href="deptdetail?deptid=<%=dept.getDepartment_id()%>"><%=dept.getDepartment_id()%></a></td>
			<td><a href="deptdetail?deptid=<%=dept.getDepartment_id()%>"><%=dept.getDepartment_name()%></a></td>
			<td><%=dept.getManager_id()%></td>
			<td><%=dept.getLocation_id()%></td>
			<td><button onclick="call(<%=dept.getDepartment_id()%>)">삭제</button></td>
		</tr>
		<%
		}
		%>
	</table>
	<script>
	//BOM(Browser Object Model) -- window, location, screen, history, object, document
	function call(deptid){
		//get방식
		location.href = "deptdelete?deptid=" + deptid;
	}
	</script>
</body>
</html>