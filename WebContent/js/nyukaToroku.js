/**
 *
 */

function cancel() {
	//alert("call cancel() success.")
	if(window.confirm("画面を閉じて一覧画面に戻ります。よろしいですか？")){
		var form = document.frm;
		form.job.value = "2";
		form.submit();
	}
}

function tori_sansho() {
	//alert("call tori_sansho success. ");
	var form = document.frm;
	form.job.value = "3";
	form.submit();
}

function yaku_sansho() {
	//alert("call yaku_sasho success");
	var form = document.frm;
	form.job.value = "8";
	form.submit();
}

function registShinki() {
	var form = document.frm;
	form.job.value = "12";
	form.submit();
}

function henshu() {
	//alert("call henshu() success");
	var form = document.frm;
	form.job.value = "14";
	form.submit();
}

function delNyuka() {
	if(window.confirm("このデータを削除しますか？")) {
		var form = document.frm;
		form.job.value = "16";
		form.submit();
	}
}
