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
	<h1>编辑入库信息</h1>
	<form id="rukuform" class="fro" name="rukuForm" method="post">
		<fieldset>
	 		<legend>入库信息</legend>
		<input type="hidden" name="rid" value="${ruku.rid}" >
		药品编号:<input type="text" name="did" value="${ruku.did}" disabled='disabled'><br>
		药品名称:<input type="text" name="rdname" value="${ruku.rdname}" disabled='disabled'><br>
		生产厂家:<input type="text" name="dvender"value="${ruku.dvender}" disabled='disabled'><br>
		生产日期:<input type="date" name="date_begin"value="${ruku.date_begin}" disabled='disabled'><br>
		失效日期:<input type="date" name="date_end"value="${ruku.date_end}" disabled='disabled'><br>
		入库时间:<input type="date" name="rdate"value="${ruku.rdate}" disabled='disabled'><br>
		操作类型:<input type="text" name="rtype"value="${ruku.rtype}" disabled='disabled'><br>
		新增入库数量:<input type="text" name="rnumber"value="${ruku.rnumber}"><br>
		新增进货金额:<input type="text" name="rprice"value="${ruku.rprice}"><br>
		入库员名:<input type="text" name="realname"value="${ruku.realname}"disabled='disabled'><br>
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
			url:"/HIS/ruku/updateRuku",
			type:'post',
			data:$('#rukuform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				//alert(url);
				alert("出错了");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
			}
		});
 	 }
 	 </script>
</html>