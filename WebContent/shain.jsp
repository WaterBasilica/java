<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<jsp:useBean id="sb" class="bean.ShainBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>薬品在庫管理システム</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/shain.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script src="js/jquery.twbsPagination.min.js" type="text/javascript"></script>
<script src="js/shain.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="content">
			<h2>社員一覧画面</h2>
			<label>社員名</label>
			<input type="text" id="shain" value="<%=sb.getShain() %>">

			<label>権限</label>
			<select id="kengen_code">
				<option value="">--</option>
				<% for(Map<String,String> map : sb.getKengenList()) { %>
					<option value="<%=map.get("kengen_code") %>"><%=map.get("kengen_name") %></option>
				<% } %>
			</select>
			<button type="button" onClick="kensaku('1')">検索</button>&nbsp;&nbsp;
			<button type="button" onClick="shinki()">新規作成</button>

		</div>
		<input type="hidden" id="page" value="<%=sb.getPage() %>">
		<div class="container">
		    <nav aria-label="Page navigation">
		        <ul class="pagination" id="pagination"></ul>
		    </nav>
		</div>
		<div id="list-area"></div>

		<!-- 画面遷移用のform -->
		<form name="frm" action="nyukatoroku" method="post">
			<input type="hidden" name="job" value="">
			<input type="hidden" name="nyuka_seq" value="">
		</form>

	</body>
</html>