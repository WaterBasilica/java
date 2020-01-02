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
<script src="js/yaku_sansho.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h2>薬品参照</h2>
	<div class="kensaku-area">
		<label>薬品：</label>
		<input type="text" id="yakuhin" value="" >
		<label>区分：</label>
		<select id="yakuhin_kbn">
			<option value="">--</option>
			<option value="1" >薬品</option>
			<option value="2" >ＯＴＣ</option>
			<option value="4" >特材</option>
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

</body>
</html>