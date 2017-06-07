<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html >
<html>
	<head>
		<meta charset="utf-8" />		
		<title>药品管理系统</title>		
	</head>	
	<link rel="stylesheet" type="text/css" href="../css/Layout.css"/>
		<link rel="stylesheet" type="text/css" href="../css/Header.css"/>
		<link rel="stylesheet" type="text/css" href="../css/Nav.css"/>
	<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="../js/xiala.js"></script>
	<script type="text/javascript" src="../js/content.js"></script>
	<body>
		<header>
			<div id="title">
				<p class="icon"></p>
				<p class="tname">药品管理系统</p>				
			</div>
			<c:set var="realName" scope="request" value="${requestScope.realName}"/>
			<div class="esc">欢迎您!<span class="nowname">${realName}</span>!<a href="denglu">[退出]</a></div>
		</header>
		<nav>
		<div class="navs">
		<c:set var="flag" scope="request" value="${requestScope.flag}"/>
		<c:if  test="${flag==1}">
			<ul class="demo1">
				<u><a>用户信息管理</a></u>						
				<li><a>- 查看用户信息</a></li>
				<li><a>- 新增用户信息</a></li>
			</ul>
			</c:if>
			<ul class="demo1">
				<u><a>厂家信息管理</a></u>						
				<li><a>- 查看厂家信息</a></li>
				<li><a>- 新增厂家信息</a></li>
			</ul>
			<ul class="demo1">
				<u><a>药品信息管理</a></u>						
				<li><a>- 查看药品信息</a></li>
				<li><a>- 新增药品信息</a></li>
			</ul>
			<ul class="demo1">
				<u><a>药品入库管理</a></u>
				<li><a>- 查看入库单</a></li>
				<li><a>- 新增入库单</a></li>		
			</ul>			
			<ul class="demo1">
				<u><a>药品库存管理</a></u>			
				<li><a>- 查看药品库存</a></li>		
			</ul>
			<ul class="demo1">
				<u><a>药品出库管理</a></u>	
				<li><a>- 查看出库单</a></li>
				<li><a>- 新增出库单</a></li>
			</ul>
			</div>
		</nav>
		<section>
			<div class="ajaxcon"></div>
		</section>
	</body>

</html>
