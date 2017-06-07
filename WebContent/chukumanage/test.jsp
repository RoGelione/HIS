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
						<ul class="tabtic8">
							<li>出库编号</li>
							<li>药品编号</li>
							<li>出库时间</li>
							<li>操作类型</li>
							<li>出库数量</li>
							<li>销售金额</li>
							<li>出库人姓名</li>
							<li>操作</li>
						</ul>

	
	<ul class="tabtic1">
	<c:forEach var="chukuDto" items="${requestScope.chukuListDto}">
		<ul class="tabtic9">
			<li>${chukuDto.cid}</li>
			<li>${chukuDto.did}</li>
			<li>${chukuDto.cdate}</li>
			<li>${chukuDto.ctype}</li>
			<li>${chukuDto.cnumber}</li>
			<li class="price">${chukuDto.cprice}</li>
			<li>${chukuDto.realname}</li>
			<li>
				<a href="javascript:edit('${chukuDto.cid}')">追加出库数量</a>
			</li>
		</ul>
	</c:forEach>
	</ul>
	<ul class="tabtic8">
							<li>合计金额</li>						
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
 	 function edit(cid){
 		var c1 = $(".ajaxcon");
 		//alert(cid);
 		$.ajax({
			url:"/HIS/chuku/getChuku",
			type:'post',
			data:{
				cid:cid					
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				//alert(url);
				alert("错了");
			},
			success: function(result){
				//alert(result);
				c1.html(result);
			}
		});
 	 }
			
		 	function ajaxGotoSearch(currentPage,Search1,Search2,Search3,Search4,Search5){
				var c2 = $(".content1");				
				c2.html("<br/><img src='../img/asd.jpg'/>");
				//alert(Search1+","+Search2+","+Search3);
				var url = '';
				if ((Search1==null || Search1 == undefined || Search1 == '')
						&&(Search2==null || Search2 == undefined || Search2 == '')
						&&(Search3==null || Search3 == undefined || Search3 == '')
						&&(Search4==null || Search4 == undefined || Search4 == '')
						&&(Search5==null || Search5 == undefined || Search5 == '')){
					url = '${pageContext.request.contextPath }/chuku/ajaxGotoPage';
				}else{
					url = '${pageContext.request.contextPath }/chuku/ajaxSearchChuku';
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
				ajaxGotoSearch(parseInt(currentPage),Search1,Search2,Search3);
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