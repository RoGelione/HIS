package com.dg.service;


import com.dg.entity.Ruku;
import com.dg.entity.Page;

public interface RukuService {
	public Ruku getRuku(String rid);
	
	public boolean findRuku(String rid);
	
	public void addRuku(Ruku ruku);
	
	public boolean updateRuku(Ruku ruku);/*只允许追加入库数量*/
	
	public Page<Ruku> getRukuList(String rid,int currentPage,int pageSize) throws Exception;
	
	public Page<Ruku> getSrukuList(String Search1,String Search2,String Search3,String Search4,String Search5,int currentPage,int pageSize) throws Exception;
}
