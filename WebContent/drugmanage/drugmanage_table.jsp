<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<head>
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/section.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<div class="content">
	<div class="search1">
		<span class="search">药品名称：<input type="search" id="search1"  name = "dname" value='' placeholder="请输入"/></span>
		<span class="search">药品类别： <select id="search2" name = "dcategory" >
					<option selected value="">请选择</option>
					<option value="处方">处方药</option>
					<option value="非处方">非处方药</option>
				  </select><br></span>
		<span class="search">药品规格：<input type="search" id="search3" name = "dspec"  value='' placeholder="请输入"/></span>
		<span class="search">生产厂家：<input type="search" id="search4" name = "dvender"  value='' placeholder="请输入"/></span>	
		<span class="tijiao"><input type="button" class="goButton" value ="查找" /></span> 
	</div>
	</div>
	<div class="content1">  
    <jsp:include page="./test.jsp" />  
	</div> 

<script type="text/javascript">
$(".tijiao").on("click",".goButton",{currentPage:"1"},gotoPageBySearch);

</script>
