package com.dg.dao;

import com.dg.entity.Drug;

public interface DrugDao {
	public Drug getDrug(String did);
	
	public void addDrug(Drug drug);
	
	public boolean delDrug(String did);
	
	public boolean updateDrug(Drug drug);
	
	public boolean findDrug(String did);
}
