package com.dg.dao;

import com.dg.entity.Kucun;

public interface KucunDao {
	public Kucun getKucun(String kid);
	public String getKnum(String did);
	public boolean delKucun(String kid);
}
