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
		<span class="search">出库编号：<input id="search1"  type="search" name = "cid" value='' placeholder="请输入"/></span>
		<span class="search">药品编号：<input id="search2"  type="search" name = "did" value='' placeholder="请输入"/></span>
		<span class="search">出库员：<input id="search3" type="search" name = "realname"  value='' placeholder="请输入"/></span>	
		<span class="date1">出库时间：<input type="date" id="search4" value=''/>至</span>
		<span class="date"><input type="date" id="search5" value=''/></span>
		<span class="tijiao"><input type="button" class="goButton" value ="查找" /></span> 
	</div>
	</div>
	<div class="content1">  
    <jsp:include page="./test.jsp" />  
	</div> 
<!-- 下面这个javascript放这里是因为不需要刷新这个js部分，会出现事件多次冒泡的情况！ -->
<script type="text/javascript">
$(".tijiao").on("click",".goButton",{currentPage:"1"},gotoPageBySearch);

</script>
