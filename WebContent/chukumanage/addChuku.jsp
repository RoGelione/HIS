<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<script type="text/javascript" src="../js/crkname.js"></script>
<title>添加出库信息</title>
</head>
<body>
	<h1 >添加出库信息</h1>
	<form id="addform" class="fro"  name="ChukuForm">
		<fieldset>
	 		<legend>出库信息</legend>
		出库编号：<input type="text" name="cid"class="required"/><br>
		药品编号：<input type="text" name="did"class="required"/><br>
		出库时间：<input type="date" name="cdate"class="required"/><br>
		操作类型:<input type="text" name="ctype" value="出库" readonly="readonly"/><br>
		出库数量:<input type="text" name="cnumber"class="required"/><br>
		销售金额:<input type="text" name="cprice"class="required"/><br>
		出库人名：<input type="text" name="realname"class="required nrealname"readonly="readonly"/><br>
		<input type="button" value="添加" onclick="addChuku()">  
  		</fieldset>
	</form>
</body>
<script type="text/javascript">
function addChuku(){
		
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
	 $.ajax({
		url:"/HIS/chuku/addChuku",
		type:'post',
		data:$('#addform').serialize(),
		async: false,
		timeout:60000,
		dataType:"json",
		error: function(e) {
			alert("出错了");
		},
		success: function(data) {
			//alert(data.result);
			if("success" == data.result){
				c3ajax();
			}if("error" == data.result){
				alert("不予出库！出库数大于库存数");
			}if("find" == data.result){
				alert("不予出库！出库单号已存在");
			}  
         }
		}); 
}
function c3ajax(){
	var c3 = $(".ajaxcon");
	$.ajax({
		url:"/HIS/chuku/doShowChuku",
		type:'post',
		async: false,
		timeout:60000,
		error: function(e) {
			alert("加载出错了，请检查网络或数据库的连接");
			c3.html("<br/><img src='../img/jiazaicuo.jpg'/>");
		},
		success: function(result){						
			c3.html(result);
		}
	});
}
</script>
</html>