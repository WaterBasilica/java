<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mb" class="bean.MenuBean" scope="request"></jsp:useBean>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>薬品在庫管理システム</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="menu-area">
		<h2>メインメニュー</h2>
		<ul>
			<% for(Map<String,String> map : mb.getList()) { %>
				<li><a href="<%=map.get("menu_uri") %>"><%=map.get("menu_name") %></a></li>
			<% } %>
		</ul>
	</div>

</body>
</html>