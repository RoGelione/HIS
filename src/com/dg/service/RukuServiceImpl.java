package com.dg.service;

import java.io.IOException;

import com.dg.Utils.StringUtil;
import com.dg.dao.PageDao;
import com.dg.dao.RukuDao;
import com.dg.entity.Page;
import com.dg.entity.Ruku;

public class RukuServiceImpl implements RukuService {
	private RukuDao rukuDao;
	public void setRukuDao(RukuDao rukuDao) {
		this.rukuDao = rukuDao;
	}
	private PageDao<Ruku> pageDao;
	public void setPageDao(PageDao<Ruku> pageDao) {
		this.pageDao= pageDao;
	}
	@Override
	public Ruku getRuku(String rid) {
		// TODO Auto-generated method stub
		return rukuDao.getRuku(rid);
	}

	@Override
	public void addRuku(Ruku ruku) {
		// TODO Auto-generated method stub
		rukuDao.addRuku(ruku);
	}

	@Override/*只允许追加入库数量*/
	public boolean updateRuku(Ruku ruku) {
		// TODO Auto-generated method stub
		return rukuDao.updateRuku(ruku);
	}

	@Override
	public Page<Ruku> getRukuList(String rid, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<Ruku> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from Ruku r where 1=1 ");   
		        countHQL.append("select count(*) from Ruku r where 1=1 ");   		          
		        if (!StringUtil.isEmpty(rid)) {  
		            sbHQL.append(" and r.rid like :rid");  
		            countHQL.append(" and r.rid like :rid");  
		        }  
		        sbHQL.append(" order by r.rid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e){
				e.printStackTrace();
			}
			return page;
	}

	@Override
	public Page<Ruku> getSrukuList(String Search1, String Search2, String Search3,String Search4, String Search5,int currentPage, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		
		Page<Ruku> page = null;  
	    StringBuffer sbHQL = new StringBuffer();  
	    StringBuffer countHQL = new StringBuffer(); 
	    try {  
	    	sbHQL.append( "from Ruku r where 1=1"); 
	        countHQL.append("select count(*) from Ruku r where 1=1");
	        if (!StringUtil.isEmpty(Search1)) {
	        	sbHQL.append( " and r.rid like'%"+Search1+"%'"); 
		        countHQL.append(" and r.rid like'%"+Search1+"%'");      
	        } 
	        if(!StringUtil.isEmpty(Search2)){
	        	sbHQL.append( " and r.rdname like'%"+Search2+"%'"); 
		        countHQL.append(" and r.rdname like'%"+Search2+"%'");
	        }
	        if(!StringUtil.isEmpty(Search3)){
	        	sbHQL.append( " and r.realname like'%"+Search3+"%'"); 
		        countHQL.append(" and r.realname like'%"+Search3+"%'");
	        }
	        if(!StringUtil.isEmpty(Search4)){
	        	sbHQL.append( " and r.rdate >= '"+Search4+"'"); 
		        countHQL.append(" and r.rdate >= '"+Search4+"'");
	        }
	        if(!StringUtil.isEmpty(Search5)){
	        	sbHQL.append( " and r.rdate <= '"+Search5+"'"); 
		        countHQL.append(" and r.rdate <= '"+Search5+"'");
	        }
	        sbHQL.append(" order by r.rid asc ");  
	        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public boolean findRuku(String rid) {
		// TODO Auto-generated method stub
		return rukuDao.findRuku(rid);
	}

}
