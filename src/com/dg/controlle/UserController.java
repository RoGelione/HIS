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
		//分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		
		
		Page<User> page = userPageService.getUserList(currentPage, pageSize);
		

		
		request.setAttribute("userListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找分页
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//前台数据传到后台查询
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
	 /*初始化分页*/
	@RequestMapping("/doShowUser")
	public String doShowUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showUser(request, response);
		
		return "/usermanage/usermanage_table";
	}
	 /*正常分页，上一页下一页*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showUser(request, response);  
          
        return "/usermanage/test"; 
        
    } 
    /*搜索后分页*/
    @RequestMapping("/ajaxSearchUser")  
    public String ajaxSearchUser(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchUser(request, response);  
          
        return "/usermanage/test";  //刷新的是这个，所以需要替换的是返回后的页面，include中应该是需要刷新的页面。
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
		if("系统管理员".equals(flag)){
			User user = userDaoService.getUser(uname);
			if(user==null){
				return "/login/error";
			}else{
			String upwd = user.getUpassword();//数据库保存的密码	
			String f = user.getFlag();//是否是管理员
			if(pwd.equals(upwd)&&"系统管理员".equals(f)){
				request.setAttribute("flag","1");
			String rname = user.getRealname();
				request.setAttribute("realName", rname);
				return "/login/index";
			}else{
				return "/login/error";
			}
		}
		}else if("药品管理员".equals(flag)){
			User user = userDaoService.getUser(uname);
			if(user==null){
				return "/login/error";
			}else{
			String upwd = user.getUpassword();//数据库保存的密码
			String f = user.getFlag();//是否是管理员
			if(pwd.equals(upwd)&&"药品管理员".equals(f)){
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

