package com.dg.service;

import com.dg.entity.Chuku;
import com.dg.entity.Page;

public interface ChukuService {
	public Chuku getChuku(String cid);
	
	public boolean findChuku(String cid);
	
	public void addChuku(Chuku chuku);
	
	public boolean updateChuku(Chuku chuku);
	
	public Page<Chuku> getChukuList(String cid,int currentPage,int pageSize) throws Exception;
	
	public Page<Chuku> getSchukuList(String Search1,String Search2,String Search3,String Search4,String Search5,int currentPage,int pageSize) throws Exception;

	
}
