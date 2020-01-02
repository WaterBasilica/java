/**
 *
 */
function cancel() {
	var form = document.frm;
	form.job.value="4";
	form.submit();
}

function kensaku() {
	arrData = {
			"yakuhin" : document.getElementById("yakuhin").value,
			"yakuhin_kbn" : document.getElementById("yakuhin_kbn").value,
			"job" : "9"
	}

	$.post(
		"nyukatoroku",
		arrData,
		function(data) {
			//alert(data);
			if(data == 0) {
				$("#pagination").twbsPagination("destroy");
				document.getElementById("list-area").textContent = "該当データなし";
			} else {
				document.getElementById("list-area").textContent = "";
			}
			$("#pagination").twbsPagination("destroy");
			$("#pagination").twbsPagination({
				totalPages:data,
				visiblePages:15,
				onPageClick:function(event,page) {
					//alert(data);
					arrData["page"] = page;
					arrData["job"] = "10";
					getList(arrData);
				}
			})
		}
	);
}

function getList(arrData) {
	//alert("OK");
	//非同期post
	$.post(
		"nyukatoroku",
		arrData,
		function(data) {
			document.getElementById("list-area").innerHTML = data;
		}
	);
}
/*
function sentaku(jan_code, yakuhin_kbn, yakuhin_kbn_name, hanbai_name, yj_code) {
	var form = document.frm;
	form.hanbai_name.value = hanbai_name;
	form.yakuhin_kbn.value = yakuhin_kbn;
	form.yakuhin_kbn_name.value = yakuhin_kbn_name;
	form.jan_code.value = jan_code;
	form.yj_code.value = yj_code;
	form.job.value = "11";
	form.submit();
}
*/





