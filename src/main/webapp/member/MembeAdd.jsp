<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>신규등록</title>
</head>
<body>

<jsp:include page="/Header.jsp"/>

<form action='add' method='post'>
	이름: <input type='text' name='name'><br>
	이메일: <input type='text' name='email'><br>
	암호: <input type='password' name='password'><br>
	<input type='submit' value='추가'>
	<input type='reset' value='취소'>
</form>

<jsp:include page="/Tail.jsp"/>
</body>
</html>