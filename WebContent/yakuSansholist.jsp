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
				<th>JANコード</th><th>薬品区分</th><th>商品名</th><th>YJコード</th>
			</tr>
			<% for(Map<String,String> map : list) { %>
				<tr>
					<td>
						<form action="nyukatoroku" method="post">
							<input type="hidden" name="jan_code" value="<%=map.get("jan_code") %>">
							<input type="hidden" name="yj_code" value="<%=map.get("yj_code") %>">
							<input type="hidden" name="hanbai_name" value="<%=map.get("hanbai_name") %>">
							<input type="hidden" name="yakuhin_kbn" value="<%=map.get("yakuhin_kbn") %>">
							<input type="hidden" name="yakuhin_kbn_name" value="<%=map.get("yakuhin_kbn_name") %>">
							<input type="hidden" name="job" value="11">
							<button type="submit"><%=map.get("jan_code") %></button>
						</form>
					</td>
					<td>
						<%=map.get("yakuhin_kbn_name") %>
					</td>
					<td>
						<%=map.get("hanbai_name") %>
					</td>
					<td>
						<%=map.get("yj_code") %>
					</td>
				</tr>
			<% } %>
		</table>

	</body>
</html>