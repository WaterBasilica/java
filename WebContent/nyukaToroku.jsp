<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="ntb" class="bean.NyukaTorokuBean" scope="session"></jsp:useBean>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/nyukaToroku.css">
<script type="text/javascript" src="js/nyukaToroku.js" charset="UTF-8"></script>
</head>
<body>
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="content">
		<h2><%=ntb.getTitle() %></h2>
   	<form name="frm" action="nyukatoroku" method="post">
		<input type="hidden" name="job" value="">
		<table>
			<tr>
				<th>入荷連番</th><th>：</th>
				<td>
					<input type="text" name="d_nyuka_seq" value="<%=ntb.getNyuka_seq() %>" disabled>
					<input type="hidden" name="nyuka_seq" value="<%=ntb.getNyuka_seq() %>" >
				</td>
				<td></td>
				<th>入荷数</th><th>：</th>
				<td>
					<input type="text" name="nyuka_su" value="<%=ntb.getNyuka_su() %>" <%=ntb.getDisabled() %>>
				</td>
			</tr>
			<tr>
				<th>店舗</th><th>：</th>
					<td><% if(ntb.getKengen_code().equals("002")){ %>
						<input type="hidden" name="tenpo_code" value="<%=ntb.getTenpo_code() %>">
						<select name="d_tenpo_code" disabled>

					<% } else { %>
						<select name="tenpo_code">
					<% } %>
						<option value="">--</option>
						<% for(Map<String,String> map : ntb.getList()){ %>
							<% if(ntb.getTenpo_code().equals(map.get("tenpo_code"))){ %>
								<option value="<%=map.get("tenpo_code") %>" selected><%=map.get("tenpo_name") %></option>
							<% } else { %>
								<option value="<%=map.get("tenpo_code") %>"><%=map.get("tenpo_name") %></option>
							<% } %>
						<% } %>
					</select>
				</td>
				<td></td>
				<th>入荷日</th><th>：</th>
				<td>
					<input type="date" name="nyuka_date" value="<%=ntb.getNyuka_date() %>" <%=ntb.getDisabled() %>>
				</td>
				</tr>

			<tr>
				<th>取引先</th><th>：</th>
				<td>
					<input type="text" name="d_torihikisaki_name" value="<%=ntb.getTorihikisaki_name() %>" disabled>
					<input type="hidden" name="torihikisaki_name" value="<%=ntb.getTorihikisaki_name() %>" >
					<input type="hidden" name="torihikisaki_code" value="<%=ntb.getTorihikisaki_code() %>">
				</td>
				<td>
					<button type="button" onclick="tori_sansho()" <%=ntb.getDisabled() %>>参照</button>
				</td>
				<th>備考</th><th>：</th>
				<td rowspan="4">
					<textarea name="biko" rows="4" cols="20" <%=ntb.getDisabled() %>><%=ntb.getBiko() %></textarea>
				</td>
			</tr>
			<tr>
				<th>薬品名</th><th>：</th>
				<td>
					<input type="text" name="d_hanbai_name" value="<%=ntb.getHanbai_name() %>" disabled>
					<input type="hidden" name="hanbai_name" value="<%=ntb.getHanbai_name() %>" >
				</td>
				<td>
					<button type="button" onclick="yaku_sansho()" <%=ntb.getDisabled() %>>参照</button>
				</td>
			</tr>
			<tr>
				<th>薬品区分</th><th>：</th>
				<td>
					<input type="text" name="d_yakuhin_kbn_name" value="<%=ntb.getYakuhin_kbn_name() %>" disabled>
					<input type="hidden" name="yakuhin_kbn_name" value="<%=ntb.getYakuhin_kbn_name() %>">
					<input type="hidden" name="yakuhin_kbn" value="<%=ntb.getYakuhin_kbn() %>">
				</td>
				<td>
				</td>
			</tr>

			<tr>
				<th>JANコード</th><th>：</th>
				<td>
					<input type="text" name="d_jan_code" value="<%=ntb.getJan_code() %>" disabled>
					<input type="hidden" name="jan_code" value="<%=ntb.getJan_code() %>" >
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<th>YJコード</th><th>：</th>
				<td>
					<input type="text" name="d_yj_code" value="<%=ntb.getYj_code() %>" disabled>
					<input type="hidden" name="yj_code" value="<%=ntb.getYj_code() %>" >
				</td>
				<td>
				</td>
				<td colspan="3" style="text-align:center">
					<%=ntb.getButton() %>&nbsp&nbsp
					<button type="button" onclick="cancel()" >キャンセル</button>
				</td>
			</tr>
		</table>
	</form>
	<%=ntb.getMsg() %>
	</div>

</body>
</html>