package com.dg.service;

import java.io.IOException;

import com.dg.Utils.StringUtil;
import com.dg.dao.DrugDao;
import com.dg.dao.PageDao;
import com.dg.entity.Drug;
import com.dg.entity.Page;

public class DrugServiceImpl implements DrugService {
	private DrugDao drugDao;
	public void setDrugDao(DrugDao drugDao){
		this.drugDao= drugDao;
	}
	private PageDao<Drug> pageDao;
	public void setPageDao(PageDao<Drug> pageDao) {
		this.pageDao= pageDao;
	}
	@Override
	public Drug getDrug(String did) {
		// TODO Auto-generated method stub
		return drugDao.getDrug(did);
	}

	@Override
	public void addDrug(Drug drug) {
		// TODO Auto-generated method stub
		drugDao.addDrug(drug);
	}

	@Override
	public boolean delDrug(String did) {
		// TODO Auto-generated method stub
		return drugDao.delDrug(did);
	}

	@Override
	public boolean updateDrug(Drug drug) {
		// TODO Auto-generated method stub
		return drugDao.updateDrug(drug);
	}

	@Override
	public Page<Drug> getDrugList(String did, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Drug> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from Drug d where 1=1 ");   
		        countHQL.append("select count(*) from Drug d where 1=1 ");   
		          
		        if (!StringUtil.isEmpty(did)) {  
		            sbHQL.append(" and d.did like :did");  
		            countHQL.append(" and d.did like :did");  
		        }  
		        sbHQL.append(" order by d.did asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}

	@Override
	public Page<Drug> getSdrugList(String Search1,String Search2,String Search3,String Search4,int currentPage,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Drug> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer(); 
		    System.out.println(Search1+","+Search2+","+Search3+","+Search4);
		    try {  
		    	sbHQL.append( "from Drug d where 1=1"); 
		        countHQL.append("select count(*) from Drug d where 1=1");
		        if (!StringUtil.isEmpty(Search1)) {
		        	sbHQL.append( " and d.dname like'%"+Search1+"%'"); 
			        countHQL.append(" and d.dname like'%"+Search1+"%'");      
		        } 
		        if(!StringUtil.isEmpty(Search2)){
		        	sbHQL.append( " and d.dcategory like'%"+Search2+"%'"); 
			        countHQL.append(" and d.dcategory like'%"+Search2+"%'");
		        }
		        if(!StringUtil.isEmpty(Search3)){
		        	sbHQL.append( " and d.dspec like'%"+Search3+"%'"); 
			        countHQL.append(" and d.dspec like'%"+Search3+"%'");
		        }
		        if(!StringUtil.isEmpty(Search4)){
		        	sbHQL.append( " and d.dvender like'%"+Search4+"%'"); 
			        countHQL.append(" and d.dvender like'%"+Search4+"%'");
		        }
		        sbHQL.append(" order by d.did asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
	@Override
	public boolean findDrug(String did) {
		// TODO Auto-generated method stub
		return drugDao.findDrug(did);
	}

}
