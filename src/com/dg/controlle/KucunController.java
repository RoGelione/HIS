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
		//��ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//ǰ̨���ݴ�����̨��ѯ
		String kid = ServletRequestUtils.getStringParameter(request, "kid");
		
		
		Page<Kucun> page = kucunPageService.getKucunList(kid, currentPage, pageSize);
		
		//���ݷ���ǰ̨
		request.setAttribute("kid", kid);
		
		request.setAttribute("kucunListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchKucun(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���ҷ�ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//ǰ̨���ݴ�����̨��ѯ
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		String Search3 = ServletRequestUtils.getStringParameter(request, "Search3");	
		Page<Kucun> page = kucunPageService.getSkucunList(Search1,Search2,Search3,currentPage, pageSize);

		
		request.setAttribute("kucunListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	private void doSearchguoqi(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���ҷ�ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//ǰ̨���ݴ�����̨��ѯ
		Page<Kucun> page = kucunPageService.getGuoqiList(currentPage, pageSize);

		
		request.setAttribute("kucunListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	
	/*���ڷ�ҳ*/
	@RequestMapping("/ajaxGotoguoqi")
	public String doShowguoqi(HttpServletRequest request, HttpServletResponse response) throws Exception{
		doSearchguoqi(request, response);
		return "/kucunmanage/test";
	}
	 /*��ʼ����ҳ*/
		@RequestMapping("/doShowKucun")
		public String doShowKucun(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			showKucun(request, response);
			
			return "/kucunmanage/kucunmanage_table";
		}
		 /*������ҳ����һҳ��һҳ*/
	    @RequestMapping("/ajaxGotoPage")  
	    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	showKucun(request, response);  
	          
	        return "/kucunmanage/test"; 
	        
	    } 
	    /*�������ҳ*/
	    @RequestMapping("/ajaxSearchKucun")  
	    public String ajaxSearchKucun(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	          
	    	doSearchKucun(request, response);  
	          
	        return "/kucunmanage/test";  //ˢ�µ��������������Ҫ�滻���Ƿ��غ��ҳ�棬include��Ӧ������Ҫˢ�µ�ҳ�档
	    }
	    

	    @RequestMapping("/addRuku2")/*�ӿ�������������*/
		public String addRuku2(String kid,HttpServletRequest request){
			
			request.setAttribute("kucun", kucunDaoService.getKucun(kid));
		
			return "/rukumanage/addRuku2";
		}
	    @RequestMapping("/addChuku2")/*�ӿ��������ĳ����*/
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
