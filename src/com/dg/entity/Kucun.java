package com.dg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KUCUN")
public class Kucun {
	@Id
	@Column(length=32)
	private String kid;/*库存编号*/
	
	@Column(length=32)
	private String did;/*药品编号*/
	
	@Column(length=32)
	private String kdname;/*药品名称*/
	
	@Column(length=32)
	private String dvender;/*生产厂家*/
	
	@Column(length=32)
	private String date_begin;/*生产日期*/
	
	@Column(length=32)
	private String date_end;/*失效日期*/
	
	@Column(length=32)
	private String knumber;/*库存数量*/

	
	public Kucun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getKdname() {
		return kdname;
	}

	public void setKdname(String kdname) {
		this.kdname = kdname;
	}

	public String getDvender() {
		return dvender;
	}

	public void setDvender(String dvender) {
		this.dvender = dvender;
	}

	public String getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getKnumber() {
		return knumber;
	}

	public void setKnumber(String knumber) {
		this.knumber = knumber;
	}
	
	
}
