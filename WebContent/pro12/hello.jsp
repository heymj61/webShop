<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
//멤버메서드 멤버변수....class 안으로 들어간다.
   String name = "듀크";
   public String getName(){ return name;}
%>    
<%
//자바코드...service메서드에 들어간다. 요청시마다 수행
String myname = "jin";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>선언문 연습</title>
  </head>
  <body>
      <h1>안녕하세요 service 메서드에 들어간다. 출력된다. <%=name %>님!! <%=myname %></h1>
  </body>
</html>
