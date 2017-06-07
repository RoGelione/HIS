package com.dg.service;

import java.io.IOException;
import java.util.Calendar;

import com.dg.Utils.StringUtil;
import com.dg.dao.KucunDao;
import com.dg.dao.PageDao;
import com.dg.entity.Kucun;
import com.dg.entity.Page;

public class KucunServiceImpl implements KucunService {
	private KucunDao kucunDao;
	public void setKucunDao(KucunDao kucunDao){
		this.kucunDao = kucunDao;
	}
	private PageDao<Kucun> pageDao;
	public void setPageDao(PageDao<Kucun> pageDao) {
		this.pageDao= pageDao;
	}
	@Override
	public Kucun getKucun(String kid) {
		// TODO Auto-generated method stub
		return kucunDao.getKucun(kid);
	}
	@Override
	public Page<Kucun> getKucunList(String kid, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Kucun> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from Kucun k where 1=1 ");   
		        countHQL.append("select count(*) from Kucun k where 1=1 ");   
		          
		        if (!StringUtil.isEmpty(kid)) {  
		            sbHQL.append(" and k.kid like :kid");  
		            countHQL.append(" and k.kid like :kid");  
		        }  
		        sbHQL.append(" order by k.kid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}

	@Override
	public Page<Kucun> getSkucunList(String Search1,String Search2,String Search3,int currentPage,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Kucun> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer(); 
		    System.out.println(Search1+","+Search2);
		    try {  
		    	sbHQL.append( "from Kucun k where 1=1"); 
		        countHQL.append("select count(*) from Kucun k where 1=1");
		        if(!StringUtil.isEmpty(Search1)){
		        	sbHQL.append( " and k.kid like'%"+Search1+"%'"); 
			        countHQL.append(" and k.kid like'%"+Search1+"%'");
		        }
		        if (!StringUtil.isEmpty(Search2)) {
		        	sbHQL.append( " and k.kdname like'%"+Search2+"%'"); 
			        countHQL.append(" and k.kdname like'%"+Search2+"%'");      
		        } 
		        if(!StringUtil.isEmpty(Search3)){
		        	sbHQL.append( " and k.dvender like'%"+Search3+"%'"); 
			        countHQL.append(" and k.dvender like'%"+Search3+"%'");
		        }
		        
		        sbHQL.append(" order by k.kid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
	public Page<Kucun> getGuoqiList(int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		
		 Page<Kucun> page = null; 
		 	Calendar now = Calendar.getInstance();
		 	int nyear = now.get(Calendar.YEAR); 
		 	int nmonth = now.get(Calendar.MONTH) + 1;
		 	int nday = now.get(Calendar.DAY_OF_MONTH); 
		 	StringBuffer ntime = new StringBuffer();
		 	ntime.append(nyear+"-"+nmonth+"-"+nday);
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from Kucun k where date_end <='"+ntime.toString()+"'");   
		        countHQL.append("select count(*) from Kucun k where date_end <='"+ntime.toString()+"'");   
		        sbHQL.append(" order by k.kid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
	@Override
	public String getKnum(String did) {
		// TODO Auto-generated method stub
		return kucunDao.getKnum(did);
	}
	@Override
	public boolean delKucun(String kid) {
		// TODO Auto-generated method stub
		return kucunDao.delKucun(kid);
	}
}
