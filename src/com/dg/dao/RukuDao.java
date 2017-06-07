package com.dg.dao;

import com.dg.entity.Ruku;

public interface RukuDao {
	public Ruku getRuku(String rid);	
	
	public boolean findRuku(String rid);
	
	public void addRuku(Ruku ruku);
	
	public boolean delRuku(String rid);
	
	public boolean updateRuku(Ruku ruku);
}
