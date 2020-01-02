<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Map<String,String>> list = new ArrayList<Map<String, String>>();
		Object obj = request.getAttribute("list");
		list = (List<Map<String, String>>)obj;
	%>

	<table class="table table-bordered table-striped">
		<tr>
			<th>入荷連番</th><th>年度</th><th>店舗</th><th>取引先</th>
			<th>薬品区分</th><th>JANコード</th><th>YJコード</th>
			<th>薬品名</th><th>入荷数</th><th>入荷日</th>
			<th>編集</th><th>削除</th>
		</tr>
		<% for(Map<String, String> map : list) { %>
			<tr>
				<td><%=map.get("nyuka_seq") %></td>
				<td><%=map.get("nendo") %></td>
				<td><%=map.get("tenpo_name") %></td>
				<td><%=map.get("torihikisaki_name") %></td>
				<td><%=map.get("yakuhin_kbn_name") %></td>
				<td><%=map.get("jan_code") %></td>
				<td><%=map.get("yj_code") %></td>
				<td><%=map.get("hanbai_name") %></td>
				<td><%=map.get("nyuka_su") %></td>
				<td><%=map.get("nyuka_date") %></td>
				<td>
					<form action ="nyukatoroku" method="post">
						<input type="hidden" name="nyuka_seq" value="<%=map.get("nyuka_seq") %>">
						<input type="hidden" name="job" value="13">
						<button type="submit">編集</button>
					</form>
				</td>
				<td>
					<form action = "nyukatoroku" method = "post">
						<input type="hidden" name="nyuka_seq" value="<%=map.get("nyuka_seq") %>">
						<input type="hidden" name="job" value="15">
						<button type="submit">削除</button>
					</form>
				</td>
			</tr>
		<% } %>

	</table>

</body>
</html>