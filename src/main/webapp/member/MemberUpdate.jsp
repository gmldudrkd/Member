<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원정보 수정</title>
</head>
<body>

<jsp:include page="/Header.jsp"/>

<h1>정보수정</h1>

<form action='update' method='post' >

번호 : <input type='text' name='no' value='${member.no }' readonly><br>
이름 : <input type='text' name='name' value='${member.name }' ><br>
이메일 : <input type='text' name='email' value='${member.email }' ><br>
가입일 : ${member.createdDate }<br>
<input type='submit' value='저장'>
<input type='button' value='취소' onclick='location.href=\"list\"'>

</form>

<jsp:include page="/Tail.jsp"/>
</body>
</html>