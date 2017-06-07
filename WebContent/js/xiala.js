$(function() {
	$(".demo1 u").on("click",function(){
		$(this).siblings().toggle(200);
	});
	$(".demo1").on("click",function(){
		$(this).addClass('white');  
        $(this).siblings().removeClass('white');
	});
});
