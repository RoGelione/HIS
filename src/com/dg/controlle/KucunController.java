package com.dg.controlle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Kucun;
import com.dg.entity.Page;
import com.dg.service.KucunService;

@Controller
@RequestMapping("/kucun")
public class KucunController {
	@Resource(name="kucunDaoService")
	private KucunService kucunDaoService;
	@Resource(name="kucunPageService")
	private KucunService kucunPageService;
	private void showKucun(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//前台数据传到后台查询
		String kid = ServletRequestUtils.getStringParameter(request, "kid");
		
		
		Page<Kucun> page = kucunPageService.getKucunList(kid, currentPage, pageSize);
		
		//数据返回前台
		request.setAttribute("kid", kid);
		
		request.setAttribute("kucunListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchKucun(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//前台数据传到后台查询
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		String Search3 = ServletRequestUtils.getStringParameter(request, "Search3");	
		Page<Kucun> page = kucunPageService.getSkucunList(Search1,Search2,Search3,currentPage, pageSize);

		
		request.setAttribute("kucunListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	private void doSearchguoqi(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//前台数据传到后台查询
		Page<Kucun> page = kucunPageService.getGuoqiList(currentPage, pageSize);

		
		request.setAttribute("kucunListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	
	/*过期分页*/
	@RequestMapping("/ajaxGotoguoqi")
	public String doShowguoqi(HttpServletRequest request, HttpServletResponse response) throws Exception{
		doSearchguoqi(request, response);
		return "/kucunmanage/test";
	}
	 /*初始化分页*/
		@RequestMapping("/doShowKucun")
		public String doShowKucun(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			showKucun(request, response);
			
			return "/kucunmanage/kucunmanage_table";
		}
		 /*正常分页，上一页下一页*/
	    @RequestMapping("/ajaxGotoPage")  
	    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	showKucun(request, response);  
	          
	        return "/kucunmanage/test"; 
	        
	    } 
	    /*搜索后分页*/
	    @RequestMapping("/ajaxSearchKucun")  
	    public String ajaxSearchKucun(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	doSearchKucun(request, response);  
	          
	        return "/kucunmanage/test";  //刷新的是这个，所以需要替换的是返回后的页面，include中应该是需要刷新的页面。
	    }
	    

	    @RequestMapping("/addRuku2")/*从库存表新增的入库表*/
		public String addRuku2(String kid,HttpServletRequest request){
			
			request.setAttribute("kucun", kucunDaoService.getKucun(kid));
		
			return "/rukumanage/addRuku2";
		}
	    @RequestMapping("/addChuku2")/*从库存表新增的出库表*/
		public String addChuku2(String kid,HttpServletRequest request){
			
			request.setAttribute("kucun", kucunDaoService.getKucun(kid));
		
			return "/chukumanage/addChuku2";
		}
	    @RequestMapping("/delKucun")
		public String delDrug(String kid,HttpServletRequest request){
			
			if(kucunDaoService.delKucun(kid)){
				return "redirect:/kucun/doShowKucun";
			}else{
				return "/error";
			}
		}
}
