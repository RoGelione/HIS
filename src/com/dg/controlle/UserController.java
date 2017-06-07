package com.dg.controlle;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Page;
import com.dg.entity.User;
import com.dg.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userDaoService")
	private UserService userDaoService;
	@Resource(name="userPageService")  
	private UserService userPageService;
	private void showUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		
		
		Page<User> page = userPageService.getUserList(currentPage, pageSize);
		

		
		request.setAttribute("userListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���ҷ�ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//ǰ̨���ݴ�����̨��ѯ
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		String Search3 = ServletRequestUtils.getStringParameter(request, "Search3");	
		Page<User> page = userPageService.getSuerList(Search1,Search2,Search3,currentPage, pageSize);

		
		request.setAttribute("userListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	@RequestMapping("/denglu")
	public String denglu(){
		return "/login/login";	
	}
	 /*��ʼ����ҳ*/
	@RequestMapping("/doShowUser")
	public String doShowUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showUser(request, response);
		
		return "/usermanage/usermanage_table";
	}
	 /*������ҳ����һҳ��һҳ*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showUser(request, response);  
          
        return "/usermanage/test"; 
        
    } 
    /*�������ҳ*/
    @RequestMapping("/ajaxSearchUser")  
    public String ajaxSearchUser(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchUser(request, response);  
          
        return "/usermanage/test";  //ˢ�µ��������������Ҫ�滻���Ƿ��غ��ҳ�棬include��Ӧ������Ҫˢ�µ�ҳ�档
    } 

//	@RequestMapping("/getAllUser")
//	public String getAllUser(HttpServletRequest request){
//		
//		request.setAttribute("userList", userDaoService.getAllUser());
//		
//		return "/index";
//	}
	
	@RequestMapping("/getUser")
	public String getUser(String uname,HttpServletRequest request){
		
		request.setAttribute("user", userDaoService.getUser(uname));
	
		return "/usermanage/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/usermanage/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userDaoService.addUser(user);
		
		return "redirect:/user/doShowUser";
	}
	
	@RequestMapping("/delUser")
	public String delUser(String uname,HttpServletRequest request){
	
		userDaoService.delUser(uname);
		return "redirect:/user/doShowUser";
		
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request) throws Exception{
		
		if(userDaoService.updateUser(user)){
			user = userDaoService.getUser(user.getUname());
			request.setAttribute("user", user);
			return "redirect:/user/doShowUser";
		}else{
			return "/error";
		}
	}
	
	@RequestMapping("/choose")
	public String choose(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String uname = ServletRequestUtils.getStringParameter(request, "uname");
		String pwd = ServletRequestUtils.getStringParameter(request, "pwd");
		String flag = ServletRequestUtils.getStringParameter(request, "C");
		if("ϵͳ����Ա".equals(flag)){
			User user = userDaoService.getUser(uname);
			if(user==null){
				return "/login/error";
			}else{
			String upwd = user.getUpassword();//���ݿⱣ�������	
			String f = user.getFlag();//�Ƿ��ǹ���Ա
			if(pwd.equals(upwd)&&"ϵͳ����Ա".equals(f)){
				request.setAttribute("flag","1");
			String rname = user.getRealname();
				request.setAttribute("realName", rname);
				return "/login/index";
			}else{
				return "/login/error";
			}
		}
		}else if("ҩƷ����Ա".equals(flag)){
			User user = userDaoService.getUser(uname);
			if(user==null){
				return "/login/error";
			}else{
			String upwd = user.getUpassword();//���ݿⱣ�������
			String f = user.getFlag();//�Ƿ��ǹ���Ա
			if(pwd.equals(upwd)&&"ҩƷ����Ա".equals(f)){
				String rname = user.getRealname();
				request.setAttribute("realName", rname);
				return "/login/index";
			}else{
				return "/login/error";
			}
		}
		}
		else{
			return "/login/error";
		}
	}			
}		

