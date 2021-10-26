<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>직원 신규등록</h1>
	<form action="empinsert" method="post">
		<table>
			<tr>
				<td>직원번호</td>
				<td><input type="number" name="employee_id"></td>
			</tr>
			<tr>
				<td>성</td>
				<td><input type="text" name="last_name"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="first_name"></td>
			</tr>
			<tr>
				<td>부서</td>
				<td><select name="department_id">
						<c:forEach items="${deptlist }" var="dept">
							<option value="${dept.department_id }">${dept.department_name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>직책</td>
				<td><select name="job_id">
						<c:forEach items="${joblist }" var="job">
							<option value="${job.job_id }">${job.job_title }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone_number"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>매니저</td>
				<td><select name="manager_id">
						<c:forEach items="${mlist }" var="man">
							<option value="${man.employee_id }">${man.first_name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>커미션</td>
				<td><input type="text" name="commission_pct"></td>
			</tr>
			<tr>
				<td>급여</td>
				<td><input type="number" name="salary"></td>
			</tr>
			<tr>
				<td>입사일</td>
				<td><input type="date" name="hire_date"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력하기"></td>
			</tr>
		</table>

	</form>

</body>
</html>