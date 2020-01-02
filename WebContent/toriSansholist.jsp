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
				<th>取引先コード</th><th>取引先名</th>
			</tr>
			<% for(Map<String,String> map : list) { %>
				<tr>
					<td>
						<a href="#" onClick="sentaku('<%=map.get("torihikisaki_code") %>', '<%=map.get("torihikisaki_name") %>')"><%=map.get("torihikisaki_code") %></a>
					</td>
					<td>
						<%=map.get("torihikisaki_name") %>
					</td>
				</tr>
			<% } %>

		</table>

	</body>
</html>