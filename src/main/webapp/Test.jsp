<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:out value="�ȳ��ϼ���"/><br>
<c:out value="${null}">�ݰ����ϴ�.</c:out><br>
<c:out value="�ȳ��ϼ���">�ݰ����ϴ�.</c:out><br>
<c:out value="${null}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h3>�� �������</h3>
<c:set var="username1" value="ȫ�浿"></c:set>
<c:set var="username2">�Ӳ���</c:set>
1. ${username1 }<br>
2. ${username2 }<br>

<h3>�⺻ ������ - page</h3>
3. ${pageScope.username1 }<br>
4. ${requestScope.username1 }

</body>
</html>