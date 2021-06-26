<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:out value="안녕하세요"/><br>
<c:out value="${null}">반갑습니다.</c:out><br>
<c:out value="안녕하세요">반갑습니다.</c:out><br>
<c:out value="${null}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h3>값 설정방식</h3>
<c:set var="username1" value="홍길동"></c:set>
<c:set var="username2">임꺽정</c:set>
1. ${username1 }<br>
2. ${username2 }<br>

<h3>기본 보관소 - page</h3>
3. ${pageScope.username1 }<br>
4. ${requestScope.username1 }

</body>
</html>