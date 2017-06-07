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
	<h1>编辑厂家</h1>
	<form id="supplierform" class="fro"  name="supplierForm" method="post">
		<fieldset>
	 		<legend>厂家信息</legend>
	 	<input type="hidden" name="sid" value="${supplier.sid}">	
		厂家名称：<input type="text" name="dvender" value="${supplier.dvender}"class="required"><br>
		厂家地址：<input type="text" name="saddress" value="${supplier.saddress}"class="required"><br>
		电话：<input type="text" name="sphone" value="${supplier.sphone}"class="required"><br>
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
			url:"/HIS/supplier/updateSupplier",
			type:'post',
			data:$('#supplierform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				//alert(url);
				alert("出错了"+"\n"+"厂家名称已存在");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
			}
		});
 	 }
 	 </script>
</html>