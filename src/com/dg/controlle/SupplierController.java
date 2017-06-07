package com.dg.controlle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.entity.Page;
import com.dg.entity.Supplier;
import com.dg.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Resource(name="supplierDaoService")
	private SupplierService supplierDaoService;
	@Resource(name="supplierPageService")  
	private SupplierService supplierPageService;
	private void showSupplier(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 3);
		
		//ǰ̨���ݴ�����̨��ѯ
		String sid = ServletRequestUtils.getStringParameter(request, "sid");
//		String dvender = ServletRequestUtils.getStringParameter(request, "dvender");
//		
		
		Page<Supplier> page = supplierPageService.getSupplierList(sid,currentPage, pageSize);
//		
//		//���ݷ���ǰ̨
//		request.setAttribute("sid", sid);
//		request.setAttribute("realName", realname);
		
		request.setAttribute("supplierListDto",page.getResult());
		request.setAttribute("pageEntity",page);
	}
	private void doSearchSupplier(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���ҷ�ҳ
		int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 9);
		
		//ǰ̨���ݴ�����̨��ѯ
		String Search1 = ServletRequestUtils.getStringParameter(request, "Search1");
		System.out.println(Search1);
		String Search2 = ServletRequestUtils.getStringParameter(request, "Search2");
		Page<Supplier> page = supplierPageService.getSsupplierList(Search1,Search2,currentPage, pageSize);

		
		request.setAttribute("supplierListDto", page.getResult());
		request.setAttribute("pageEntity", page);
	}
	 /*��ʼ����ҳ*/
	@RequestMapping("/doShowSupplier")
	public String doShowSupplier(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		showSupplier(request, response);
		
		return "/suppliermanage/suppliermanage_table";
	}
	 /*������ҳ����һҳ��һҳ*/
    @RequestMapping("/ajaxGotoPage")  
    public String ajaxGotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	showSupplier(request, response);  
          
        return "/suppliermanage/test"; 
        
    } 
    /*�������ҳ*/
    @RequestMapping("/ajaxSearchSupplier")  
    public String ajaxSearchSupplier(HttpServletRequest request, HttpServletResponse response) throws Exception {  
          
    	doSearchSupplier(request, response);  
          
        return "/suppliermanage/test";  //ˢ�µ��������������Ҫ�滻���Ƿ��غ��ҳ�棬include��Ӧ������Ҫˢ�µ�ҳ�档
    }
    @RequestMapping("/getSupplier")
	public String getSupplier(String sid,HttpServletRequest request){
		
		request.setAttribute("supplier", supplierDaoService.getSupplier(sid));
	
		return "/suppliermanage/editSupplier";
	}
	
	@RequestMapping("/toAddSupplier")
	public String toAddSupplier(){
		return "/suppliermanage/addSupplier";
	}
	
	@RequestMapping("/addSupplier")
	public String addSupplier(Supplier supplier,HttpServletRequest request){
		
		supplierDaoService.addSupplier(supplier);
		
		return "redirect:/supplier/doShowSupplier";
	}
	@RequestMapping("/delSupplier")
	public String delSupplier(String sid,HttpServletRequest request){
	
		supplierDaoService.delSupplier(sid);
		return "redirect:/supplier/doShowSupplier";
		
	}
	
	@RequestMapping("/updateSupplier")
	public String updateSupplier(Supplier supplier,HttpServletRequest request) throws Exception{
		
		if(supplierDaoService.updateSupplier(supplier)){
			supplier = supplierDaoService.getSupplier(supplier.getSid());
			request.setAttribute("supplier", supplier);
			return "redirect:/supplier/doShowSupplier";
		}else{
			return "/error";
		}
	}
}
