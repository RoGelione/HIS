package com.dg.controlle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Page;
import com.dg.entity.Ruku;
import com.dg.service.DrugService;
import com.dg.service.RukuService;
import com.dg.service.SupplierService;

@Controller
@RequestMapping("/ruku")
public class RukuController {
	@Resource(name="rukuDaoService")
	private RukuService rukuDaoService;
	@Resource(name="rukuPageService")  
	private RukuService rukuPageService;
	@Resource(name="drugDaoService")  
	private DrugService drugDaoService;
	@Resource(name="supplierDaoService")  
	private SupplierService supplierDaoService;
	private void showRuku(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//前台数据传到后台查询
		String rid = ServletRequestUtils.getStringParameter(request, "rid");
		
		
		Page<Ruku> page = rukuPageService.getRukuList(rid, currentPage, pageSize);
		
		//数据返回前台
		request.setAttribute("rid", rid);
		
		request.setAttribute("rukuListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchRuku(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//前台数据传到后台查询
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		String Search3 = ServletRequestUtils.getStringParameter(request, "Search3");
		String Search4 = ServletRequestUtils.getStringParameter(request, "Search4");
		String Search5 = ServletRequestUtils.getStringParameter(request, "Search5");
		Page<Ruku> page = rukuPageService.getSrukuList(Search1,Search2,Search3,Search4,Search5,currentPage, pageSize);

		
		request.setAttribute("rukuListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	 /*初始化分页*/
		@RequestMapping("/doShowRuku")
		public String doShowRuku(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			showRuku(request, response);
			
			return "/rukumanage/rukumanage_table";
		}
		 /*正常分页，上一页下一页*/
	    @RequestMapping("/ajaxGotoPage")  
	    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	showRuku(request, response);  
	          
	        return "/rukumanage/test"; 
	        
	    } 
	    /*搜索后分页*/
	    @RequestMapping("/ajaxSearchRuku")  
	    public String ajaxSearchRuku(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	doSearchRuku(request, response);  
	          
	        return "/rukumanage/test";  //刷新的是这个，所以需要替换的是返回后的页面，include中应该是需要刷新的页面。
	    }
	    
	    @RequestMapping("/getRuku")
		public String getRuku(String rid,HttpServletRequest request){
			
			request.setAttribute("ruku", rukuDaoService.getRuku(rid));
		
			return "/rukumanage/editRuku";
		}
		
		@RequestMapping("/toAddRuku")
		public String toAddRuku(){
			return "/rukumanage/addRuku";
		}
		
		@RequestMapping("/addRuku")
		public void addRuku(Ruku ruku,HttpServletRequest request,HttpServletResponse response){
			String rid = ruku.getRid();
			String did = ruku.getDid();
			String dvender = ruku.getDvender();
			boolean f1 =rukuDaoService.findRuku(rid);//已存在入库编号，不能通过
			boolean f2 =drugDaoService.findDrug(did);//找不到药品编号，不能通过
			boolean f3 =supplierDaoService.findSupplier(dvender);//找不到厂家名称，不能通过
			String result = new String();
			if( !f2){
				result = "{\"result\":\"error2\"}";			
			}
			if( !f3){
				result = "{\"result\":\"error3\"}";			
			}
			if( !f1 && f2 && f3){
				rukuDaoService.addRuku(ruku);
				result = "{\"result\":\"success\"}";
			}
			if(f1){
				result = "{\"result\":\"find\"}";
			}
			System.out.println(result);
			response.setContentType("application/json");//ajax返回的是json字符串才可以解析，所以返回格式为json
			//return "result";	
			try {
				PrintWriter out = response.getWriter();//Write 方法将指定的字符串写到当前的 HTTP 输出。
				out.write(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/updateRuku")
		public String updateRuku(Ruku ruku,HttpServletRequest request) throws Exception{
			
			if(rukuDaoService.updateRuku(ruku)){
				
				ruku = rukuDaoService.getRuku(ruku.getRid());
				request.setAttribute("ruku", ruku);
				return "redirect:/ruku/doShowRuku";
			}else{
				return "/error";
			}
		}
//		@RequestMapping("/addRuku2")/*从库存表新增的*/
//		public String addRuku2(String kid,HttpServletRequest request){
//			
//			request.setAttribute("kucun", rukuDaoService.getRuku(kid));
//		
//			return "/rukumanage/addRuku2";
//		}
}
