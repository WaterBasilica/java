<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Object obj = request.getAttribute("list");
			list = (List<Map<String,String>>)obj;
		%>
		<table class="table table-bordered table-striped">
			<tr>
				<th>社員コード</th><th>社員名</th><th>カナ</th>
				<th>権限</th><th>編集</th><th>削除</th>
			</tr>
			<% for(Map<String,String> map : list) { %>
				<tr>
					<td><%=map.get("shain_code") %></td>
					<td><%=map.get("shain_name") %></td>
					<td><%=map.get("shain_name_kana") %></td>
					<td><%=map.get("kengen_name") %></td>
					<td><button type="button" onClick="henshu()">編集</button></td>
					<td><button type="button" onClick="sakujo()">削除</button></td>
				</tr>

			<% } %>
		</table>
	</body>
</html>