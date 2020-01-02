<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<div class="header-area">
		<h1>薬品在庫管理システム</h1>
		<div class="user-info">
			<label>ログイン者:</label>
			<span><%=session.getAttribute("login_shain_name") %></span>
			<label>権限</label>
			<span><%=session.getAttribute("login_kengen_name") %></span>
			<label>店舗:</label>
			<span><%=session.getAttribute("login_tenpo_name") %></span>
			<a href="menu">メニューへ戻る</a>
		</div>
	</div>
</body>
</html>