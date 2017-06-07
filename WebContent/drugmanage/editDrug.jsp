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
	<h1>编辑药品信息</h1>
	<form id="drugform" class="fro" name="drugForm" method="post">
		<fieldset>
	 		<legend>药品信息</legend>
		<input type="hidden" name="did" value="${drug.did}">
		药品名称:<input type="text" name="dname" value="${drug.dname}"class="required"><br>
		药品类别:<select name="dcategory" >
				<option value="处方药">处方药</option>
				<option value="非处方药">非处方药</option>
			  </select><br>
		药品规格:<input  type="text" name="dspec" value="${drug.dspec}"class="required"><br>
		生产厂家:<input  type="text" name="dvender"value="${drug.dvender}"class="required"><br>
		生产日期:<input  type="date" name="date_begin"value="${drug.date_begin}"class="required"><br>
		失效日期:<input type="date" name="date_end"value="${drug.date_end}"class="required"><br>
		进货单价:<input type="text" name="dprice"value="${drug.dprice}"class="required"><br>
		零售价:<input type="text" name="retail_price"value="${drug.retail_price}"class="required"><br>
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
			url:"/HIS/drug/updateDrug",
			type:'post',
			data:$('#drugform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
            async: false,
			timeout:60000,
			error: function(e) {
				alert("错了");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
			}
		});
 	 }
 	 </script>
</html>