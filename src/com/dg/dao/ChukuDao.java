package com.dg.dao;

import com.dg.entity.Chuku;

public interface ChukuDao {
	public Chuku getChuku(String cid);
	
	public void addChuku(Chuku chuku);
	
	public boolean updateChuku(Chuku chuku);
	
	public boolean findChuku(String cid);
}
