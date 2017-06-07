package com.dg.service;

import java.io.IOException;

import com.dg.Utils.StringUtil;
import com.dg.dao.PageDao;
import com.dg.dao.SupplierDao;
import com.dg.entity.Page;
import com.dg.entity.Supplier;

public class SupplierServiceImpl implements SupplierService {
	private SupplierDao supplierDao;
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	private PageDao<Supplier> pageDao;
	public void setPageDao(PageDao<Supplier> pageDao) {
		this.pageDao= pageDao;
	}
	@Override
	public Supplier getSupplier(String sid) {
		// TODO Auto-generated method stub
		return supplierDao.getSupplier(sid);
	}

	@Override
	public void addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierDao.addSupplier(supplier);
	}

	@Override
	public boolean delSupplier(String sid) {
		// TODO Auto-generated method stub
		return supplierDao.delSupplier(sid);
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDao.updateSupplier(supplier);
	}

	@Override
	public Page<Supplier> getSupplierList(String sid, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Supplier> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from Supplier s where 1=1 ");   
		        countHQL.append("select count(*) from Supplier s where 1=1 ");   
		          
		        if (!StringUtil.isEmpty(sid)) {  
		            sbHQL.append(" and s.sid like :sid");  
		            countHQL.append(" and s.sid like :sid");  
		        }  
		        sbHQL.append(" order by s.sid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}

	@Override
	public Page<Supplier> getSsupplierList(String Search1, String Search2, int currentPage, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		Page<Supplier> page = null;  
	    StringBuffer sbHQL = new StringBuffer();  
	    StringBuffer countHQL = new StringBuffer(); 
	    try {  
	    	sbHQL.append( "from Supplier s where 1=1"); 
	        countHQL.append("select count(*) from Supplier s where 1=1");
	        if (!StringUtil.isEmpty(Search1)) {
	        	sbHQL.append( " and s.dvender like'%"+Search1+"%'"); 
		        countHQL.append(" and s.dvender like'%"+Search1+"%'");      
	        } 
	        if(!StringUtil.isEmpty(Search2)){
	        	sbHQL.append( " and s.saddress like'%"+Search2+"%'"); 
		        countHQL.append(" and s.saddress like'%"+Search2+"%'");
	        }
	        sbHQL.append(" order by s.sid asc ");  
	        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public boolean findSupplier(String dvender) {
		// TODO Auto-generated method stub
		return supplierDao.findSupplier(dvender);
	}

}
