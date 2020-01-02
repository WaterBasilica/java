<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="nb" class="bean.NyukaBean" scope="session"></jsp:useBean>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/nyuka.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script src="js/jquery.twbsPagination.min.js" type="text/javascript"></script>
<script src="js/nyuka.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="kensaku-area">
	<h2>入荷データ一覧</h2>

	<table class="table">
		<tr>
			<th>薬品区分</th><th>:</th>
			<td>
				<select id="yakuhin_kbn">
					<option value="">--</option>
					<% for(int i = 0 ; i < 3 ; i++) { %>
						<% if(nb.getYakuhin_kbn().equals(nb.getY_kbn_code() [i])) { %>
							<option value="<%=nb.getY_kbn_code()[i] %>" selected><%=nb.getY_kbn_name()[i] %></option>
						<% } else { %>
							<option value="<%=nb.getY_kbn_code()[i] %>" ><%=nb.getY_kbn_name()[i] %></option>
						<% } %>
					<% } %>
				</select>
			</td>
			<th>店舗</th><th>:</th>
			<td>
				<% if(session.getAttribute("login_kengen_code").equals("002")){ %>

					<select id="tenpo_code" disabled>
				<% } else { %>
					<select id="tenpo_code">
				<% } %>
				<option value="">---</option>
				<% for(Map<String,String> map : nb.getList()) { %>
					<% if(nb.getTenpo_code().equals(map.get("tenpo_code"))) { %>
						<option value="<%=map.get("tenpo_code") %>" selected><%=map.get("tenpo_name") %></option>
					<% } else { %>
						<option value="<%=map.get("tenpo_code") %>" ><%=map.get("tenpo_name") %></option>
					<% } %>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<th>入荷日</th><th>:</th>
			<td>
				<input type="date" id="date_from" value="<%=nb.getDate_from() %>">&nbsp;&nbsp;～&nbsp;&nbsp;
				<input type="date" id="date_to" value="<%=nb.getDate_to() %>">
			</td>
			<th>薬品</th><th>:</th>
			<td>
				<input type="text" id="yakuhin" value="<%=nb.getYakuhin() %>">
			</td>
		</tr>
		<tr>
			<th>取引先</th><th>:</th>
			<td>
				<input type="text" id="torihikisaki" value="<%=nb.getTorihikisaki() %>">
			</td>
			<td colspan="3" style="text-align:center">
				<button onclick="kensaku(1)">検索</button>&nbsp;&nbsp;
				<button onclick="shinki()">新規作成</button>
			</td>
		</tr>
	</table>
</div>

<input type="hidden" id="page" value="<%=nb.getPage() %>">
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
</div>

<div id="list-area">
</div>

<!-- 画面遷移用のform -->
<form name="frm" action="nyukatoroku" method="post">
	<input type="hidden" name="job" value="">
	<input type="hidden" name="nyuka_seq" value="">
</form>
<script type="text/javascript">
	var page = document.getElementById("page").value;
	if(page > 0) kensaku(page);


</script>

</body>
</html>