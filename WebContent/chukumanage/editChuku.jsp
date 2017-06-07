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
	<h1>编辑出库信息</h1>
	<form id="chukuform" class="fro" name="chukuForm" method="post">
		<fieldset>
	 		<legend>出库信息</legend>
		<input type="hidden" name="cid" value="${chuku.cid}" >
		药品编号:<input type="text" name="did" value="${chuku.did}" disabled='disabled'><br>
		出库时间:<input type="date" name="cdate" value="${chuku.cdate}" disabled='disabled'><br>
		操作类型:<input type="text" name="ctype"value="${chuku.ctype}" disabled='disabled'><br>
		新增出库数量:<input type="text" name="cnumber"value="${chuku.cnumber}"class="required"><br>
		新增销售金额:<input type="text" name="cprice"value="${chuku.cprice}"class="required"><br>
		出库员名:<input type="text" name="realname"value="${chuku.realname}" disabled='disabled'><br>
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
 				alert("不能为空");
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
			url:"/HIS/chuku/updateChuku",
			type:'post',
			data:$('#chukuform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				//alert(url);
				alert("不予出库！出库数大于库存数");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
			}
		});
 	 }
 	 </script>
</html>