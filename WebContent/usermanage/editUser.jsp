<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<title>Insert title here</title>
</head>
<body>
	<h1>编辑用户</h1>
	<form id="userform" class="fro"  name="userForm" method="post">
		<fieldset>
	 		<legend>用户信息</legend>
		用户名：<input type="text" name="uname" value="${user.uname}"class="required"><br>
		<input type="hidden" name="realname" value="${user.realname}">
		职务：<br><select name="flag" >
				<option value="系统管理员">系统管理员</option>
				<option value="药品管理员">药品管理员</option>
			  </select><br>
		电话：<input type="text" name="uphone" value="${user.uphone}"class="required"><br>
		<input type="hidden" name="upassword" value="${user.upassword}"class="required"><br>
		<input type="button" onclick=sumbit() value="编辑" >
		</fieldset>
	</form>
</body>
<script type="text/javascript">
//action="/HIS/drug/updateDrug"
 	 function sumbit(){
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
			url:"/HIS/user/updateUser",
			type:'post',
			data:$('#userform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				alert("出错了"+"\n"+"注意用户名不能重复");
			},
			success: function(result){
				c3.html(result);
			}
		}); 
 	 }
 	 </script>
</html>