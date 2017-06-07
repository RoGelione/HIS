<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<title>Insert title here</title>
<script type="text/javascript">
//action="/HIS/drug/updateDrug"
 	 function addUser(){
 		var req = $(".required");
		var con = 0;
		for (var i = 0;i<req.length; i++ ) {
			if (req[i].value==null || req[i].value == undefined || req[i].value == '') {
				alert("所填信息不能为空");
				break;
			} else{		
			con++;
			}
		}
		if (con==req.length) {
			ajax();
		}
 	 }
function ajax(){
	var c3 = $(".ajaxcon");
		$.ajax({
		url:"/HIS/user/addUser",
		type:'post',
		data:$('#addform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
        async: false,
		timeout:60000,
		error: function(e) {
			alert("出错了"+"\n"+"注意用户名不能重复"+"\n"+"或者您已经有帐号了");
		},
		success: function(result){
			c3.html(result);
		}
	});
}
 	 </script>
</head>
<body>
	<h1>添加用户</h1>
	<form id="addform" class="fro" name="userForm">
		<fieldset>
	 		<legend>用户信息</legend>
		用户名：<input type="text" name="uname"class="required"><br>
		真实姓名：<input type="text" name="realname"class="required"><br>
		职务：<br><select name="flag">
				<option selected value="系统管理员">系统管理员</option>
				<option value="药品管理员">药品管理员</option>
			  </select><br>
		电话：<input type="text" name="uphone"class="required"><br>
		密码：<input type="text" name="upassword"class="required"><br>
		<input type="button" value="添加" onclick="addUser()">
		</fieldset>
	</form>
</body>
</html>