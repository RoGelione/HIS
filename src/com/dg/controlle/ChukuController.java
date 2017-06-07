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
		//��ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//ǰ̨���ݴ�����̨��ѯ
		String cid = ServletRequestUtils.getStringParameter(request, "cid");
		
		
		Page<Chuku> page = chukuPageService.getChukuList(cid, currentPage, pageSize);
		
		//���ݷ���ǰ̨
		request.setAttribute("cid", cid);
		
		request.setAttribute("chukuListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchChuku(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���ҷ�ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//ǰ̨���ݴ�����̨��ѯ
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
	/*��ʼ����ҳ*/
	@RequestMapping("/doShowChuku")
	public String doShowChuku(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showChuku(request, response);
		
		return "/chukumanage/chukumanage_table";
	}
	 /*������ҳ����һҳ��һҳ*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showChuku(request, response);  
          
        return "/chukumanage/test"; 
        
    } 
    /*�������ҳ*/
    @RequestMapping("/ajaxSearchChuku")  
    public String ajaxSearchChuku(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchChuku(request, response);  
          
        return "/chukumanage/test";  //ˢ�µ��������������Ҫ�滻���Ƿ��غ��ҳ�棬include��Ӧ������Ҫˢ�µ�ҳ�档
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
		response.setContentType("application/json");//ajax���ص���json�ַ����ſ��Խ��������Է��ظ�ʽΪjson
		//return "result";	
		try {
			PrintWriter out = response.getWriter();//Write ������ָ�����ַ���д����ǰ�� HTTP �����
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
