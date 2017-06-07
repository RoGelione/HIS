package com.dg.controlle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Chuku;
import com.dg.entity.Page;
import com.dg.service.ChukuService;
import com.dg.service.KucunService;

@Controller
@RequestMapping("/chuku")
public class ChukuController {
	@Resource(name="chukuDaoService")
	private ChukuService chukuDaoService;
	@Resource(name="chukuPageService")  
	private ChukuService chukuPageService;
	@Resource(name="kucunDaoService")
	private KucunService kucunDaoService;
	private void showChuku(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//前台数据传到后台查询
		String cid = ServletRequestUtils.getStringParameter(request, "cid");
		
		
		Page<Chuku> page = chukuPageService.getChukuList(cid, currentPage, pageSize);
		
		//数据返回前台
		request.setAttribute("cid", cid);
		
		request.setAttribute("chukuListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchChuku(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		Page<Chuku> page = chukuPageService.getSchukuList(Search1,Search2,Search3,Search4,Search5,currentPage, pageSize);

		
		request.setAttribute("chukuListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	/*初始化分页*/
	@RequestMapping("/doShowChuku")
	public String doShowChuku(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showChuku(request, response);
		
		return "/chukumanage/chukumanage_table";
	}
	 /*正常分页，上一页下一页*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showChuku(request, response);  
          
        return "/chukumanage/test"; 
        
    } 
    /*搜索后分页*/
    @RequestMapping("/ajaxSearchChuku")  
    public String ajaxSearchChuku(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchChuku(request, response);  
          
        return "/chukumanage/test";  //刷新的是这个，所以需要替换的是返回后的页面，include中应该是需要刷新的页面。
    }
    
    @RequestMapping("/getChuku")
	public String getChuku(String cid,HttpServletRequest request){
		
		request.setAttribute("chuku", chukuDaoService.getChuku(cid));
	
		return "/chukumanage/editChuku";
	}
	
	@RequestMapping("/toAddChuku")
	public String toAddRuku(){
		return "/chukumanage/addChuku";
	}
	
	@RequestMapping("/addChuku")
	public void addChuku(Chuku chuku,HttpServletRequest request,HttpServletResponse response){
		String wid = chuku.getDid();
		String cid = chuku.getCid();
		System.out.println(cid);
		int cnum = Integer.parseInt(chuku.getCnumber());
		int knum = Integer.parseInt(kucunDaoService.getKnum(wid));
		boolean f = chukuDaoService.findChuku(cid);
		String result = new String();
		if( cnum >knum){
			result = "{\"result\":\"error\"}";			
		}
		if( cnum<=knum && !f){
			chukuDaoService.addChuku(chuku);
			result = "{\"result\":\"success\"}";
		//return "redirect:/chuku/doShowChuku";
		}
		if(f){
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
	
	@RequestMapping("/updateChuku")
	public String updateRuku(Chuku chuku,HttpServletRequest request) throws Exception{
		
		if(chukuDaoService.updateChuku(chuku)){			
			chuku = chukuDaoService.getChuku(chuku.getCid());
			request.setAttribute("chuku", chuku);
			return "redirect:/chuku/doShowChuku";
		}else{
			return "/error";
		}
	}
}
