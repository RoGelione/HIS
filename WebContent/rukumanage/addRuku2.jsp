<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<script type="text/javascript" src="../js/crkname.js"></script>
<title>添加入库</title>
</head>
<body>
	<h1 >添加入库信息</h1>
	<form id="addform" class="fro"  name="rukuForm">
		<fieldset>
	 		<legend>入库信息</legend>
		入库编号：<input type="text" name="rid" class="required"/><br>
		药品编号：<input type="text" name="did" value="${kucun.did}" readonly="readonly"/><br>
		药品名称：<input type="text" name="rdname" value="${kucun.kdname}" readonly="readonly"/><br>
		生产厂家：<input type="text" name="dvender" value="${kucun.dvender}" readonly="readonly"/><br>		
		生产日期：<input type="date" name="date_begin" value="${kucun.date_begin}" readonly="readonly"/><br>
		失效日期：<input type="date" name="date_end" value="${kucun.date_end}" readonly="readonly"/><br>
		入库时间：<input type="date" name="rdate"class="required"/><br>
		操作类型 ：<input type="text" name="rtype" value="入库" readonly="readonly"/><br>
		入库数量 ：<input type="text" name="rnumber"class="required"/><br>
		进货金额：<input type="text" name="rprice"class="required"/><br>
		入库人名：<input type="text" name="realname" class = "nrealname required"readonly="readonly"/><br>
		<input type="button" value="添加" onclick="addRuku()">  
  		</fieldset>
	</form>
</body>
<script type="text/javascript">
function addRuku(){
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
	url:"/HIS/ruku/addRuku",
	type:'post',
	data:$('#addform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
    async: false,
	timeout:60000,
	error: function(e) {
		alert("出错了");
	},
	success: function(data){
		//alert(result);
		if("success" == data.result){
			c3ajax();
		}if("error2" == data.result){
			alert("不予入库！找不到所输入药品编号的信息"+"\n"+"请前去药品信息界面添加相应信息");
		}if("error3" == data.result){
			alert("不予入库！找不到所输入厂家名称的信息"+"\n"+"请前去厂家信息界面添加相应信息");
		}if("find" == data.result){
			alert("不予入库！入库单号已存在");
		} 
		}
	});
}
function c3ajax(){
	var c3 = $(".ajaxcon");
	$.ajax({
		url:"/HIS/ruku/doShowRuku",
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