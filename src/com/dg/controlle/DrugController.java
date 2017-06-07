package com.dg.controlle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Drug;
import com.dg.entity.Page;
import com.dg.service.DrugService;

@Controller
@RequestMapping("/drug")
public class DrugController {
	@Resource(name="drugDaoService")
	private DrugService drugDaoService;/*药品信息代理*/
	@Resource(name="drugPageService")  
	private DrugService drugPageService;/*药品分页代理*/
	private void showDrug(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//前台数据传到后台查询
		String did = ServletRequestUtils.getStringParameter(request, "did");
		
		
		
		Page<Drug> page = drugPageService.getDrugList(did,currentPage, pageSize);
		
		//数据返回前台
		request.setAttribute("did", did);
		
		request.setAttribute("drugListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchDrug(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);		
		//前台数据传到后台查询
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		String Search3 = ServletRequestUtils.getStringParameter(request, "Search3");
		String Search4 = ServletRequestUtils.getStringParameter(request, "Search4");
		Page<Drug> page = drugPageService.getSdrugList(Search1,Search2,Search3,Search4,currentPage, pageSize);
		request.setAttribute("drugListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	 /*初始化分页*/
	@RequestMapping("/doShowDrug")
	public String doShowDrug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showDrug(request, response);
		
		return "/drugmanage/drugmanage_table";
	}
	 /*正常分页，上一页下一页*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showDrug(request, response);  
          
        return "/drugmanage/test"; 
        
    } 
    /*搜索后分页*/
    @RequestMapping("/ajaxSearchDrug")  
    public String ajaxSearchDrug(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchDrug(request, response);  
          
        return "/drugmanage/test";  //刷新的是这个，所以需要替换的是返回后的页面，include中应该是需要刷新的页面。
    } 
	
	@RequestMapping("/getDrug")
	public String getDrug(String did,HttpServletRequest request){
		
		request.setAttribute("drug", drugDaoService.getDrug(did));
	
		return "/drugmanage/editDrug";
	}
	
	@RequestMapping("/toAddDrug")
	public String toAddDrug(){
		return "/drugmanage/addDrug";
	}
	
	@RequestMapping("/addDrug")
	public String addDrug(Drug drug,HttpServletRequest request){
		
		drugDaoService.addDrug(drug);
		
		return "redirect:/drug/doShowDrug";
	}
	
	@RequestMapping("/delDrug")
	public String delDrug(String did,HttpServletRequest request){
		
		if(drugDaoService.delDrug(did)){
			return "redirect:/drug/doShowDrug";
		}else{
			return "/error";
		}
	}
	@RequestMapping("/updateDrug")
	public String updateDrug(Drug drug,HttpServletRequest request) throws Exception{
		if(drugDaoService.updateDrug(drug)){
			drug = drugDaoService.getDrug(drug.getDid());
			request.setAttribute("drug", drug);
			return "redirect:/drug/doShowDrug";
		}else{
			return "/error";
		}
	}			
}
