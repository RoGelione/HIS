package com.dg.service;

import com.dg.entity.Page;
import com.dg.entity.Supplier;

public interface SupplierService {
	public Supplier getSupplier(String sid);
	
	public boolean findSupplier(String dvender);
	
	public void addSupplier(Supplier supplier);
	
	public boolean delSupplier(String sid);
	
	public boolean updateSupplier(Supplier supplier);
	
	public Page<Supplier> getSupplierList(String sid,int currentPage,int pageSize) throws Exception;
	
	public Page<Supplier> getSsupplierList(String Search1,String Search2,int currentPage,int pageSize) throws Exception;
}
