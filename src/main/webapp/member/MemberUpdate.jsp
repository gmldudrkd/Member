<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������ ����</title>
</head>
<body>

<jsp:include page="/Header.jsp"/>

<h1>��������</h1>

<form action='update' method='post' >

��ȣ : <input type='text' name='no' value='${member.no }' readonly><br>
�̸� : <input type='text' name='name' value='${member.name }' ><br>
�̸��� : <input type='text' name='email' value='${member.email }' ><br>
������ : ${member.createdDate }<br>
<input type='submit' value='����'>
<input type='button' value='���' onclick='location.href=\"list\"'>

</form>

<jsp:include page="/Tail.jsp"/>
</body>
</html>