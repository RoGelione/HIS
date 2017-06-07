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
	<ul class="tabtic">
		<li>用户名</li>
		<li>真实姓名</li>
		<li>职务</li>
		<li>联系电话</li>
		<li>操作</li>
	</ul>
	<ul class="tabtic1">
	<c:forEach var="userDto" items="${requestScope.userListDto}">
		<ul class="tabtic2">
		<li>${userDto.uname}</li>
		<li>${userDto.realname}</li>
		<li>${userDto.flag}</li>
		<li>${userDto.uphone}</li>
		<li>
			<a href="javascript:edit('${userDto.uname}')">编辑</a>
			<a href="javascript:xianshi('${userDto.uname}')">删除</a>
		</li>
	</ul>
	</c:forEach>
	</ul>
	</div>
  <jsp:include page="./page_ajax.jsp" />
  
</body>
 <script type="text/javascript">	
	function xianshi(un){	
		    var mymessage=confirm("删除用户将会使与出入库人员姓名有关的信息全部删除！\r确定删除吗？");  
		    if(mymessage==true)  
		    {  
		    	del(un);  
		    }   		  
	}
	 function edit(uname){
	 		var c1 = $(".ajaxcon");
	 		$.ajax({
				url:"/HIS/user/getUser",
				type:'post',
				data:{
					uname:uname					
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
		 function del(uname) {
				var c1 = $(".ajaxcon");
		 		$.ajax({
					url:"/HIS/user/delUser",
					type:'post',
					data:{
						uname:uname					
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
				
				function ajaxGotoSearch(currentPage,Search1,Search2,Search3){
					var c2 = $(".content1");				
					c2.html("<br/><img src='../img/asd.jpg'/>");
				//	alert(Search1+","+Search2+","+Search3+","+Search4);
					var url = '';
					if ((Search1==null || Search1 == undefined || Search1 == '')
							&&(Search2==null || Search2 == undefined || Search2 == '')
							&&(Search3==null || Search3 == undefined || Search3 == '')
							){
						url = '${pageContext.request.contextPath }/user/ajaxGotoPage';
					}else{
						url = '${pageContext.request.contextPath }/user/ajaxSearchUser';
					}
					//alert(url);
					
					$.ajax({
						url:url,
						type:'post',
						data:{
							currentPage:currentPage,
							Search1:Search1,
							Search2:Search2,
							Search3:Search3					
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
					
					var currentPage=document.getElementById('goInput').value;
					ajaxGotoSearch(parseInt(currentPage),Search1,Search2,Search3);
				}
				function gotoPageBySearch(event){
					Search1 = document.getElementById('search1').value;
					Search2 = document.getElementById('search2').value;
					Search3 = document.getElementById('search3').value;
					
					//alert(SearchString);
					//alert(event.data.currentPage+","+SearchString);
					ajaxGotoSearch(event.data.currentPage,Search1,Search2,Search3);
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