package com.dg.service;

import com.dg.entity.Page;
import com.dg.entity.Drug;

public interface DrugService {
	public Drug getDrug(String drug);
	
	public boolean findDrug(String did);
	
	public void addDrug(Drug drug);
	
	public boolean delDrug(String did);
	
	public boolean updateDrug(Drug drug);
	
	public Page<Drug> getDrugList(String did,int currentPage,int pageSize) throws Exception;
	
	public Page<Drug> getSdrugList(String Search1,String Search2,String Search3,String Search4,int currentPage,int pageSize) throws Exception;
}
