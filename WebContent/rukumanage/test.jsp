<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/heji.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="tab">
						<ul class="tabtic4">
							<li>入库编号</li>
							<li>药品编号</li>
							<li>药品名称</li>
							<li>生产厂家</li>
							<li>生产日期</li>
							<li>失效日期</li>
							<li>操作类型</li>
							<li>入库时间</li>
							<li>数量</li>
							<li>进货金额</li>
							<li>入库人姓名</li>
							<li>操作</li>
						</ul>

	
	<ul class="tabtic1">
	<c:forEach var="rukuDto" items="${requestScope.rukuListDto}">
		<ul class="tabtic5">
			<li>${rukuDto.rid}</li>
			<li>${rukuDto.did}</li>
			<li>${rukuDto.rdname}</li>
			<li>${rukuDto.dvender}</li>
			<li>${rukuDto.date_begin}</li>
			<li>${rukuDto.date_end}</li>
			<li>${rukuDto.rtype}</li>
			<li>${rukuDto.rdate}</li>
			<li>${rukuDto.rnumber}</li>
			<li class="price">${rukuDto.rprice}</li>
			<li>${rukuDto.realname}</li>
			<li>
				<a href="javascript:edit('${rukuDto.rid}')">追加入库</a>
			</li>
		</ul>
	</c:forEach>
	</ul>
						<ul class="tabtic4">
							<li>合计金额</li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li id="money"></li>
							<li></li>
							<li></li>
						</ul>
	</div>
  <jsp:include page="./page_ajax.jsp" />
  
</body>
 <script type="text/javascript">
/*/HIS/drug/getDrug?did=${drugDto.did}*/
 	 function edit(rid){
 		var c1 = $(".ajaxcon");
 		//alert(rid);
 		$.ajax({
			url:"/HIS/ruku/getRuku",
			type:'post',
			data:{
				rid:rid					
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				//alert(url);
				alert("出错了");
			},
			success: function(result){
				//alert(result);
				c1.html(result);
			}
		});
 	 }
			
			function ajaxGotoSearch(currentPage,Search1,Search2,Search3,Search4,Search5){
				var c2 = $(".content1");				
				c2.html("<br/><img src='../img/aa.png'/>");
				//alert(Search1+","+Search2+","+Search3);
				var url = '';
				if ((Search1==null || Search1 == undefined || Search1 == '')
						&&(Search2==null || Search2 == undefined || Search2 == '')
						&&(Search3==null || Search3 == undefined || Search3 == '')
						&&(Search4==null || Search4 == undefined || Search4 == '')
						&&(Search5==null || Search5 == undefined || Search5 == '')){
					url = '${pageContext.request.contextPath }/ruku/ajaxGotoPage';
				}else{
					url = '${pageContext.request.contextPath }/ruku/ajaxSearchRuku';
				}
				//alert(url);				
				$.ajax({
					url:url,
					type:'post',
					data:{
						currentPage:currentPage,
						Search1:Search1,
						Search2:Search2,
						Search3:Search3,
						Search4:Search4,
						Search5:Search5	
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
				Search3 = document.getElementById('search3').value;
				Search4 = document.getElementById('search4').value;
				Search5 = document.getElementById('search5').value;
				var currentPage=document.getElementById('goInput').value;
				ajaxGotoSearch(parseInt(currentPage),Search1,Search2,Search3,Search4,Search5);
			}
			function gotoPageBySearch(event){
				Search1 = document.getElementById('search1').value;
				Search2 = document.getElementById('search2').value;
				Search3 = document.getElementById('search3').value;
				Search4 = document.getElementById('search4').value;
				Search5 = document.getElementById('search5').value;
				//alert(SearchString);
				//alert(event.data.currentPage+","+SearchString);
				ajaxGotoSearch(event.data.currentPage,Search1,Search2,Search3,Search4,Search5);
			}
			var Search1,Search2,Search3,Search4,Search5;
			var last = '${pageEntity.currentPage-1}';
			var next = '${pageEntity.currentPage+1}';
			var end  ='${pageEntity.totalsPage}';
			
			
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