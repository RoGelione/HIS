package com.dg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RUKU")
public class Ruku {
	@Id
	@Column(length=32)
	private String rid;/*入库编号*/
	
	@Column(length=32)
	private String did;/*药品编号*/
	
	@Column(length=32)
	private String rdname;/*药品名称*/
	
	@Column(length=32)
	private String dvender;/*生产厂家*/
	
	@Column(length=32)
	private String date_begin;/*生产日期*/
	
	@Column(length=32)
	private String date_end;/*失效日期*/
	
	@Column(length=32)
	private String rdate;/*入库时间*/
	
	@Column(length=32)
	private String rtype;/*操作类型*/
	
	@Column(length=32)
	private String rnumber;/*入库数量*/
	
	@Column(length=32)
	private String rprice;/*入库数量*/
	
	@Column(length=32)
	private String realname;/*入库人员姓名*/
	
	public Ruku(){
		
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getRdname() {
		return rdname;
	}

	public void setRdname(String rdname) {
		this.rdname = rdname;
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

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getRprice() {
		return rprice;
	}

	public void setRprice(String rprice) {
		this.rprice = rprice;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


}
