<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="lb" class="bean.LoginBean" scope="request"></jsp:useBean>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I薬品在庫管理システム</title>
<link rel="stylesheet"  href="css/login.css">
</head>
<body>
	<div class="content">
	<h1>薬品在庫管理システム </h1>
	<h2>ログイン</h2>
	<form action="login" method="post">
		<label>社員コード：</label>
		<input type="text" name="shain_code" value="<%=lb.getShain_code() %>">
		<label>パスワード：</label>
		<input type="password" name="password" value="<%=lb.getPassword() %>">
		<label>店舗：</label>
		<select name="tenpo_code">
			<option value="">--店舗を選択--</option>
			<% for(Map<String,String> map : lb.getTenpo_data()) { %>
				<% if(lb.getTenpo_code() == map.get("tenpo_code")) { %>
					<option value="<%=map.get("tenpo_code") %>" selected><%=map.get("tenpo_name") %></option>
				<% } else { %>
					<option value="<%=map.get("tenpo_code") %>"><%=map.get("tenpo_name") %></option>
				<% } %>
			<% } %>
		</select>
		<button type="submit">ログイン</button>
	</form>
	<%=lb.getMsg() %>
	</div>
</body>
</html>