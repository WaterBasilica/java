/**
 *
 */

function kensaku(startpage) {

	var arrData = {
			"yakuhin_kbn":document.getElementById("yakuhin_kbn").value,
			"tenpo_code":document.getElementById("tenpo_code").value,
			"date_from":document.getElementById("date_from").value,
			"date_to":document.getElementById("date_to").value,
			"yakuhin":document.getElementById("yakuhin").value,
			"torihikisaki":document.getElementById("torihikisaki").value,
			"page":document.getElementById("page").value,
			"job":"1"
	}


	//非同期POST
	//alert(arrData["yakuhin_kbn"]);
	//alert(data);
	//"nyuka" nyuka.javaにリクエストだしてる、遷移はしていない、画面を動かさないままリクエストだす
	//レスポンスはfunctionの引数に返ってくる
	$.post(
		"nyuka",
		arrData,
		function(data){
			if(data == 0){
				$("#pagination").twbsPagination("destroy");
				document.getElementById("list-area").textContent = "該当データなし";
				return;
			}else{
				document.getElementById("list-area").textContext = "";
			}
			//alert(data);
			//alert(parseInt(startpage));
			//jspの中に#というid名の領域に表示させようとしている
			//"destroy"はpagenationを消す命令
			//visiblePages全体として最大何ページ表示するのか、それ以外はnext,lastで移動させる
			$("#pagination").twbsPagination("destroy");
			$("#pagination").twbsPagination({
				startPage:parseInt(startpage),
				totalPages:data,
				visiblePages:10,
				onPageClick:function(event,page) {
					//alert(page);
					document.getElementById("page").value = page;
					arrData["page"] = page;//クリックされたページ
					arrData["job"] = "2";
					getList(arrData);
				}
			});
		}
	);

	//一覧表示用
	//ここのdataにnyukalist.jspが返ってくる
	function getList(arrData) {
		$.post( //同じ検索項目、jobに2が入っている,
			"nyuka",
			arrData,
			function(data){
				document.getElementById("list-area").innerHTML = data;
			}
		);
	}
}


//新規作成
function shinki() {
	//alert("call shinki success.");
	var arrData = {
			"yakuhin_kbn":document.getElementById("yakuhin_kbn").value,
			"tenpo_code":document.getElementById("tenpo_code").value,
			"date_from":document.getElementById("date_from").value,
			"date_to":document.getElementById("date_to").value,
			"yakuhin":document.getElementById("yakuhin").value,
			"torihikisaki":document.getElementById("torihikisaki").value,
			"page":document.getElementById("page").value,
			"job":"3"
	}

	$.post(
		"nyuka",
		arrData,
		function(data) {
			dispShinki();
		}
	);
}

//新規作成画面表示
function dispShinki() {
	//alert("call dispShinki success");
	var form = document.frm;//formタグ全体を指す
	form.job.value = "1";
	form.submit();//formタグ以外からsubmitしたいときよく使う
}
