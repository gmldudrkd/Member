<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�űԵ��</title>
</head>
<body>

<jsp:include page="/Header.jsp"/>

<form action='add' method='post'>
	�̸�: <input type='text' name='name'><br>
	�̸���: <input type='text' name='email'><br>
	��ȣ: <input type='password' name='password'><br>
	<input type='submit' value='�߰�'>
	<input type='reset' value='���'>
</form>

<jsp:include page="/Tail.jsp"/>
</body>
</html>