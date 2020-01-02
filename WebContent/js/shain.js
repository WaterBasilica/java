/**
 *
 */
function kensaku(startpage) {
	//alert("cakk kensaku() success");
	var arrData = {
		"shain" : document.getElementById("shain").value,
		"kengen_code" : document.getElementById("kengen_code").value,
		"page" : document.getElementById("page").value,
		"job" : "1"
	}

	//非同期post
	$.post(
		"shain",
		arrData,
		function(data) {
			//alert(data);
			if(data == 0) {
				$("#pagination").twbsPagination("destroy");
				document.getElementById("list-area").textContent = "該当データなし";
				return;
			} else {
				document.getElementById("list-area").textContent = "";
			}
			$("#pagination").twbsPagination("destroy");
			$("#pagination").twbsPagination({
				startPage:parseInt(startpage),
				totalPages:data,
				visiblePages:10,
				onPageClick:function(event,page) {
					document.getElementById("page").value = page;
					arrData['page'] = page;
					arrData['job'] = '2';
					getList(arrData);
				}

			});
		}
	);
}

function getList(arrData) {
	$.post(
		"shain",
		arrData,
		function(data) {
			document.getElementById("list-area").innerHTML = data;
		}
	);
}





