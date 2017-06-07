<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/section.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="tab">
	<ul class="tabtic">
		<li>厂家编号</li>
		<li>厂家名称</li>
		<li>厂家地址</li>
		<li>厂家电话</li>
		<li>操作</li>
	</ul>
	<ul class="tabtic1">
	<c:forEach var="supplierDto" items="${requestScope.supplierListDto}">
		<ul class="tabtic2">
		<li>${supplierDto.sid}</li>
		<li>${supplierDto.dvender}</li>
		<li>${supplierDto.saddress}</li>
		<li>${supplierDto.sphone}</li>
		<li>
			<a href="javascript:edit('${supplierDto.sid}')">编辑</a>
			<a href="javascript:xianshi('${supplierDto.sid}')">删除</a>
		</li>
	</ul>
	</c:forEach>
	</ul>
	</div>
  <jsp:include page="./page_ajax.jsp" />
  
</body>
 <script type="text/javascript">
 function xianshi(un){	

		    var mymessage=confirm("删除供应商将会使与供应商有关的信息全部删除！\r确定删除吗？");  
		    if(mymessage==true)  
		    {  
		    	del(un);  
		    }   		  
	}
	 function edit(sid){
	 		var c1 = $(".ajaxcon");
	 		$.ajax({
				url:"/HIS/supplier/getSupplier",
				type:'post',
				data:{
					sid:sid					
				},
				dataType:'text',
				timeout:60000,
				error: function(e) {
					alert("错了");
				},
				success: function(result){
					//alert(result);
					c1.html(result);
				}
			});
	 	 }
		 function del(sid) {
				var c1 = $(".ajaxcon");
		 		$.ajax({
					url:"/HIS/supplier/delSupplier",
					type:'post',
					data:{
						sid:sid					
					},
					dataType:'text',
					timeout:60000,
					error: function(e) {
						alert("错了");
					},
					success: function(result){
						alert("删除成功");
						c1.html(result);
					}
				});
			}
				
				function ajaxGotoSearch(currentPage,Search1,Search2,Search3){
					var c2 = $(".content1");				
					c2.html("<br/><img src='../img/asd.jpg'/>");
				//	alert(Search1+","+Search2+","+Search3+","+Search4);
					var url = '';
					if ((Search1==null || Search1 == undefined || Search1 == '')
							&&(Search2==null || Search2 == undefined || Search2 == '')
							&&(Search3==null || Search3 == undefined || Search3 == '')
							){
						url = '${pageContext.request.contextPath }/supplier/ajaxGotoPage';
					}else{
						url = '${pageContext.request.contextPath }/supplier/ajaxSearchSupplier';
					}
					//alert(url);
					
					$.ajax({
						url:url,
						type:'post',
						data:{
							currentPage:currentPage,
							Search1:Search1,
							Search2:Search2					
						},
						dataType:'text',
						timeout:60000,
						error: function(e) {
							alert("错了");
						},
						success: function(result){
							//alert(result);
							c2.html(result);
						}
					});
				}
				function gotoPageByInput(){
					Search1 = document.getElementById('search1').value;
					Search2 = document.getElementById('search2').value;
					
					var currentPage=document.getElementById('goInput').value;
					ajaxGotoSearch(parseInt(currentPage),Search1,Search2);
				}
				function gotoPageBySearch(event){
					Search1 = document.getElementById('search1').value;
					Search2 = document.getElementById('search2').value;
					
					//alert(SearchString);
					//alert(event.data.currentPage+","+SearchString);
					ajaxGotoSearch(event.data.currentPage,Search1,Search2);
				}
				var Search1,Search2,Search3;
			var last = '${pageEntity.currentPage-1}',
			 next = '${pageEntity.currentPage+1}',
			 end  ='${pageEntity.totalsPage}';
			
			
			$(".pager").on("click",".frist",{currentPage:"1"},gotoPageBySearch);
			$(".pager").on("click",".end",{currentPage:end},gotoPageBySearch);
			var current = '${pageEntity.currentPage}', 
			totals = '${pageEntity.totalsPage}';
			if (current<totals) {
				$(".pager").on("click",".next",{currentPage:next},gotoPageBySearch);	
			}
			if (current!=1) {
				$(".pager").on("click",".last",{currentPage:last},gotoPageBySearch);	
			}
			if(current==1){
				$(".pager").off("click",".last",{currentPage:last},gotoPageBySearch);
			} if(current==totals && current!=1){
				$(".pager").on("click",".last",{currentPage:last},gotoPageBySearch);
				$(".pager").off("click",".next",{currentPage:next},gotoPageBySearch);
			}
</script> 
</html>