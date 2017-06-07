<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<head>
<script type="text/javascript" src="../js/jquery-3.1.0.min.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/section.css"/>
<title>Insert title here</title>
</head>
	<div class="content">
		<div class="search1">
		<span class="search">库存编号：<input type="search" id="search1"  name = "kid" value='' placeholder="请输入"/></span>
		<span class="search">药品名称：<input type="search" id="search2"  name = "kdname" value='' placeholder="请输入"/></span>
		<span class="search">生产厂家：<input type="search" id="search3" name = "dvender"  value='' placeholder="请输入"/></span>		
		<span class="tijiao"><input type="button" class="goButton" value ="查找" /></span> 
		<span class="tijiao1"><input type="button" class="goButton1" value ="即将失效和已失效的药品" /></span> 
		</div>
	</div>
	<div class="content1">  
    <jsp:include page="./test.jsp" />  
	</div> 
<!-- 下面这个javascript放这里是因为不需要刷新这个js部分，会出现事件多次冒泡的情况！ -->
<script type="text/javascript">
$(".tijiao").on("click",".goButton",{currentPage:"1"},gotoPageBySearch);
$(".tijiao1").on("click",".goButton1",{currentPage:"1"},gotoPageByguoqi);
</script>
