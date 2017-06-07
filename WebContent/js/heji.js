$(function(){
			var price = $(".price"),
			money =document.getElementById("money"),
			count=0;
			
			for (var i=0;i<price.length;i++){
				count+=Number(price[i].innerHTML);
			} 
			money.innerHTML=count;
});
