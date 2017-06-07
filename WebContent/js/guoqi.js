$(function(){
			 var g =$(".guoqi"),arrend,endTime,endTimes;
			var danqian = new Date().getTime();
			for (var i=0;i<g.length;i++) {
				arrend = g[i].innerHTML.split("-");
				endTime = new Date(arrend[0], arrend[1]-2, arrend[2]);
				endTimes = endTime.getTime();
				if (endTimes<=danqian) {
		   		g[i].style.color="red";
					}else{
					g[i].style.color="black";	
					}
			}		
});
