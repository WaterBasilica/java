/**
 *
 */

function cancel() {
	//alert("call canceel sucsess");
	var form = document.frm;
	form.job.value = "4"; //取引先参照キャンセル
	form.submit();
}

function kensaku() {
	//alert("call tori_sansho sucess");
	var arrData = {
		"torihikisaki" : document.getElementById("torihikisaki").value,
		"torihikisaki_kbn" : document.getElementById("torihikisaki_kbn").value,
		"job" : "5"
	}

	$.post(
		"nyukatoroku",
		arrData,
		function(data) {
			if(data == 0) {
				$("#pagination").twbsPagination("destroy");
				document.getElementById("list-area").textContent = "該当データなし";
				return;
			} else {
				document.getElementById("list-area").textContent = "";
			}
			$("#pagination").twbsPagination("destroy");
			$("#pagination").twbsPagination({
				totalPages:data,
				visiblePages:15,
				onPageClick:function(event,page) {
					//alert(page);
					arrData["page"] = page;
					arrData["job"] = "6";
					getList(arrData);
				}
			})
		}

	);

}
//サーブレットにリクエストを出す
function getList(arrData) {
	$.post(
		"nyukatoroku",
		arrData,
		function(data) {
			document.getElementById("list-area").innerHTML = data;
		}
	);
}

function sentaku(code, name) {
	var form = document.frm;
	form.torihikisaki_code.value = code;
	form.torihikisaki_name.value = name;
	form.job.value = "7";
	form.submit();
}





