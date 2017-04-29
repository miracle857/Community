function isNicknameExit(){
	var nickTag = document.getElementById("nickname_1");
	var nickname = nickTag.value;
	console.log(nickname);
	var ajax = new XMLHttpRequest();
	var url = "isNicknameExit?isnickname="+nickname;
	ajax.open("get", url, true);
	ajax.onreadystatechange=function(){
		if(ajax.readyState==4){
			if(ajax.status==200){
				var data = ajax.responseText;
				if(data != ""){
					alert(data);
					document.reg.nickname.focus();
				}
			}
		}
	}
	ajax.send();
}

function isLogin(){
	//var nickTag = document.getElementById("isLogin");
	//var nickname = nickTag.value;
	//console.log(nickname);
	var ajax = new XMLHttpRequest();
	var url = "isLogin";
	console.log("islogin?");
	ajax.open("get", url, false);
	ajax.onreadystatechange=function(){
		if(ajax.readyState==4){
			if(ajax.status==200){
				var data = ajax.responseText;
				if(data != ""){
					alert(data);
					window.location.href='login';
					return true;
				}else{
					window.location.href='bbspublish.html';
					return true;
				}
			}
		}
	}
	ajax.send();
/*	console.log("islogin11111?");
	//window.location.href='login';
	window.location.href='bbspublish.html';
	return true;*/
	
}