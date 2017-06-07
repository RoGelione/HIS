$(function () {
	$(".demo1").on("click","a",function(){
		var s = $(this).html();
		var url ='' ;
		switch (s){
			case "- 查看用户信息":
			url = "/HIS/user/doShowUser";
				ajaxGoto(url);
				break;
			case "- 新增用户信息":
				url = "/HIS/user/toAddUser";
				ajaxGoto(url);
				break;
			case "- 查看药品信息":
			url = "/HIS/drug/doShowDrug";
				ajaxGoto(url);
				break;
			case "- 新增药品信息":
				url = "/HIS/drug/toAddDrug";
				ajaxGoto(url);
				break;
			case "- 查看入库单":
				url = "/HIS/ruku/doShowRuku";
				ajaxGoto(url);
				break;
			case "- 新增入库单":
				url = "/HIS/ruku/toAddRuku";
				ajaxGoto(url);
				break;
			case "- 查看药品库存":
				url = "/HIS/kucun/doShowKucun";
				ajaxGoto(url);
				alert("失效日期是红字则代表药物失效或即将失效，请留意。");
				break;
			case "- 查看出库单":
				url = "/HIS/chuku/doShowChuku";
				ajaxGoto(url);
				break;
			case "- 新增出库单":
				url = "/HIS/chuku/toAddChuku";
				ajaxGoto(url);
				break;
			case "- 新增厂家信息":
				url = "/HIS/supplier/toAddSupplier";
				ajaxGoto(url);
				break;
			case "- 查看厂家信息":
				url = "/HIS/supplier/doShowSupplier";
				ajaxGoto(url);
				break;
			default:
				break;
		}
	});
	function ajaxGoto(url){				
				var ajax = $(".ajaxcon");
				ajax.html("<br/><img src='../img/aa.png'/>");
				$.ajax({
					url:url,
					type:'post',
					timeout:60000,
					error: function(e) {
						alert("加载出错了，请检查网络或数据库的连接");
						ajax.html("<br/><img src='../img/jiazaicuo.jpg'/>");
					},
					success: function(result){						
						ajax.html(result);
					}
				});			
			}
});