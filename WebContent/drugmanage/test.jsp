<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
</head>
<body>
	<div class="tab">
						<ul class="tabtic0">
							<li>药品编号</li>
							<li>药品名称</li>
							<li>药品类别</li>
							<li>药品规格</li>
							<li>生产厂家</li>
							<li>生产日期</li>
							<li>失效日期</li>
							<li>进货单价</li>
							<li>零售价</li>
							<li>操作</li>
						</ul>
	<ul class="tabtic1">
	<c:forEach var="drugDto" items="${requestScope.drugListDto}">
		<ul class="tabtic3">
			<li>${drugDto.did}</li>
			<li>${drugDto.dname}</li>
			<li>${drugDto.dcategory}</li>
			<li>${drugDto.dspec}</li>
			<li>${drugDto.dvender}</li>
			<li>${drugDto.date_begin}</li>
			<li>${drugDto.date_end}</li>
			<li>${drugDto.dprice}</li>
			<li>${drugDto.retail_price}</li>
			<li>
				<a href="javascript:edit('${drugDto.did}')">编辑</a>
				<a href="javascript:xianshi('${drugDto.did}')">删除</a>
			</li>
		</ul>
		</c:forEach>
	</ul>

	</div>
  <jsp:include page="./page_ajax.jsp" />
  
</body>
 <script type="text/javascript">
 function xianshi(un){	
		    var mymessage=confirm("删除药品将会使与药品有关的信息全部删除！\r确定删除吗？");  
		    if(mymessage==true)  
		    {  
		    	del(un);  
		    }   		  
	}
 	 function edit(did){
 		var c1 = $(".ajaxcon");
 		$.ajax({
			url:"/HIS/drug/getDrug",
			type:'post',
			data:{
				did:did					
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
	 function del(did) {
		 var c1 = $(".ajaxcon");
	 		$.ajax({
				url:"/HIS/drug/delDrug",
				type:'post',
				data:{
					did:did					
				},
				dataType:'text',
				timeout:60000,
				error: function(e) {
					alert("删除失败");
				},
				success: function(result){
					alert("删除成功");
					c1.html(result);
				}
			});
		}
			
			function ajaxGotoSearch(currentPage,Search1,Search2,Search3,Search4){
				var c2 = $(".content1");				
				c2.html("<br/><img src='../img/asd.jpg'/>");
				//alert(Search1+","+Search2+","+Search3+","+Search4);
				var url = '';
				if ((Search1==null || Search1 == undefined || Search1 == '')
						&&(Search2==null || Search2 == undefined || Search2 == '')
						&&(Search3==null || Search3 == undefined || Search3 == '')
						&&(Search4==null || Search4 == undefined || Search4 == '')){
					url = '${pageContext.request.contextPath }/drug/ajaxGotoPage';
				}else{
					url = '${pageContext.request.contextPath }/drug/ajaxSearchDrug';
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
						Search4:Search4						
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
				var currentPage=document.getElementById('goInput').value;
				ajaxGotoSearch(parseInt(currentPage),Search1,Search2,Search3,Search4);
			}
			function gotoPageBySearch(event){
				Search1 = document.getElementById('search1').value;
				Search2 = document.getElementById('search2').value;
				Search3 = document.getElementById('search3').value;
				Search4 = document.getElementById('search4').value;
				//alert(SearchString);
				//alert(event.data.currentPage+","+SearchString);
				ajaxGotoSearch(event.data.currentPage,Search1,Search2,Search3,Search4);
			}
			var Search1,Search2,Search3,Search4;
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