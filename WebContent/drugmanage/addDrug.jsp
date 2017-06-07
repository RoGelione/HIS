<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/add-edit.css"/>
<title>添加药品</title>

</head>
<body>
	<h1 >添加药品信息</h1>
	<form id="addform" class="fro"  name="drugForm">
		<fieldset>
	 		<legend>药品信息</legend>
		药品编号：<input type="text" name="did"class="required"/><br>
		药品名称：<input type="text" name="dname"class="required"/><br>
		药品类别：<select name="dcategory">
				<option value="处方药">处方药</option>
				<option value="非处方药">非处方药</option>
			  </select><br>
		药品规格：<input type="text" name="dspec"class="required"/><br>
		生产厂家：<input type="text" name="dvender"class="required"/><br>
		生产日期：<input type="date" name="date_begin"class="required"/><br>
		失效日期：<input type="date" name="date_end"class="required"/><br>
		进货价格 ：<input type="text" name="dprice"class="required"/><br>
		     零售价：<input type="text" name="retail_price"class="required"/><br>
		<input type="button" value="添加" onclick="addDrug()">  
  		</fieldset>
	</form>
</body>
<script type="text/javascript">
function addDrug(){
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
			url:"/HIS/drug/addDrug",
			type:'post',
			data:$('#addform').serialize(),//这个方法真的好用。哈哈哈。可以把表格数据提交
	        async: false,
			timeout:60000,
			error: function(e) {
				alert("出错了"+"\n"+"药品信息已存在");
			},
			success: function(result){
				//alert(result);
				c3.html(result);
				}
			});
	 }
</script>
</html>