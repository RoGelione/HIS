package com.dg.dao;



import com.dg.entity.Supplier;

public interface SupplierDao {	
	public Supplier getSupplier(String sid);
	
	public void addSupplier(Supplier supplier);
	
	public boolean delSupplier(String sid);
	
	public boolean updateSupplier(Supplier supplier);
	
	public boolean findSupplier(String dvender);
}
