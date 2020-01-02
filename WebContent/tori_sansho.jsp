<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="js/tori_sansho.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h2>取引先参照</h2>
	<div class="kensaku-area">
		<label>取引先：</label>
		<input type="text" id="torihikisaki" value="" >
		<label>区分：</label>
		<select id="torihikisaki_kbn">
			<option value="">--</option>
			<option value="1" >問屋</option>
			<option value="2" >他店舗</option>
			<option value="3" >他薬局</option>
		</select>
		<button onclick="kensaku()">検索</button>
		<button onclick="cancel()">キャンセル</button>
	</div>
	<div class="container">
    	<nav aria-label="Page navigation">
        	<ul class="pagination" id="pagination"></ul>
    	</nav>
	</div>

	<div id="list-area"></div>

	<form name="frm" action="nyukatoroku" method="post">
		<input type="hidden" name="job" value="" >
		<input type="hidden" name="torihikisaki_code" value="">
		<input type="hidden" name="torihikisaki_name" value="">
	</form>

</body>
</html>