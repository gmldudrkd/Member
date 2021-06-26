<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<% //!없는 경우 service메소드에서 동작하는 값들
String v1 = "";
String v2 = "";
String result = "";
String[] selected = {"","","",""};

if(request.getParameter("v1") != null){
	v1 = request.getParameter("v1");
	v2 = request.getParameter("v2");
	String op = request.getParameter("op");
	
	result = calculate(Integer.parseInt(v1),Integer.parseInt(v2),op);
	
	if("+".equals(op)){
		selected[0] = "selected";
	}else if("-".equals(op)){
		selected[1] = "selected";
	}else if("*".equals(op)){
		selected[2] = "selected";
	}else if("/".equals(op)){
		selected[3] = "selected";
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>계산기</title>
</head>
<body>

<h2>JSP 계산기</h2>
<form action="Calculator.jsp" method="get">
	<input type="text" name="v1" size="4" value="<%=v1%>">
	<select name="op">
		<option value="+" <%=selected[0]%>>+</option>
		<option value="-" <%=selected[1]%>>-</option>
		<option value="*" <%=selected[2]%>>*</option>
		<option value="/" <%=selected[3]%>>/</option>
	</select>
	<input type="text" name="v2" size="4" value="<%=v2%>">
	<input type="submit" value="=">
	<input type="text" size="8" value="<%=result%>"><br>
</form>

</body>
</html>

<%! //!는 멤버 function
private String calculate(int a, int b, String op){
	int r=0;
	
	if("+".equals(op)){
		r = a+b;
	}else if("-".equals(op)){
		r = a-b;
	}else if("*".equals(op)){
		r = a*b;
	}else if("/".equals(op)){
		r = a/b;
	}
	
	return Integer.toString(r);
}
%>