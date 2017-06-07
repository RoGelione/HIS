package com.dg.service;

import com.dg.entity.Kucun;
import com.dg.entity.Page;

public interface KucunService {
	public Kucun getKucun(String kid);
	public String getKnum(String did);
	public boolean delKucun(String kid);
	public Page<Kucun> getKucunList(String did,int currentPage,int pageSize) throws Exception;
	
	public Page<Kucun> getSkucunList(String Search1,String Search2,String Search3,int currentPage,int pageSize) throws Exception;

	public Page<Kucun> getGuoqiList(int currentPage, int pageSize) throws Exception;
}
