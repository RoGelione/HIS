<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<title>Insert title here</title>

</head>
<body>
	<h1>添加厂家</h1>
	<form id="addform" class="fro" name="supplierForm"class="required">
		<fieldset>
	 		<legend>厂家信息</legend>
	 	厂家编号：<input type="text" name="sid" class="required"/><br>
		厂家名称：<input type="text" name="dvender"class="required"/><br>
		厂家地址：<input type="text" name="saddress"class="required" /><br>
		电话：<input type="text" name="sphone"class="required"/><br>
		<input type="button" value="添加" onclick="addSupplier()">
		</fieldset>
	</form>
</body>
<script type="text/javascript">
//action="/HIS/drug/updateDrug"
 	 function addSupplier(){
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
			url:"/HIS/supplier/addSupplier",
			type:'post',
			data:$('#addform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				alert("出错了"+"\n"+"厂家编号或名称已存在");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
			}
		});
 	 }
 	 </script>
</html>