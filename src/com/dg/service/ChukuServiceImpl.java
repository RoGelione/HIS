package com.dg.service;

import java.io.IOException;

import com.dg.Utils.StringUtil;
import com.dg.dao.ChukuDao;

import com.dg.dao.PageDao;
import com.dg.entity.Chuku;
import com.dg.entity.Page;

public class ChukuServiceImpl implements ChukuService{
	private ChukuDao chukuDao;
	public void setChukuDao(ChukuDao chukuDao){
		this.chukuDao = chukuDao;
	}
	private PageDao<Chuku> pageDao;
	public void setPageDao(PageDao<Chuku> pageDao) {
		this.pageDao= pageDao;
	}
	
	@Override
	public Chuku getChuku(String cid) {
		// TODO Auto-generated method stub
		return chukuDao.getChuku(cid);
	}
	
	@Override
	public void addChuku(Chuku chuku) {
		// TODO Auto-generated method stub
		chukuDao.addChuku(chuku);
	}
	
	@Override/*只允许追加出库数量*/
	public boolean updateChuku(Chuku chuku) {
		// TODO Auto-generated method stub
		return chukuDao.updateChuku(chuku);
	}
	
	@Override
	public Page<Chuku> getChukuList(String cid, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		Page<Chuku> page = null;  
	    StringBuffer sbHQL = new StringBuffer();  
	    StringBuffer countHQL = new StringBuffer();  
	    try {  
	        sbHQL.append("from Chuku c where 1=1 ");   
	        countHQL.append("select count(*) from Chuku c where 1=1 ");   
	          
	        if (!StringUtil.isEmpty(cid)) {  
	            sbHQL.append(" and c.cid like :cid");  
	            countHQL.append(" and c.cid like :cid");  
	        }  
	        sbHQL.append(" order by c.cid asc ");  
	        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public Page<Chuku> getSchukuList(String Search1, String Search2, String Search3,String Search4,String Search5, int currentPage, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		 Page<Chuku> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer(); 
		    System.out.println(Search1+","+Search2);
		    try {  
		    	sbHQL.append( "from Chuku c where 1=1"); 
		        countHQL.append("select count(*) from Chuku c where 1=1");
		        if(!StringUtil.isEmpty(Search1)){
		        	sbHQL.append( " and c.cid like'%"+Search1+"%'"); 
			        countHQL.append(" and c.cid like'%"+Search1+"%'");
		        }
		        if (!StringUtil.isEmpty(Search2)) {
		        	sbHQL.append( " and c.did like'%"+Search2+"%'"); 
			        countHQL.append(" and c.did like'%"+Search2+"%'");      
		        } 
		        if(!StringUtil.isEmpty(Search3)){
		        	sbHQL.append( " and c.realname like'%"+Search3+"%'"); 
			        countHQL.append(" and c.realname like'%"+Search3+"%'");
		        }
		        if(!StringUtil.isEmpty(Search4)){
		        	sbHQL.append( " and c.cdate >= '"+Search4+"'"); 
			        countHQL.append(" and c.cdate >= '"+Search4+"'");
		        }
		        if(!StringUtil.isEmpty(Search5)){
		        	sbHQL.append( " and c.cdate <= '"+Search5+"'"); 
			        countHQL.append(" and c.cdate <= '"+Search5+"'");
		        }
		        sbHQL.append(" order by c.cid asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}

	@Override
	public boolean findChuku(String cid) {
		// TODO Auto-generated method stub
		return chukuDao.findChuku(cid);
	}

	
}
